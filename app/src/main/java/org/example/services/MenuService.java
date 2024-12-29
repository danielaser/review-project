package org.example.services;

import org.example.models.Menu;
import org.example.models.Plate;
import org.example.models.Restaurant;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;

import java.util.Set;

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
    public boolean editRestaurantPlate(String restaurantName, String plateName, String newPlateName, Double newPrice) {
        Restaurant restaurant = restaurantRepository.getRestaurant(restaurantName);
        if (restaurant == null) {
            System.out.println("Restaurante no encontrado.");
            return false;
        }
        return menuRepository.editPlateInMenu(restaurantName, plateName, newPlateName, newPrice);
    }

    public void viewPlatesInRestaurant(String restaurantName) {
        Set<Plate> plates = menuRepository.getPlatesByRestaurantName(restaurantName);
        if (plates.isEmpty()) {
            System.out.println("No hay platos en el menú del restaurante " + restaurantName);
        } else {
            System.out.println("Platos en el menu de " + restaurantName + ":");
            for (Plate plate : plates) {
                System.out.println(plate.getPlateName() + " - $" + plate.getPrice());
            }
        }
    }
}
