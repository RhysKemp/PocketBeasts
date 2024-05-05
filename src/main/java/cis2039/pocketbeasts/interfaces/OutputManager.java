package cis2039.pocketbeasts.interfaces;

import cis2039.pocketbeasts.models.Player;

/**
 * Manages the output to the user.
 * <p>
 * The OutputManager interface is responsible for managing the output to the user.
 * It contains methods for displaying messages to the user, such as the welcome message,
 * the player's turn, the player's health, and the winner of the game.
 * Chosen implementation of the OutputManager interface will be used by the GameManager class to display messages to the user.
 * </p>
 *
 * @author Rhys Kemp
 * @see Player
 * @see cis2039.pocketbeasts.gameengine.GameManager
 * @see cis2039.pocketbeasts.ui.textbased.ConsoleOutputManager
 */
public interface OutputManager {

    /**
     * Displays the welcome message to the user.
     */
    void welcomeMessage();

    /**
     * Displays the player's turn.
     *
     * @param player The player whose turn it is.
     */
    void displayPlayerTurn(Player player);

    /**
     * Displays a message to the user indicating that the player has taken fatigue damage.
     */
    void displayFatigueDamage(Player player);

    /**
     * Displays the player's health to the user.
     *
     * @param player The player whose health to display.
     */
    void displayPlayerHealth(Player player);

    /**
     * Displays a message to the user indicating that the player has won the game.
     *
     * @param player The player who has won the game.
     */
    void displayWinner(Player player);

    /**
     * Displays a message to the user indicating that the player has been defeated.
     *
     * @param player The player who has been defeated.
     */
    void displayDefeated(Player player);

    /**
     * Displays the final play state of a player.
     *
     * @param player The player whose final play state to display.
     */
    void displayFinalPlayState(Player player);
}
