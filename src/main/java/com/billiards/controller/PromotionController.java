package com.billiards.controller;

import com.billiards.domain.Promotion;
import com.billiards.service.PromotionService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/promotions")
public class PromotionController {
    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @PostMapping
    public Promotion create(@RequestBody Promotion promotion) {
        return promotionService.create(promotion);
    }

    @GetMapping
    public List<Promotion> list() {
        return promotionService.findAll();
    }
}
