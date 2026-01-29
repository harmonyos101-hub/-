package com.example.mentalhealth.model;

import jakarta.validation.constraints.NotBlank;

public record CounselorProfile(
    @NotBlank String id,
    @NotBlank String name,
    @NotBlank String credential,
    String specialty,
    boolean available
) {}
