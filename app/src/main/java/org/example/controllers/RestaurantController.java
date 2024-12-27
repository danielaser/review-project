package org.example.controllers;

import org.example.models.Restaurant;
import org.example.services.RestaurantService;

public class RestaurantController {

    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    public void addRestaurant(String name, String address, String city) {
        Restaurant restaurant = new Restaurant(name, address, city);
        restaurantService.addRestaurant(restaurant);
    }

    public void deleteRestaurant(String name) {
        restaurantService.deleteRestaurant(name);
    }
}
