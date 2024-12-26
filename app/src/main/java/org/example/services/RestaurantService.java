package org.example.services;

import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;

public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    public RestaurantService() {
        this.restaurantRepository = restaurantRepository.getInstance();
    }

    public void addRestaurant(String name, String address, String city) {
        Restaurant restaurant = new Restaurant(name, address, city);
        restaurantRepository.addRestaurant(restaurant);
    }

    public void deleteRestaurant(String name) {
        restaurantRepository.deleteRestaurant(name);
    }
}
