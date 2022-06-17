package com.licenta.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Data
public class Ram {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String name;
    private Long warranty;
    private Long total;
    private String type;
    private Long frequency;
    private String format;
    private Long forLaptop;
    @Lob
    @JsonBackReference
    private Blob photo;
    private Long stock;
}
