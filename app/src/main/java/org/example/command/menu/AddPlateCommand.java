package org.example.command.menu;

import org.example.command.ICommand;
import org.example.controllers.MenuController;

import java.util.Scanner;

public class AddPlateCommand implements ICommand {

    private MenuController menuController;

    public AddPlateCommand(MenuController menuController) {
        this.menuController = menuController;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del restaurante: ");
        String restaurantName = scanner.nextLine();
        System.out.print("Ingrese el nombre del plato: ");
        String plateName = scanner.nextLine();
        System.out.print("Ingrese el valor: ");
        Double price = scanner.nextDouble();

        menuController.addPlateToRestaurant(restaurantName, plateName, price);
    }
}
