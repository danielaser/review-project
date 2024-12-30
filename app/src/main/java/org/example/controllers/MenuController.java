package org.example.controllers;

import org.example.services.MenuService;

public class MenuController {

    private final MenuService menuService;

    public MenuController() {
        this.menuService = new MenuService();
    }

    public void addPlateToRestaurant(String restaurantName, String plateName, Double price) {
        menuService.addRestaurantPlate(restaurantName, plateName, price);
        System.out.println("El plato se ha agregado exitosamente.");
    }

    public void deletePlateToRestaurant(String restaurantName, String plateName) {
        menuService.deleteRestaurantPlate(restaurantName, plateName);
        System.out.println("El plato se ha eliminado.");
    }

    public void editPlateToRestaurant(String restaurantName, String plateName, String newPlateName, Double newPrice) {
        menuService.editRestaurantPlate(restaurantName, plateName, newPlateName, newPrice);
        System.out.println("El plato se ha actualizado.");
    }

    public void getMenuInRestaurant(String restaurantName){
        menuService.viewPlatesInRestaurant(restaurantName);
    }

}
