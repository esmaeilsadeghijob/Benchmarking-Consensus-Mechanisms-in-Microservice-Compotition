package com.javatar.genresservice.event;

import com.javatar.genresservice.model.Genres;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenresTransactionEvent {

    private String transactionId;
    private Genres genres;
}
