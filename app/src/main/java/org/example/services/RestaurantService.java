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
//        restaurant.addObserver(new EntityObserver("Restaurante"));
        restaurantRepository.addRestaurant(restaurant);
        menuRepository.addMenu(restaurant.getMenu());
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

        // Editar los atributos
        restaurant.setRestaurantName(newName);
        restaurant.setAddress(newAddress);
        restaurant.setCity(newCity);

        // Actualizar el repositorio si es necesario (por ejemplo, cambiar la clave)
        if (!currentName.equals(newName)) {
            restaurantRepository.deleteRestaurant(currentName); // Eliminar la antigua clave
            restaurantRepository.addRestaurant(restaurant); // Agregar con la nueva clave
        }

        System.out.println("Restaurante editado con éxito.");
        return true;
    }

    public Map<String, Restaurant> getRestaurants(){
      return restaurantRepository.getRestaurants();
    }
}
