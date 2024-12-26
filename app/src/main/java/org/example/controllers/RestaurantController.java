package org.example.controllers;

import org.example.models.Restaurant;
import org.example.services.RestaurantService;

public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController() {
        this.restaurantService = new RestaurantService();
    }

    public void addRestaurant(String name, String address, String city) {
        restaurantService.addRestaurant(name, address, city);
        System.out.println("El restaurante se ha agregado exitosamente.");
    }

    public void deleteRestaurant(String name) {
        restaurantService.deleteRestaurant(name);
    }
}
