package cis2039.pocketbeasts.interfaces;

/**
 * <h2>Subject</h2>
 * Subject interface for the Observer Design Pattern.
 * <p>
 * This interface contains methods for registering, removing and notifying
 * observers of events.
 * <p>
 * This interface is part of the Observer Design Pattern.
 *
 * @author Rhys Kemp
 * @see Observer
 */
public interface Subject {
    /**
     * Registers an observer with the subject.
     *
     * @param observer The observer to register.
     */
    void registerObserver(Observer observer);

    /**
     * Removes an observer from the subject.
     *
     * @param observer The observer to remove.
     */
    void removeObserver(Observer observer);

    /**
     * Notifies all observers of an event.
     *
     * @param event The event to notify the observers of.
     */
    void notifyObservers(String event);

    /**
     * Overloaded method to allow passing an object to the observers
     *
     * @param event The event to notify the observers of.
     */
    void notifyObservers(String event, Object... object);


}
