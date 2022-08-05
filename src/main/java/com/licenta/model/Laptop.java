package com.licenta.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;

@Entity
@Data
public class Laptop implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String name;
    private Long warranty;
    @OneToOne
    @JoinColumn(name = "DISPLAY_ID", foreignKey = @ForeignKey(name = "LAPTOP_DISPLAY_ID_FK"))
    private Display display;
    @OneToOne
    @JoinColumn(name = "PROCESSOR_ID", foreignKey = @ForeignKey(name = "LAPTOP_PROCESSOR_ID_FK"))
    private Processor processor;
    @OneToOne
    @JoinColumn(name = "STORAGE_ID", foreignKey = @ForeignKey(name = "LAPTOP_STORAGE_ID_FK"))
    @JsonBackReference
    private Storage storage;
    @OneToOne
    @JoinColumn(name = "GRAPHICS_CARD_ID", foreignKey = @ForeignKey(name = "LAPTOP_GRAPHICS_CARD_ID_PK"))
    private GraphicsCard graphicsCard;
    private Long price;
    @Lob
    private Blob photo;
    private Long photosId;
    @OneToOne
    @JoinColumn(name = "RAM_ID", foreignKey = @ForeignKey(name = "LAPTOP_RAM_ID_FK"))
    @JsonBackReference
    private Ram ram;
    @Column(name = "ADDITIONAL_M2_SLOTS")
    private Long additionalM2Slots;
    @Column(name = "ADDITIONAL_SATA_SLOTS")
    private Long additionalSataSlots;
    @Column(name = "ADDITIONAL_SATA_TYPE")
    private String additionalSataType;
    @Column(name = "ADDITIONAL_M2_INTERFACE")
    private String additionalM2Interface;
    @Column(name = "ADDITIONAL_RAM_TYPE")
    private String additionalRamType;
    @Column(name = "ADDITIONAL_RAM_FREQUENCY")
    private String additionalRamFrequency;
    @Column(name = "ADDITIONAL_RAM_FORMAT")
    private String additionalRamFormat;
    @Column(name = "ADDITIONAL_M2_FORM_FACTOR")
    private String additionalM2FormFactor;
    private Long stock;
}
