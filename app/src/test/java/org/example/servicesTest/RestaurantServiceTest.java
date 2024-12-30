package org.example.servicesTest;

import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;
import org.example.services.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.ArgumentCaptor;

import java.util.Map;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantService = new RestaurantService(restaurantRepository, menuRepository);
    }

    @Test
    @DisplayName("Test addRestaurant: should add a restaurant and its menu")
    void testAddRestaurant() {
        String name = "Restaurante A";
        String address = "123 Test St";
        String city = "Test City";

        Restaurant restaurant = mock(Restaurant.class);
        Menu menu = mock(Menu.class);

        restaurantRepository = mock(RestaurantRepository.class);
        menuRepository = mock(MenuRepository.class);

        when(restaurantRepository.getRestaurant(name)).thenReturn(null);
        when(restaurant.getMenu()).thenReturn(menu);
        when(restaurant.getRestaurantName()).thenReturn(name);

        restaurantService = new RestaurantService();
        restaurantService.restaurantRepository = restaurantRepository;
        restaurantService.menuRepository = menuRepository;

        restaurantService.addRestaurant(name, address, city);

        verify(restaurantRepository, times(1)).addRestaurant(any(Restaurant.class));

        ArgumentCaptor<Menu> menuCaptor = ArgumentCaptor.forClass(Menu.class);
        verify(menuRepository, times(1)).addMenu(menuCaptor.capture());

        Menu capturedMenu = menuCaptor.getValue();
        assertNotNull(capturedMenu);

        assertEquals(name, capturedMenu.getRestaurant().getRestaurantName());
        assertEquals(address, capturedMenu.getRestaurant().getAddress());
        assertEquals(city, capturedMenu.getRestaurant().getCity());
    }

    @Test
    @DisplayName("Test deleteRestaurant: should delete the restaurant")
    void testDeleteRestaurant() {
        String restaurantName = "Restaurante B";

        Restaurant restaurant = mock(Restaurant.class);
        when(restaurantRepository.getRestaurant(restaurantName)).thenReturn(restaurant);

        restaurantService.deleteRestaurant(restaurantName);

        verify(restaurant, times(1)).removeAllObservers();
        verify(restaurantRepository, times(1)).deleteRestaurant(restaurantName);
    }

    @Test
    @DisplayName("Test editRestaurant: should update the restaurant details and name")
    void testEditRestaurant() {
        String currentName = "Restaurante C";
        String newName = "Restaurante C Modificado";
        String newAddress = "456 New St";
        String newCity = "New City";

        Restaurant restaurant = mock(Restaurant.class);
        when(restaurantRepository.getRestaurant(currentName)).thenReturn(restaurant);

        boolean result = restaurantService.editRestaurant(currentName, newName, newAddress, newCity);

        verify(restaurant, times(1)).setRestaurantName(newName);
        verify(restaurant, times(1)).setAddress(newAddress);
        verify(restaurant, times(1)).setCity(newCity);

        verify(restaurantRepository, times(1)).deleteRestaurant(currentName);
        verify(restaurantRepository, times(1)).addRestaurant(restaurant);

        assertTrue(result);
    }

    @Test
    @DisplayName("Test editRestaurant: should not update if restaurant does not exist")
    void testEditRestaurantNotFound() {
        String currentName = "Restaurante No Existente";
        String newName = "Nuevo Restaurante";
        String newAddress = "789 Nonexistent St";
        String newCity = "Unknown City";

        when(restaurantRepository.getRestaurant(currentName)).thenReturn(null);

        boolean result = restaurantService.editRestaurant(currentName, newName, newAddress, newCity);

        assertFalse(result);
        verify(restaurantRepository, times(0)).deleteRestaurant(currentName);
        verify(restaurantRepository, times(0)).addRestaurant(any());
    }

    @Test
    @DisplayName("Test getRestaurants: should return the list of all restaurants")
    void testGetRestaurants() {

        Restaurant restaurant1 = mock(Restaurant.class);
        Restaurant restaurant2 = mock(Restaurant.class);
        when(restaurantRepository.getRestaurants()).thenReturn(Map.of(
                "Restaurante 1", restaurant1,
                "Restaurante 2", restaurant2
        ));

        Map<String, Restaurant> result = restaurantService.getRestaurants();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.containsKey("Restaurante 1"));
        assertTrue(result.containsKey("Restaurante 2"));
    }
}

