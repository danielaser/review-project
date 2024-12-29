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
        menus.put(menu.getRestaurant().getRestaurantName(), menu);
    }

    public Menu getMenu(String name) {
        return menus.get(name);
    }

    public void deletePlateFromMenu(Restaurant restaurant, String plateName) {
        Menu menu = menus.get(restaurant);
        if (menu != null) menu.deletePlate(plateName);
    }

    public boolean editPlateInMenu(String restaurantName, String plateName, String newPlateName, Double newPrice) {
        Plate plate = menus.getOrDefault(restaurantName, new Menu()).getPlateByName(plateName);
        if (plate != null) {
            plate.setPlateName(newPlateName);
            plate.setPrice(newPrice);
            return true;
        }
        return false;
    }

    public Set<Plate> getPlatesByRestaurantName(String restaurantName) {
        Menu menu = menus.get(restaurantName);
        return menu != null ? menu.getPlates() : new HashSet<>();
    }
}
