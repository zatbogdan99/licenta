package com.licenta.dto;

import com.licenta.Constants;
import com.licenta.model.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;

@Getter
@Setter
public class LaptopDTO {
    private Long id;
    private String name;
    private Long warranty;
    private Display display;
    private Processor processor;
    private Ram ram;
    private Storage storage;
    private GraphicsCard graphicsCard;
    private Long price;
    private String photo;
    private Long photosId;
    private Long stock;
    private String additionalRamType;

    public static ProductDTO toProduct(LaptopDTO laptopDTO) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(laptopDTO.getName());
        productDTO.setProductType(Constants.ProductType.LAPTOP.getValue());
        productDTO.setDescription(Constants.ProductType.LAPTOP.getValue() + " " + laptopDTO.getName() + ", "
                + "Processor " + laptopDTO.getProcessor().getProducer() + " " + laptopDTO.getProcessor().getName() + " "
                + laptopDTO.getProcessor().getModel() + ", " + laptopDTO.getStorage().getName() + ", RAM " + laptopDTO.getRam().getType()
                + ", Placa video " + laptopDTO.getGraphicsCard().getType() + " " + laptopDTO.getGraphicsCard().getModel()
                + " " + laptopDTO.getGraphicsCard().getCapacity() + " Gb");
        productDTO.setPrice(laptopDTO.getPrice());
        productDTO.setPhoto(laptopDTO.getPhoto());
        productDTO.setId(laptopDTO.getId());
        productDTO.setStock(laptopDTO.getStock());

        return productDTO;
    }
}
