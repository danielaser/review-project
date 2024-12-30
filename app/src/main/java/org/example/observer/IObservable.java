package org.example.observer;

public interface IObservable {
    void addObserver(IObserver observer);
    void removeAllObservers();
    void notifyObservers(String message);
}