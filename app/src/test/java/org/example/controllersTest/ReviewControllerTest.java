package org.example.controllersTest;

import org.example.controllers.ReviewController;
import org.example.services.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.LinkedList;

import static org.mockito.Mockito.*;

class ReviewControllerTest {

    @Mock
    private ReviewService reviewService;

    private ReviewController reviewController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reviewController = new ReviewController(reviewService);
    }

    @Test
    @DisplayName("Test addReview")
    void testAddReview() {
        String restaurantName = "Restaurante A";
        Double rating = 4.5;
        String comment = "Excelente comida";

        reviewController.addReview(restaurantName, rating, comment);

        verify(reviewService, times(1)).addReview(restaurantName, rating, comment);
    }

    @Test
    @DisplayName("Test addPlateReview")
    void testAddPlateReview() {
        String restaurantName = "Restaurante B";
        String plateName = "Plato 1";
        Double rating = 3.5;
        String comment = "Buen plato, pero podría mejorar";

        reviewController.addPlateReview(restaurantName, plateName, rating, comment);

        verify(reviewService, times(1)).addPlateReview(restaurantName, plateName, rating, comment); // Verifica que se haya invocado addPlateReview
    }

    @Test
    @DisplayName("Test getRestaurantReviews when there are reviews")
    void testGetRestaurantReviews() {
        String restaurantName = "Restaurante C";

        when(reviewService.getRestaurantReviews(restaurantName)).thenReturn(new LinkedList<>());

        reviewController.getRestaurantReviews(restaurantName);

        verify(reviewService, times(1)).getRestaurantReviews(restaurantName);
    }

    @Test
    @DisplayName("Test getRestaurantReviews when there are no reviews")
    void testGetRestaurantReviewsNoReviews() {
        String restaurantName = "Restaurante D";

        when(reviewService.getRestaurantReviews(restaurantName)).thenReturn(new LinkedList<>());

        reviewController.getRestaurantReviews(restaurantName);

        verify(reviewService, times(1)).getRestaurantReviews(restaurantName);
    }

    @Test
    @DisplayName("Test getPlateReviews when there are reviews")
    void testGetPlateReviews() {
        String restaurantName = "Restaurante E";
        String plateName = "Plato 2";

        when(reviewService.getPlateReviews(restaurantName, plateName)).thenReturn(new LinkedList<>());

        reviewController.getPlateReviews(restaurantName, plateName);

        verify(reviewService, times(1)).getPlateReviews(restaurantName, plateName);
    }

    @Test
    @DisplayName("Test getPlateReviews when there are no reviews")
    void testGetPlateReviewsNoReviews() {
        String restaurantName = "Restaurante F";
        String plateName = "Plato 3";

        when(reviewService.getPlateReviews(restaurantName, plateName)).thenReturn(new LinkedList<>());

        reviewController.getPlateReviews(restaurantName, plateName);

        verify(reviewService, times(1)).getPlateReviews(restaurantName, plateName);
    }
}
