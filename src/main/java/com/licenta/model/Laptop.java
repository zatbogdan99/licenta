package com.licenta.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "LAPTOP")
public class Laptop {
    @Id
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "GUARANTEE")
    private Long guarantee;
    @Column(name = "DISPLAY")
    private Long display;
    @Column (name = "PROCESSOR")
    private Long processor;
    @Column (name = "RAM_TOTAL")
    private Long ram_total;
    @Column (name = "RAM_TYPE")
    private String ram_type;
    @Column (name = "RAM_FREQUENCY")
    private Long ram_frequency;
    @Column (name = "RAM_SLOTS")
    private Long ram_slots;
    @Column (name = "STORAGE")
    private String storage;
    @Column (name = "STORAGE_CAPACITY")
    private Long storage_capacity;
    @Column (name = "STORAGE_INTERFACE")
    private String storage_interface;
    @Column (name = "STORAGE_FORM_FACTOR")
    private Long storage_form_factor;
    @Column (name = "GRAPHICS_CARD")
    private Long graphics_card;

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

    public Long getRam_total() {
        return ram_total;
    }

    public void setRam_total(Long ram_total) {
        this.ram_total = ram_total;
    }

    public String getRam_type() {
        return ram_type;
    }

    public void setRam_type(String ram_type) {
        this.ram_type = ram_type;
    }

    public Long getRam_frequency() {
        return ram_frequency;
    }

    public void setRam_frequency(Long ram_frequency) {
        this.ram_frequency = ram_frequency;
    }

    public Long getRam_slots() {
        return ram_slots;
    }

    public void setRam_slots(Long ram_slots) {
        this.ram_slots = ram_slots;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public Long getStorage_capacity() {
        return storage_capacity;
    }

    public void setStorage_capacity(Long storage_capacity) {
        this.storage_capacity = storage_capacity;
    }

    public String getStorage_interface() {
        return storage_interface;
    }

    public void setStorage_interface(String storage_interface) {
        this.storage_interface = storage_interface;
    }

    public Long getStorage_form_factor() {
        return storage_form_factor;
    }

    public void setStorage_form_factor(Long storage_form_factor) {
        this.storage_form_factor = storage_form_factor;
    }

    public Long getGraphics_card() {
        return graphics_card;
    }

    public void setGraphics_card(Long graphics_card) {
        this.graphics_card = graphics_card;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
