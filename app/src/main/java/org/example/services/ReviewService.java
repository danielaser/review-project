package org.example.services;

import org.example.factory.ReviewFactory;
import org.example.models.Plate;
import org.example.models.Restaurant;
import org.example.models.Review;
import org.example.repositories.RestaurantRepository;
import org.example.repositories.ReviewRepository;

public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    public ReviewService() {
        this.reviewRepository = ReviewRepository.getInstance();
        this.restaurantRepository = RestaurantRepository.getInstance();
    }

    public void addReview(String restaurantName, Double rating, String comment) {
        Restaurant restaurant = restaurantRepository.getRestaurant(restaurantName);
        if (restaurant != null) {
            Review review = ReviewFactory.createReview(restaurant, rating, comment);
            reviewRepository.addReview(review);
        }else {
            System.out.println("Restaurante no encontrado.");
        }
    }

    public void deleteReview(Review review) {
        reviewRepository.deleteReview(review);
    }

    public double calculateAverageRating(String targetType) {
        return reviewRepository.calculateAverageRating(targetType);
    }
}
