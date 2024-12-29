package org.example.services;

import org.example.models.Restaurant;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;

import java.util.Map;

public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    public RestaurantService() {
        this.restaurantRepository = RestaurantRepository.getInstance();
        this.menuRepository = MenuRepository.getInstance();
    }

    public void addRestaurant(String name, String address, String city) {
        Restaurant restaurant = new Restaurant(name, address, city);
        restaurantRepository.addRestaurant(restaurant);
        if (restaurant.getMenu() == null) {
            System.out.println("Error al crear el menú del restaurante");
        } else {
            menuRepository.addMenu(restaurant.getMenu());
            System.out.println("Menú asociado al restaurante " + name);
        }
    }

    public void deleteRestaurant(String name) {
        restaurantRepository.deleteRestaurant(name);
    }

    public boolean editRestaurant(String currentName, String newName, String newAddress, String newCity) {
        Restaurant restaurant = restaurantRepository.getRestaurant(currentName);

        if (restaurant == null) {
            System.out.println("Restaurante no encontrado.");
            return false;
        }
        restaurant.setRestaurantName(newName);
        restaurant.setAddress(newAddress);
        restaurant.setCity(newCity);

        if (!currentName.equals(newName)) {
            restaurantRepository.deleteRestaurant(currentName);
            restaurantRepository.addRestaurant(restaurant);
        }
        System.out.println("Restaurante editado con exito.");
        return true;
    }

    public Map<String, Restaurant> getRestaurants(){
      return restaurantRepository.getRestaurants();
    }
}
