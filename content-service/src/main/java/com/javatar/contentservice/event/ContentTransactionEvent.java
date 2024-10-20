package com.javatar.contentservice.event;

import com.javatar.contentservice.model.Content;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContentTransactionEvent {

    private String transactionId;
    private Content content;
}
