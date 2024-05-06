package cis2039.pocketbeasts.models.factory;

import cis2039.pocketbeasts.models.Deck;
import cis2039.pocketbeasts.models.players.LocalPlayer;
import cis2039.pocketbeasts.models.players.Player;
import cis2039.pocketbeasts.models.players.RemotePlayer;

/**
 * <h2>PlayerFactory</h2>
 * <p>
 * PlayerFactory class provides a factory for creating players.
 * It creates players based on the player type and returns the player.
 * <p>
 * This class is part of the Factory Design Pattern.
 *
 * @author Rhys Kemp
 * @see Player
 * @see LocalPlayer
 * @see RemotePlayer
 * @see cis2039.pocketbeasts.models.players
 */
public class PlayerFactory {
    /**
     * Creates a player based on the player type.
     *
     * @param playerType The type of player to create.
     * @param playerName The name of the player.
     * @return Player The player that was created.
     */
    public static Player createPlayer(String playerType, String playerName) {
        return switch (playerType) {
            case "Player" -> new Player(playerName);
            case "LocalPlayer" -> new LocalPlayer(playerName);
            case "RemotePlayer" -> new RemotePlayer(playerName);
            default -> throw new IllegalArgumentException("Invalid player type");
        };
    }

    /**
     * Creates a player based on the player type.
     * Overloaded method that also takes a deck for the player.
     *
     * @param playerType The type of player to create.
     * @param playerName The name of the player.
     * @param deck      The player's deck.
     * @return Player The player that was created.
     */
    public static Player createPlayer(String playerType, String playerName, Deck deck) {
        return switch (playerType) {
            case "Player" -> new Player(playerName, deck);
            case "LocalPlayer" -> new LocalPlayer(playerName);
            case "RemotePlayer" -> new RemotePlayer(playerName);
            default -> throw new IllegalArgumentException("Invalid player type");
        };
    }
}
