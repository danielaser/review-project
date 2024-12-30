package org.example.services;

import org.example.models.Menu;
import org.example.models.Plate;
import org.example.models.Restaurant;
import org.example.observer.TheObserver;
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
        plate.addObserver(new TheObserver());

        restaurant = existRestaurant(restaurantName, restaurant);

        if (restaurant.getMenu() != null) {
            restaurant.getMenu().addPlate(plate);
            System.out.println("Plato " + plateName + " agregado al menu de " + restaurantName);
        } else System.out.println("Error: El restaurante no tiene un menu asociado.");
    }

    private Restaurant existRestaurant(String restaurantName, Restaurant restaurant) {
        if (restaurant == null) {
            System.out.println("Restaurante no encontrado. Creando nuevo restaurante...");
            restaurant = new Restaurant(restaurantName);
            restaurantRepository.addRestaurant(restaurant);
            Menu menu = new Menu(restaurant);
            menuRepository.addMenu(menu);
        }
        return restaurant;
    }

    public void deleteRestaurantPlate(String restaurantName, String plateName) {
        Restaurant restaurant = restaurantRepository.getRestaurant(restaurantName);
        if (restaurant != null) {
            Plate plate = restaurant.getMenu().getPlateByName(plateName);
            if (plate != null) {
                plate.removeAllObservers();
                menuRepository.deletePlateFromMenu(restaurant, plateName);
            } else System.out.println("Plato no encontrado en el menu del restaurante.");
        } else System.out.println("Restaurante no encontrado.");
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
        if (plates == null || plates.isEmpty()) {
            System.out.println("No hay platos en el menu del restaurante ");
        } else {
            System.out.println("Platos en el menu de " + restaurantName + ":");
            plates.forEach(plate -> System.out.println(plate.getPlateName() + " - $" + plate.getPrice()));
        }
    }
}
