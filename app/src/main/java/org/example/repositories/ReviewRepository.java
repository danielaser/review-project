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

    public LinkedList<Review> getReviewsByTarget(Object target) {
        LinkedList<Review> filteredReviews = new LinkedList<>();
        for (Review review : reviews) if (review.getTarget().equals(target)) filteredReviews.add(review);
        return filteredReviews;
    }

    public double calculateAverageRating(Object target) {
        double totalRating = 0;
        int count = 0;
        for (Review review : reviews)
            if (review.getTarget().equals(target)) {
                totalRating += review.getRating();
                count++;
            }
        return count > 0 ? totalRating / count : 0;
    }
}
