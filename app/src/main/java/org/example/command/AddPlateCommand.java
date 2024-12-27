package org.example.command;

import org.example.controllers.PlateController;
import org.example.models.Plate;

public class AddPlateCommand implements ICommand{
    private PlateController plateController;

    public AddPlateCommand(PlateController plateController) {
        this.plateController = plateController;
    }

    @Override
    public void execute() {
        Plate plate = new Plate("Plato Especial", 10.5);
        plateController.addPlateToRestaurant("Nuevo Restaurante", plate);
        System.out.println("Plato agregado.");
    }
}
