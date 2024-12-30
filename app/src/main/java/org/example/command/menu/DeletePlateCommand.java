package org.example.command.menu;

import org.example.command.ICommand;
import org.example.command.utils.IHandler;
import org.example.controllers.MenuController;

public class DeletePlateCommand implements ICommand {
    private MenuController menuController;
    private final IHandler handler;

    public DeletePlateCommand(MenuController menuController, IHandler handler) {
        this.menuController = menuController;
        this.handler = handler;
    }

    @Override
    public void execute() {
        handler.writeLine("Ingrese el nombre del restaurante al que pertenece el plato: ");
        String restaurantName = handler.readLine();
        handler.writeLine("Ingrese el nombre del plato que desea eliminar: ");
        String plateName = handler.readLine();

        menuController.deletePlateToRestaurant(restaurantName, plateName);
    }
}
