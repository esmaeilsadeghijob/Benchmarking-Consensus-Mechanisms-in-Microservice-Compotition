package com.javatar.contentservice.controller;

import com.javatar.contentservice.model.Content;
import com.javatar.contentservice.service.ContentService;
import com.javatar.contentservice.service.EventBus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("content")
@Tag(name = "Content")
public class ContentController {

    @Autowired
    private ContentService contentService;
    @Autowired
    private EventBus eventBus;

    @PostMapping
    @Operation(summary = "Create a new content")
    public Content createAccount(@RequestBody Content content) {
        return contentService.createContent(content);
    }

    @GetMapping("/review/{reviewid}")
    @Operation(summary = "Get content by review ID")
    public Content findByReviewid(@PathVariable("reviewid") Integer reviewid) {
        return contentService.findByReviewid(reviewid);
    }

    @PutMapping("/{id}/deposit/{content}")
    @Operation(summary = "Deposit money in review content")
    public Content deposit(@PathVariable("id") Integer reviewid,
                           @PathVariable("content") String content,
                           @RequestHeader("X-Txn-ID") String transactionId) {
        contentService.deposit(reviewid, content, transactionId);
        return eventBus.receiveEvent(transactionId).getContent();
    }

    @PutMapping("/{id}/withdrawl/{content}")
    @Operation(summary = "Withdraw money from review content")
    public Content withdrawl(@PathVariable("id") Integer reviewid,
                             @PathVariable("content") String content,
                             @RequestHeader("X-Txn-ID") String transactionId) {
        contentService.withdrawl(reviewid, content, transactionId);
        return eventBus.receiveEvent(transactionId).getContent();
    }

}
