package org.example.models;

public class Plate {
    private String plateName;
    private Double price;

    public Plate(String plateName, Double price) {
        this.plateName = plateName;
        this.price = price;
    }

    public Plate() {
    }

    // getters and setters
    public String getPlateName() {
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
