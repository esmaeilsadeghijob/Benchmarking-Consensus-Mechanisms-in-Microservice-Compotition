package com.javatar.reviewsservice.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Data
public class Node {
    private final String id;
    private AtomicLong term = new AtomicLong(0);
    private NodeState state = NodeState.FOLLOWER;
    private String votedFor;
    private List<LogEntry> log = new ArrayList<>();
    private long commitIndex;
    private long lastApplied;

    public Node(String id) {
        this.id = id;
    }
}

