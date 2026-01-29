package com.example.mentalhealth.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserProfile(
    @NotBlank String id,
    @NotBlank String name,
    @Email String email,
    String role,
    String status
) {}
