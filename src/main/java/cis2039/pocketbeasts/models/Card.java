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

import cis2039.pocketbeasts.interfaces.Attackable;
import cis2039.pocketbeasts.interfaces.ICard;

import java.util.List;

/**
 * Represents a card in a game of Pocket Beasts.
 *
 * @author Steven Mead
 * @author Chris Curry
 * @author Rhys Kemp
 * @see Attackable
 * @see Comparable
 */
public class Card implements ICard, Attackable {

    private final String id;
    private final String name;
    private final int manaCost;
    private final int attack;
    private int health;

    /**
     * Constructor for the Card class.
     *
     * @param id The card's unique identifier.
     * @param name The card's name.
     * @param manaCost The card's mana cost.
     * @param attack The card's attack value.
     * @param health The card's health value.
     */
    public Card(String id, String name, int manaCost, int attack, int health) {
        this.id = id;
        this.name = name;
        this.manaCost = manaCost;
        this.attack = attack;
        this.health = health;
    }

    /**
     * Copy constructor.
     *
     * @param card The card to copy.
     */
    public Card(Card card) {
        this.id = card.id;
        this.name = card.name;
        this.manaCost = card.manaCost;
        this.attack = card.attack;
        this.health = card.health;
    }

    /**
     * Gets the card's unique identifier.
     *
     * @return String {@code id} - The card's unique identifier.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Gets the card's name.
     *
     * @return String {@code name} - The card's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the card's mana cost.
     *
     * @return int {@code manaCost} - The card's mana cost.
     */
    public int getManaCost() {
        return this.manaCost;
    }

    /**
     * Gets the card's attack value.
     *
     * @return int {@code attack} - The card's attack value.
     */
    public int getAttack() {
        return this.attack;
    }

    /**
     * Sets the card's health value.
     *
     * @return int health - The card's new health value.
     */
    @Override
    public int getHealth() {
        return this.health;
    }


    /**
     * Reduces the health of the card by the specified amount.
     *
     * @param amount the amount to reduce the health by
     */
    @Override
    public void damage(int amount) {
        if (amount < 0) { // Defensive programming
            throw new IllegalArgumentException("Damage amount must be positive.");
        }
        this.health -= amount;
    }

    /**
     * Checks if the card is dead.
     *
     * @return boolean {@code true} if the card is dead, {@code false} otherwise.
     */
    @Override
    public boolean isDead() {
        return health <= 0;
    }

    @Override
    public List<ICard> getCardsInPlay() {
        return List.of();
    }

    /**
     * Returns a string representation of the card.
     *
     * @return String representation of the card.
     */
    @Override
    public String toString() {
        return this.name + " (" + this.id + ") Mana Cost/" + this.manaCost + 
                " Attack/" + this.attack + " Health/" + this.health;
    }

    /**
     * Compares this card to another card based on mana cost.
     *
     * @param o the card to compare to
     * @return int a negative integer, zero, or a positive integer as this card is less than, equal to, or greater than the specified card
     */
    @Override
    public int compareTo(ICard o) {
        return Integer.compare(this.getManaCost(), o.getManaCost());
    }
}
