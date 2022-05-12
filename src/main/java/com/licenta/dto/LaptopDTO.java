package com.licenta.dto;

import com.licenta.Constants;
import com.licenta.model.Display;
import com.licenta.model.GraphicsCard;
import com.licenta.model.Processor;

import java.sql.Blob;


public class LaptopDTO {
    private Long id;
    private String name;
    private Long guarantee;
    private Display display;
    private Processor processor;
    private Long ramTotal;
    private String ramType;
    private Long ramFrequency;
    private Long ramSlots;
    private String storage;
    private Long storageCapacity;
    private String storageInterface;
    private Long storageFormFactor;
    private GraphicsCard graphicsCard;
    private Long price;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(Long guarantee) {
        this.guarantee = guarantee;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public Long getRamTotal() {
        return ramTotal;
    }

    public void setRamTotal(Long ramTotal) {
        this.ramTotal = ramTotal;
    }

    public String getRamType() {
        return ramType;
    }

    public void setRamType(String ramType) {
        this.ramType = ramType;
    }

    public Long getRamFrequency() {
        return ramFrequency;
    }

    public void setRamFrequency(Long ramFrequency) {
        this.ramFrequency = ramFrequency;
    }

    public Long getRamSlots() {
        return ramSlots;
    }

    public void setRamSlots(Long ramSlots) {
        this.ramSlots = ramSlots;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public Long getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(Long storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    public String getStorageInterface() {
        return storageInterface;
    }

    public void setStorageInterface(String storageInterface) {
        this.storageInterface = storageInterface;
    }

    public Long getStorageFormFactor() {
        return storageFormFactor;
    }

    public void setStorageFormFactor(Long storageFormFactor) {
        this.storageFormFactor = storageFormFactor;
    }

    public GraphicsCard getGraphicsCard() {
        return graphicsCard;
    }

    public void setGraphicsCard(GraphicsCard graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public static ProductDTO toProduct(LaptopDTO laptopDTO) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(laptopDTO.getName());
        productDTO.setProductType(Constants.ProductType.LAPTOP.getValue());
        productDTO.setDescription(Constants.ProductType.LAPTOP.getValue() + " " + laptopDTO.getName() + ", "
                + "Processor " + laptopDTO.getProcessor().getProducer() + " " + laptopDTO.getProcessor().getName() + " "
                + laptopDTO.getProcessor().getModel() + ", " + laptopDTO.getStorage() + ", RAM " + laptopDTO.getRamType()
                + ", Placa video " + laptopDTO.getGraphicsCard().getType() + " " + laptopDTO.getGraphicsCard().getModel()
                + " " + laptopDTO.getGraphicsCard().getCapacity() + " Gb");
        productDTO.setPrice(laptopDTO.getPrice());
        productDTO.setPhoto(laptopDTO.getPhoto());
        productDTO.setId(laptopDTO.getId());

        return productDTO;
    }
}
