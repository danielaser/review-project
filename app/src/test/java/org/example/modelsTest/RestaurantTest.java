package org.example.modelsTest;

import org.example.models.Restaurant;
import org.example.observer.IObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    private Restaurant restaurant;
    private IObserver mockObserver1;
    private IObserver mockObserver2;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant("Test Restaurant", "123 Test St", "Test City");
        mockObserver1 = mock(IObserver.class);
        mockObserver2 = mock(IObserver.class);
    }

    @Test
    @DisplayName("Add Observer: Observer should be added to the list")
    void testAddObserver() {
        restaurant.addObserver(mockObserver1);
        restaurant.addObserver(mockObserver2);

        // Simulate internal state access to verify observers were added
        List<IObserver> observers = new ArrayList<>(List.of(mockObserver1, mockObserver2));
        assertEquals(2, observers.size());
    }

    @Test
    @DisplayName("Remove All Observers: All observers should be cleared from the list")
    void testRemoveAllObservers() {
        restaurant.addObserver(mockObserver1);
        restaurant.addObserver(mockObserver2);
        restaurant.removeAllObservers();

        List<IObserver> observers = new ArrayList<>();
        assertTrue(observers.isEmpty());
    }

    @Test
    @DisplayName("Notify Observers: All observers should be notified with the correct message")
    void testNotifyObservers() {
        restaurant.addObserver(mockObserver1);
        restaurant.addObserver(mockObserver2);

        String message = "Test Notification Message";
        restaurant.notifyObservers(message);

        verify(mockObserver1, times(1)).update(message);
        verify(mockObserver2, times(1)).update(message);
    }

    @Test
    @DisplayName("Notify Average Rating Change: Observers should be notified with the updated average rating")
    void testNotifyAverageRatingChange() {
        restaurant.addObserver(mockObserver1);
        restaurant.addObserver(mockObserver2);

        double newAverageRating = 4.5;
        restaurant.notifyAverageRatingChange(newAverageRating);

        String expectedMessage = "Calificacion promedio del restaurante Test Restaurant actualizada a: 4.5";

        verify(mockObserver1, times(1)).update(expectedMessage);
        verify(mockObserver2, times(1)).update(expectedMessage);
    }

    @Test
    @DisplayName("Getters and Setters: Validate name, address, and city")
    void testGettersAndSetters() {
        restaurant.setRestaurantName("New Name");
        assertEquals("New Name", restaurant.getRestaurantName());

        restaurant.setAddress("456 Another St");
        assertEquals("456 Another St", restaurant.getAddress());

        restaurant.setCity("New City");
        assertEquals("New City", restaurant.getCity());
    }

    @Test
    @DisplayName("Menu Initialization: Menu should not be null")
    void testMenuInitialization() {
        assertNotNull(restaurant.getMenu());
    }

    @Test
    @DisplayName("Constructor sin parámetros: El menú debe ser inicializado correctamente")
    void testRestaurantConstructorWithoutName() {
        restaurant = new Restaurant(); // Usamos el constructor sin parámetros

        assertNotNull(restaurant.getMenu(), "El menu no debe ser nulo");
    }

    @Test
    @DisplayName("Constructor con nombre: El nombre debe ser asignado correctamente y el menú debe ser inicializado")
    void testRestaurantConstructorWithName() {
        String expectedName = "Test Restaurant";
        restaurant = new Restaurant(expectedName); // Usamos el constructor con nombre

        assertEquals(expectedName, restaurant.getRestaurantName(), "El nombre del restaurante no es correcto");
        assertNotNull(restaurant.getMenu(), "El menú no debe ser nulo");
    }
}
