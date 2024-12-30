package org.example.command.menu;

import org.example.command.ICommand;
import org.example.command.utils.IHandler;
import org.example.controllers.MenuController;

public class AddPlateCommand implements ICommand {
    private MenuController menuController;
    private final IHandler handler;

    public AddPlateCommand(MenuController menuController, IHandler handler) {
        this.menuController = menuController;
        this.handler = handler;
    }

    @Override
    public void execute() {
        handler.writeLine("Ingrese el nombre del restaurante: ");
        String restaurantName = handler.readLine();
        handler.writeLine("Ingrese el nombre del plato: ");
        String plateName = handler.readLine();
        handler.writeLine("Ingrese el valor: ");
        Double price = Double.parseDouble(handler.readLine());

        menuController.addPlateToRestaurant(restaurantName, plateName, price);
    }
}
