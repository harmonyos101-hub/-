package com.example.mentalhealth.controller;

import com.example.mentalhealth.model.TrendInsight;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

  @GetMapping("/trends/{userId}")
  public ResponseEntity<TrendInsight> userTrends(@PathVariable String userId) {
    return ResponseEntity.ok(new TrendInsight(
        userId,
        "last_30_days",
        List.of(30, 28, 35, 40, 32, 29, 31),
        "low"
    ));
  }
}
