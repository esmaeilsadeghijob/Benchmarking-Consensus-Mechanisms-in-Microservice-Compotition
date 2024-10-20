package com.javatar.genresservice.event.listener;


import com.javatar.genresservice.client.DistributedTransaction;
import com.javatar.genresservice.service.EventBus;
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
            @QueueBinding(value = @Queue("txn-events-genres"), exchange = @Exchange(type = ExchangeTypes.TOPIC, name = "txn-events"))
    })
    public void onMessage(DistributedTransaction transaction) {
//        debug.info("Transaction message received: {}", transaction);
        System.out.println("Transaction message received: {" + transaction);
        eventBus.sendTransaction(transaction);
    }

}