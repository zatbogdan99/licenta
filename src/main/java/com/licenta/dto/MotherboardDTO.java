package com.licenta.dto;

import com.licenta.utils.Utils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MotherboardDTO {
    private Long id;
    private String name;
    private String format;
    private Long cpuSocket;
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
    private String photo;
    private Long price;
    private Long stock;

    public static ProductDTO toProduct(MotherboardDTO motherboardDTO) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductType(Utils.ProductTypes.MOTHERBOARD.getValue());
        productDTO.setName(motherboardDTO.getName());
        productDTO.setDescription("Placa de baza " + motherboardDTO.getChipsetProducer() + ", " + motherboardDTO.getChipsetModel() + ", " +
                motherboardDTO.getFormat());
        productDTO.setId(motherboardDTO.getId());
        productDTO.setPhoto(motherboardDTO.getPhoto());
        productDTO.setPrice(motherboardDTO.getPrice());
        productDTO.setStock(motherboardDTO.getStock());

        return productDTO;
    }
}
