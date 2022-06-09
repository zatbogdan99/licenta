package com.licenta.model;

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
    private Blob photo;
}
