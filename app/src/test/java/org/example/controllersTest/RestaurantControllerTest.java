package org.example.controllersTest;

import org.example.controllers.RestaurantController;
import org.example.models.Restaurant;
import org.example.services.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class RestaurantControllerTest {

    @Mock
    private RestaurantService restaurantService;

    private RestaurantController restaurantController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantController = new RestaurantController(restaurantService);
    }

    @Test
    @DisplayName("Test addRestaurant")
    void testAddRestaurant() {

        String name = "Restaurante A";
        String address = "Calle Ficticia 123";
        String city = "Ciudad X";

        restaurantController.addRestaurant(name, address, city);

        verify(restaurantService, times(1)).addRestaurant(name, address, city);
    }

    @Test
    @DisplayName("Test deleteRestaurant")
    void testDeleteRestaurant() {

        String name = "Restaurante B";

        restaurantController.deleteRestaurant(name);

        verify(restaurantService, times(1)).deleteRestaurant(name);
    }

    @Test
    @DisplayName("Test editRestaurant when edit is successful")
    void testEditRestaurantSuccess() {

        String currentName = "Restaurante C";
        String newName = "Restaurante C Modificado";
        String newAddress = "Calle Nueva 456";
        String newCity = "Ciudad Y";

        restaurantController.editRestaurant(currentName, newName, newAddress, newCity);

        verify(restaurantService, times(1)).editRestaurant(currentName, newName, newAddress, newCity);
    }

    @Test
    @DisplayName("Test editRestaurant when edit fails")
    void testEditRestaurantFail() {

        String currentName = "Restaurante D";
        String newName = "Restaurante D Modificado";
        String newAddress = "Calle Ficticia 789";
        String newCity = "Ciudad Z";

        restaurantController.editRestaurant(currentName, newName, newAddress, newCity);

        verify(restaurantService, times(1)).editRestaurant(currentName, newName, newAddress, newCity);
    }

    @Test
    @DisplayName("Test getRestaurants")
    void testGetRestaurants() {

        String name1 = "Restaurante E";
        String name2 = "Restaurante F";
        when(restaurantService.getRestaurants()).thenReturn(Map.of(
                name1, new Restaurant(name1, "Calle E 123", "Ciudad E"),
                name2, new Restaurant(name2, "Calle F 456", "Ciudad F")
        ));

        Map<String, Restaurant> restaurants = restaurantController.getRestaurants();

        verify(restaurantService, times(1)).getRestaurants();
        assert restaurants != null;
        assert restaurants.size() == 2;
        assert restaurants.containsKey(name1);
        assert restaurants.containsKey(name2);
    }

    @Test
    @DisplayName("Test constructor de RestaurantController con RestaurantService")
    void testRestaurantControllerConstructor() {
        RestaurantController restaurantController = new RestaurantController();

        assertNotNull(restaurantController);

    }
}
