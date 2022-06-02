package com.licenta.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Data
public class Photos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Lob
    private Blob photo;
    private Long productId;
    private String productType;
}
