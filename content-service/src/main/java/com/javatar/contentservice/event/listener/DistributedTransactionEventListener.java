package com.javatar.contentservice.event.listener;

import com.javatar.contentservice.client.DistributedTransaction;
import com.javatar.contentservice.service.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DistributedTransactionEventListener {

    @Autowired
    private EventBus eventBus;

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue("txn-events-content"), exchange = @Exchange(type = ExchangeTypes.TOPIC, name = "txn-events"))
    })
    public void onMessage(DistributedTransaction transaction) {
//        debug.info("Transaction message received: {}", transaction);
        System.out.println("Transaction message received: {" + transaction);
        eventBus.sendTransaction(transaction);
    }

}
