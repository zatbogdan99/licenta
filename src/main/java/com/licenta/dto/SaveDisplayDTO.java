package com.licenta.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveDisplayDTO {
    private Long id;

    private Long screenSize;

    private String resolution;

    private Long gsync;

    private Long freesync;

    private Long refreshRate;

    private Long brightness;

    private Long stock;
}
