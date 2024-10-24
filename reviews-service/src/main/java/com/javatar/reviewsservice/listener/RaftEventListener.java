package com.javatar.reviewsservice.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RaftEventListener {

    @KafkaListener(topics = "raft-topic")
    public void handleRaftEvent(String message) {
        // Handle Raft event
    }
}
