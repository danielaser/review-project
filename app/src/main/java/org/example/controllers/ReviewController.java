package org.example.controllers;

import org.example.models.Review;
import org.example.services.ReviewService;

import java.util.LinkedList;

public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController() {
        this.reviewService = new ReviewService();
    }

    public void addReview(String restaurantName, Double rating, String comment) {
      reviewService.addReview(restaurantName, rating, comment);
    }

    public void addPlateReview(String restaurantName, String plateName, Double rating, String comment) {
        reviewService.addPlateReview(restaurantName, plateName, rating, comment);
    }

    public void getRestaurantReviews(String restaurantName) {
        LinkedList<Review> reviews = reviewService.getRestaurantReviews(restaurantName);
        if (reviews.isEmpty()) {
            System.out.println("No hay reviews para el restaurante: " + restaurantName);
        }
    }

    public void getPlateReviews(String restaurantName, String plateName) {
        LinkedList<Review> reviews = reviewService.getPlateReviews(restaurantName, plateName);
        if (reviews.isEmpty()) {
            System.out.println("No hay reviews para el plato: " + plateName + " en el restaurante: " + restaurantName);
        }
    }
}
