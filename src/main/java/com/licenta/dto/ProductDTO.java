package com.licenta.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String productType;
    private Long price;
    private String photo;
    private Long stock;
    private Long forLaptop;
}
