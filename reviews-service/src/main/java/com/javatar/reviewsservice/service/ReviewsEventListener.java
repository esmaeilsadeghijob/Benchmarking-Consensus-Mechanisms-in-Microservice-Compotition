package com.javatar.reviewsservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ReviewsEventListener {

    @KafkaListener(topics = "crd-topic")
    public void handlePaymentEvent(String message) {
        // Handle payment event, check for failure and trigger compensation if necessary
    }
}
