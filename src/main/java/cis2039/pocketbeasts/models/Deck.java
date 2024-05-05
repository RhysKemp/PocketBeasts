/*
 * This file is part of PocketBeasts.
 *
 * PocketBeasts is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PocketBeasts is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <https://www.gnu.org/licenses/>.
 */
package cis2039.pocketbeasts.models;

import cis2039.pocketbeasts.abstracts.AbstractCardGroup;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a deck of cards in a game of Pocket Beasts.
 *
 * @author Steven Mead
 * @author Chris Curry
 * @author Rhys Kemp
 * @see Card
 * @see Player
 */
public class Deck extends AbstractCardGroup {

    /**
     * Constructor for the Deck class.
     *
     * @param cards ArrayList<Card> - The cards to add to the deck.
     */
    public Deck(ArrayList<Card> cards) {
        this.cards.addAll(cards);
    }


    /**
     * Draws a card from the deck.
     *
     * @return Card - The card drawn from the deck.
     * @throws IllegalStateException if the deck is empty.
     */
    public Card draw() {
        if (this.cards.isEmpty()) {
            throw new IllegalStateException("The deck is empty.");
        }
        Card card = this.cards.get(0);
        this.cards.remove(0);
        return card;
    }

    /**
     * Shuffles the deck.
     */
    public void shuffle() {
        Collections.shuffle(this.cards);
    }


}
