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
import cis2039.pocketbeasts.utils.Config;

/**
 * Represents a player in a game of Pocket Beasts.
 *
 * @author Steven Mead
 * @author Chris Curry
 * @author Rhys Kemp
 */
public class Player implements Attackable {

    private final String name;
    private final int id;
    private static int nextId = 1;
    private int health;
    private final int MAX_MANA;
    private int manaAvailable;
    private int manaTicker;


    private Deck deck;
    private final Hand hand;
    private final InPlay inPlay;
    private final Graveyard graveyard;

    /**
     * Default constructor for Player.
     * Initialises a new player with the given name.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.id = nextId++;
        this.health = Config.INITIAL_HEALTH;
        this.MAX_MANA = Config.MAX_MANA;
        this.manaAvailable = 0;
        this.manaTicker = 0;
        this.hand = new Hand();
        this.inPlay = new InPlay();
        this.graveyard = new Graveyard();
    }

    /**
     * Overloaded constructor for testing purposes.
     * Initialises a new player with the given name and deck.
     *
     * @param name The name of the player.
     * @param deck The deck of cards to give the player.
     */
    public Player(String name, Deck deck) {
        this.name = name;
        this.id = nextId++;
        this.health = Config.INITIAL_HEALTH;
        this.MAX_MANA = Config.MAX_MANA;
        this.manaAvailable = 0;
        this.manaTicker = 0;
        this.deck = deck;
        this.hand = new Hand();
        this.inPlay = new InPlay();
        this.graveyard = new Graveyard();
    }

    /**
     * Gets the player's name.
     *
     * @return String this.name - The player's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the player's ID.
     *
     * @return int this.id - The player's ID.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets the player's health.
     *
     * @return int this.health - The player's health.
     */
    @Override
    public int getHealth() {
        return this.health;
    }

    /**
     * Gets the player's deck.
     *
     * @return Deck this.deck - The player's deck.
     */
    public Deck getDeck() {
        return this.deck;
    }

    /**
     * Sets the player's deck.
     *
     * @param deck The deck to set for the player.
     */
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    /**
     * Gets the player's hand.
     *
     * @return Hand this.hand - The player's hand.
     */
    public Hand getHand() {
        return this.hand;
    }

    /**
     * Gets the player's in-play cards.
     *
     * @return InPlay this.inPlay - The player's in-play cards.
     */
    public InPlay getInPlay() {
        return this.inPlay;
    }

    /**
     * Gets the player's graveyard.
     *
     * @return Graveyard this.graveyard - The player's graveyard.
     */
    public Graveyard getGraveyard() {
        return this.graveyard;
    }

    /**
     * Gets mana available to the player.
     *
     * @return this.manaAvailable - The amount of mana available to the player.
     */
    public int getManaAvailable() {
        return this.manaAvailable;
    }

    /**
     * Sets mana available to the player.
     * This is used for testing purposes & potential expansion.
     *
     * @param mana The amount of mana available to the player.
     */
    public void setManaAvailable(int mana) {
        this.manaAvailable = mana;
    }


    /**
     * Adds mana to the player's mana pool.
     */
    public void addMana() {
        // This is passive regen, not applicable to other methods of mana gain
        if (this.manaTicker < this.MAX_MANA) {
            this.manaTicker++;
        }
        this.manaAvailable = manaTicker;
    }

    /**
     * Removes mana from the player's mana pool.
     *
     * @param amount The amount of mana to remove from the player's mana pool.
     */
    public void useMana(int amount) {
        // No validation for mana cost, as this is handled in the game loop
        this.manaAvailable -= amount;
    }

    /**
     * Damages the player by a given amount.
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
     * Checks if the player's health is <= 0.
     *
     * @return Boolean - true if the player is dead, false otherwise
     */
    @Override
    public boolean isDead() {
        return health <= 0;
    }

    /**
     * Provides a string representation of the player.
     *
     * @return String - A string representation of the player.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-9s HEALTH/%-5d MANA/%d\n", this.name, this.health, this.manaAvailable));

        for (int i=0; i<this.inPlay.count()+2; i++) {
            sb.append("+-------+ ");
        }
        sb.append("\n");

        for (int i=0; i<2; i++) {
            sb.append("|       | ");
        }
        for (int i=0; i<this.inPlay.count(); i++) {
            sb.append(String.format("|%7d| ", this.inPlay.getCard(i).getManaCost()));
        }
        sb.append("\n");

        sb.append("| DECK  | ");
        sb.append("| GRAVE | ");
        for (int i=0; i<this.inPlay.count(); i++) {
            sb.append(String.format("|  %-5s| ", this.inPlay.getCard(i).getId()));
        }
        sb.append("\n");

        sb.append(String.format("| %-6d| ", this.deck.count()));
        sb.append(String.format("| %-6d| ", this.graveyard.count()));
        for (int i=0; i<this.inPlay.count(); i++) {
            sb.append("|       | ");
        }
        sb.append("\n");

        for (int i=0; i<2; i++) {
            sb.append("|       | ");
        }
        for (int i=0; i<this.inPlay.count(); i++) {
            sb.append(String.format("|%-2d %4d| ", this.inPlay.getCard(i).getAttack(), this.inPlay.getCard(i).getHealth()));
        }
        sb.append("\n");

        for (int i=0; i<this.inPlay.count()+2; i++) {
            sb.append("+-------+ ");
        }
        sb.append("\n");
        sb.append(String.format("%d card(s) in hand.\n", this.hand.count()));
        sb.append("\n");

        sb.append(this.hand.toString());

        return sb.toString();
    }
}
