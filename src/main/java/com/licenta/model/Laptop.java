package com.licenta.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "LAPTOP")
@Data
public class Laptop implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "GUARANTEE")
    private Long guarantee;
    @OneToOne
    @JoinColumn(name = "DISPLAY_ID", foreignKey = @ForeignKey(name = "LAPTOP_DISPLAY_ID_FK"))
    private Display display;
    @OneToOne
    @JoinColumn(name = "PROCESSOR_ID", foreignKey = @ForeignKey(name = "LAPTOP_PROCESSOR_ID_FK"))
    private Processor processor;
    @Column (name = "RAM_TOTAL")
    private Long ram_total;
    @Column (name = "RAM_TYPE")
    private String ram_type;
    @Column (name = "RAM_FREQUENCY")
    private Long ram_frequency;
    @Column (name = "RAM_SLOTS")
    private Long ram_slots;
    @Column (name = "STORAGE")
    private String storage;
    @Column (name = "STORAGE_CAPACITY")
    private Long storage_capacity;
    @Column (name = "STORAGE_INTERFACE")
    private String storage_interface;
    @Column (name = "STORAGE_FORM_FACTOR")
    private Long storage_form_factor;
    @OneToOne
    @JoinColumn(name = "GRAPHICS_CARD_ID", foreignKey = @ForeignKey(name = "LAPTOP_GRAPHICS_CARD_ID_PK"))
    private GraphicsCard graphics_card;
}
