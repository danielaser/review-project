package org.example.factoryTest;

import org.example.factory.ReviewFactory;
import org.example.models.Plate;
import org.example.models.Restaurant;
import org.example.models.Review;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewFactoryTest {

    @Test
    @DisplayName("Crear reseña para un restaurante")
    void createReviewForRestaurant() {
        Restaurant restaurant = new Restaurant("Restaurante Ejemplo");
        double rating = 4.5;
        String comment = "Buena comida";

        Review review = ReviewFactory.createReview(restaurant, rating, comment);

        assertNotNull(review, "La reseña no debe ser nula");
        assertEquals(restaurant, review.getTarget(), "El objetivo de la reseña debe ser el restaurante");
        assertEquals(rating, review.getRating(), "La calificación de la reseña no es correcta");
        assertEquals(comment, review.getComment(), "El comentario de la reseña no es correcto");
    }

    @Test
    @DisplayName("Crear reseña para un plato")
    void createReviewForPlate() {
        Plate plate = new Plate("Plato Ejemplo", 3.8);
        double rating = 3.8;
        String comment = "Sabroso";

        Review review = ReviewFactory.createReview(plate, rating, comment);

        assertNotNull(review, "La reseña no debe ser nula");
        assertEquals(plate, review.getTarget(), "El objetivo de la reseña debe ser el plato");
        assertEquals(rating, review.getRating(), "La calificación de la reseña no es correcta");
        assertEquals(comment, review.getComment(), "El comentario de la reseña no es correcto");
    }

    @Test
    @DisplayName("Lanzar excepción para tipo desconocido")
    void createReviewForUnknownType() {
        Object unknownTarget = new Object();
        double rating = 2.5;
        String comment = "Comentario genérico";

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                ReviewFactory.createReview(unknownTarget, rating, comment)
        );

        assertEquals("Tipo desconocido.", exception.getMessage(), "El mensaje de excepción no es el esperado");
    }
}
