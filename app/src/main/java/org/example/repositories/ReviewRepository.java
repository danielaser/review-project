package org.example.repositories;

import org.example.models.Review;
import java.util.LinkedList;

public class ReviewRepository {

    private static ReviewRepository instance;
    private LinkedList<Review> reviews;

    private ReviewRepository() {
        reviews = new LinkedList<>();
    }

    public static synchronized ReviewRepository getInstance() {
        if (instance == null) {
            instance = new ReviewRepository();
        }
        return instance;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void deleteReview(Review review) {
        reviews.remove(review);
    }

    public LinkedList<Review> getReviews() {
        return reviews;
    }

    public double calculateAverageRating(String targetType) {
        double totalRating = 0;
        int count = 0;
        for (Review review : reviews) {
            if (review.getTargetType().equals(targetType)) {
                totalRating += review.getRating();
                count++;
            }
        }
        return count > 0 ? totalRating / count : 0;
    }
}
