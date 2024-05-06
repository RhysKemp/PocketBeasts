package cis2039.pocketbeasts.models.players;

/**
 * Represents a remote player in a game of Pocket Beasts.
 * <p>
 * A remote player is a player that is controlled by a remote player.
 * It extends the Player class and inherits all of its attributes and methods.
 * The RemotePlayer class is used to differentiate between local and remote players in the game.
 * <p>
 * <Strong>AS YET REMOTE PLAYERS ARE NOT IMPLEMENTED.</Strong>
 * <p>
 * This class is a placeholder for future development.
 *
 * @see Player
 */
public class RemotePlayer extends Player{
    /**
     * Default constructor for RemotePlayer.
     * Initialises a new player with the given name.
     *
     * @param name The name of the player.
     */
    public RemotePlayer(String name) {
        super(name);
    }
}
