package cis2039.pocketbeasts.gameengine;

import cis2039.pocketbeasts.interfaces.ICard;
import cis2039.pocketbeasts.models.CardLibrary;
import cis2039.pocketbeasts.models.players.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * PlayerManagerTest class
 * <p>
 * Test class for the PlayerManager class
 *
 * @author Rhys Kemp
 */
public class PlayerManagerTest {
    private PlayerManager playerManager;

    /**
     * Set up the PlayerManager for testing
     */
    @Before
    public void setUp() {
        playerManager = new PlayerManager("Player1", "Player2");
    }

    /**
     * Test of constructor method, of class PlayerManager.
     * Test that the constructor creates players with the correct names.
     */
    @Test
    public void constructor_CreatesPlayersWithNames() {
        assertEquals("Player1", playerManager.getPlayer(0).getName());
        assertEquals("Player2", playerManager.getPlayer(1).getName());
    }

    /**
     * Test of constructor method, of class PlayerManager.
     * Test the constructor throws an exception for no player names
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructor_ThrowsExceptionForNoPlayerNames() {
        new PlayerManager();
    }

    /**
     * Test of getPlayers method, of class PlayerManager.
     * Test that the getPlayers method returns all players
     */
    @Test
    public void getPlayers_ReturnsAllPlayers() {
        ArrayList<Player> players = playerManager.getPlayers();
        assertEquals(2, players.size());
        assertEquals("Player1", players.get(0).getName());
        assertEquals("Player2", players.get(1).getName());
    }

    /**
     * Test of getPlayers method, of class PlayerManager.
     * Test that the getPlayers method returns the correct players.
     */
    @Test
    public void getPlayer_ReturnsCorrectPlayer() {
        assertEquals("Player1", playerManager.getPlayer(0).getName());
    }

    /**
     * Test of getPlayers method, of class PlayerManager.
     * Test that the getPlayers method throws an exception for an invalid index.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void getPlayer_ThrowsExceptionForInvalidIndex() {
        playerManager.getPlayer(10);
    }

}
