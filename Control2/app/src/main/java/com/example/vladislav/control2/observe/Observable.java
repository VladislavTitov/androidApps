package com.example.vladislav.control2.observe;

import com.example.vladislav.control2.observe.Observer;

public interface Observable {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();

}
