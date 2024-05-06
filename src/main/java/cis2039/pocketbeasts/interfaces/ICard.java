package cis2039.pocketbeasts.interfaces;


/**
 * ICard interface represents a card in the game.
 * It provides methods to get card's properties and manipulate its state.
 *
 * @author Rhys Kemp
 */
public interface ICard extends Comparable<ICard> {
    /**
     * Gets the card's unique identifier.
     */
    String getId();

    /**
     * Gets the card's name.
     */
    String getName();

    /**
     * Gets the card's mana cost.
     */
    int getManaCost();

    /**
     * Gets the card's attack value.
     */
    int getAttack();

    /**
     * Gets the card's health value.
     */
    int getHealth();

    /**
     * Reduces the card's health by the specified amount.
     *
     * @param amount the amount to reduce the health by
     */
    void damage(int amount);

    /**
     * Checks if the card is dead.
     */
    boolean isDead();
}
