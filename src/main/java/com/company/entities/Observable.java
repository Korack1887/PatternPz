package com.company.entities;

public interface Observable {
    void registerObserver(ObserverOrder o);
    void removeObserver(ObserverOrder o);
    void notifyObservers();
}
