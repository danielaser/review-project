package org.example.controllers;

import org.example.models.Plate;
import org.example.services.MenuService;

public class MenuController {

    private final MenuService menuService;

    public MenuController() {
        this.menuService = new MenuService();
    }

    public void addPlateToRestaurant(String restaurantName, String plateName, Double price) {
        menuService.addRestaurantPlate(restaurantName, plateName, price);
    }

    public void deletePlateToRestaurant(String restaurantName, String plateName) {
        menuService.deleteRestaurantPlate(restaurantName, plateName);
    }

//    public void deletePlateToRestaurant(String plateName, Plate plato) {
//        menuService.deleteRestaurantPlate(restaurantName, plato);
//    }
}
