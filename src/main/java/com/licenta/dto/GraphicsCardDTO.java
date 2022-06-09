package com.licenta.dto;

import com.licenta.utils.Utils;

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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getForLaptop() {
        return forLaptop;
    }

    public void setForLaptop(Long forLaptop) {
        this.forLaptop = forLaptop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }


    public static ProductDTO toProduct(GraphicsCardDTO graphicsCardDTO) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductType(Utils.ProductTypes.GRAPHICS_CARD.getValue());
        productDTO.setName(graphicsCardDTO.getChipset() + " " + graphicsCardDTO.getName() + " " + graphicsCardDTO.getModel());
        productDTO.setDescription("Placa video " + graphicsCardDTO.getChipset() + " " + graphicsCardDTO.getName() + " "
                + graphicsCardDTO.getModel() + ", " + graphicsCardDTO.getCapacity() + ", " + graphicsCardDTO.getTechnology());
        productDTO.setId(graphicsCardDTO.getId());
        productDTO.setPhoto(graphicsCardDTO.getPhoto());
        productDTO.setPrice(graphicsCardDTO.getPrice());

        return productDTO;
    }
}
