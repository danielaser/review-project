package org.example.repositories;

import org.example.models.Restaurant;

import java.util.HashMap;
import java.util.Map;

public class RestaurantRepository {

    private static RestaurantRepository instance;
    private Map<String, Restaurant> restaurants;

    private RestaurantRepository() {
        restaurants = new HashMap<>();
    }

    public static synchronized RestaurantRepository getInstance() {
        if (instance == null) {
            instance = new RestaurantRepository();
        }
        return instance;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.put(restaurant.getRestaurantName(), restaurant);
    }

    public void deleteRestaurant(String name) {
        restaurants.remove(name);
    }

    public Restaurant getRestaurant(String name) {
        return restaurants.get(name);
    }

    public Map<String, Restaurant> getRestaurants() {
        return restaurants;
    }
}
