package com.licenta.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductStock {
    private Long selectedProduct;
    private String selectedProductType;
    private Long value;
}
