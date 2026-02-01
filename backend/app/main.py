from datetime import datetime, time

from fastapi import Depends, FastAPI, HTTPException
from sqlalchemy import func, select
from sqlalchemy.orm import Session

from .db import Base, engine, get_db
from .models import Bill, Coupon, Feedback, Maintenance, Reservation, Shift, Table, User
from .schemas import (
    Bill as BillSchema,
    BillCreate,
    BillUpdate,
    Coupon as CouponSchema,
    CouponCreate,
    CouponUpdate,
    Feedback as FeedbackSchema,
    FeedbackCreate,
    FeedbackUpdate,
    Maintenance as MaintenanceSchema,
    MaintenanceCreate,
    MaintenanceUpdate,
    ReportSummary,
    Reservation as ReservationSchema,
    ReservationCreate,
    ReservationUpdate,
    Shift as ShiftSchema,
    ShiftCreate,
    ShiftUpdate,
    Table as TableSchema,
    TableCreate,
    TableUpdate,
    User as UserSchema,
    UserCreate,
    UserUpdate,
)

Base.metadata.create_all(bind=engine)

app = FastAPI(title="Billiards Hall Manager", version="0.1.0")

BUSINESS_OPEN = time(9, 0)
BUSINESS_CLOSE = time(2, 0)


@app.get("/api/health")
async def health():
    return {"status": "ok"}


@app.get("/api/users", response_model=list[UserSchema])
async def list_users(db: Session = Depends(get_db)):
    return db.execute(select(User)).scalars().all()


@app.post("/api/users", response_model=UserSchema)
async def create_user(payload: UserCreate, db: Session = Depends(get_db)):
    user = User(**payload.model_dump())
    db.add(user)
    db.commit()
    db.refresh(user)
    return user


@app.put("/api/users/{user_id}", response_model=UserSchema)
async def update_user(user_id: int, payload: UserUpdate, db: Session = Depends(get_db)):
    user = db.get(User, user_id)
    if not user:
        raise HTTPException(status_code=404, detail="User not found")
    for key, value in payload.model_dump(exclude_unset=True).items():
        setattr(user, key, value)
    db.commit()
    db.refresh(user)
    return user


@app.get("/api/tables", response_model=list[TableSchema])
async def list_tables(db: Session = Depends(get_db)):
    return db.execute(select(Table)).scalars().all()


@app.post("/api/tables", response_model=TableSchema)
async def create_table(payload: TableCreate, db: Session = Depends(get_db)):
    table = Table(**payload.model_dump())
    db.add(table)
    db.commit()
    db.refresh(table)
    return table


@app.put("/api/tables/{table_id}", response_model=TableSchema)
async def update_table(table_id: int, payload: TableUpdate, db: Session = Depends(get_db)):
    table = db.get(Table, table_id)
    if not table:
        raise HTTPException(status_code=404, detail="Table not found")
    for key, value in payload.model_dump(exclude_unset=True).items():
        setattr(table, key, value)
    db.commit()
    db.refresh(table)
    return table


@app.get("/api/reservations", response_model=list[ReservationSchema])
async def list_reservations(db: Session = Depends(get_db)):
    return db.execute(select(Reservation)).scalars().all()


@app.post("/api/reservations", response_model=ReservationSchema)
async def create_reservation(payload: ReservationCreate, db: Session = Depends(get_db)):
    if payload.start_time >= payload.end_time:
        raise HTTPException(status_code=400, detail="End time must be after start time")
    table = db.get(Table, payload.table_id)
    if not table:
        raise HTTPException(status_code=404, detail="Table not found")
    user = db.get(User, payload.user_id)
    if not user:
        raise HTTPException(status_code=404, detail="User not found")
    overlap_stmt = select(Reservation).where(
        Reservation.table_id == payload.table_id,
        Reservation.status.in_(["pending", "confirmed"]),
        Reservation.start_time < payload.end_time,
        Reservation.end_time > payload.start_time,
    )
    if db.execute(overlap_stmt).scalars().first():
        raise HTTPException(status_code=409, detail="Table already reserved for this time")
    reservation = Reservation(**payload.model_dump())
    db.add(reservation)
    db.commit()
    db.refresh(reservation)
    return reservation


@app.put("/api/reservations/{reservation_id}", response_model=ReservationSchema)
async def update_reservation(reservation_id: int, payload: ReservationUpdate, db: Session = Depends(get_db)):
    reservation = db.get(Reservation, reservation_id)
    if not reservation:
        raise HTTPException(status_code=404, detail="Reservation not found")
    updates = payload.model_dump(exclude_unset=True)
    if "start_time" in updates and "end_time" in updates:
        if updates["start_time"] >= updates["end_time"]:
            raise HTTPException(status_code=400, detail="End time must be after start time")
    for key, value in updates.items():
        setattr(reservation, key, value)
    db.commit()
    db.refresh(reservation)
    return reservation


@app.get("/api/bills", response_model=list[BillSchema])
async def list_bills(db: Session = Depends(get_db)):
    return db.execute(select(Bill)).scalars().all()


@app.post("/api/bills", response_model=BillSchema)
async def create_bill(payload: BillCreate, db: Session = Depends(get_db)):
    reservation = db.get(Reservation, payload.reservation_id)
    if not reservation:
        raise HTTPException(status_code=404, detail="Reservation not found")
    bill = Bill(**payload.model_dump())
    db.add(bill)
    db.commit()
    db.refresh(bill)
    return bill


