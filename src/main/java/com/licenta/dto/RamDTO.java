package com.licenta.dto;

import com.licenta.utils.Utils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RamDTO {
    private Long id;
    private String name;
    private Long warranty;
    private Long total;
    private String type;
    private Long frequency;
    private String format;
    private Long forLaptop;

    public static ProductDTO toProduct(RamDTO ramDTO) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(ramDTO.getName());
        productDTO.setProductType(Utils.ProductTypes.RAM.getValue());
        productDTO.setDescription(ramDTO.getName() + ", " + ramDTO.getTotal() + " " + ramDTO.getType() + " " + ramDTO.getFrequency());
        productDTO.setId(ramDTO.getId());

        return productDTO;
    }
}