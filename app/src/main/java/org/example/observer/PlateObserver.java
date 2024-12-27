package org.example.observer;

public class PlateObserver implements IObserver{
    @Override
    public void update() {
        System.out.println("Notificacion: La calificacion de un plato ha cambiado.");
    }
}
