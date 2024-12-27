package org.example.command;

import org.example.controllers.RestaurantController;

public class DeleteRestaurantCommand implements ICommand{
    private RestaurantController restaurantController;

    public DeleteRestaurantCommand(RestaurantController restaurantController) {
        this.restaurantController = restaurantController;
    }

    @Override
    public void execute() {
        restaurantController.deleteRestaurant("Nuevo Restaurante");
        System.out.println("Restaurante eliminado.");
    }
}
