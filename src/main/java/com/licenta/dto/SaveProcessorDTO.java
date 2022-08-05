package com.licenta.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    private Long stock;
    private String[] photos;
    private Long price;
    private Long forLaptop;
}
