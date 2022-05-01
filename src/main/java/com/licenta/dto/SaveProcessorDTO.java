package com.licenta.dto;

public class SaveProcessorDTO {
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

    public String getIntegratedGraphics() {
        return integratedGraphics;
    }

    public void setIntegratedGraphics(String integratedGraphics) {
        this.integratedGraphics = integratedGraphics;
    }
}
