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

/**
 * Represents a card in a game of Pocket Beasts.
 *
 * @author Steven Mead
 * @author Chris Curry
 * @author Rhys Kemp
 * @see Attackable
 * @see Comparable
 */
public class Card implements Attackable, Comparable<Card> {

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

    public String getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }

    public int getManaCost() {
        return this.manaCost;
    }
    
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


    @Override
    public void damage(int amount) {
        if (amount < 0) { // Defensive programming
            throw new IllegalArgumentException("Damage amount must be positive.");
        }
        this.health -= amount;
    }

    @Override
    public boolean isDead() {
        return health <= 0;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.id + ") Mana Cost/" + this.manaCost + 
                " Attack/" + this.attack + " Health/" + this.health;
    }

    @Override
    public int compareTo(Card o) {
        return Integer.compare(this.getManaCost(), o.getManaCost());
    }
}
