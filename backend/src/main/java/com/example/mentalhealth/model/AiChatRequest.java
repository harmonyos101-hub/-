package com.example.mentalhealth.model;

import jakarta.validation.constraints.NotBlank;

public record AiChatRequest(
    @NotBlank String userId,
    @NotBlank String message,
    String mood
) {}
