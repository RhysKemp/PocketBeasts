package cis2039.pocketbeasts.gameengine;

import cis2039.pocketbeasts.models.Card;
import cis2039.pocketbeasts.models.CardLibrary;
import cis2039.pocketbeasts.models.Deck;
import cis2039.pocketbeasts.models.Player;

import java.util.ArrayList;

/**
 * Initialises a new game with the given players.
 *
 * @author Rhys Kemp
 *
 * @see Player
 * @see Deck
 * @see Card
 * @see CardLibrary
 * @see GameLoop
 */
public class PlayerInitializer {

    private ArrayList<Player> players;

    /**
     * Initialises a new game with the given players.
     * <p>
     * Each player is given a starter deck of cards.
     *
     * @param playerNames The names of the players in the game.
     */
    public PlayerInitializer(String... playerNames) {
        this.players = new ArrayList<>(playerNames.length);
        System.out.println("\nplayerNames.length: " + playerNames.length);
        System.out.println("\nthis.players.size(): " + this.players.size());
        for (int i = 0; i < playerNames.length; i++) {
            System.out.println(i);
            this.players.add(new Player(playerNames[i]));
            this.players.get(i).setDeck(new Deck(getStarterDeck()));
        }
    }

    /**
     * Gets the players in the game.
     *
     * @return this.players - The players in the game.
     */
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /**
     * Gets a starter deck of cards.
     *
     * @return starterDeck - A starter deck of cards.
     */
    public static ArrayList<Card> getStarterDeck() {
        ArrayList<Card> starterDeck = new ArrayList<>();
        for (Card card : CardLibrary.STARTER_CARDS) {
            starterDeck.add(new Card(card));
        }
        return starterDeck;
    }

}
