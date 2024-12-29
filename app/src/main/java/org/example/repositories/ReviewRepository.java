package org.example.repositories;

import org.example.models.Restaurant;
import org.example.models.Review;

import java.util.HashMap;
import java.util.Map;

public class ReviewRepository {

    private static ReviewRepository instance;
    private Map<String, Review> reviews;

    private ReviewRepository() {
        reviews = new HashMap<>();
    }

    public static synchronized ReviewRepository getInstance() {
        if (instance == null) {
            instance = new ReviewRepository();
        }
        return instance;
    }

    public void addReview(Review review) {

    }

    public void deleteReview() {

    }
//
//    public Review getReview() {
//       return ;
//    }

    public Map<String, Review> getReviews() {
        return reviews;
    }
}
