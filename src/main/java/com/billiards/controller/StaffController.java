package com.billiards.controller;

import com.billiards.domain.Staff;
import com.billiards.dto.StaffRequest;
import com.billiards.service.StaffService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping
    public Staff create(@Valid @RequestBody StaffRequest request) {
        return staffService.create(request);
    }

    @GetMapping
    public List<Staff> list() {
        return staffService.findAll();
    }
}
