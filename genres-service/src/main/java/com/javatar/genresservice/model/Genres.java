package com.javatar.genresservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "genres")
public class Genres {

    @Id
    private Integer reviewid;
    private String genre;
}
