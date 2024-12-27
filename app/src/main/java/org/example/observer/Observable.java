package org.example.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public abstract class Observable {
    private final List<IObserver> observers = new ArrayList<>();

    // Método para agregar observadores
    public void addObserver(IObserver observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }

    // Método para eliminar observadores
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // Notifica a todos los observadores
    public void notifyObservers(String message) {
        for (IObserver observer : observers) {
            observer.notify(message);  // Se pasa el mensaje sin redundancia
        }
    }
}