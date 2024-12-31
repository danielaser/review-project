package org.example.repositoriesTest;

import org.example.models.Review;
import org.example.repositories.ReviewRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ReviewRepositoryTest {

    private ReviewRepository reviewRepository;

    @BeforeEach
    void setUp() {
        reviewRepository = ReviewRepository.getInstance();
    }

    @AfterEach
    void tearDown() {
        reviewRepository.reset();
    }

    @Test
    @DisplayName("Test agregar una reseña")
    void testAddReview() {
        Object target = new Object();
        Review review = new Review(target, 4.0, "Excelente");

        reviewRepository.addReview(review);

        LinkedList<Review> reviews = reviewRepository.getReviewsByTarget(target);

        assertNotNull(reviews, "La lista de reseñas no debería ser nula.");
        assertEquals(1, reviews.size(), "Debería haber una reseña agregada.");
        assertEquals(review, reviews.getFirst(), "La reseña agregada no coincide con la recuperada.");
    }

    @Test
    @DisplayName("Test obtener reseñas por objetivo")
    void testGetReviewsByTarget() {
        Object target1 = new Object();
        Object target2 = new Object();

        Review review1 = new Review(target1, 5.0, "Muy bueno");
        Review review2 = new Review(target2,3.0, "Bueno");

        reviewRepository.addReview(review1);
        reviewRepository.addReview(review2);

        LinkedList<Review> reviewsForTarget1 = reviewRepository.getReviewsByTarget(target1);
        LinkedList<Review> reviewsForTarget2 = reviewRepository.getReviewsByTarget(target2);

        assertEquals(1, reviewsForTarget1.size(), "Debería haber una reseña para el objetivo 1.");
        assertEquals(review1, reviewsForTarget1.getFirst(), "La reseña para el objetivo 1 no coincide.");

        assertEquals(1, reviewsForTarget2.size(), "Debería haber una reseña para el objetivo 2.");
        assertEquals(review2, reviewsForTarget2.getFirst(), "La reseña para el objetivo 2 no coincide.");
    }

    @Test
    @DisplayName("Test calcular calificación promedio para un objetivo")
    void testCalculateAverageRating() {
        Object target = new Object();

        Review review1 = new Review(target, 5.0, "Excelente");
        Review review2 = new Review(target,3.0, "Regular");

        reviewRepository.addReview(review1);
        reviewRepository.addReview(review2);

        double averageRating = reviewRepository.calculateAverageRating(target);

        assertEquals(4.0, averageRating, 0.001, "El promedio de calificaciones no es correcto.");
    }

    @Test
    @DisplayName("Test calcular calificación promedio sin reseñas para un objetivo")
    void testCalculateAverageRatingNoReviews() {
        Object target = new Object();

        double averageRating = reviewRepository.calculateAverageRating(target);

        assertEquals(0.0, averageRating, "El promedio de calificaciones debería ser 0 cuando no hay reseñas.");
    }

    @Test
    @DisplayName("Test obtener reseñas para un objetivo sin reseñas")
    void testGetReviewsForTargetNoReviews() {
        Object target = new Object();

        LinkedList<Review> reviews = reviewRepository.getReviewsByTarget(target);

        assertNotNull(reviews, "La lista de reseñas no debería ser nula.");
        assertTrue(reviews.isEmpty(), "La lista de reseñas debería estar vacía para un objetivo sin reseñas.");
    }
}

