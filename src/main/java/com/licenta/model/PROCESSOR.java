package com.licenta.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "PROCESSOR")
public class PROCESSOR {
    @Id
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getCores() {
        return cores;
    }

    public void setCores(Long cores) {
        this.cores = cores;
    }

    public Long getThreads() {
        return threads;
    }

    public void setThreads(Long threads) {
        this.threads = threads;
    }

    public Long getBaseFrequency() {
        return baseFrequency;
    }

    public void setBaseFrequency(Long baseFrequency) {
        this.baseFrequency = baseFrequency;
    }

    public Long getMaxTurboFrequency() {
        return maxTurboFrequency;
    }

    public void setMaxTurboFrequency(Long maxTurboFrequency) {
        this.maxTurboFrequency = maxTurboFrequency;
    }

    public Long getL2Cache() {
        return l2Cache;
    }

    public void setL2Cache(Long l2Cache) {
        this.l2Cache = l2Cache;
    }

    public Long getL3Cache() {
        return l3Cache;
    }

    public void setL3Cache(Long l3Cache) {
        this.l3Cache = l3Cache;
    }

    public Long getTechnology() {
        return technology;
    }

    public void setTechnology(Long technology) {
        this.technology = technology;
    }

    public String getIntegrated_graphics() {
        return integrated_graphics;
    }

    public void setIntegrated_graphics(String integrated_graphics) {
        this.integrated_graphics = integrated_graphics;
    }
}
