package org.example.observer;

public class TheObserver implements IObserver{
    @Override
    public void update(String message) {
        System.out.println("Notificacion recibida: " + message);
    }
}
