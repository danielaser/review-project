package org.example.servicesTest;

import org.example.models.Menu;
import org.example.models.Plate;
import org.example.models.Restaurant;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;
import org.example.services.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.ArgumentCaptor;

class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private Restaurant restaurant;

    @Mock
    private Menu menu;

    @Mock
    private Plate plate;

    private MenuService menuService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        menuService = new MenuService();
        menuService.menuRepository = menuRepository;
        menuService.restaurantRepository = restaurantRepository;
    }

    @Test
    @DisplayName("Test addRestaurantPlate: should add a plate to the restaurant menu")
    void testAddRestaurantPlate() {
        String restaurantName = "Restaurante A";
        String plateName = "Plato 1";
        Double price = 12.5;

        Plate plate = new Plate(plateName, price);

        when(restaurantRepository.getRestaurant(restaurantName)).thenReturn(restaurant);
        when(restaurant.getMenu()).thenReturn(menu);

        menuService.addRestaurantPlate(restaurantName, plateName, price);

        ArgumentCaptor<Plate> plateCaptor = ArgumentCaptor.forClass(Plate.class);

        verify(menu, times(1)).addPlate(plateCaptor.capture());

        Plate capturedPlate = plateCaptor.getValue();
        assertEquals(plateName, capturedPlate.getPlateName());
        assertEquals(price, capturedPlate.getPrice());

        verify(restaurantRepository, times(1)).getRestaurant(restaurantName);

        verify(restaurant, times(1)).getMenu();

        verify(restaurantRepository, times(0)).addRestaurant(any(Restaurant.class));
        verify(menuRepository, times(0)).addMenu(any(Menu.class));
    }

    @Test
    @DisplayName("Test deleteRestaurantPlate: should remove a plate from the restaurant menu")
    void testDeleteRestaurantPlate() {
        String restaurantName = "Restaurante A";
        String plateName = "Plato 1";

        when(restaurantRepository.getRestaurant(restaurantName)).thenReturn(restaurant);
        when(restaurant.getMenu()).thenReturn(menu);
        when(menu.getPlateByName(plateName)).thenReturn(plate);

        menuService.deleteRestaurantPlate(restaurantName, plateName);

        verify(menuRepository, times(1)).deletePlateFromMenu(restaurant, plateName);
        verify(plate, times(1)).removeAllObservers();
    }

    @Test
    @DisplayName("Test deleteRestaurantPlate: should print error when plate not found")
    void testDeleteRestaurantPlateWhenPlateNotFound() {
        String restaurantName = "Restaurante A";
        String plateName = "Plato 1";

        when(restaurantRepository.getRestaurant(restaurantName)).thenReturn(restaurant);
        when(restaurant.getMenu()).thenReturn(menu);
        when(menu.getPlateByName(plateName)).thenReturn(null);

        menuService.deleteRestaurantPlate(restaurantName, plateName);

        verify(menuRepository, times(0)).deletePlateFromMenu(restaurant, plateName);
    }

    @Test
    @DisplayName("Test editRestaurantPlate: should edit an existing plate")
    void testEditRestaurantPlate() {
        String restaurantName = "Restaurante A";
        String plateName = "Plato 1";
        String newPlateName = "Plato 1 Modificado";
        Double newPrice = 15.0;

        when(restaurantRepository.getRestaurant(restaurantName)).thenReturn(restaurant);
        when(menuRepository.editPlateInMenu(restaurantName, plateName, newPlateName, newPrice)).thenReturn(true);

        boolean result = menuService.editRestaurantPlate(restaurantName, plateName, newPlateName, newPrice);

        assertTrue(result);
        verify(menuRepository, times(1)).editPlateInMenu(restaurantName, plateName, newPlateName, newPrice);
    }

    @Test
    @DisplayName("Test editRestaurantPlate: should return false if restaurant not found")
    void testEditRestaurantPlateWhenRestaurantNotFound() {
        String restaurantName = "Restaurante A";
        String plateName = "Plato 1";
        String newPlateName = "Plato 1 Modificado";
        Double newPrice = 15.0;

        when(restaurantRepository.getRestaurant(restaurantName)).thenReturn(null);

        boolean result = menuService.editRestaurantPlate(restaurantName, plateName, newPlateName, newPrice);

        assertFalse(result);
        verify(menuRepository, times(0)).editPlateInMenu(restaurantName, plateName, newPlateName, newPrice);
    }

    @Test
    @DisplayName("Test viewPlatesInRestaurant: should print plates in restaurant menu")
    void testViewPlatesInRestaurant() {
        String restaurantName = "Restaurante A";
        Set<Plate> plates = Set.of(new Plate("Plato 1", 12.5), new Plate("Plato 2", 15.0));

        when(menuRepository.getPlatesByRestaurantName(restaurantName)).thenReturn(plates);

        menuService.viewPlatesInRestaurant(restaurantName);

        verify(menuRepository, times(1)).getPlatesByRestaurantName(restaurantName);
    }

    @Test
    @DisplayName("Test viewPlatesInRestaurant: should print error message if no plates found")
    void testViewPlatesInRestaurantWhenNoPlatesFound() {
        String restaurantName = "Restaurante A";
        Set<Plate> plates = Set.of();

        when(menuRepository.getPlatesByRestaurantName(restaurantName)).thenReturn(plates);

        menuService.viewPlatesInRestaurant(restaurantName);

        verify(menuRepository, times(1)).getPlatesByRestaurantName(restaurantName);
    }
}

