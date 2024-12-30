package org.example.models;

import org.example.observer.IObservable;
import org.example.observer.IObserver;

import java.util.ArrayList;
import java.util.List;

public class Restaurant implements IObservable {
    private String name;
    private String address;
    private String city;
    private Menu menu;
    private List<IObserver> observers;

    public Restaurant(String name, String address, String city) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.menu = new Menu(this);
        this.observers = new ArrayList<>();
    }

    public Restaurant() {
        this.menu = new Menu(this);
    }

    public Restaurant(String name) {
        this.name = name;
        this.menu = new Menu(this);
    }

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeAllObservers() {
        observers.clear();
        System.out.println("Todos los observadores han sido removidos.");
    }

    @Override
    public void notifyObservers(String message) {
        for (IObserver observer : observers) {
            observer.update(message);
        }
    }

    public void notifyAverageRatingChange(double newAverageRating) {
        notifyObservers("Calificacion promedio del restaurante " + name + " actualizada a: " + newAverageRating);
    }

    // getters and setters
    public String getRestaurantName() {
        return name;
    }

    public void setRestaurantName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Menu getMenu() {
        return menu;
    }

}
