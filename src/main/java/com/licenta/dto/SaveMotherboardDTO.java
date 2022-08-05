package com.licenta.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveMotherboardDTO {
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
    private Long m2Ports;
    private String ramType;
    private Long maxRam;
    private Long ramSlotsNumber;
    private String supportedFrequencies;
    private Long pciExpress40x16;
    private Long pciExpress30x16;
    private Long pci_express_x1;
    private Long hdmi;
    private Long displayPort;
    private Long usb20;
    private Long usb32gen1TypeA;
    private Long usb32gen2TypeA;
    private Long usb32gen2TypeC;
    private Long rj45Lan;
    private Long audioJack;
    private Long dvi;
    private Long vga;
    private Long ps2Mouse;
    private Long ps2Keyboard;
    private String[] photos;
    private Long price;
    private Long warranty;
    private Long stock;
}
