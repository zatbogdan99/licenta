package com.licenta.dto;

import com.licenta.utils.Utils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GraphicsCardDTO {
    private Long id;
    private String chipset;
    private String type;
    private String model;
    private Long capacity;
    private String technology;
    private String name;
    private Long forLaptop;
    private String photo;
    private Long price;
    private Long stock;
    private Long warranty;


    public static ProductDTO toProduct(GraphicsCardDTO graphicsCardDTO) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductType(Utils.ProductTypes.GRAPHICS_CARD.getValue());
        productDTO.setName(graphicsCardDTO.getChipset() + " " + graphicsCardDTO.getName() + " " + graphicsCardDTO.getModel());
        productDTO.setDescription("Placă video " + graphicsCardDTO.getChipset() + " " + graphicsCardDTO.getName() + " "
                + graphicsCardDTO.getModel() + ", " + graphicsCardDTO.getCapacity() + ", " + graphicsCardDTO.getTechnology());
        productDTO.setId(graphicsCardDTO.getId());
        productDTO.setPhoto(graphicsCardDTO.getPhoto());
        productDTO.setPrice(graphicsCardDTO.getPrice());
        productDTO.setStock(graphicsCardDTO.getStock());
        productDTO.setForLaptop(graphicsCardDTO.getForLaptop());

        return productDTO;
    }
}
