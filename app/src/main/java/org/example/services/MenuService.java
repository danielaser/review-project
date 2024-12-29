package org.example.services;

import org.example.models.Menu;
import org.example.models.Plate;
import org.example.models.Restaurant;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;

public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuService() {
        this.menuRepository = MenuRepository.getInstance();
        this.restaurantRepository = RestaurantRepository.getInstance();
    }

    public void addRestaurantPlate(String restaurantName, String plateName, Double price) {
        Restaurant restaurant = restaurantRepository.getRestaurant(restaurantName);
        Plate plate = new Plate(plateName, price);
        if (restaurant == null){
            restaurant = new Restaurant(restaurantName);
            restaurantRepository.addRestaurant(restaurant);
            menuRepository.addMenu(restaurant.getMenu());
        }
        restaurant.getMenu().addPlate(plate);
    }

    public void deleteRestaurantPlate(String restaurantName, String plateName) {
        Restaurant restaurant = restaurantRepository.getRestaurant(restaurantName);
        if (restaurant != null) {
            menuRepository.deletePlateFromMenu(restaurant, plateName);
        } else {
            System.out.println("Restaurante no encontrado.");
        }
    }
}
