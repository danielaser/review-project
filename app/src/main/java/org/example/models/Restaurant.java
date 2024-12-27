package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String address;
    private String city;
    private Menu menu;
    private List<Review> reviews;

    public Restaurant(String name, String address, String city) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.menu = new Menu();
        this.reviews = new ArrayList<>();
    }

    public Restaurant() {
    }

    public Restaurant(String name) {
    }

    public Menu getMenu() {
        return menu;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    // getters and setters
    public String getRestaurantName() {
        return name;
    }

    public void setRestaurantName(String name) {
        this.name = name;
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setRestaurantReviews(List<Review> restaurantReviews) {
        this.reviews = reviews;
    }
}
