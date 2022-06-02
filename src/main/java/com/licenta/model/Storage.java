package com.licenta.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String type;
    private String name;
    private Long warranty;
    private Long capacity;
    private String storage_interface;
    private Long form_factor;
    private Long speed;
}
