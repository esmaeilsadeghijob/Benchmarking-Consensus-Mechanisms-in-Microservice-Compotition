package com.javatar.reviewsservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CrdEventListener {

    @KafkaListener(topics = "crd-topic")
    public void handleReviewsEvent(String message) {
        // Handle reviews event, check for failure and trigger compensation if necessary
    }
}
