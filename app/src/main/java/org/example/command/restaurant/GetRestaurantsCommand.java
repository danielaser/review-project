package org.example.command.restaurant;

import org.example.command.ICommand;
import org.example.controllers.RestaurantController;
import org.example.models.Restaurant;

import java.util.Map;

public class GetRestaurantsCommand implements ICommand {

    private RestaurantController restaurantController;

    public GetRestaurantsCommand(RestaurantController restaurantController) {
        this.restaurantController = restaurantController;
    }

    @Override
    public void execute() {
        Map<String, Restaurant> restaurantMap = restaurantController.getRestaurants();
        System.out.println("Lista de restaurantes disponibles:");
        for (Restaurant restaurant : restaurantMap.values()) {
            System.out.println("\nNombre: " + restaurant.getRestaurantName());
            System.out.println("Direccion: " + restaurant.getAddress());
            System.out.println("Ciudad: " + restaurant.getCity());
            System.out.println("-----------------------");
        }
    }
}
