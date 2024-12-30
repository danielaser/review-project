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

        // Crear una instancia de ReviewService y asignar repositorios
        reviewService = new ReviewService(reviewRepository, restaurantRepository);

        // Crear un restaurante mock
        restaurant = mock(Restaurant.class);

        // Crear y asignar un menú al restaurante
        Menu menu = mock(Menu.class);
        when(restaurant.getMenu()).thenReturn(menu);

        // Simular la búsqueda de un restaurante por nombre
        when(restaurantRepository.getRestaurant("Restaurante Ejemplo")).thenReturn(restaurant);

        // Crear un plato para el menú y configurarlo para que se devuelva cuando se solicite
        Plate plate = new Plate("Plato de Ejemplo", 15.0);
        when(menu.getPlateByName("Plato de Ejemplo")).thenReturn(plate);

        // Registrar el mockObserver como observador del restaurante
        restaurant.addObserver(mockObserver);
    }



    @Test
    void testAddReview() {
        // Llamar al método addReview
        reviewService.addReview("Restaurante Ejemplo", 4.5, "Excelente comida");

        // Verificar que el método notifyAverageRatingChange fue llamado en el restaurante
        verify(restaurant).notifyAverageRatingChange(4.5);

        // Verificar que el observador fue notificado con el mensaje correcto
        verify(mockObserver).update("Calificación promedio del restaurante Restaurante Ejemplo actualizada a: 4.5");
    }



    @Test
    void testAddPlateReview() {
        // Configurar los mocks
        RestaurantRepository restaurantRepository = mock(RestaurantRepository.class);
        ReviewRepository reviewRepository = mock(ReviewRepository.class);
        ReviewService reviewService = new ReviewService(reviewRepository, restaurantRepository);

        Restaurant restaurant = mock(Restaurant.class);
        when(restaurantRepository.getRestaurant("Restaurante Ejemplo")).thenReturn(restaurant);

        // Crear un plato mock para el menú
        Plate plate = new Plate("Plato de Ejemplo", 15.0);
        when(restaurant.getMenu().getPlateByName("Plato de Ejemplo")).thenReturn(plate);

        // Test de la función de añadir reseña a un plato
        reviewService.addPlateReview("Restaurante Ejemplo", "Plato de Ejemplo", 4.5, "Muy sabroso");

        // Verificar que el repositorio agregue la reseña
        verify(reviewRepository).addReview(any(Review.class));

        // Verificar que se llame al método notifyAverageRatingChange del plato
        verify(restaurant).notifyAverageRatingChange(anyDouble());
    }

    @Test
    void testGetRestaurantReviews() {
        // Configurar los mocks
        RestaurantRepository restaurantRepository = mock(RestaurantRepository.class);
        ReviewRepository reviewRepository = mock(ReviewRepository.class);
        ReviewService reviewService = new ReviewService(reviewRepository, restaurantRepository);

        Restaurant restaurant = mock(Restaurant.class);
        when(restaurantRepository.getRestaurant("Restaurante Ejemplo")).thenReturn(restaurant);

        // Crear una lista de reseñas para el restaurante
        LinkedList<Review> mockReviews = new LinkedList<>();
        mockReviews.add(new Review(restaurant, 4.0, "Excelente comida"));

        // Simular el comportamiento del repositorio
        when(reviewRepository.getReviewsByTarget(restaurant)).thenReturn(mockReviews);
        when(reviewRepository.calculateAverageRating(restaurant)).thenReturn(4.0);

        // Obtener las reseñas del restaurante
        LinkedList<Review> reviews = reviewService.getRestaurantReviews("Restaurante Ejemplo");

        // Verificar que se haya llamado al repositorio para obtener las reseñas
        verify(reviewRepository).getReviewsByTarget(restaurant);

        // Asegurarse de que las reseñas se devuelvan correctamente
        assertEquals(1, reviews.size());
        assertEquals(4.0, reviews.get(0).getRating());
        assertEquals("Excelente comida", reviews.get(0).getComment());
    }

    @Test
    void testGetPlateReviews() {
        // Configurar los mocks
        RestaurantRepository restaurantRepository = mock(RestaurantRepository.class);
        ReviewRepository reviewRepository = mock(ReviewRepository.class);
        ReviewService reviewService = new ReviewService(reviewRepository, restaurantRepository);

        Restaurant restaurant = mock(Restaurant.class);
        when(restaurantRepository.getRestaurant("Restaurante Ejemplo")).thenReturn(restaurant);

        // Crear un plato mock para el menú
        Plate plate = new Plate("Plato de Ejemplo", 15.0);
        when(restaurant.getMenu().getPlateByName("Plato de Ejemplo")).thenReturn(plate);

        // Crear una lista de reseñas para el plato
        LinkedList<Review> mockReviews = new LinkedList<>();
        mockReviews.add(new Review(restaurant, 5.0, "Excelente plato"));

        // Simular el comportamiento del repositorio
        when(reviewRepository.getReviewsByTarget(plate)).thenReturn(mockReviews);
        when(reviewRepository.calculateAverageRating(plate)).thenReturn(5.0);

        // Obtener las reseñas del plato
        LinkedList<Review> reviews = reviewService.getPlateReviews("Restaurante Ejemplo", "Plato de Ejemplo");

        // Verificar que se haya llamado al repositorio para obtener las reseñas
        verify(reviewRepository).getReviewsByTarget(plate);

        // Asegurarse de que las reseñas se devuelvan correctamente
        assertEquals(1, reviews.size());
        assertEquals(5.0, reviews.get(0).getRating());
        assertEquals("Excelente plato", reviews.get(0).getComment());
    }


    @Test
    void testAddReviewRestaurantNotFound() {
        // Test para cuando no se encuentra el restaurante
        when(restaurantRepository.getRestaurant("Restaurante Inexistente")).thenReturn(null);

        reviewService.addReview("Restaurante Inexistente", 4.0, "Excelente servicio");

        // Verificar que no se intente agregar reseña y no se haga ninguna notificación
        verify(reviewRepository, never()).addReview(any(Review.class));
        verify(restaurant, never()).notifyAverageRatingChange(anyDouble());
    }

    @Test
    void testAddPlateReviewPlateNotFound() {
        // Test para cuando no se encuentra el plato en el menú
        when(restaurant.getMenu().getPlateByName("Plato Inexistente")).thenReturn(null);

        reviewService.addPlateReview("Restaurante Ejemplo", "Plato Inexistente", 4.5, "Muy sabroso");

        // Verificar que no se intente agregar reseña y no se haga ninguna notificación
        verify(reviewRepository, never()).addReview(any(Review.class));
        verify(restaurant, never()).notifyAverageRatingChange(anyDouble());
    }

    @Test
    void testGetRestaurantReviewsNotFound() {
        // Test para cuando no se encuentra el restaurante
        when(restaurantRepository.getRestaurant("Restaurante Inexistente")).thenReturn(null);

        LinkedList<Review> reviews = reviewService.getRestaurantReviews("Restaurante Inexistente");

        // Verificar que la lista de reseñas devuelta está vacía
        assertTrue(reviews.isEmpty());
    }

    @Test
    void testGetPlateReviewsNotFound() {
        // Test para cuando no se encuentra el plato
        when(restaurantRepository.getRestaurant("Restaurante Ejemplo")).thenReturn(restaurant);
        when(restaurant.getMenu().getPlateByName("Plato Inexistente")).thenReturn(null);

        LinkedList<Review> reviews = reviewService.getPlateReviews("Restaurante Ejemplo", "Plato Inexistente");

        // Verificar que la lista de reseñas devuelta está vacía
        assertTrue(reviews.isEmpty());
    }
}
