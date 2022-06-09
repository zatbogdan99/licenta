package com.licenta.utils;

public class Utils {
    public enum ProductTypes {
        LAPTOP("Laptop"),
        DESKTOP("Desktop"),
        DISPLAY("Display"),
        GRAPHICS_CARD("GraphicsCard"),
        PROCESSOR("Processor"),
        RAM("RAM"),
        STORAGE("Storage"),
        MOTHERBOARD("Motherboard");

        private String value;

        ProductTypes(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
