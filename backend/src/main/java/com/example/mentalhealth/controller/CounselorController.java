package com.example.mentalhealth.controller;

import com.example.mentalhealth.model.AppointmentRequest;
import com.example.mentalhealth.model.CounselorProfile;
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
@RequestMapping("/api/counselor")
public class CounselorController {

  @PostMapping("/profile")
  public ResponseEntity<CounselorProfile> createProfile(
      @RequestBody @Valid CounselorProfile profile) {
    return ResponseEntity.ok(profile);
  }

  @GetMapping("/appointments/{counselorId}")
  public ResponseEntity<List<Map<String, String>>> listAppointments(
      @PathVariable String counselorId) {
    return ResponseEntity.ok(List.of(
        Map.of("appointmentId", "apt-1001", "status", "pending"),
        Map.of("appointmentId", "apt-1002", "status", "confirmed")
    ));
  }

  @PostMapping("/appointments/confirm")
  public ResponseEntity<Map<String, String>> confirmAppointment(
      @RequestBody @Valid AppointmentRequest request) {
    return ResponseEntity.ok(Map.of(
        "appointmentId", "apt-1001",
        "status", "confirmed"
    ));
  }
}
