package com.example.mentalhealth.model;

import java.util.List;

public record AiChatResponse(
    String reply,
    List<String> tips,
    boolean crisisFlag
) {}
