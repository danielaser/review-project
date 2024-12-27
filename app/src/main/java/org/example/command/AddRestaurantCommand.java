package org.example.command;

import org.example.controllers.RestaurantController;

import java.util.Scanner;

public class AddRestaurantCommand implements ICommand {

    private RestaurantController restaurantController;

    public AddRestaurantCommand(RestaurantController restaurantController) {
        this.restaurantController = restaurantController;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del restaurante: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese la direccion: ");
        String address = scanner.nextLine();
        System.out.print("Ingrese la ciudad: ");
        String city = scanner.nextLine();

        restaurantController.addRestaurant(name, address, city);
    }
}
