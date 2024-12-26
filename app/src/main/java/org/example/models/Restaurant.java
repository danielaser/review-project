package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String restaurantName;
    private String address;
    private String city;
    private List<Review> restaurantReviews;

    public Restaurant(String restaurantName, String address, String city) {
        this.restaurantName = restaurantName;
        this.address = address;
        this.city = city;
        this.restaurantReviews = new ArrayList<>();
    }

    public Restaurant() {
    }

    // getters and setters
    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Review> getRestaurantReviews() {
        return restaurantReviews;
    }

    public void setRestaurantReviews(List<Review> restaurantReviews) {
        this.restaurantReviews = restaurantReviews;
    }
}
