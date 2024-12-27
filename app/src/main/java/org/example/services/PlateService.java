package org.example.services;

import org.example.models.Plate;
import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;

public class PlateService {
    private RestaurantRepository restaurantRepository;

    public PlateService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public void addRestaurantPlate(String restaurantName, Plate plato) {
        Restaurant restaurant = restaurantRepository.getRestaurant(restaurantName);
        if (restaurant != null) {
            restaurant.getMenu().addPlate(plato);
        }
    }

    public void deleteRestaurantPlate(String restaurantName, Plate plato) {
        Restaurant restaurant = restaurantRepository.getRestaurant(restaurantName);
        if (restaurant != null) {
            restaurant.getMenu().deletePlate(plato);
        }
    }
}
