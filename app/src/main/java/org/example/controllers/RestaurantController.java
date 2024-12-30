package org.example.controllers;

import org.example.models.Restaurant;
import org.example.services.RestaurantService;

import java.util.Map;

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
        System.out.println("El restaurante se ha eliminado.");
    }

    public void editRestaurant(String currentName, String newName, String newAddress, String newCity) {
        boolean success = restaurantService.editRestaurant(currentName, newName, newAddress, newCity);
        System.out.println("La informacion del restaurante se ha actualizado.");
        if (!success) {
            System.out.println("No se pudo editar el restaurante.");
        }
    }

    public Map<String, Restaurant> getRestaurants(){
       return restaurantService.getRestaurants();
    }
}
