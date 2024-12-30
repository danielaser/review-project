package org.example.models;

import org.example.observer.IObservable;
import org.example.observer.IObserver;

import java.util.ArrayList;
import java.util.List;

public class Plate implements IObservable {
    private String plateName;
    private Double price;
    private List<IObserver> observers;

    public Plate(String plateName, Double price) {
        this.plateName = plateName;
        this.price = price;
        this.observers = new ArrayList<>();
    }

    public Plate() {
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
        notifyObservers("Calificacion promedio del plato " + plateName + " actualizada a: " + newAverageRating);
    }

    // getters and setters
    public String getPlateName() {
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
