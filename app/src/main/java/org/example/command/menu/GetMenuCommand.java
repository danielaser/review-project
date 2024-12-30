package org.example.command.menu;

import org.example.command.ICommand;
import org.example.controllers.MenuController;

import java.util.Scanner;

public class GetMenuCommand implements ICommand {

    private MenuController menuController;

    public GetMenuCommand(MenuController menuController) {
        this.menuController = menuController;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del restaurante para ver el menu: ");
        String restaurantName = scanner.nextLine();

        menuController.getMenuInRestaurant(restaurantName);
    }
}
