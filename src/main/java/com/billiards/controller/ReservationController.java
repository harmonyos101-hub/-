package com.billiards.controller;

import com.billiards.domain.Enums;
import com.billiards.domain.Reservation;
import com.billiards.dto.ReservationRequest;
import com.billiards.service.ReservationService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation create(@Valid @RequestBody ReservationRequest request) {
        return reservationService.create(request);
    }

    @GetMapping
    public List<Reservation> list() {
        return reservationService.findAll();
    }

    @PatchMapping("/{id}/status/{status}")
    public Reservation updateStatus(@PathVariable Long id, @PathVariable Enums.ReservationStatus status) {
        return reservationService.updateStatus(id, status);
    }
}
