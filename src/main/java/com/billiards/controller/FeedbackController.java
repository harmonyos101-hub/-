package com.billiards.controller;

import com.billiards.domain.Feedback;
import com.billiards.dto.FeedbackRequest;
import com.billiards.service.FeedbackService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public Feedback create(@Valid @RequestBody FeedbackRequest request) {
        return feedbackService.create(request);
    }

    @GetMapping
    public List<Feedback> list() {
        return feedbackService.findAll();
    }
}
