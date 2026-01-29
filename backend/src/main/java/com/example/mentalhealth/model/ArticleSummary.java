package com.example.mentalhealth.model;

public record ArticleSummary(
    String id,
    String title,
    String category,
    int viewCount
) {}
