package org.example.command;

import org.example.controllers.RestaurantController;
import org.example.models.Restaurant;

import java.util.Map;
import java.util.Set;

public class GetRestaurantsCommand implements ICommand{

    private RestaurantController restaurantController;

    public GetRestaurantsCommand(RestaurantController restaurantController) {
        this.restaurantController = restaurantController;
    }

    @Override
    public void execute() {
        Map<String, Restaurant> restaurantMap = restaurantController.getRestaurants();
        Set<String> restaurantNames = restaurantMap.keySet();

        System.out.println("Lista de restaurantes disponibles:");
        for (String name : restaurantNames) {
            System.out.println("- " + name);
        }
    }
}
