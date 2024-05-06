package cis2039.pocketbeasts.gameengine;

import cis2039.pocketbeasts.interfaces.Observer;
import cis2039.pocketbeasts.interfaces.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * <h2>GameEventNotifier</h2>
 * Notifies observers of game events.
 * <p>
 * This class contains methods for adding, removing and notifying
 * observers of game events.
 * <p>
 * This class is part of the Observer Design Pattern.
 *
 * @author Rhys Kemp
 * @see Observer
 */
public class GameEventNotifier implements Subject {
    private final List<Observer> observers = new ArrayList<>();

    /**
     * Adds an observer to the list of observers.
     *
     * @param observer The observer to add.
     */
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the list of observers.
     *
     * @param observer The observer to remove.
     */
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers of an event.
     *
     * @param event The event to notify the observers of.
     */
    public void notifyObservers(String event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }

    /**
     * Overloaded method to allow passing an object to the observers
     *
     * @param event The event to notify the observers of.
     */
    public void notifyObservers(String event, Object... object) {
        for (Observer observer : observers) {
            observer.update(event, object);
        }
    }
}
