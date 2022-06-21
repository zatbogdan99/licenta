package com.licenta.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveRamDTO {
    private Long id;
    private String name;
    private Long warranty;
    private Long total;
    private String type;
    private Long frequency;
    private String format;
    private Long forLaptop;
    private Long stock;
    private Long price;
    private String[] photos;
}
