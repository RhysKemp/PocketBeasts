package cis2039.pocketbeasts.gameengine;

import cis2039.pocketbeasts.models.Card;
import cis2039.pocketbeasts.models.CardLibrary;
import cis2039.pocketbeasts.models.Deck;
import cis2039.pocketbeasts.models.Player;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
     * @throws IllegalArgumentException if playerNames is null or empty
     * @see Player
     * @see Deck
     */
    public PlayerInitializer(String... playerNames) {
        if (playerNames == null || playerNames.length == 0) { // Throws exception if playerNames is null or empty
            throw new IllegalArgumentException("At least one player is required.");
        }
        this.players = new ArrayList<>(playerNames.length);
        for (int i = 0; i < playerNames.length; i++) { // for each player name, create a new player with a starter deck
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
//    public static ArrayList<Card> getStarterDeck() {
//        return CardLibrary.STARTER_CARDS.stream()
//                .map(Card::new)
//                .collect(Collectors.toCollection(ArrayList::new));
//    }

}
