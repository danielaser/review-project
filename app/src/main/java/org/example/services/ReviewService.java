package org.example.services;

import org.example.factory.ReviewFactory;
import org.example.models.Plate;
import org.example.models.Restaurant;
import org.example.models.Review;
import org.example.observer.TheObserver;
import org.example.repositories.RestaurantRepository;
import org.example.repositories.ReviewRepository;

import java.util.LinkedList;

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
            System.out.println("Review agregada al restaurante: " + restaurantName);
            restaurant.notifyAverageRatingChange(reviewRepository.calculateAverageRating(restaurant));
        } else {
            System.out.println("Restaurante no encontrado.");
        }
    }

    public void addPlateReview(String restaurantName, String plateName, Double rating, String comment) {
        Restaurant restaurant = restaurantRepository.getRestaurant(restaurantName);
        if (restaurant != null) {
            Plate plate = restaurant.getMenu().getPlateByName(plateName);
            if (plate != null) {
                Review review = ReviewFactory.createReview(plate, rating, comment);
                reviewRepository.addReview(review);
                System.out.println("Review agregada al plato: " + plateName);
                plate.notifyAverageRatingChange(reviewRepository.calculateAverageRating(plate));
            } else {
                System.out.println("Plato no encontrado en el restaurante: " + restaurantName);
            }
        } else {
            System.out.println("Restaurante no encontrado.");
        }
    }

    public LinkedList<Review> getRestaurantReviews(String restaurantName) {
        Restaurant restaurant = restaurantRepository.getRestaurant(restaurantName);
        if (restaurant == null) {
            System.out.println("Restaurante no encontrado.");
            return new LinkedList<>();
        }

        LinkedList<Review> reviews = reviewRepository.getReviewsByTarget(restaurant);
        double averageRating = reviewRepository.calculateAverageRating(restaurant);

        System.out.println("Reviews del restaurante: " + restaurantName);
        reviews.forEach(review ->
                System.out.println("- Calificacion: " + review.getRating() + ", Comentario: " + review.getComment())
        );
        System.out.println(reviews.isEmpty() ? "No hay reviews para este restaurante." :
                String.format("Calificacion promedio del restaurante: %.2f%n", averageRating));

        return reviews;
    }

    public LinkedList<Review> getPlateReviews(String restaurantName, String plateName) {
        Restaurant restaurant = restaurantRepository.getRestaurant(restaurantName);
        if (restaurant == null) {
            System.out.println("Restaurante no encontrado.");
            return new LinkedList<>();
        }

        Plate plate = restaurant.getMenu().getPlateByName(plateName);
        if (plate == null) {
            System.out.println("Plato no encontrado en el menú del restaurante: " + restaurantName);
            return new LinkedList<>();
        }

        LinkedList<Review> reviews = reviewRepository.getReviewsByTarget(plate);
        double averageRating = reviewRepository.calculateAverageRating(plate);

        System.out.println("Reviews del plato: " + plateName + " en el restaurante: " + restaurantName);
        reviews.forEach(review ->
                System.out.println("- Calificacion: " + review.getRating() + ", Comentario: " + review.getComment())
        );
        System.out.println(reviews.isEmpty() ? "No hay reviews para este plato." :
                String.format("Calificacion promedio del plato: %.2f%n", averageRating));

        return reviews;
    }

}
