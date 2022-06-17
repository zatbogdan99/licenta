package com.licenta.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.List;

@Entity
@Table(name = "PROCESSOR")
@Data
public class Processor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String producer;
    private String name;
    private String family;
    private String model;
    private Long cores;
    private Long threads;
    private Long baseFrequency;
    private Long maxTurboFrequency;
    private Long l2Cache;
    private Long l3Cache;
    private Long technology;
    private String integratedGraphics;
    private Long forLaptop;
    @Lob
    @JsonBackReference
    private Blob photo;
    private Long stock;
}
