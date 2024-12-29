package org.example.models;

import org.example.observer.IObserver;

public class User implements IObserver {
    private String userName;

    public User(String userName) {
        this.userName = userName;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void update(String message) {
        System.out.println("Cliente " + userName + " recibio una notificacion: " + message);
    }
}
