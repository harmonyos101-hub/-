package com.example.mentalhealth.controller;

import com.example.mentalhealth.model.CounselorProfile;
import com.example.mentalhealth.model.UserProfile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

  @GetMapping("/users")
  public ResponseEntity<List<UserProfile>> listUsers() {
    return ResponseEntity.ok(List.of(
        new UserProfile("u-001", "张三", "user@example.com", "USER", "ACTIVE"),
        new UserProfile("u-002", "李四", "counselor@example.com", "COUNSELOR", "PENDING")
    ));
  }

  @GetMapping("/counselors")
  public ResponseEntity<List<CounselorProfile>> listCounselors() {
    return ResponseEntity.ok(List.of(
        new CounselorProfile("c-001", "王老师", "国二", "焦虑疏导", true),
        new CounselorProfile("c-002", "刘老师", "国二", "家庭咨询", false)
    ));
  }
}
