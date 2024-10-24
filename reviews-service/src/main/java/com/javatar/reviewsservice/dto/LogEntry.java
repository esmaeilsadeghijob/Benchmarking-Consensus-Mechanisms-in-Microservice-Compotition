package com.javatar.reviewsservice.dto;


import lombok.Data;

@Data
public class LogEntry {
    private long term;
    private String command;

    public LogEntry(long term, String command) {
        this.term = term;
        this.command = command;
    }

}