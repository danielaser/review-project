package org.example.command.menu;

import org.example.command.ICommand;
import org.example.command.utils.IHandler;
import org.example.controllers.MenuController;


public class EditMenuCommand implements ICommand {
    private MenuController menuController;
    private final IHandler handler;

    public EditMenuCommand(MenuController menuController, IHandler handler) {
        this.menuController = menuController;
        this.handler = handler;
    }

    @Override
    public void execute() {

        handler.writeLine("Ingrese el nombre del restaurante del cual desea editar su menu: ");
        String restaurantName = handler.readLine();

        handler.writeLine("Ingrese el nombre del plato que desea editar: ");
        String plateName = handler.readLine();

        handler.writeLine("Ingrese el nuevo nombre del plato: ");
        String newPlateName = handler.readLine();

        handler.writeLine("Ingrese el nuevo precio del plato: ");
        Double newPrice = Double.parseDouble(handler.readLine());

        menuController.editPlateToRestaurant(restaurantName, plateName, newPlateName, newPrice);
    }
}
