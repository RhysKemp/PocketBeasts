package cis2039.pocketbeasts.interfaces;

public interface Attackable {

    /**
     * Reduce the health of the object by the given amount
     *
     * @param amount the amount to reduce the health by
     */
    void damage(int amount);

    /**
     * Checks if the object's health is <= 0
     *
     * @return Boolean - true if the object is dead, false otherwise
     */
    boolean isDead();

    /**
     * Get the object's current health
     *
     * @return Int health - the object's current health
     */
    int getHealth();
}
