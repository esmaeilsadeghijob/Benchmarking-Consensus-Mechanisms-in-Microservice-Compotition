package com.javatar.reviewsservice.controller;

import com.javatar.reviewsservice.dto.LogEntry;
import com.javatar.reviewsservice.service.TendermintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tendermint")
public class TendermintController {

    @Autowired
    private TendermintService tendermintService;

    @PostMapping("/election")
    public void startElection() {
        tendermintService.startElection();
    }

    @PostMapping("/append")
    public void appendEntries(@RequestBody List<LogEntry> entries) {
        tendermintService.appendEntries(entries);
    }
}
