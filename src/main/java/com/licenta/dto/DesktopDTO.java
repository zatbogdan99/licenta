package com.licenta.dto;

import com.licenta.model.*;
import com.licenta.utils.Utils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import java.sql.Blob;

@Getter
@Setter
public class DesktopDTO {
    private Long id;
    private String name;
    private Long warranty;
    private String photo;
    private Long photos_id;
    private String type;
    private Processor processor;
    private Ram ram;
    private Storage storage;
    private GraphicsCard graphicsCard;
    private Long price;
    private Long stock;

    public static ProductDTO toProduct(DesktopDTO desktopDTO) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(desktopDTO.getName());
        productDTO.setProductType(Utils.ProductTypes.DESKTOP.getValue());
        productDTO.setDescription(desktopDTO.getName() + " cu procesor " + desktopDTO.getProcessor().getName() +
                ", Placa video " + desktopDTO.getGraphicsCard().getName() + ", " + desktopDTO.getRam().getTotal() + " " +
                desktopDTO.getRam().getType() + ", " + desktopDTO.getStorage().getType() + " " + desktopDTO.getStorage().getCapacity());
        productDTO.setId(desktopDTO.getId());
        productDTO.setPhoto(desktopDTO.getPhoto());
        productDTO.setPrice(desktopDTO.getPrice());
        productDTO.setStock(desktopDTO.getStock());

        return productDTO;
    }
}
