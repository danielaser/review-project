package org.example.command.restaurant;

import org.example.command.ICommand;
import org.example.command.utils.IHandler;
import org.example.controllers.RestaurantController;
import org.example.models.Restaurant;

import java.util.Map;

public class GetRestaurantsCommand implements ICommand {
    private RestaurantController restaurantController;
    private final IHandler handler;

    public GetRestaurantsCommand(RestaurantController restaurantController, IHandler handler) {
        this.restaurantController = restaurantController;
        this.handler = handler;
    }

    @Override
    public void execute() {
        Map<String, Restaurant> restaurantMap = restaurantController.getRestaurants();
        handler.writeLine("Lista de restaurantes disponibles:");
        for (Restaurant restaurant : restaurantMap.values()) {
            handler.writeLine("\nNombre: " + restaurant.getRestaurantName());
            handler.writeLine("Direccion: " + restaurant.getAddress());
            handler.writeLine("Ciudad: " + restaurant.getCity());
            handler.writeLine("-----------------------");
        }
    }
}
