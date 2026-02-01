from datetime import datetime

from sqlalchemy import Boolean, DateTime, Enum, Float, ForeignKey, Integer, String, Text
from sqlalchemy.orm import Mapped, mapped_column, relationship

from .db import Base


class User(Base):
    __tablename__ = "users"

    id: Mapped[int] = mapped_column(Integer, primary_key=True, index=True)
    name: Mapped[str] = mapped_column(String(100), nullable=False)
    phone: Mapped[str] = mapped_column(String(30), unique=True, nullable=False)
    email: Mapped[str | None] = mapped_column(String(120))
    role: Mapped[str] = mapped_column(Enum("customer", "staff", "admin", name="role"), default="customer")
    member_level: Mapped[str | None] = mapped_column(String(50))
    points: Mapped[int] = mapped_column(Integer, default=0)
    created_at: Mapped[datetime] = mapped_column(DateTime, default=datetime.utcnow)

    reservations = relationship("Reservation", back_populates="user")
    feedbacks = relationship("Feedback", back_populates="user")


class Table(Base):
    __tablename__ = "tables"

    id: Mapped[int] = mapped_column(Integer, primary_key=True, index=True)
    label: Mapped[str] = mapped_column(String(50), unique=True, nullable=False)
    table_type: Mapped[str] = mapped_column(String(50), default="standard")
    size: Mapped[str] = mapped_column(String(50), default="9ft")
    status: Mapped[str] = mapped_column(
        Enum("available", "reserved", "maintenance", name="table_status"),
        default="available",
    )
    hourly_rate: Mapped[float] = mapped_column(Float, default=60.0)

    reservations = relationship("Reservation", back_populates="table")
    maintenances = relationship("Maintenance", back_populates="table")


class Reservation(Base):
    __tablename__ = "reservations"

    id: Mapped[int] = mapped_column(Integer, primary_key=True, index=True)
    user_id: Mapped[int] = mapped_column(ForeignKey("users.id"))
    table_id: Mapped[int] = mapped_column(ForeignKey("tables.id"))
    start_time: Mapped[datetime] = mapped_column(DateTime, nullable=False)
    end_time: Mapped[datetime] = mapped_column(DateTime, nullable=False)
    status: Mapped[str] = mapped_column(
        Enum("pending", "confirmed", "cancelled", "completed", name="reservation_status"),
        default="pending",
    )
    paid_amount: Mapped[float] = mapped_column(Float, default=0.0)
    created_at: Mapped[datetime] = mapped_column(DateTime, default=datetime.utcnow)

    user = relationship("User", back_populates="reservations")
    table = relationship("Table", back_populates="reservations")
    bill = relationship("Bill", back_populates="reservation", uselist=False)


class Bill(Base):
    __tablename__ = "bills"

    id: Mapped[int] = mapped_column(Integer, primary_key=True, index=True)
    reservation_id: Mapped[int] = mapped_column(ForeignKey("reservations.id"), unique=True)
    total_amount: Mapped[float] = mapped_column(Float, default=0.0)
    discount_amount: Mapped[float] = mapped_column(Float, default=0.0)
    final_amount: Mapped[float] = mapped_column(Float, default=0.0)
    status: Mapped[str] = mapped_column(Enum("unpaid", "paid", name="bill_status"), default="unpaid")
    created_at: Mapped[datetime] = mapped_column(DateTime, default=datetime.utcnow)

    reservation = relationship("Reservation", back_populates="bill")


class Coupon(Base):
    __tablename__ = "coupons"

    id: Mapped[int] = mapped_column(Integer, primary_key=True, index=True)
    code: Mapped[str] = mapped_column(String(40), unique=True, nullable=False)
    description: Mapped[str | None] = mapped_column(String(200))
    discount_type: Mapped[str] = mapped_column(Enum("percent", "fixed", name="coupon_type"), default="percent")
    value: Mapped[float] = mapped_column(Float, default=10.0)
    active: Mapped[bool] = mapped_column(Boolean, default=True)
    expires_at: Mapped[datetime | None] = mapped_column(DateTime)


class Feedback(Base):
    __tablename__ = "feedbacks"

    id: Mapped[int] = mapped_column(Integer, primary_key=True, index=True)
    user_id: Mapped[int] = mapped_column(ForeignKey("users.id"))
    content: Mapped[str] = mapped_column(Text, nullable=False)
    status: Mapped[str] = mapped_column(Enum("open", "resolved", name="feedback_status"), default="open")
    response: Mapped[str | None] = mapped_column(Text)
    created_at: Mapped[datetime] = mapped_column(DateTime, default=datetime.utcnow)

    user = relationship("User", back_populates="feedbacks")


class Maintenance(Base):
    __tablename__ = "maintenances"

    id: Mapped[int] = mapped_column(Integer, primary_key=True, index=True)
    table_id: Mapped[int] = mapped_column(ForeignKey("tables.id"))
    description: Mapped[str] = mapped_column(Text, nullable=False)
    status: Mapped[str] = mapped_column(Enum("open", "done", name="maintenance_status"), default="open")
    started_at: Mapped[datetime] = mapped_column(DateTime, default=datetime.utcnow)
    ended_at: Mapped[datetime | None] = mapped_column(DateTime)

    table = relationship("Table", back_populates="maintenances")


class Shift(Base):
    __tablename__ = "shifts"

    id: Mapped[int] = mapped_column(Integer, primary_key=True, index=True)
    staff_id: Mapped[int] = mapped_column(ForeignKey("users.id"))
    start_time: Mapped[datetime] = mapped_column(DateTime, nullable=False)
    end_time: Mapped[datetime] = mapped_column(DateTime, nullable=False)
    role: Mapped[str] = mapped_column(String(50), default="frontdesk")
