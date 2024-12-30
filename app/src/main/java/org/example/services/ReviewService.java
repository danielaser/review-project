package org.example.services;

import org.example.factory.ReviewFactory;
import org.example.models.Plate;
import org.example.models.Restaurant;
import org.example.models.Review;
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
        } else System.out.println("Restaurante no encontrado.");
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
            } else System.out.println("Plato no encontrado en el restaurante: " + restaurantName);
        } else System.out.println("Restaurante no encontrado.");
    }

    public LinkedList<Review> getRestaurantReviews(String restaurantName) {
        Restaurant restaurant = restaurantRepository.getRestaurant(restaurantName);
        LinkedList<Review> x = existingRestaurant(restaurant);
        if (x != null) return x;
        return getReviews(restaurantName, restaurant);
    }

    private LinkedList<Review> getReviews(String restaurantName, Restaurant restaurant) {
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
        LinkedList<Review> x1 = existingRestaurant(restaurant);
        if (x1 != null) return x1;
        Plate plate = restaurant.getMenu().getPlateByName(plateName);
        LinkedList<Review> x = existingPlate(restaurantName, plate);
        if (x != null) return x;
        return getReviewsPlates(restaurantName, plateName, plate);
    }

    private static LinkedList<Review> existingRestaurant(Restaurant restaurant) {
        if (restaurant == null) {
            System.out.println("Restaurante no encontrado.");
            return new LinkedList<>();
        }
        return null;
    }

    private static LinkedList<Review> existingPlate(String restaurantName, Plate plate) {
        if (plate == null) {
            System.out.println("Plato no encontrado en el menu del restaurante: " + restaurantName);
            return new LinkedList<>();
        }
        return null;
    }

    private LinkedList<Review> getReviewsPlates(String restaurantName, String plateName, Plate plate) {
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
