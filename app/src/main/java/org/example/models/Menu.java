package org.example.models;

import java.util.HashSet;
import java.util.Set;

public class Menu {
    private Restaurant restaurant;
    private Set<Plate> plates;

    public Menu(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.plates = new HashSet<>();
    }

    public Menu() {
        this.plates = new HashSet<>();
    }

    public void addPlate(Plate plate) {
        plates.add(plate);
    }

    public void deletePlate(String plateName) {
        boolean removed = plates.removeIf(plate -> plate.getPlateName().equals(plateName));
        if (removed) {
            System.out.println("Plato " + plateName + " eliminado del menu.");
        } else {
            System.out.println("Plato " + plateName + " no encontrado en el menu.");
        }
    }

    public Plate getPlateByName(String plateName) {
        for (Plate plate : plates) {
            if (plate.getPlateName().equals(plateName)) {
                return plate;
            }
        }
        return null;
    }

    public Set<Plate> getPlates() {
        return plates;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
