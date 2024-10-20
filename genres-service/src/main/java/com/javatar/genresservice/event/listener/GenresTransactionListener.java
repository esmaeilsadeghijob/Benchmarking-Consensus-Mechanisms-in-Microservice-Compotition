package com.javatar.genresservice.event.listener;


import com.javatar.genresservice.client.DistributedTransaction;
import com.javatar.genresservice.devil.GenresProcessingException;
import com.javatar.genresservice.event.GenresTransactionEvent;
import com.javatar.genresservice.eventlistener.TransactionListener;
import com.javatar.genresservice.service.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

import static com.javatar.genresservice.client.DistributedTransactionStatus.CONFIRMED;
import static com.javatar.genresservice.client.DistributedTransactionStatus.TO_ROLLBACK;

@Component
@Slf4j
public class GenresTransactionListener implements TransactionListener<GenresTransactionEvent> {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EventBus eventBus;

    @Override
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleEvent(GenresTransactionEvent event) throws GenresProcessingException {
        log.debug("Handling event before commit: {}", event);
        eventBus.sendEvent(event);

        DistributedTransaction transaction = null;
        int count = 3000;
        log.info("Waiting for receiving transaction.");
        while (count > 0) {
            transaction = eventBus.receiveTransaction(event.getTransactionId());
            if (transaction == null) {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException ex) {
                    log.error("Error while receiving transaction for: {}. Cause: {}", event.getTransactionId(), ex);
                }
                --count;
            } else {
                break;
            }
        }
        if (transaction == null || transaction.getStatus() != CONFIRMED) {
            log.info("Transaction received after waiting: {}", transaction);
            throw new GenresProcessingException("Distributed transaction wasn't confirmed for txnId: " + event.getTransactionId());
        }
    }

    @Override
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void handleAfterRollback(GenresTransactionEvent event) {
        log.debug("Updating transaction[{}] status to : {} for genres-service", event.getTransactionId(), TO_ROLLBACK);
        restTemplate.put(
                "http://transaction-server/transactions/{transactionId}/participants/{serviceId}/status/{status}",
                null,
                event.getTransactionId(),
                "genres-service",
                TO_ROLLBACK);
    }

    @Override
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleAfterCompletion(GenresTransactionEvent event) {
        log.debug("Updating transaction[{}] status to: {} for genres-service.", event.getTransactionId(), CONFIRMED);
        restTemplate.put(
                "http://transaction-server/transactions/{transactionId}/participants/{serviceId}/status/{status}",
                null,
                event.getTransactionId(),
                "genres-service",
                CONFIRMED);
    }

}
