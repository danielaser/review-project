package org.example.repositories;

import org.example.models.Menu;
import org.example.models.Plate;
import org.example.models.Restaurant;

import java.util.HashMap;
import java.util.Map;

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

    public void deleteMenu(String name) {
        menus.remove(name);
    }
}
