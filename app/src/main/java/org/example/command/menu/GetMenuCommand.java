package org.example.command.menu;

import org.example.command.ICommand;
import org.example.command.utils.IHandler;
import org.example.controllers.MenuController;

public class GetMenuCommand implements ICommand {
    private MenuController menuController;
    private final IHandler handler;

    public GetMenuCommand(MenuController menuController, IHandler handler) {
        this.menuController = menuController;
        this.handler = handler;
    }

    @Override
    public void execute() {
        handler.writeLine("Ingrese el nombre del restaurante para ver el menu: ");
        String restaurantName = handler.readLine();

        menuController.getMenuInRestaurant(restaurantName);
    }
}
