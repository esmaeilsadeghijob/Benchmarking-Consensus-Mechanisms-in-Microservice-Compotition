package com.javatar.reviewsservice.controller;

import com.javatar.reviewsservice.dto.LogEntry;
import com.javatar.reviewsservice.service.RaftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/raft")
public class RaftController {

    @Autowired
    private RaftService raftService;

    @PostMapping("/election")
    public void startElection() {
        raftService.startElection();
    }

    @PostMapping("/append")
    public void appendEntries(@RequestBody List<LogEntry> entries) {
        raftService.appendEntries(entries);
    }
}
