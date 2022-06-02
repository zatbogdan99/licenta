package com.licenta.model;

import lombok.Data;

import javax.persistence.*;

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
    private Long type;
    private Long frequency;
    private String format;
    private Long forLaptop;
}
