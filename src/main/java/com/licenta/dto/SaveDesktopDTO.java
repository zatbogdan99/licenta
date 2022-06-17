package com.licenta.dto;

import com.licenta.model.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveDesktopDTO {
    private Long id;
    private String name;
    private Long warranty;
    private String photo;
    private Long photos_id;
    private Long processor;
    private Long ram;
    private Long storage;
    private Long graphicsCard;
    private String type;
    private Long stock;
    private String[] photos;
}
