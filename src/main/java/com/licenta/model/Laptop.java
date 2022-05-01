package com.licenta.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;

@Entity
@Table(name = "LAPTOP")
@Data
public class Laptop implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String name;
    private Long guarantee;
    @OneToOne
    @JoinColumn(name = "DISPLAY_ID", foreignKey = @ForeignKey(name = "LAPTOP_DISPLAY_ID_FK"))
    private Display display;
    @OneToOne
    @JoinColumn(name = "PROCESSOR_ID", foreignKey = @ForeignKey(name = "LAPTOP_PROCESSOR_ID_FK"))
    private Processor processor;
    private Long ramTotal;
    private String ramType;
    private Long ramFrequency;
    private Long ramSlots;
    private String storage;
    private Long storageCapacity;
    private String storageInterface;
    private Long storageFormFactor;
    private Long price;
    @OneToOne
    @JoinColumn(name = "GRAPHICS_CARD_ID", foreignKey = @ForeignKey(name = "LAPTOP_GRAPHICS_CARD_ID_PK"))
    private GraphicsCard graphicsCard;
    @Lob
    private Blob photo;
}
