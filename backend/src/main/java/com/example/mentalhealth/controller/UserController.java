package com.example.mentalhealth.controller;

import com.example.mentalhealth.model.AppointmentRequest;
import com.example.mentalhealth.model.ArticleSummary;
import com.example.mentalhealth.model.AssessmentReport;
import com.example.mentalhealth.model.AssessmentRequest;
import com.example.mentalhealth.model.CommunityPost;
import com.example.mentalhealth.model.UserProfile;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @PostMapping("/register")
  public ResponseEntity<UserProfile> register(@RequestBody @Valid UserProfile profile) {
    return ResponseEntity.ok(profile);
  }

  @PostMapping("/assessments")
  public ResponseEntity<AssessmentReport> submitAssessment(
      @RequestBody @Valid AssessmentRequest request) {
    AssessmentReport report = new AssessmentReport(
        "assess-001",
        request.scale(),
        42,
        "Moderate",
        List.of("建议保持规律作息", "可尝试正念训练")
    );
    return ResponseEntity.ok(report);
  }

  @GetMapping("/assessments/{userId}/history")
  public ResponseEntity<List<AssessmentReport>> assessmentHistory(@PathVariable String userId) {
    return ResponseEntity.ok(List.of(
        new AssessmentReport("assess-001", "GAD-7", 8, "Mild", List.of("保持运动")),
        new AssessmentReport("assess-002", "PHQ-9", 12, "Moderate", List.of("联系咨询师"))
    ));
  }

  @PostMapping("/appointments")
  public ResponseEntity<Map<String, String>> createAppointment(
      @RequestBody @Valid AppointmentRequest request) {
    return ResponseEntity.ok(Map.of(
        "appointmentId", "apt-1001",
        "status", "pending"
    ));
  }

  @GetMapping("/articles")
  public ResponseEntity<List<ArticleSummary>> listArticles() {
    return ResponseEntity.ok(List.of(
        new ArticleSummary("art-1", "压力管理技巧", "压力", 1200),
        new ArticleSummary("art-2", "睡眠与情绪", "睡眠", 980)
    ));
  }

  @PostMapping("/community/posts")
  public ResponseEntity<CommunityPost> createPost(@RequestBody @Valid CommunityPost post) {
    return ResponseEntity.ok(post);
  }
}
