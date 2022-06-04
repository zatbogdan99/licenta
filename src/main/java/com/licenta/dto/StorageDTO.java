package com.licenta.dto;

import com.licenta.utils.Utils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StorageDTO {
    private Long id;
    private String type;
    private String name;
    private Long warranty;
    private Long capacity;
    private String storageInterface;
    private Long formFactor;
    private Long speed;

    public static ProductDTO toProduct(StorageDTO storageDTO) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductType(Utils.ProductTypes.STORAGE.getValue());
        productDTO.setName(storageDTO.getType() + " " + storageDTO.getName() + ", " + storageDTO.getCapacity());
        productDTO.setDescription(productDTO.getName() + ", " + storageDTO.getStorageInterface() + ", " +
                (storageDTO.getType().equals("SSD") ? storageDTO.getFormFactor() : storageDTO.getSpeed()));
        productDTO.setId(storageDTO.getId());

        return productDTO;
    }
}
