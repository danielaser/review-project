package org.example.modelsTest;

import org.example.models.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {

    private Review review;
    private Object mockTarget;

    @BeforeEach
    void setUp() {
        mockTarget = new Object();
        review = new Review(mockTarget, 4.5, "Great experience!");
    }

    @Test
    @DisplayName("Constructor: Values should be correctly assigned")
    void testConstructor() {
        assertEquals(mockTarget, review.getTarget());
        assertEquals(4.5, review.getRating());
        assertEquals("Great experience!", review.getComment());
    }

    @Test
    @DisplayName("Get Target: Should return the correct target")
    void testGetTarget() {
        assertEquals(mockTarget, review.getTarget());
    }

    @Test
    @DisplayName("Get Rating: Should return the correct rating")
    void testGetRating() {
        assertEquals(4.5, review.getRating());
    }

    @Test
    @DisplayName("Get Comment: Should return the correct comment")
    void testGetComment() {
        assertEquals("Great experience!", review.getComment());
    }

    @Test
    @DisplayName("Setters: Should update values correctly")
    void testSetters() {
        Object newTarget = new Object();
        double newRating = 3.0;
        String newComment = "Average experience.";

        review = new Review();
        review = new Review(newTarget, newRating, newComment);

        assertEquals(newTarget, review.getTarget());
        assertEquals(newRating, review.getRating());
        assertEquals(newComment, review.getComment());
    }
}
