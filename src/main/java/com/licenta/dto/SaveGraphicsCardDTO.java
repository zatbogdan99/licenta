package com.licenta.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveGraphicsCardDTO {
    private String chipset;
    private String name;
    private String model;
    private Long capacity;
    private String technology;
    private String type;
    private Long stock;
    private String[] photos;
}
