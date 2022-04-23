package com.licenta;

public class Constants {
    public enum ProductType {
        LAPTOP("Laptop"),
        DESKTOP("Desktop");

        private final String type;

        public String getValue() {
            return type;
        }

        ProductType(String type) {
            this.type = type;
        }
    }
}
