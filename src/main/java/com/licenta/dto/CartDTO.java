package com.licenta.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {
    private Long id;
    private String productType;
    private Long stock;
    private Long productNumber;
}