@app.put("/api/bills/{bill_id}", response_model=BillSchema)
async def update_bill(bill_id: int, payload: BillUpdate, db: Session = Depends(get_db)):
    bill = db.get(Bill, bill_id)
    if not bill:
        raise HTTPException(status_code=404, detail="Bill not found")
    for key, value in payload.model_dump(exclude_unset=True).items():
        setattr(bill, key, value)
    db.commit()
    db.refresh(bill)
    return bill


@app.get("/api/coupons", response_model=list[CouponSchema])
async def list_coupons(db: Session = Depends(get_db)):
    return db.execute(select(Coupon)).scalars().all()


@app.post("/api/coupons", response_model=CouponSchema)
async def create_coupon(payload: CouponCreate, db: Session = Depends(get_db)):
    coupon = Coupon(**payload.model_dump())
    db.add(coupon)
    db.commit()
    db.refresh(coupon)
    return coupon


@app.put("/api/coupons/{coupon_id}", response_model=CouponSchema)
async def update_coupon(coupon_id: int, payload: CouponUpdate, db: Session = Depends(get_db)):
    coupon = db.get(Coupon, coupon_id)
    if not coupon:
        raise HTTPException(status_code=404, detail="Coupon not found")
    for key, value in payload.model_dump(exclude_unset=True).items():
        setattr(coupon, key, value)
    db.commit()
    db.refresh(coupon)
    return coupon


@app.get("/api/feedbacks", response_model=list[FeedbackSchema])
async def list_feedbacks(db: Session = Depends(get_db)):
    return db.execute(select(Feedback)).scalars().all()


@app.post("/api/feedbacks", response_model=FeedbackSchema)
async def create_feedback(payload: FeedbackCreate, db: Session = Depends(get_db)):
    feedback = Feedback(**payload.model_dump())
    db.add(feedback)
    db.commit()
    db.refresh(feedback)
    return feedback


@app.put("/api/feedbacks/{feedback_id}", response_model=FeedbackSchema)
async def update_feedback(feedback_id: int, payload: FeedbackUpdate, db: Session = Depends(get_db)):
    feedback = db.get(Feedback, feedback_id)
    if not feedback:
        raise HTTPException(status_code=404, detail="Feedback not found")
    for key, value in payload.model_dump(exclude_unset=True).items():
        setattr(feedback, key, value)
    db.commit()
    db.refresh(feedback)
    return feedback


@app.get("/api/maintenances", response_model=list[MaintenanceSchema])
async def list_maintenances(db: Session = Depends(get_db)):
    return db.execute(select(Maintenance)).scalars().all()


@app.post("/api/maintenances", response_model=MaintenanceSchema)
async def create_maintenance(payload: MaintenanceCreate, db: Session = Depends(get_db)):
    maintenance = Maintenance(**payload.model_dump())
    db.add(maintenance)
    db.commit()
    db.refresh(maintenance)
    return maintenance


@app.put("/api/maintenances/{maintenance_id}", response_model=MaintenanceSchema)
async def update_maintenance(
    maintenance_id: int, payload: MaintenanceUpdate, db: Session = Depends(get_db)
):
    maintenance = db.get(Maintenance, maintenance_id)
    if not maintenance:
        raise HTTPException(status_code=404, detail="Maintenance record not found")
    for key, value in payload.model_dump(exclude_unset=True).items():
        setattr(maintenance, key, value)
    db.commit()
    db.refresh(maintenance)
    return maintenance


@app.get("/api/shifts", response_model=list[ShiftSchema])
async def list_shifts(db: Session = Depends(get_db)):
    return db.execute(select(Shift)).scalars().all()


@app.post("/api/shifts", response_model=ShiftSchema)
async def create_shift(payload: ShiftCreate, db: Session = Depends(get_db)):
    shift = Shift(**payload.model_dump())
    db.add(shift)
    db.commit()
    db.refresh(shift)
    return shift


@app.put("/api/shifts/{shift_id}", response_model=ShiftSchema)
async def update_shift(shift_id: int, payload: ShiftUpdate, db: Session = Depends(get_db)):
    shift = db.get(Shift, shift_id)
    if not shift:
        raise HTTPException(status_code=404, detail="Shift not found")
    for key, value in payload.model_dump(exclude_unset=True).items():
        setattr(shift, key, value)
    db.commit()
    db.refresh(shift)
    return shift


@app.get("/api/reports/summary", response_model=ReportSummary)
async def report_summary(db: Session = Depends(get_db)):
    total_revenue = db.execute(select(func.coalesce(func.sum(Bill.final_amount), 0.0))).scalar_one()
    total_reservations = db.execute(select(func.count(Reservation.id))).scalar_one()
    active_tables = db.execute(select(func.count(Table.id)).where(Table.status == "available")).scalar_one()
    active_members = db.execute(select(func.count(User.id)).where(User.member_level.is_not(None))).scalar_one()

    busy_hours = [
        int(row[0])
        for row in db.execute(
            select(func.strftime("%H", Reservation.start_time), func.count(Reservation.id))
            .group_by(func.strftime("%H", Reservation.start_time))
            .order_by(func.count(Reservation.id).desc())
            .limit(5)
        ).all()
    ]

    return ReportSummary(
        total_revenue=total_revenue,
        total_reservations=total_reservations,
        active_tables=active_tables,
        active_members=active_members,
        busy_hours=busy_hours,
    )


@app.get("/api/business-hours")
async def business_hours():
    return {
        "open": BUSINESS_OPEN.strftime("%H:%M"),
        "close": BUSINESS_CLOSE.strftime("%H:%M"),
    }
