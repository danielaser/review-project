package org.example.factory;

import org.example.models.Review;

public class ReviewFactory {

    public static Review addReview(Double rating, String comment) {
        return new Review(rating, comment);
    }
}
