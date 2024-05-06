package cis2039.pocketbeasts.models.factory;

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
    public Player createPlayer(String playerType, String playerName) {
        switch (playerType) {
            case "Player":
                return new Player(playerName);
            case "LocalPlayer":
                return new LocalPlayer(playerName);
            case "RemotePlayer":
                return new RemotePlayer(playerName);
            default:
                throw new IllegalArgumentException("Invalid player type");
        }
    }
}
