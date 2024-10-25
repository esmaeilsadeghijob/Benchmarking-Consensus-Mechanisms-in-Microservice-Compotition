package com.javatar.reviewsservice.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TendermintEventListener {

    @KafkaListener(topics = "tendermint-topic")
    public void handleTendermintEvent(String message) {
        // Handle Tendermint event
    }
}

