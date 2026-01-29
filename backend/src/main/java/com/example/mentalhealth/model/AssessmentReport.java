package com.example.mentalhealth.model;

import java.util.List;

public record AssessmentReport(
    String assessmentId,
    String scale,
    int score,
    String level,
    List<String> suggestions
) {}
