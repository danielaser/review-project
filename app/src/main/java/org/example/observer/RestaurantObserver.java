package org.example.observer;

public class RestaurantObserver implements IObserver {
    @Override
    public void update() {
        System.out.println("Notificacion: La calificacion de un restaurante ha cambiado.");
    }
}
