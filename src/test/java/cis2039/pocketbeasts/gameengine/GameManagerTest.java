package cis2039.pocketbeasts.gameengine;

import cis2039.pocketbeasts.models.Game;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameManagerTest {
    private GameManager gameManager;

    @Before
    public void setUp() {
        Game game = new Game();
        gameManager = new GameManager(game);
    }

    /**
     * Test of startTextBased method, of class GameManager
     */
    @Test
    public void startTextBased_WithNoPlayers_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> gameManager.startTextBased());
    }
}
