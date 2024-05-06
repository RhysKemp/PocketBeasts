package cis2039.pocketbeasts.ui.textbased;

import cis2039.pocketbeasts.models.players.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * ConsoleOutputManagerTest class
 * <p>
 * Tests for the ConsoleOutputManager class
 *
 * @author Rhys Kemp
 * @see ConsoleOutputManager
 * @see Player
 */
public class ConsoleOutputManagerTest {
    private TestConsoleOutputManager outputManager;
    private Player player;

    /**
     * Set up the test output manager and player for testing.
     */
    @Before
    public void setUp() {
        outputManager = new TestConsoleOutputManager();
        player = new Player("TestPlayer");
    }

    /**
     * Test of the displayPlayerTurn method, of the ConsoleOutputManager class.
     */
    @Test
    public void displayPlayerTurn_ReturnsCorrectResponse() {
        outputManager.displayPlayerTurn(player);
        assertEquals("TestPlayer's turn.", outputManager.getLastMessage());
    }

    /**
     * Test of the displayFatigueDamage method, of the ConsoleOutputManager class.
     */
    @Test
    public void displayFatigueDamage_ReturnsCorrectResponse() {
        outputManager.displayFatigueDamage(player);
        assertEquals("TestPlayer's deck is empty, they have taken 1 fatigue damage.", outputManager.getLastMessage());
    }

    /**
     * Test of the displayPlayerHealth method, of the ConsoleOutputManager class.
     */
    @Test
    public void displayPlayerHealth_ReturnsCorrectResponse() {
        outputManager.displayPlayerHealth(player);
        assertEquals("TestPlayer is now at 15 HP.", outputManager.getLastMessage());
    }

    /**
     * Test of the displayWinner method, of the ConsoleOutputManager class.
     */
    @Test
    public void displayWinner_ReturnsCorrectResponse() {
        outputManager.displayWinner(player);
        assertEquals("TestPlayer has won the game!", outputManager.getLastMessage());
    }

    /**
     * Test of the displayDefeated method, of the ConsoleOutputManager class.
     */
    @Test
    public void displayDefeated_ReturnsCorrectResponse() {
        outputManager.displayDefeated(player);
        assertEquals("TestPlayer has been defeated.", outputManager.getLastMessage());
    }

    /**
     * TestConsoleOutputManager class
     * <p>
     * Test output manager for the ConsoleOutputManager class
     */
    private static class TestConsoleOutputManager extends ConsoleOutputManager {
        private String lastMessage;

        /**
         * Get the last message displayed.
         *
         * @return The last message displayed.
         */
        String getLastMessage() {
            return lastMessage;
        }

        /**
         * Append lastMessage with the player's name and turn message.
         *
         * @param player The player whose turn it is.
         */
        @Override
        public void displayPlayerTurn(Player player) {
            lastMessage = player.getName() + "'s turn.";
        }

        /**
         * Append lastMessage with the player's name and fatigue damage message.
         *
         * @param player The player who is taking fatigue damage.
         */
        @Override
        public void displayFatigueDamage(Player player) {
            lastMessage = player.getName() + "'s deck is empty, they have taken 1 fatigue damage.";
        }

        /**
         * Append lastMessage with the player health message.
         *
         * @param player The player whose health is being displayed.
         */
        @Override
        public void displayPlayerHealth(Player player) {
            lastMessage = player.getName() + " is now at " + player.getHealth() + " HP.";
        }

        /**
         * Append lastMessage with the player's name and winner message.
         *
         * @param player The player who has won the game.
         */
        @Override
        public void displayWinner(Player player) {
            lastMessage = player.getName() + " has won the game!";
        }

        /**
         * Append lastMessage with the player's name and defeated message.
         *
         * @param player The player who has been defeated.
         */
        @Override
        public void displayDefeated(Player player) {
            lastMessage = player.getName() + " has been defeated.";
        }
    }
}
