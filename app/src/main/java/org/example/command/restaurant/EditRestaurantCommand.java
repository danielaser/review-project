package org.example.command.restaurant;

import org.example.command.ICommand;
import org.example.command.utils.IHandler;
import org.example.controllers.RestaurantController;
import org.example.models.Restaurant;

import java.util.Map;
import java.util.Set;

public class EditRestaurantCommand implements ICommand {
    private RestaurantController restaurantController;
    private final IHandler handler;

    public EditRestaurantCommand(RestaurantController restaurantController,  IHandler handler) {
        this.restaurantController = restaurantController;
        this.handler = handler;
    }

    @Override
    public void execute() {
        Map<String, Restaurant> restaurantMap = restaurantController.getRestaurants();
        Set<String> restaurantNames = restaurantMap.keySet();

        handler.writeLine("Lista de restaurantes:");
        for (String name : restaurantNames) {
            handler.writeLine("- " + name);
        }

        handler.writeLine("Ingrese el nombre del restaurante que desea editar: ");
        String currentName = handler.readLine();

        handler.writeLine("Ingrese el nuevo nombre del restaurante: ");
        String newName = handler.readLine();

        handler.writeLine("Ingrese la nueva direccion: ");
        String newAddress = handler.readLine();

        handler.writeLine("Ingrese la ciudad actualizada ");
        String newCity = handler.readLine();

        restaurantController.editRestaurant(currentName, newName, newAddress, newCity);
    }
}
