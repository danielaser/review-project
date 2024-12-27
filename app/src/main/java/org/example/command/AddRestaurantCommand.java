package org.example.command;

import org.example.controllers.RestaurantController;

public class AddRestaurantCommand implements ICommand {

    private RestaurantController restaurantController;

    public AddRestaurantCommand(RestaurantController restaurantController) {
    }

    public void addRestaurantCommand(RestaurantController restaurantController) {
        this.restaurantController = restaurantController;
    }

    @Override
    public void execute() {
        restaurantController.addRestaurant("nuevo restaurante", "calle ", "lebrija");
        System.out.println("Restaurante creado.");
    }
}
