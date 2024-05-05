package cis2039.pocketbeasts.ui.textbased;

import cis2039.pocketbeasts.gameengine.PlayerManager;
import cis2039.pocketbeasts.models.Card;
import cis2039.pocketbeasts.models.Deck;
import cis2039.pocketbeasts.models.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * ConsoleInputManagerTest class
 * <p>
 * Tests for the ConsoleInputManager class
 *
 * @author Rhys Kemp
 * @see ConsoleInputManager
 * @see Player
 * @see Card
 * @see PlayerManager
 */
public class ConsoleInputManagerTest {
    private TestConsoleInputManager inputManager;
    private Player player;
    private Card card;

    /**
     * Set up the test input manager and player for testing.
     */
    @Before
    public void setUp() {
        inputManager = new TestConsoleInputManager();
        player = new Player("TestPlayer", new Deck(PlayerManager.getStarterDeck()));
        card = new Card("id1", "name1", 1, 2, 3);
    }

    /**
     * Test of the yesNoPrompt method, of the ConsoleInputManager class.
     */
    @Test
    public void yesNoPrompt_ReturnsCorrectResponse() {
        inputManager.setNextResponse("Yes");
        assertTrue(inputManager.yesNoPrompt("Test prompt"));

        inputManager.setNextResponse("No");
        assertFalse(inputManager.yesNoPrompt("Test prompt"));
    }

    /**
     * Test of the playCardPrompt method, of the ConsoleInputManager class.
     */
    @Test
    public void playCardPrompt_ReturnsCorrectResponse() {
        inputManager.setNextResponse("Yes");
        assertTrue(inputManager.playCardPrompt(player, card));

        inputManager.setNextResponse("No");
        assertFalse(inputManager.playCardPrompt(player, card));
    }

    /**
     * Test of the attackWithCardPrompt method, of the ConsoleInputManager class.
     */
    @Test
    public void attackWithCardPrompt_ReturnsCorrectResponse() {
        inputManager.setNextResponse("Yes");
        assertTrue(inputManager.attackWithCardPrompt(player, card));

        inputManager.setNextResponse("No");
        assertFalse(inputManager.attackWithCardPrompt(player, card));
    }

    /**
     * Test of the attackWhichPlayerPrompt method, of the ConsoleInputManager class.
     */
    @Test
    public void attackWhichPlayerPrompt_ReturnsCorrectResponse() {
        PlayerManager playerManager = new PlayerManager("Player1", "Player2");
        ArrayList<Player> players = playerManager.getPlayers();


        inputManager.setNextResponse("1");
        assertEquals(players.get(0).getId(), inputManager.attackWhichPlayerPrompt(players));
        inputManager.setNextResponse("2");
        assertEquals(players.get(1).getId(), inputManager.attackWhichPlayerPrompt(players));
    }

    /**
     * Test of the getAttackChoicePrompt method, of the ConsoleInputManager class.
     */
    @Test
    public void getAttackChoicePrompt_ReturnsCorrectResponse() {
        inputManager.setNextResponse("1");
        assertEquals(1, inputManager.getAttackChoicePrompt(player, card));

        inputManager.setNextResponse("2");
        assertEquals(2, inputManager.getAttackChoicePrompt(player, card));
    }



    /**
     * TestConsoleInputManager class
     * <p>
     * A test implementation of the ConsoleInputManager class
     */
    private static class TestConsoleInputManager extends ConsoleInputManager {
        private String nextResponse;

        /**
         * Sets the next response to be returned by getPrompt
         *
         * @param nextResponse The next response to be returned by getPrompt
         */
        void setNextResponse(String nextResponse) {
            this.nextResponse = nextResponse;
        }

        /**
         * Returns the next response set by setNextResponse
         *
         * @param prompt The prompt to display to the user.
         * @param validResponse The valid responses the user can give.
         * @return The next response set by setNextResponse
         */
        @Override
        public String getPrompt(String prompt, String[] validResponse) {
            return nextResponse;
        }
    }
}
