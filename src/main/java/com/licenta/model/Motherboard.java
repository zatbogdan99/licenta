package com.licenta.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Data
public class Motherboard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String name;
    private String format;
    private String cpuSocket;
    private String chipsetProducer;
    private String chipsetModel;
    private String supportedCpus;
    private String graphicalInterface;
    private String audioIntegrated;
    private String audioChipset;
    private String integratedNetworkCard;
    private String networkChipset;
    private Long sata3Slots;
    @Column(name = "m.2_ports")
    private Long m2Ports;
    private String ramType;
    private Long maxRam;
    private Long ramSlotsNumber;
    private String supportedFrequencies;
    @Column(name = "pci_express_4.0x16")
    private Long pciExpress40x16;
    @Column(name = "pci_express_3.0x16")
    private Long pciExpress30x16;
    private Long pci_express_x1;
    private Long hdmi;
    private Long displayPort;
    @Column(name = "usb_2.0")
    private Long usb20;
    @Column(name = "usb_3.2_gen1_typeA")
    private Long usb32gen1TypeA;
    @Column(name = "usb_3.2_gen2_typeA")
    private Long usb32gen2TypeA;
    @Column(name = "usb_3.2_gen2_typeC")
    private Long usb32gen2TypeC;
    private Long rj45Lan;
    private Long audioJack;
    private Long dvi;
    private Long vga;
    private Long ps2Mouse;
    private Long ps2Keyboard;
    @Lob
    private Blob photo;
    private Long price;
    private Long warranty;
    private Long stock;
}
