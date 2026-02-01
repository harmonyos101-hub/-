package com.billiards.controller;

import com.billiards.domain.Shift;
import com.billiards.service.ShiftService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shifts")
public class ShiftController {
    private final ShiftService shiftService;

    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @PostMapping
    public Shift create(@RequestBody Shift shift) {
        return shiftService.create(shift);
    }

    @GetMapping
    public List<Shift> list() {
        return shiftService.findAll();
    }
}
