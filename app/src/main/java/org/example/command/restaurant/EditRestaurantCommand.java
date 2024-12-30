package org.example.command.restaurant;

import org.example.command.ICommand;
import org.example.controllers.RestaurantController;
import org.example.models.Restaurant;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class EditRestaurantCommand implements ICommand {
    private RestaurantController restaurantController;

    public EditRestaurantCommand(RestaurantController restaurantController) {
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

        System.out.print("Ingrese el nombre del restaurante que desea editar: ");
        String currentName = scanner.nextLine();

        System.out.print("Ingrese el nuevo nombre del restaurante: ");
        String newName = scanner.nextLine();

        System.out.print("Ingrese la nueva direccion: ");
        String newAddress = scanner.nextLine();

        System.out.print("Ingrese la ciudad actualizada ");
        String newCity = scanner.nextLine();

        restaurantController.editRestaurant(currentName, newName, newAddress, newCity);
    }
}
