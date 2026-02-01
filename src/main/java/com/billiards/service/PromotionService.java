package com.billiards.service;

import com.billiards.domain.Promotion;
import com.billiards.repository.PromotionRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {
    private final PromotionRepository promotionRepository;

    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public Promotion create(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    public List<Promotion> findAll() {
        return promotionRepository.findAll();
    }
}
