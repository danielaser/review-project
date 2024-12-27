package org.example.observer;

public class EntityObserver implements IObserver{
    private final String entityName;

    public EntityObserver(String entityName) {
        this.entityName = entityName;
    }
    @Override
    public void notify(String message) {
        System.out.println(message);
    }
}
