package org.example.modelsTest;

import org.example.models.Menu;
import org.example.models.Plate;
import org.example.models.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuTest {

    private Menu menu;
    private Restaurant mockRestaurant;

    @BeforeEach
    void setUp() {
        mockRestaurant = mock(Restaurant.class);
        menu = new Menu();
    }

    @Test
    @DisplayName("Add Plate: Plate should be added to the menu")
    void testAddPlate() {
        Plate plate = new Plate("Pasta", 10.0);
        menu.addPlate(plate);

        Set<Plate> plates = menu.getPlates();
        assertTrue(plates.contains(plate));
    }

    @Test
    @DisplayName("Delete Plate: Plate should be removed by name")
    void testDeletePlate() {
        Plate plate = new Plate("Pizza", 12.0);
        menu.addPlate(plate);
        menu.deletePlate("Pizza");

        Set<Plate> plates = menu.getPlates();
        assertFalse(plates.contains(plate));
    }

    @Test
    @DisplayName("Edit Plate: Plate should be updated with new details")
    void testEditPlate() {
        Plate plate = new Plate("Burger", 8.0);
        menu.addPlate(plate);

        boolean result = menu.editPlate("Burger", "Cheeseburger", 9.5);

        Plate updatedPlate = menu.getPlateByName("Cheeseburger");
        assertTrue(result);
        assertNotNull(updatedPlate);
        assertEquals("Cheeseburger", updatedPlate.getPlateName());
        assertEquals(9.5, updatedPlate.getPrice());
    }

    @Test
    @DisplayName("Edit Plate: Return false if plate not found")
    void testEditPlateNotFound() {
        boolean result = menu.editPlate("NonExistent", "NewName", 15.0);
        assertFalse(result);
    }

    @Test
    @DisplayName("Get Plate By Name: Should return the correct plate")
    void testGetPlateByName() {
        Plate plate = new Plate("Salad", 5.0);
        menu.addPlate(plate);

        Plate foundPlate = menu.getPlateByName("Salad");
        assertNotNull(foundPlate);
        assertEquals("Salad", foundPlate.getPlateName());
    }

    @Test
    @DisplayName("Get Plate By Name: Return null if plate not found")
    void testGetPlateByNameNotFound() {
        Plate foundPlate = menu.getPlateByName("NonExistent");
        assertNull(foundPlate);
    }

    @Test
    @DisplayName("Get Restaurant: Should return the associated restaurant")
    void testGetRestaurant() {
        assertEquals(mockRestaurant, menu.getRestaurant());
    }

    @Test
    @DisplayName("Set Restaurant: Should update the associated restaurant")
    void testSetRestaurant() {
        Restaurant newMockRestaurant = mock(Restaurant.class);
        menu.setRestaurant(newMockRestaurant);

        assertEquals(newMockRestaurant, menu.getRestaurant());
    }

    @Test
    @DisplayName("Get Plates: Should return all plates")
    void testGetPlates() {
        Plate plate1 = new Plate("Soup", 6.0);
        Plate plate2 = new Plate("Steak", 15.0);
        menu.addPlate(plate1);
        menu.addPlate(plate2);

        Set<Plate> plates = menu.getPlates();
        assertEquals(2, plates.size());
        assertTrue(plates.contains(plate1));
        assertTrue(plates.contains(plate2));
    }
}
