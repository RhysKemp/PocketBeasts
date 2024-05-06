package cis2039.pocketbeasts.abstracts;

import cis2039.pocketbeasts.interfaces.ICard;
import cis2039.pocketbeasts.models.Card;

import java.util.ArrayList;

/**
 * AbstractCardGroup class
 * <p>
 * Abstract class for card groups
 *
 * @version 0.1.0
 *
 * @author Rhys Kemp
 *
 */

public abstract class AbstractCardGroup {

    protected final ArrayList<ICard> cards;

    /**
     * Constructor for AbstractCardGroup
     */
    public AbstractCardGroup() {
        this.cards = new ArrayList<>();
    }

    /**
     * Get the cards in the group
     *
     * @return ArrayList<Card> cards
     */
    public ArrayList<ICard> getCards() {
        return this.cards;
    }

    /**
     * Add a card to the group
     *
     * @param card Card to add
     */
    public void add(ICard card) {
        this.cards.add(card);
    }

    /**
     * Remove a card from the group
     *
     * @param card Card to remove
     */
    public void remove(ICard card) {
        this.cards.remove(card);
    }

    /**
     * Remove all cards from the group
     *
     * @param cards ArrayList<Card> cards to remove
     */
    public void removeAll(ArrayList<ICard> cards) {
        this.cards.removeAll(cards);
    }

    /**
     * Get the number of cards in the group
     *
     * @return int number of cards
     */
    public int count() {
        return this.cards.size();
    }

    /**
     * Checks if the deck is empty.
     *
     * @return Boolean - True if the deck is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.cards.isEmpty();
    }

}
