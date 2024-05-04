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

    private String name;
    private int health;
    private final int MAX_MANA;
    private int manaAvailable;
    private int manaTicker;


    private Deck deck;
    private final Hand hand;
    private final InPlay inPlay;
    private final Graveyard graveyard;

    public Player(String name) {
        this.name = name;
        this.health = Config.INITIAL_HEALTH;
        this.MAX_MANA = Config.MAX_MANA;
        this.manaAvailable = 0;
        this.manaTicker = 0;
        this.hand = new Hand();
        this.inPlay = new InPlay();
        this.graveyard = new Graveyard();
    }

    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return this.health;
    }
    
    public Deck getDeck() {
        return this.deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
    
    public Hand getHand() {
        return this.hand;
    }

    public InPlay getInPlay() {
        return this.inPlay;
    }
    
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

    @Override
    public void damage(int amount) {
        this.health -= amount;
    }

    @Override
    public boolean isDead() {
        return health <= 0;
    }

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
