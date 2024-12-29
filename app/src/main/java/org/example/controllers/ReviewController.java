package org.example.controllers;

import org.example.factory.ReviewFactory;
import org.example.models.Review;
import org.example.services.MenuService;
import org.example.services.ReviewService;

public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController() {
        this.reviewService = new ReviewService();
    }

    public void addReview(String restaurantName, Double rating, String comment) {
      reviewService.addReview(restaurantName, rating, comment);
    }

    public void deleteReview(Review review) {
        reviewService.deleteReview(review);
    }

    public double calculateAverageRating(String targetType) {
        return reviewService.calculateAverageRating(targetType);
    }
}
