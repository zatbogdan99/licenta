package com.licenta.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterDTO {
    private int minRange;
    private int maxRange;
    private String[] processor;
    private String[] memory;
    private String[] memoryCapacity;
    private String[] ram;
    private String productType;
    private String[] selectedRAMFrequency;
    private String[] selectedRAMCAS;
    private String[] ramCapacity;
    private String[] selectedVRAM;
    private String[] selectedSocket;
    private String[] selectedGraphicalInterface;
}
