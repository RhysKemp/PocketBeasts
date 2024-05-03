package cis2039.pocketbeasts;

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

    protected final ArrayList<Card> cards;

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
    public ArrayList<Card> getCards() {
        return this.cards;
    }

    /**
     * Add a card to the group
     *
     * @param card Card to add
     */
    public void add(Card card) {
        this.cards.add(card);
    }

    /**
     * Remove a card from the group
     *
     * @param card Card to remove
     */
    public void remove(Card card) {
        this.cards.remove(card);
    }

    /**
     * Remove all cards from the group
     *
     * @param cards ArrayList<Card> cards to remove
     */
    public void removeAll(ArrayList<Card> cards) {
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

}
