package com.billiards.service;

import com.billiards.domain.BilliardTable;
import com.billiards.domain.Customer;
import com.billiards.domain.Enums;
import com.billiards.domain.Reservation;
import com.billiards.dto.ReservationRequest;
import com.billiards.repository.BilliardTableRepository;
import com.billiards.repository.CustomerRepository;
import com.billiards.repository.ReservationRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final BilliardTableRepository tableRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              CustomerRepository customerRepository,
                              BilliardTableRepository tableRepository) {
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
        this.tableRepository = tableRepository;
    }

    public Reservation create(ReservationRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow();
        BilliardTable table = tableRepository.findById(request.getTableId()).orElseThrow();
        table.setStatus(Enums.TableStatus.RESERVED);
        tableRepository.save(table);

        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setBilliardTable(table);
        reservation.setStartTime(request.getStartTime());
        reservation.setEndTime(request.getEndTime());
        reservation.setStatus(Enums.ReservationStatus.CONFIRMED);
        return reservationRepository.save(reservation);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Reservation updateStatus(Long id, Enums.ReservationStatus status) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        reservation.setStatus(status);
        return reservationRepository.save(reservation);
    }
}
