package org.example.services;

import org.example.models.Plate;
import org.example.models.Restaurant;
import org.example.models.Review;
import org.example.repositories.RestaurantRepository;

public class ReviewService {

    private RestaurantRepository restaurantRepository;

    public ReviewService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public void addReviewRestaurant(String restaurantName, Review review) {
        Restaurant restaurant = restaurantRepository.getRestaurant(restaurantName);
        if (restaurant != null) {
            restaurant.addReview(review);
        }
    }

    public void addReviewPlate(String restaurantName, Plate plate, Review review) {
        Restaurant restaurant = restaurantRepository.getRestaurant(restaurantName);
        if (restaurant != null) {
            if (restaurant.getMenu().getPlates().contains(plate)) {
                plate.addReview(review);
            }
        }
    }
}
