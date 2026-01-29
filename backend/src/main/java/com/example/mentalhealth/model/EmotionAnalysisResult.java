package com.example.mentalhealth.model;

import java.util.Map;

public record EmotionAnalysisResult(
    String userId,
    Map<String, Double> emotionScores,
    String summary
) {}
