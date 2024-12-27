package org.example.services;

import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;

public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurantRepository.addRestaurant(restaurant);
    }

    public void deleteRestaurant(String name) {
        restaurantRepository.deleteRestaurant(name);
    }
}
