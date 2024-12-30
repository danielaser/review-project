package org.example.controllersTest;

import org.example.controllers.MenuController;
import org.example.services.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class MenuControllerTest {

    @Mock
    private MenuService menuService;

    private MenuController menuController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        menuController = new MenuController(menuService);
    }

    @Test
    @DisplayName("Test addPlateToRestaurant")
    void testAddPlateToRestaurant() {
        String restaurantName = "Restaurante A";
        String plateName = "Plato 1";
        Double price = 12.5;

        menuController.addPlateToRestaurant(restaurantName, plateName, price);

        verify(menuService, times(1)).addRestaurantPlate(restaurantName, plateName, price);
    }

    @Test
    @DisplayName("Test deletePlateToRestaurant")
    void testDeletePlateToRestaurant() {
        String restaurantName = "Restaurante B";
        String plateName = "Plato 2";

        menuController.deletePlateToRestaurant(restaurantName, plateName);

        verify(menuService, times(1)).deleteRestaurantPlate(restaurantName, plateName);
    }

    @Test
    @DisplayName("Test editPlateToRestaurant")
    void testEditPlateToRestaurant() {

        String restaurantName = "Restaurante C";
        String plateName = "Plato 3";
        String newPlateName = "Plato 3 Modificado";
        Double newPrice = 15.0;

        menuController.editPlateToRestaurant(restaurantName, plateName, newPlateName, newPrice);

        verify(menuService, times(1)).editRestaurantPlate(restaurantName, plateName, newPlateName, newPrice);
    }

    @Test
    @DisplayName("Test getMenuInRestaurant")
    void testGetMenuInRestaurant() {

        String restaurantName = "Restaurante D";

        menuController.getMenuInRestaurant(restaurantName);

        verify(menuService, times(1)).viewPlatesInRestaurant(restaurantName);
    }

    @Test
    @DisplayName("Test constructor de MenuController con MenuService")
    void testMenuControllerConstructor() {
        MenuController menuController = new MenuController();

        assertNotNull(menuController);

    }
}
