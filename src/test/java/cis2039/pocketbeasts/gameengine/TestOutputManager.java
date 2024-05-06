package cis2039.pocketbeasts.gameengine;

import cis2039.pocketbeasts.interfaces.OutputManager;
import cis2039.pocketbeasts.models.players.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * TestOutputManager class
 * <p>
 * Test class for the OutputManager interface
 * For use in testing the GameManager class.
 *
 * @author Rhys Kemp
 * @see OutputManager
 * @see Player
 */
public class TestOutputManager implements OutputManager {
    private final List<String> messages = new ArrayList<>();

    /**
     * Simulates displaying a welcome message.
     */
    @Override
    public void welcomeMessage() {
        messages.add("Welcome message");
    }

    /**
     * Simulates displaying a player's turn.
     *
     * @param player The player whose turn it is.
     */
    @Override
    public void displayPlayerTurn(Player player) {
        messages.add(player.getName() + "'s turn");
    }

    /**
     * Simulates displaying fatigue damage.
     *
     * @param player The player who is taking fatigue damage.
     */
    @Override
    public void displayFatigueDamage(Player player) {
        messages.add("Fatigue damage: " + player.getName() + " Health: " + player.getHealth());
    }

    /**
     * Simulates displaying a player's health.
     *
     * @param player The player whose health is being displayed.
     */
    @Override
    public void displayPlayerHealth(Player player) {

    }

    /**
     * Simulates displaying a player's play state.
     *
     * @param player The player whose play state to display.
     */
    @Override
    public void displayPlayer(Player player) {

    }

    /**
     * Simulates displaying a winner.
     *
     * @param player The player who has won the game.
     */
    @Override
    public void displayWinner(Player player) {
        messages.add("Winner: " + player.getName());
    }

    /**
     * Simulates displaying a defeated player.
     *
     * @param player The player who has been defeated.
     */
    @Override
    public void displayDefeated(Player player) {
        messages.add("Defeated: " + player.getName());
    }

    /**
     * Simulates displaying a final play state.
     *
     * @param player The player whose final play state to display.
     */
    @Override
    public void displayFinalPlayState(Player player) {

    }

    /**
     * Gets the messages that have been displayed.
     *
     * @return List of messages that have been displayed.
     */
    public List<String> getMessages() {
        return messages;
    }

    /**
     * Updates the messages list with a new message.
     *
     * @param event The message to add to the list.
     */
    @Override
    public void update(String event) {
        messages.add(event);
    }

    /**
     * Updates the messages list with a new message.
     *
     * @param event  The message to add to the list.
     * @param object The object to add to the list.
     */
    @Override
    public void update(String event, Object... object) {
        messages.add(event);
    }
}

