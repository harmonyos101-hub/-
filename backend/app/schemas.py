from datetime import datetime

from pydantic import BaseModel, ConfigDict, Field


class UserBase(BaseModel):
    name: str
    phone: str
    email: str | None = None
    role: str = "customer"
    member_level: str | None = None
    points: int = 0


class UserCreate(UserBase):
    pass


class UserUpdate(BaseModel):
    name: str | None = None
    phone: str | None = None
    email: str | None = None
    role: str | None = None
    member_level: str | None = None
    points: int | None = None


class User(UserBase):
    id: int
    created_at: datetime

    model_config = ConfigDict(from_attributes=True)


class TableBase(BaseModel):
    label: str
    table_type: str = "standard"
    size: str = "9ft"
    status: str = "available"
    hourly_rate: float = 60.0


class TableCreate(TableBase):
    pass


class TableUpdate(BaseModel):
    label: str | None = None
    table_type: str | None = None
    size: str | None = None
    status: str | None = None
    hourly_rate: float | None = None


class Table(TableBase):
    id: int

    model_config = ConfigDict(from_attributes=True)


class ReservationBase(BaseModel):
    user_id: int
    table_id: int
    start_time: datetime
    end_time: datetime
    status: str = "pending"
    paid_amount: float = 0.0


class ReservationCreate(ReservationBase):
    pass


class ReservationUpdate(BaseModel):
    start_time: datetime | None = None
    end_time: datetime | None = None
    status: str | None = None
    paid_amount: float | None = None


class Reservation(ReservationBase):
    id: int
    created_at: datetime

    model_config = ConfigDict(from_attributes=True)


class BillBase(BaseModel):
    reservation_id: int
    total_amount: float = 0.0
    discount_amount: float = 0.0
    final_amount: float = 0.0
    status: str = "unpaid"


class BillCreate(BillBase):
    pass


class BillUpdate(BaseModel):
    total_amount: float | None = None
    discount_amount: float | None = None
    final_amount: float | None = None
    status: str | None = None


class Bill(BillBase):
    id: int
    created_at: datetime

    model_config = ConfigDict(from_attributes=True)


class CouponBase(BaseModel):
    code: str
    description: str | None = None
    discount_type: str = "percent"
    value: float = 10.0
    active: bool = True
    expires_at: datetime | None = None


class CouponCreate(CouponBase):
    pass


class CouponUpdate(BaseModel):
    description: str | None = None
    discount_type: str | None = None
    value: float | None = None
    active: bool | None = None
    expires_at: datetime | None = None


class Coupon(CouponBase):
    id: int

    model_config = ConfigDict(from_attributes=True)


class FeedbackBase(BaseModel):
    user_id: int
    content: str
    status: str = "open"
    response: str | None = None


class FeedbackCreate(FeedbackBase):
    pass


class FeedbackUpdate(BaseModel):
    status: str | None = None
    response: str | None = None


class Feedback(FeedbackBase):
    id: int
    created_at: datetime

    model_config = ConfigDict(from_attributes=True)


class MaintenanceBase(BaseModel):
    table_id: int
    description: str
    status: str = "open"
    started_at: datetime | None = None
    ended_at: datetime | None = None


class MaintenanceCreate(MaintenanceBase):
    pass


class MaintenanceUpdate(BaseModel):
    status: str | None = None
    ended_at: datetime | None = None


class Maintenance(MaintenanceBase):
    id: int

    model_config = ConfigDict(from_attributes=True)


class ShiftBase(BaseModel):
    staff_id: int
    start_time: datetime
    end_time: datetime
    role: str = "frontdesk"


class ShiftCreate(ShiftBase):
    pass


class ShiftUpdate(BaseModel):
    start_time: datetime | None = None
    end_time: datetime | None = None
    role: str | None = None


class Shift(ShiftBase):
    id: int

    model_config = ConfigDict(from_attributes=True)


class ReportSummary(BaseModel):
    total_revenue: float
    total_reservations: int
    active_tables: int
    active_members: int
    busy_hours: list[int] = Field(default_factory=list)
