package com.licenta.dto;

public class FilterDTO {
    private int minRange;
    private int maxRange;
    private String[] processor;
    private String[] memory;
    private String[] memoryCapacity;
    private String[] ram;

    public int getMinRange() {
        return minRange;
    }

    public void setMinRange(int minRange) {
        this.minRange = minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(int maxRange) {
        this.maxRange = maxRange;
    }

    public String[] getProcessor() {
        return processor;
    }

    public void setProcessor(String[] processor) {
        this.processor = processor;
    }

    public String[] getMemory() {
        return memory;
    }

    public void setMemory(String[] memory) {
        this.memory = memory;
    }

    public String[] getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(String[] memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    public String[] getRam() {
        return ram;
    }

    public void setRam(String[] ram) {
        this.ram = ram;
    }
}
