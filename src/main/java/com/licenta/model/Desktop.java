package com.licenta.model;

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
    @Lob
    private Blob photo;
    private Long photos_id;
    @OneToOne
    @JoinColumn(name = "PROCESSOR_ID", foreignKey = @ForeignKey(name = "DESKTOP_PROCESSOR_ID_FK"))
    private Processor processor;
    @OneToOne
    @JoinColumn(name = "DISPLAY_ID", foreignKey = @ForeignKey(name = "DESKTOP_DISPLAY_ID_FK"))
    private Display display;
    @OneToOne
    @JoinColumn(name = "RAM_ID", foreignKey = @ForeignKey(name = "DESKTOP_RAM_ID_FK"))
    private Ram ram;
    @OneToOne
    @JoinColumn(name = "STORAGE", foreignKey = @ForeignKey(name = "DESKTOP_STORAGE_ID_FK"))
    private Storage storage;
}
