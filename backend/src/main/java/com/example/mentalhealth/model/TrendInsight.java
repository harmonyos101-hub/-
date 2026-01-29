package com.example.mentalhealth.model;

import java.util.List;

public record TrendInsight(
    String userId,
    String period,
    List<Integer> stressScores,
    String riskLevel
) {}
