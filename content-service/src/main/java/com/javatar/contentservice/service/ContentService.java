package com.javatar.contentservice.service;

import com.javatar.contentservice.devil.ContentNotFoundException;
import com.javatar.contentservice.event.ContentTransactionEvent;
import com.javatar.contentservice.model.Content;
import com.javatar.contentservice.repository.ContentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Transactional
    public Content createContent(Content content) {
        return contentRepository.save(content);
    }

    public Content findByReviewid(Integer reviewid) {
        return contentRepository.findByReviewid(reviewid).orElseThrow(() ->
                new ContentNotFoundException("Content not exists for review ID: " + reviewid));
    }

    @Async
    @Transactional
    public void deposit(Integer reviewid, String content, String transactionId) {
        transfer(reviewid, content, transactionId);
    }

    @Async
    @Transactional
    public void withdrawl(Integer reviewid, String content, String transactionId) {
        transfer(reviewid, content, transactionId);
    }

    protected void transfer(Integer reviewid, String content, String transactionId) {
        log.info("Transferring money to account={}, amount={}, txnId: {}", reviewid, content, transactionId);
        Optional<Content> accountOpt = contentRepository.findById(reviewid);
        accountOpt.ifPresent(contents -> {
            contents.setContent(content);
            eventPublisher.publishEvent(new ContentTransactionEvent(transactionId, contents));
            contentRepository.save(contents);
        });
    }
}

