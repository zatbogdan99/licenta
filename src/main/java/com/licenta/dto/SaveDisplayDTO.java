package com.licenta.dto;

public class SaveDisplayDTO {
    private Long id;

    private Long screenSize;

    private String resolution;

    private Long gsync;

    private Long freesync;

    private Long refreshRate;

    private Long brightness;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(Long screenSize) {
        this.screenSize = screenSize;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Long getGsync() {
        return gsync;
    }

    public void setGsync(Long gsync) {
        this.gsync = gsync;
    }

    public Long getFreesync() {
        return freesync;
    }

    public void setFreesync(Long freesync) {
        this.freesync = freesync;
    }

    public Long getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(Long refreshRate) {
        this.refreshRate = refreshRate;
    }

    public Long getBrightness() {
        return brightness;
    }

    public void setBrightness(Long brightness) {
        this.brightness = brightness;
    }
}
