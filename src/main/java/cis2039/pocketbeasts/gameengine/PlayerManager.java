package cis2039.pocketbeasts.gameengine;

import cis2039.pocketbeasts.models.Card;
import cis2039.pocketbeasts.models.CardLibrary;
import cis2039.pocketbeasts.models.Deck;
import cis2039.pocketbeasts.models.Player;

import java.util.ArrayList;

/**
 * PlayerManager class
 * <p>
 * The PlayerManager class is responsible for managing the players in the game.
 * It initialises the game with the given players and assigns each player a starter deck of cards.
 * </p>
 *
 * @author Rhys Kemp
 * @see Player
 * @see Deck
 * @see Card
 * @see CardLibrary
 * @see GameManager
 */
public class PlayerManager {

    private final ArrayList<Player> players;

    /**
     * Initialises a new game with the given players.
     * <p>
     * Each player is given a starter deck of cards.
     *
     * @param playerNames The names of the players in the game.
     * @throws IllegalArgumentException if playerNames is null or empty
     * @see Player
     * @see Deck
     */
    public PlayerManager(String... playerNames) {
        if (playerNames == null || playerNames.length == 0) { // Throws exception if playerNames is null or empty
            throw new IllegalArgumentException("At least one player is required.");
        }
        this.players = new ArrayList<>(playerNames.length);
        for (int i = 0; i < playerNames.length; i++) { // for each player name, create a new player with a starter deck
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
     * Gets a player by index.
     *
     * @param index The index of the player to get.
     * @return Player - The player at the given index.
     */
    public Player getPlayer(int index) {
        return this.players.get(index);
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