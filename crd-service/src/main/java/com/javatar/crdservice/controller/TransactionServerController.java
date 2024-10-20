package com.javatar.crdservice.controller;

import com.javatar.crdservice.client.DistributedTransaction;
import com.javatar.crdservice.client.DistributedTransactionParticipant;
import com.javatar.crdservice.client.DistributedTransactionStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.javatar.crdservice.repository.DistributedTransactionRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transactions")
@Slf4j
public class TransactionServerController {

    @Autowired
    private DistributedTransactionRepo repository;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    @Operation(summary = "Add a new transaction")
    public DistributedTransaction add(@RequestBody DistributedTransaction transaction) {
        return repository.save(transaction);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get transaction by id")
    public Optional<DistributedTransaction> findById(@PathVariable("id") String id) {
        return repository.findById(id);
    }

    @GetMapping
    @Operation(summary = "Get all transactions")
    public List<DistributedTransaction> findAll() {
        return repository.findAll();
    }

    @PutMapping("/{id}/finish/{status}")
    @Operation(summary = "Finish a transaction with status")
    public void finish(@PathVariable("id") String id,
                       @PathVariable("status") DistributedTransactionStatus status) {
        log.info("Finishing transaction as id: {}, status: {}", id, status);
        repository.findById(id).ifPresent(txn -> {
            txn.setStatus(status);
            repository.update(txn);
            log.info("Publishing transaction[{}] finish event with status: {}", id, status);
            this.publishEvent(new DistributedTransaction(id, status));
        });
    }

    @PutMapping("/{id}/participants")
    @Operation(summary = "Add participant in a transaction")
    public void addParticipant(@PathVariable("id") String id,
                               @RequestBody DistributedTransactionParticipant participant) {
        log.info("Adding participant in transaction: {}, participant: {}", id, participant);
        repository.findById(id).ifPresent(txn -> {
            txn.getParticipants().add(participant);
            repository.update(txn);
            log.info("All participant: {}", txn.getParticipants());
        });
    }

    @PutMapping("/{id}/participants/{serviceId}/status/{status}")
    @Operation(summary = "Update participant status in a transaction")
    public void updateParticipant(@PathVariable("id") String id,
                                  @PathVariable("serviceId") String serviceId,
                                  @PathVariable("status") DistributedTransactionStatus status) {
        log.info("Updating participant id: {}, serviceId: {}, status: {}", id, serviceId, status);
        repository.findById(id).ifPresent(txn -> {
            List<DistributedTransactionParticipant> participants = txn.getParticipants().stream().map(p -> {
                if (p.getServiceId().equals(serviceId)) {
                    p.setStatus(status);
                }
                return p;
            }).collect(Collectors.toList());
            txn.setParticipants(participants);
            repository.update(txn);
            this.publishEvent(new DistributedTransaction(id, status));
            log.info("Publishing transaction [{}] event with status: {}", id, status);
        });
    }

    protected void publishEvent(DistributedTransaction transaction) {
        rabbitTemplate.convertAndSend("txn-events", "txn-events", transaction);
    }

}
