package com.billiards.controller;

import com.billiards.domain.Coupon;
import com.billiards.service.CouponService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {
    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping
    public Coupon create(@RequestBody Coupon coupon) {
        return couponService.create(coupon);
    }

    @GetMapping
    public List<Coupon> list() {
        return couponService.findAll();
    }
}
