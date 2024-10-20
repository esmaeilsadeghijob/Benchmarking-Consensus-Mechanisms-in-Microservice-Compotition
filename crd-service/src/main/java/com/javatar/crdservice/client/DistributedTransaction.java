package com.javatar.crdservice.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static com.javatar.crdservice.client.DistributedTransactionStatus.NEW;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistributedTransaction implements Serializable {

    private static final long serialVersionUID = -8594438501671636539L;
    private String id;
    @Builder.Default
    private DistributedTransactionStatus status = NEW;
    @Builder.Default
    private transient List<DistributedTransactionParticipant> participants = Collections.emptyList();

    public DistributedTransaction(String id, DistributedTransactionStatus status) {
        super();
        this.id = id;
        this.status = status;
    }

}
