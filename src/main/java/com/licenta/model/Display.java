package com.licenta.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DISPLAY")
@Data
public class Display implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long screenSize;

    private String resolution;

    private String technology;

    private Long gsync;

    private Long freesync;

    private Long refreshRate;

    private Long brightness;
}
