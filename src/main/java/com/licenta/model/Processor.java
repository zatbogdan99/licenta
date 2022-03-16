package com.licenta.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PROCESSOR")
@Data
public class Processor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "PRODUCER")
    private String producer;
    @Column(name = "NAME")
    private String name;
    @Column(name = "FAMILY")
    private String family;
    @Column(name = "MODEL")
    private String model;
    @Column(name = "CORES")
    private Long cores;
    @Column(name = "THREADS")
    private Long threads;
    @Column(name = "BASE_FREQUENCY")
    private Long baseFrequency;
    @Column(name = "MAX_TURBO_FREQUENCY")
    private Long maxTurboFrequency;
    @Column(name = "L2_CACHE")
    private Long l2Cache;
    @Column(name = "L3_CACHE")
    private Long l3Cache;
    @Column(name = "TECHNOLOGY")
    private Long technology;
    @Column(name = "INTEGRATED_GRAPHICS")
    private String integrated_graphics;
}
