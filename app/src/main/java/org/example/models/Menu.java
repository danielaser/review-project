package org.example.models;

import java.util.HashSet;
import java.util.Set;

public class Menu {
    private Restaurant restaurant;
    private Set<Plate> plates;

    public Menu() {
        this.plates = new HashSet<>();
    }

    public void addPlate(Plate plate) {
        plates.add(plate);
    }

    public void deletePlate(Plate plate) {
        plates.remove(plate);
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
