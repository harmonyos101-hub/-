package com.billiards.service;

import com.billiards.domain.Customer;
import com.billiards.domain.Feedback;
import com.billiards.dto.FeedbackRequest;
import com.billiards.repository.CustomerRepository;
import com.billiards.repository.FeedbackRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final CustomerRepository customerRepository;

    public FeedbackService(FeedbackRepository feedbackRepository, CustomerRepository customerRepository) {
        this.feedbackRepository = feedbackRepository;
        this.customerRepository = customerRepository;
    }

    public Feedback create(FeedbackRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow();
        Feedback feedback = new Feedback();
        feedback.setCustomer(customer);
        feedback.setContent(request.getContent());
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> findAll() {
        return feedbackRepository.findAll();
    }
}
