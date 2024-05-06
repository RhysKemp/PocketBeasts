package cis2039.pocketbeasts.models.players;

/**
 * <h2>LocalPlayer</h2>
 * Represents a local player in a game of Pocket Beasts.
 * <p>
 * A local player is a player that is controlled by a human player.
 * It extends the Player class and inherits all of its attributes and methods.
 * The LocalPlayer class is used to differentiate between local and remote players in the game.
 * <p>
 * <Strong>AS YET REMOTE PLAYERS ARE NOT IMPLEMENTED.</Strong>
 * <p>
 * This class is a placeholder for future development.
 *
 * @author Rhys Kemp
 * @see Player
 */
public class LocalPlayer extends Player {

    /**
     * Default constructor for LocalPlayer.
     * Initialises a new player with the given name.
     *
     * @param name The name of the player.
     */
    public LocalPlayer(String name) {
        super(name);
    }
}
