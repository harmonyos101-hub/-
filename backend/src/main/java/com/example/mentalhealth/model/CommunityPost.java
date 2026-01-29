package com.example.mentalhealth.model;

public record CommunityPost(
    String id,
    String author,
    String topic,
    String content,
    boolean anonymous
) {}
