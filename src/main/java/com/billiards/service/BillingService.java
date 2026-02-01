package com.billiards.service;

import com.billiards.config.PricingProperties;
import com.billiards.domain.Bill;
import com.billiards.domain.Customer;
import com.billiards.domain.Enums;
import com.billiards.domain.Reservation;
import com.billiards.dto.BillRequest;
import com.billiards.repository.BillRepository;
import com.billiards.repository.ReservationRepository;
import java.math.BigDecimal;
import java.time.Duration;
import org.springframework.stereotype.Service;

@Service
public class BillingService {
    private final BillRepository billRepository;
    private final ReservationRepository reservationRepository;
    private final PricingProperties pricingProperties;

    public BillingService(BillRepository billRepository,
                          ReservationRepository reservationRepository,
                          PricingProperties pricingProperties) {
        this.billRepository = billRepository;
        this.reservationRepository = reservationRepository;
        this.pricingProperties = pricingProperties;
    }

    public Bill createBill(BillRequest request) {
        Reservation reservation = reservationRepository.findById(request.getReservationId()).orElseThrow();
        Duration duration = Duration.between(reservation.getStartTime(), reservation.getEndTime());
        long hours = Math.max(1, duration.toHours());
        BigDecimal amount = BigDecimal.valueOf(pricingProperties.getHourlyRate()).multiply(BigDecimal.valueOf(hours));

        Customer customer = reservation.getCustomer();
        if (customer.getMemberProfile() != null && customer.getMemberProfile().getDiscountRate() != null) {
            amount = amount.multiply(BigDecimal.valueOf(customer.getMemberProfile().getDiscountRate()));
        } else if (pricingProperties.getMemberDiscount() != null) {
            amount = amount.multiply(BigDecimal.valueOf(pricingProperties.getMemberDiscount()));
        }

        Bill bill = new Bill();
        bill.setReservation(reservation);
        bill.setAmount(amount);
        bill.setPaymentMethod(request.getPaymentMethod());
        bill.setStatus(Enums.BillStatus.PAID);

        reservation.setStatus(Enums.ReservationStatus.COMPLETED);
        reservationRepository.save(reservation);

        return billRepository.save(bill);
    }
}
