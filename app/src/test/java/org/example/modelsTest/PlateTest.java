package org.example.modelsTest;

import org.example.models.Plate;
import org.example.observer.IObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PlateTest {

    private Plate plate;
    private IObserver mockObserver1;
    private IObserver mockObserver2;

    @BeforeEach
    void setUp() {
        plate = new Plate("Pasta", 12.5);
        mockObserver1 = mock(IObserver.class);
        mockObserver2 = mock(IObserver.class);
    }

    @Test
    @DisplayName("Add Observer: Observer should be added to the list")
    void testAddObserver() {
        plate.addObserver(mockObserver1);
        plate.addObserver(mockObserver2);

        plate.notifyObservers("Test Message");

        verify(mockObserver1, times(1)).update("Test Message");
        verify(mockObserver2, times(1)).update("Test Message");
    }

    @Test
    @DisplayName("Remove All Observers: List of observers should be cleared")
    void testRemoveAllObservers() {
        plate.addObserver(mockObserver1);
        plate.addObserver(mockObserver2);
        plate.removeAllObservers();

        plate.notifyObservers("Message after removal");

        verify(mockObserver1, never()).update(anyString());
        verify(mockObserver2, never()).update(anyString());
    }

    @Test
    @DisplayName("Notify Observers: All observers should receive the message")
    void testNotifyObservers() {
        plate.addObserver(mockObserver1);
        plate.addObserver(mockObserver2);

        String message = "Test Notification Message";
        plate.notifyObservers(message);

        verify(mockObserver1, times(1)).update(message);
        verify(mockObserver2, times(1)).update(message);
    }

    @Test
    @DisplayName("Notify Average Rating Change: Observers should be notified with the new rating")
    void testNotifyAverageRatingChange() {
        plate.addObserver(mockObserver1);
        plate.addObserver(mockObserver2);

        double newAverageRating = 4.8;
        plate.notifyAverageRatingChange(newAverageRating);

        String expectedMessage = "Calificacion promedio del plato Pasta actualizada a: 4.8";

        verify(mockObserver1, times(1)).update(expectedMessage);
        verify(mockObserver2, times(1)).update(expectedMessage);
    }

    @Test
    @DisplayName("Getters and Setters: Plate name and price should be correctly updated")
    void testGettersAndSetters() {
        plate.setPlateName("Pizza");
        assertEquals("Pizza", plate.getPlateName());

        plate.setPrice(15.0);
        assertEquals(15.0, plate.getPrice());
    }
}
