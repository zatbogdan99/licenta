package com.licenta.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Data
public class Desktop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String name;
    private Long warranty;
    private String type;
    @Lob
    private Blob photo;
    private Long photos_id;
    @OneToOne
    @JoinColumn(name = "PROCESSOR_ID", foreignKey = @ForeignKey(name = "DESKTOP_PROCESSOR_ID_FK"))
    @JsonBackReference
    private Processor processor;
    @OneToOne
    @JoinColumn(name = "GRAPHICS_CARD_ID", foreignKey = @ForeignKey(name = "DESKTOP_GRAPHICS_CARD_ID_FK"))
    @JsonBackReference
    private GraphicsCard graphicsCard;
    @OneToOne
    @JoinColumn(name = "RAM_ID", foreignKey = @ForeignKey(name = "DESKTOP_RAM_ID_FK"))
    private Ram ram;
    @OneToOne
    @JoinColumn(name = "STORAGE", foreignKey = @ForeignKey(name = "DESKTOP_STORAGE_ID_FK"))
    private Storage storage;
    private Long price;
    private Long stock;
}
