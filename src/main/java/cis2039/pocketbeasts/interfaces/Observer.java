package cis2039.pocketbeasts.interfaces;

/**
 * <h2>Observer</h2>
 * <p>
 * Observer interface provides a method for updating observers.
 * <p>
 * This interface is part of the Observer Design Pattern.
 *
 * @author Rhys Kemp
 */
public interface Observer {

    /**
     * Updates the observer with an event.
     *
     * @param event The event to update the observer with.
     */
    void update(String event);

    /**
     * Overloaded method to allow passing of objects to the observer.
     *
     * @param event  The event to update the observer with.
     * @param object The object to update the observer with.
     */
    void update(String event, Object... object);
}
