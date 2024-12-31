package org.example.repositoriesTest;

import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantRepositoryTest {

    private RestaurantRepository restaurantRepository;

    @BeforeEach
    void setUp() {
        restaurantRepository = RestaurantRepository.getInstance();
    }

    @AfterEach
    void tearDown() {
        restaurantRepository.reset();
    }

    @Test
    @DisplayName("Test agregar un restaurante")
    void testAddRestaurant() {
        Restaurant restaurant = new Restaurant("Restaurante Ejemplo");

        restaurantRepository.addRestaurant(restaurant);

        Restaurant retrievedRestaurant = restaurantRepository.getRestaurant("Restaurante Ejemplo");

        assertNotNull(retrievedRestaurant, "El restaurante debería haberse agregado al repositorio.");
        assertEquals("Restaurante Ejemplo", retrievedRestaurant.getRestaurantName(),
                "El nombre del restaurante recuperado no coincide.");
    }

    @Test
    @DisplayName("Test eliminar un restaurante")
    void testDeleteRestaurant() {
        Restaurant restaurant = new Restaurant("Restaurante Ejemplo");

        restaurantRepository.addRestaurant(restaurant);
        restaurantRepository.deleteRestaurant("Restaurante Ejemplo");

        Restaurant retrievedRestaurant = restaurantRepository.getRestaurant("Restaurante Ejemplo");

        assertNull(retrievedRestaurant, "El restaurante debería haber sido eliminado del repositorio.");
    }

    @Test
    @DisplayName("Test obtener un restaurante existente")
    void testGetExistingRestaurant() {
        Restaurant restaurant = new Restaurant("Restaurante Ejemplo");

        restaurantRepository.addRestaurant(restaurant);

        Restaurant retrievedRestaurant = restaurantRepository.getRestaurant("Restaurante Ejemplo");

        assertNotNull(retrievedRestaurant, "Se esperaba un restaurante existente.");
        assertEquals("Restaurante Ejemplo", retrievedRestaurant.getRestaurantName(),
                "El nombre del restaurante recuperado no coincide.");
    }

    @Test
    @DisplayName("Test intentar obtener un restaurante que no existe")
    void testGetNonExistingRestaurant() {
        Restaurant retrievedRestaurant = restaurantRepository.getRestaurant("Restaurante Inexistente");

        assertNull(retrievedRestaurant, "No se esperaba encontrar un restaurante inexistente.");
    }

    @Test
    @DisplayName("Test obtener todos los restaurantes")
    void testGetRestaurants() {
        Restaurant restaurant1 = new Restaurant("Restaurante 1");
        Restaurant restaurant2 = new Restaurant("Restaurante 2");

        restaurantRepository.addRestaurant(restaurant1);
        restaurantRepository.addRestaurant(restaurant2);

        Map<String, Restaurant> restaurants = restaurantRepository.getRestaurants();

        assertNotNull(restaurants, "La lista de restaurantes no debería ser nula.");
        assertEquals(2, restaurants.size(), "Deberían haberse agregado dos restaurantes.");
        assertTrue(restaurants.containsKey("Restaurante 1"), "La lista debería contener 'Restaurante 1'.");
        assertTrue(restaurants.containsKey("Restaurante 2"), "La lista debería contener 'Restaurante 2'.");
    }

    @Test
    @DisplayName("Test intentar eliminar un restaurante que no existe")
    void testDeleteNonExistingRestaurant() {
        restaurantRepository.deleteRestaurant("Restaurante Inexistente");

        Map<String, Restaurant> restaurants = restaurantRepository.getRestaurants();

        assertTrue(restaurants.isEmpty(), "La lista de restaurantes debería seguir vacía.");
    }
}

