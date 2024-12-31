package org.example.servicesTest;

import org.example.models.Menu;
import org.example.models.Plate;
import org.example.models.Restaurant;
import org.example.models.Review;
import org.example.observer.IObserver;
import org.example.repositories.RestaurantRepository;
import org.example.repositories.ReviewRepository;
import org.example.services.ReviewService;
import org.example.factory.ReviewFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReviewServiceTest {
    private ReviewService reviewService;
    private RestaurantRepository restaurantRepository;
    private ReviewRepository reviewRepository;
    private Restaurant restaurant;
    private IObserver mockObserver;

    @BeforeEach
    void setUp() {
        // Crear instancias necesarias para los test
        restaurantRepository = mock(RestaurantRepository.class);
        reviewRepository = mock(ReviewRepository.class);
        mockObserver = mock(IObserver.class);

        reviewService = new ReviewService(reviewRepository, restaurantRepository);

        restaurant = mock(Restaurant.class);

        Menu menu = mock(Menu.class);
        when(restaurant.getMenu()).thenReturn(menu);

        when(restaurantRepository.getRestaurant("Restaurante Ejemplo")).thenReturn(restaurant);

        Plate plate = new Plate("Plato de Ejemplo", 15.0);
        when(menu.getPlateByName("Plato de Ejemplo")).thenReturn(plate);

        when(reviewRepository.calculateAverageRating(restaurant)).thenReturn(4.5);

        restaurant.addObserver(mockObserver);
    }


    @Test
    void testGetRestaurantReviews() {
        RestaurantRepository restaurantRepository = mock(RestaurantRepository.class);
        ReviewRepository reviewRepository = mock(ReviewRepository.class);
        ReviewService reviewService = new ReviewService(reviewRepository, restaurantRepository);

        Restaurant restaurant = mock(Restaurant.class);
        when(restaurantRepository.getRestaurant("Restaurante Ejemplo")).thenReturn(restaurant);

        LinkedList<Review> mockReviews = new LinkedList<>();
        mockReviews.add(new Review(restaurant, 4.0, "Excelente comida"));

        when(reviewRepository.getReviewsByTarget(restaurant)).thenReturn(mockReviews);
        when(reviewRepository.calculateAverageRating(restaurant)).thenReturn(4.0);

        LinkedList<Review> reviews = reviewService.getRestaurantReviews("Restaurante Ejemplo");

        verify(reviewRepository).getReviewsByTarget(restaurant);

        assertEquals(1, reviews.size());
        assertEquals(4.0, reviews.get(0).getRating());
        assertEquals("Excelente comida", reviews.get(0).getComment());
    }


    @Test
    void testAddReviewRestaurantNotFound() {
        when(restaurantRepository.getRestaurant("Restaurante Inexistente")).thenReturn(null);

        reviewService.addReview("Restaurante Inexistente", 4.0, "Excelente servicio");

        verify(reviewRepository, never()).addReview(any(Review.class));
        verify(restaurant, never()).notifyAverageRatingChange(anyDouble());
    }

    @Test
    void testAddPlateReviewPlateNotFound() {
        when(restaurant.getMenu().getPlateByName("Plato Inexistente")).thenReturn(null);

        reviewService.addPlateReview("Restaurante Ejemplo", "Plato Inexistente", 4.5, "Muy sabroso");

        verify(reviewRepository, never()).addReview(any(Review.class));
        verify(restaurant, never()).notifyAverageRatingChange(anyDouble());
    }

    @Test
    void testGetRestaurantReviewsNotFound() {
        when(restaurantRepository.getRestaurant("Restaurante Inexistente")).thenReturn(null);

        LinkedList<Review> reviews = reviewService.getRestaurantReviews("Restaurante Inexistente");

        assertTrue(reviews.isEmpty());
    }

    @Test
    void testGetPlateReviewsNotFound() {
        when(restaurantRepository.getRestaurant("Restaurante Ejemplo")).thenReturn(restaurant);
        when(restaurant.getMenu().getPlateByName("Plato Inexistente")).thenReturn(null);

        LinkedList<Review> reviews = reviewService.getPlateReviews("Restaurante Ejemplo", "Plato Inexistente");

        assertTrue(reviews.isEmpty());
    }

    @Test
    void testAddReviewWhenRestaurantExists() {
        String restaurantName = "Restaurante Ejemplo";
        double rating = 4.0;
        String comment = "Excelente comida";

        when(restaurantRepository.getRestaurant(restaurantName)).thenReturn(restaurant);

        reviewService.addReview(restaurantName, rating, comment);

        verify(reviewRepository).addReview(any(Review.class));
        verify(restaurant).notifyAverageRatingChange(anyDouble());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        reviewService.addReview(restaurantName, rating, comment);
        assertTrue(outputStream.toString().contains("Review agregada al restaurante"));
    }

    @Test
    void testAddPlateReviewWhenPlateExists() {
        String restaurantName = "Restaurante Ejemplo";
        String plateName = "Plato de Ejemplo";
        double rating = 4.5;
        String comment = "Muy sabroso";

        Plate plate = mock(Plate.class);
        when(restaurant.getMenu().getPlateByName(plateName)).thenReturn(plate);
        when(restaurantRepository.getRestaurant(restaurantName)).thenReturn(restaurant);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        reviewService.addPlateReview(restaurantName, plateName, rating, comment);

        verify(reviewRepository).addReview(any(Review.class));
        verify(plate).notifyAverageRatingChange(anyDouble());

        assertTrue(outputStream.toString().contains("Review agregada al plato"));

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @Test
    void testGetRestaurantReviewsWhenReviewsExist() {
        String restaurantName = "Restaurante Ejemplo";
        Restaurant restaurant = mock(Restaurant.class);
        LinkedList<Review> mockReviews = new LinkedList<>();
        mockReviews.add(new Review(restaurant, 4.5, "Excelente comida"));

        when(restaurantRepository.getRestaurant(restaurantName)).thenReturn(restaurant);
        when(reviewRepository.getReviewsByTarget(restaurant)).thenReturn(mockReviews);
        when(reviewRepository.calculateAverageRating(restaurant)).thenReturn(4.5);

        LinkedList<Review> reviews = reviewService.getRestaurantReviews(restaurantName);

        assertEquals(1, reviews.size());
        assertEquals(4.5, reviews.get(0).getRating());
        assertEquals("Excelente comida", reviews.get(0).getComment());
    }

    @Test
    void testGetPlateReviewsWhenReviewsExist() {
        String restaurantName = "Restaurante Ejemplo";
        String plateName = "Plato de Ejemplo";
        Plate plate = mock(Plate.class);
        LinkedList<Review> mockReviews = new LinkedList<>();
        mockReviews.add(new Review(restaurant, 4.5, "Muy sabroso"));

        when(restaurantRepository.getRestaurant(restaurantName)).thenReturn(restaurant);
        when(restaurant.getMenu().getPlateByName(plateName)).thenReturn(plate);
        when(reviewRepository.getReviewsByTarget(plate)).thenReturn(mockReviews);
        when(reviewRepository.calculateAverageRating(plate)).thenReturn(4.5);

        LinkedList<Review> reviews = reviewService.getPlateReviews(restaurantName, plateName);

        assertEquals(1, reviews.size());
        assertEquals(4.5, reviews.get(0).getRating());
        assertEquals("Muy sabroso", reviews.get(0).getComment());
    }

    @Test
    void testGetRestaurantReviewsWhenNoReviewsExist() {
        String restaurantName = "Restaurante Ejemplo";
        LinkedList<Review> mockReviews = new LinkedList<>();

        when(restaurantRepository.getRestaurant(restaurantName)).thenReturn(restaurant);
        when(reviewRepository.getReviewsByTarget(restaurant)).thenReturn(mockReviews);

        LinkedList<Review> reviews = reviewService.getRestaurantReviews(restaurantName);

        assertTrue(reviews.isEmpty());
    }



}
