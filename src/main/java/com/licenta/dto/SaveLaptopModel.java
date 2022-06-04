package com.licenta.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
public class SaveLaptopModel {
    private Long id;
    private String name;
    private Long warranty;
    private Long display;
    private Long processor;
    private Long ram;
    private Long storage;
    private Long graphicsCard;
    private Long price;
    private String[] photos;
}
