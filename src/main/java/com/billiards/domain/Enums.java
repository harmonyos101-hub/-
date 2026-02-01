package com.billiards.domain;

public class Enums {
    public enum TableStatus {AVAILABLE, RESERVED, IN_USE, MAINTENANCE}
    public enum ReservationStatus {PENDING, CONFIRMED, CANCELLED, COMPLETED}
    public enum BillStatus {UNPAID, PAID, VOID}
    public enum FeedbackStatus {OPEN, IN_PROGRESS, RESOLVED}
    public enum ShiftStatus {SCHEDULED, COMPLETED, CANCELLED}
}
