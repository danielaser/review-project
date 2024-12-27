package org.example.controllers;

import org.example.models.Plate;
import org.example.services.PlateService;

public class PlateController {

    private PlateService plateService;

    public PlateController(PlateService plateService) {
        this.plateService = plateService;
    }

    public void addPlateToRestaurant(String restaurantName, Plate plate) {
        plateService.addRestaurantPlate(restaurantName, plate);
    }

    public void deletePlateToRestaurant(String restaurantName, Plate plato) {
        plateService.deleteRestaurantPlate(restaurantName, plato);
    }
}
