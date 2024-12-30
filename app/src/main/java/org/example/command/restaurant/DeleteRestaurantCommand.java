package org.example.command.restaurant;

import org.example.command.ICommand;
import org.example.controllers.RestaurantController;
import org.example.models.Restaurant;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DeleteRestaurantCommand implements ICommand {
    private RestaurantController restaurantController;

    public DeleteRestaurantCommand(RestaurantController restaurantController) {
        this.restaurantController = restaurantController;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Restaurant> restaurantMap = restaurantController.getRestaurants();

        Set<String> restaurantNames = restaurantMap.keySet();

        System.out.println("Lista de restaurantes:");
        for (String name : restaurantNames) {
            System.out.println("- " + name);
        }

        System.out.print("Ingrese el nombre del restaurante que desea borrar: ");
        String name = scanner.nextLine();

        restaurantController.deleteRestaurant(name);
    }
}
