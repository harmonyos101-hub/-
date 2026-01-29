package com.example.mentalhealth.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentRequest(
    @NotBlank String userId,
    @NotBlank String counselorId,
    @NotNull LocalDateTime startTime,
    String channel
) {}
