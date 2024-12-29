package org.example.repositories;

import org.example.models.Menu;
import org.example.models.Plate;
import org.example.models.Restaurant;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MenuRepository {

    private static MenuRepository instance;
    private Map<String, Menu> menus;

    private MenuRepository() {
        menus = new HashMap<>();
    }

    public static synchronized MenuRepository getInstance() {
        if (instance == null) {
            instance = new MenuRepository();
        }
        return instance;
    }

    public void addMenu(Menu menu) {
        String restaurantName = menu.getRestaurant().getRestaurantName();
        if (!menus.containsKey(restaurantName)) {
            menus.put(restaurantName, menu);
            System.out.println("Menú agregado para el restaurante: " + restaurantName);
        } else {
            System.out.println("Ya existe un menú para el restaurante: " + restaurantName);
        }
    }

    public Menu getMenu(String name) {
        return menus.get(name);
    }

    public void deletePlateFromMenu(Restaurant restaurant, String plateName) {
        Menu menu = menus.get(restaurant.getRestaurantName());
        if (menu != null) {
            menu.deletePlate(plateName);
            System.out.println("Plato eliminado del menú de " + restaurant.getRestaurantName());
        } else {
            System.out.println("Menú no encontrado para el restaurante: " + restaurant.getRestaurantName());
        }
    }

    public boolean editPlateInMenu(String restaurantName, String plateName, String newPlateName, Double newPrice) {
        Menu menu = menus.get(restaurantName);
        if (menu != null) {
            boolean success = menu.editPlate(plateName, newPlateName, newPrice);
            if (success) {
                System.out.println("Plato actualizado: " + plateName + " a " + newPlateName);
            } else {
                System.out.println("Plato no encontrado en el menú de " + restaurantName);
            }
            return success;
        }
        System.out.println("Menú no encontrado para el restaurante: " + restaurantName);
        return false;
    }

    public Set<Plate> getPlatesByRestaurantName(String restaurantName) {
        Menu menu = menus.get(restaurantName);
        if (menu != null) {
            return menu.getPlates();
        } else {
            System.out.println("No hay menú para el restaurante: " + restaurantName);
            return new HashSet<>();
        }
    }
}
