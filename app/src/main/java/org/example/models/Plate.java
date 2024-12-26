package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Plate {
    private String plateName;
    private Double price;
    private List<Review> plateReviews;

    public Plate(String plateName, Double price) {
        this.plateName = plateName;
        this.price = price;
        this.plateReviews = new ArrayList<>();
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

    public List<Review> getPlateReviews() {
        return plateReviews;
    }

    public void setPlateReviews(List<Review> plateReviews) {
        this.plateReviews = plateReviews;
    }
}
