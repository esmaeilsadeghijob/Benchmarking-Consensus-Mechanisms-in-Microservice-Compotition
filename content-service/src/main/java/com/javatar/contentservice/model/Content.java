package com.javatar.contentservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "content")
public class Content {

    @Id
    private Integer reviewid;
    private String content;
}
