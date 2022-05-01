package com.licenta.dto;

import javax.persistence.Entity;

public class SaveLaptopModel {
    private Long id;
    private String name;
    private Long waranty;
    private Long display;
    private Long processor;
    private Long ramTotal;
    private String ramType;
    private Long ramFrequency;
    private Long ramSlots;
    private String storage;
    private Long storageCapacity;
    private String storageInterface;
    private Long storageFormFactor;
    private Long graphicsCard;
    private Long price;
    private String[] photos;

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

    public Long getWaranty() {
        return waranty;
    }

    public void setWaranty(Long waranty) {
        this.waranty = waranty;
    }

    public Long getDisplay() {
        return display;
    }

    public void setDisplay(Long display) {
        this.display = display;
    }

    public Long getProcessor() {
        return processor;
    }

    public void setProcessor(Long processor) {
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

    public Long getGraphicsCard() {
        return graphicsCard;
    }

    public void setGraphicsCard(Long graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String[] getPhotos() {
        return photos;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }
}
