package com.example.mentalhealth.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record AssessmentRequest(
    @NotBlank String userId,
    @NotBlank String scale,
    @NotNull Map<String, Integer> answers
) {}
