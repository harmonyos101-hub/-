package com.example.mentalhealth.controller;

import com.example.mentalhealth.model.AiChatRequest;
import com.example.mentalhealth.model.AiChatResponse;
import com.example.mentalhealth.model.EmotionAnalysisResult;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AiController {

  @PostMapping("/chat")
  public ResponseEntity<AiChatResponse> chat(@RequestBody @Valid AiChatRequest request) {
    AiChatResponse response = new AiChatResponse(
        "我理解你的感受，先做一个深呼吸，我们一起梳理一下。",
        java.util.List.of("保持规律作息", "尝试记录情绪日记"),
        false
    );
    return ResponseEntity.ok(response);
  }

  @PostMapping("/emotion")
  public ResponseEntity<EmotionAnalysisResult> analyzeEmotion(
      @RequestBody @Valid AiChatRequest request) {
    EmotionAnalysisResult result = new EmotionAnalysisResult(
        request.userId(),
        Map.of("calm", 0.72, "anxious", 0.18, "sad", 0.10),
        "情绪整体平稳，存在轻度焦虑倾向。"
    );
    return ResponseEntity.ok(result);
  }
}
