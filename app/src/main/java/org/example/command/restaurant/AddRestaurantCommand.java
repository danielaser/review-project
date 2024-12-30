package org.example.command.restaurant;

import org.example.command.ICommand;
import org.example.command.utils.IHandler;
import org.example.controllers.RestaurantController;

public class AddRestaurantCommand implements ICommand {
    private RestaurantController restaurantController;
    private final IHandler handler;

    public AddRestaurantCommand(RestaurantController restaurantController, IHandler handler) {
        this.restaurantController = restaurantController;
        this.handler = handler;
    }

    @Override
    public void execute() {

        handler.writeLine("Ingrese el nombre del restaurante: ");
        String name = handler.readLine();

        handler.writeLine("Ingrese la direccion: ");
        String address = handler.readLine();

        handler.writeLine("Ingrese la ciudad: ");
        String city = handler.readLine();

        restaurantController.addRestaurant(name, address, city);
    }
}
