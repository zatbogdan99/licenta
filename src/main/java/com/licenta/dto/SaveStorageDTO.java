package com.licenta.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveStorageDTO {
    private String type;
    private String name;
    private Long warranty;
    private Long capacity;
    private String storage_interface;
    private Long form_factor;
    private Long speed;
    private String[] photos;
}
