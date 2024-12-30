package org.example.factory;

import org.example.models.Plate;
import org.example.models.Restaurant;
import org.example.models.Review;

public class ReviewFactory {

    public static Review createReview(Object target, Double rating, String comment) {
        if (target instanceof Restaurant || target instanceof Plate) {
            return new Review(target, rating, comment);
        }
        throw new IllegalArgumentException("Tipo desconocido.");
    }
}
