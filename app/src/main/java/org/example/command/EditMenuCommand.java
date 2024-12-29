package org.example.command;

import org.example.controllers.MenuController;
import org.example.models.Restaurant;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class EditMenuCommand implements ICommand{
    private MenuController menuController;

    public EditMenuCommand(MenuController menuController) {
        this.menuController = menuController;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del restaurante del cual desea editar su menu: ");
        String restaurantName = scanner.nextLine();

        System.out.print("Ingrese el nombre del plato que desea editar: ");
        String plateName = scanner.nextLine();

        System.out.print("Ingrese el nuevo nombre del plato: ");
        String newPlateName = scanner.nextLine();

        System.out.print("Ingrese el nuevo precio del plato: ");
        Double newPrice = scanner.nextDouble();

        menuController.editPlateToRestaurant(restaurantName, plateName, newPlateName, newPrice);
    }
}
