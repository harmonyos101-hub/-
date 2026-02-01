package com.billiards.controller;

import com.billiards.domain.Bill;
import com.billiards.dto.BillRequest;
import com.billiards.service.BillingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bills")
public class BillController {
    private final BillingService billingService;

    public BillController(BillingService billingService) {
        this.billingService = billingService;
    }

    @PostMapping
    public Bill create(@Valid @RequestBody BillRequest request) {
        return billingService.createBill(request);
    }
}
