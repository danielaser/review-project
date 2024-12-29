package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Plate {
    private String plateName;
    private Double price;
    private List<Review> reviews;

    public Plate(String plateName, Double price) {
        this.plateName = plateName;
        this.price = price;
        this.reviews = new ArrayList<>();
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
