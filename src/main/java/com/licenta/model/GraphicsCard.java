package com.licenta.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;

@Entity
@Table(name = "GRAPHICS_CARD")
@Data
public class GraphicsCard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String chipset;

    private String type;

    private String model;

    private Long capacity;

    private String technology;

    private String name;

    private Long forLaptop;

    @Lob
    @JsonBackReference
    private Blob photo;

    private Long price;

    private Long stock;

    @Column(name = "INTERFACE")
    private String graphicsCardInterface;

    private Long warranty;
}
