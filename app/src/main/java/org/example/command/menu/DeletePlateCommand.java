package org.example.command.menu;

import org.example.command.ICommand;
import org.example.controllers.MenuController;

import java.util.Scanner;

public class DeletePlateCommand implements ICommand {
    private MenuController menuController;

    public DeletePlateCommand(MenuController menuController) {
        this.menuController = menuController;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del restaurante al que pertenece el plato: ");
        String restaurantName = scanner.nextLine();
        System.out.print("Ingrese el nombre del plato que desea eliminar: ");
        String plateName = scanner.nextLine();

        menuController.deletePlateToRestaurant(restaurantName, plateName);
    }
}
