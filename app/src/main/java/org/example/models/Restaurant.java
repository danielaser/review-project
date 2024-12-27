package org.example.models;

import org.example.observer.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Restaurant extends Observable {
    private String name;
    private String address;
    private String city;
    private Menu menu;
    private List<Review> reviews = new ArrayList<>();

    public Restaurant(String name, String address, String city) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.menu = new Menu();
    }

    public Restaurant() {
    }

    public Restaurant(String name) {
    }


    public void addReview(Review review) {
        reviews.add(review);
        notifyObservers("Nueva review añadida al restaurante: " + name);
        calculateRatingAverage();
    }

    private void calculateRatingAverage() {
        double average = reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);
        notifyObservers("La calificación promedio del restaurante '" + name + "' es ahora: " + average);
    }

    public Menu getMenu() {
        return menu;
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
