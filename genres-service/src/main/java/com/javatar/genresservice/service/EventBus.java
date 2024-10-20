package com.javatar.genresservice.service;


import com.javatar.genresservice.client.DistributedTransaction;
import com.javatar.genresservice.event.GenresTransactionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class EventBus {

    private List<DistributedTransaction> transactions;

    private List<GenresTransactionEvent> events;

    public EventBus() {
        this.transactions = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    public void sendTransaction(DistributedTransaction transaction) {
        transactions.add(transaction);
    }

    public DistributedTransaction receiveTransaction(String eventId) {
        DistributedTransaction transaction = transactions.stream().filter(tx -> tx.getId().equals(eventId)).findAny().orElse(null);
        if (transaction != null) {
            transactions.remove(transaction);
        }
        return transaction;
    }

    public void sendEvent(GenresTransactionEvent event) {
        events.add(event);
    }

    public GenresTransactionEvent receiveEvent(String eventId) {
        GenresTransactionEvent event = null;
        while (event == null) {
            event = events.stream().filter(evnt -> evnt.getTransactionId().equals(eventId)).findAny().orElse(null);
            events.remove(event);
            if (event != null) {
                return event;
            }
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException ex) {
                log.error("Error while received event for: {}, Cause:{}", eventId, ex);
            }
        }
        return event;
    }

}
