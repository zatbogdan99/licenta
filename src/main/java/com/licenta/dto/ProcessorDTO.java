package com.licenta.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessorDTO {
    private Long id;
    private String producer;
    private String name;
    private String family;
    private String model;
    private Long cores;
    private Long threads;
    private Long baseFrequency;
    private Long maxTurboFrequency;
    private Long l2Cache;
    private Long l3Cache;
    private Long technology;
    private String integratedGraphics;
    private Long forLaptop;
    private String photo;
    private Long stock;
    private Long price;
    private Long warranty;

    public static ProductDTO toProduct(ProcessorDTO processorDTO) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setName(processorDTO.getName());
        productDTO.setProductType("Processor");
        productDTO.setDescription(processorDTO.getProducer() + " " + processorDTO.getName() + " " +
                processorDTO.getModel() + ", " +  processorDTO.getBaseFrequency() + "Ghz, " + processorDTO.getCores() + " cores, " +
                processorDTO.getThreads() + " threads");
        productDTO.setId(processorDTO.id);
        productDTO.setPhoto(processorDTO.getPhoto());
        productDTO.setStock(processorDTO.getStock());
        productDTO.setPrice(processorDTO.getPrice());

        return productDTO;
    }
}
