package cis2039.pocketbeasts.gameengine;

import cis2039.pocketbeasts.interfaces.ICard;
import cis2039.pocketbeasts.models.Card;
import cis2039.pocketbeasts.models.Deck;
import cis2039.pocketbeasts.models.Game;
import cis2039.pocketbeasts.models.factory.DeckFactory;
import cis2039.pocketbeasts.models.players.Player;
import cis2039.pocketbeasts.utils.Config;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

/**
 * GameManagerTest class
 * <p>
 * Tests the functionality of the game loop and the GameManager class.
 * Essentially tests all the private methods in the GameManager class without running them explicitly.
 *
 * @author Rhys Kemp
 * @see GameManager
 * @see Game
 * @see TestInputManager
 * @see TestOutputManager
 * @see Player
 * @see Card
 */
public class GameManagerTest {
    private GameManager gameManager;
    private Game game;
    private TestInputManager inputManager;
    private TestOutputManager outputManager;

    /**
     * Set up the game and game manager for testing.
     */
    @Before
    public void setUp() {
        game = new Game();

        // Set up the game manager with a test managers.
        Queue<Boolean> attackResponses = new LinkedList<>();
        Queue<Integer> targetPlayerResponses = new LinkedList<>();
        Queue<Integer> attackChoiceResponses = new LinkedList<>();
        inputManager = new TestInputManager(attackResponses, targetPlayerResponses, attackChoiceResponses);
        outputManager = new TestOutputManager();
        gameManager = new GameManager(game, outputManager, inputManager);
    }

    @Test
    public void start_WithNoPlayers_ThrowsException() {
//        System.out.println("GameManagerTest: start_WithNoPlayers_ThrowsException " + game.getPlayers());
        assertThrows(IllegalArgumentException.class, () -> gameManager.start());
    }

    @Test
    public void start_WithOnePlayer_StopsGameAndDeclaresWinner() {
        game.addPlayer(new Player("Player1", DeckFactory.createDeck("StarterDeck")));
        System.out.println("GameManagerTest: start_WithOnePlayer_StopsGameAndDeclaresWinner " + game.getPlayers());
        gameManager.start();
        assertFalse(gameManager.isRunning());
    }

    /**
     * Test of start method, of class GameManager
     * Test fast-fail behaviour when a player is defeated before the game starts.
     */
    @Test
    public void start_WithDeadPlayer_RemovesPlayerAndStopsGame() {
        Player player = new Player("Player1", DeckFactory.createDeck("StarterDeck"));
        Player player2 = new Player("Player2", DeckFactory.createDeck("StarterDeck"));

        player2.damage(Config.INITIAL_HEALTH);
        game.addPlayer(player);
        game.addPlayer(player2);

        gameManager.start();
        assertFalse(gameManager.isRunning());
        assertTrue(outputManager.getMessages().contains("Defeated: Player2"));
        assertTrue(game.getPlayers().contains(player) && !game.getPlayers().contains(player2));
    }

    /**
     * Test of start method, of class GameManager
     * Test that fatigue damage is applied to a player with an empty deck & the game ends after the player is defeated.
     */
    @Test
    public void start_WithTwoPlayers_ChecksFatigueDeath() {
        Player player1 = new Player("Player1", new Deck(new ArrayList<>())); // Empty deck to force fatigue damage
        Player player2 = new Player("Player2", DeckFactory.createDeck("StarterDeck"));
        game.addPlayer(player1);
        game.addPlayer(player2);

        gameManager.start();

        assertFalse(gameManager.isRunning());
        assertTrue(outputManager.getMessages().contains("Fatigue damage: Player1 Health: " + (Config.INITIAL_HEALTH - Config.FATIGUE_DAMAGE)) && outputManager.getMessages().contains("Defeated: Player1"));
    }

    /**
     * Test of start method, of class GameManager
     * <p>
     * Tests the functionality of the game loop, specifically attacking other players.
     * Simulates a game with two players and two rounds of play.
     * Player 1 attacks player 2 twice with Barn Rat (1ATK)
     *
     * @throws InterruptedException if the thread is interrupted
     * @see GameManager
     * @see Game
     * @see TestInputManager
     * @see TestOutputManager
     * @see Thread
     * @see CountDownLatch
     */
    @Test
    public void start_WithMultipleRounds_AttackPlayerDirectly() throws InterruptedException {
        Player player1 = new Player("Player1", DeckFactory.createDeck("StarterDeck"));
        Player player2 = new Player("Player2", DeckFactory.createDeck("StarterDeck"));
        game.addPlayer(player1);
        game.addPlayer(player2);

        // Simulate 2 rounds of play
        for (int i = 0; i < 2; i++) {
            inputManager.getAttackResponses().add(true);    // Player 1 attacks with card
            inputManager.getTargetPlayerResponses().add(1); // Player 1 attacks player 2
            inputManager.getAttackChoiceResponses().add(1); // Player 1 attacks player 2 directly
        }

        CountDownLatch latch = new CountDownLatch(2);

        // Utilise a thread to run the game and a latch to read the state of the game after n rounds
        Thread gameThread = getThread(latch);
        latch.await();

        assertTrue(gameManager.isRunning());
        assertTrue(player1.getHealth() < Config.INITIAL_HEALTH);
        gameThread.interrupt();
    }

    /**
     * Test of start method, of class GameManager
     * <p>
     * Tests the functionality of the game loop, specifically taking turns in the correct order.
     * Simulates a game with three players and two rounds of play.
     */
    @Test
    public void start_WithMultiplePlayers_PlayersTakeTurnsInCorrectOrder() {
        Player player1 = new Player("Player1", DeckFactory.createDeck("StarterDeck"));
        Player player2 = new Player("Player2", DeckFactory.createDeck("StarterDeck"));
        Player player3 = new Player("Player3", DeckFactory.createDeck("StarterDeck"));
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);

        // Simulate 2 rounds of play
        for (int i = 0; i < 2; i++) {
            inputManager.getAttackResponses().add(true);
            inputManager.getAttackResponses().add(true);
            inputManager.getTargetPlayerResponses().add(1);
            inputManager.getTargetPlayerResponses().add(1);
            inputManager.getAttackChoiceResponses().add(1);
            inputManager.getAttackChoiceResponses().add(1);
        }

        gameManager.start();

        List<String> messages = outputManager.getMessages().stream()
                .filter(m -> m.contains("'s turn"))
                .toList();

        assertEquals(messages.get(0), "Player1's turn");
        assertEquals(messages.get(1), "Player2's turn");
        assertEquals(messages.get(2), "Player3's turn");
        assertEquals(messages.get(3), "Player1's turn");
        assertEquals(messages.get(4), "Player2's turn");
        assertEquals(messages.get(5), "Player3's turn");

        System.out.println("GameManagerTest: start_WithMultiplePlayers_PlayersTakeTurnsInCorrectOrder, players take turns in correct order");
    }


    /**
     * Get a thread to run the game.
     * <p>
     * This method creates a thread to run the game.
     *
     * @return {@code gameThread} - The thread to run the game.
     */
    private Thread getThread() {
        Thread gameThread = new Thread(() -> {
            gameManager = new GameManager(game, outputManager, inputManager);
            gameManager.start();
        });

        gameThread.start();
        return gameThread;
    }

    /**
     * Overloaded method for getThread() to accept a latch parameter.
     * <p>
     * This method creates a thread to run the game and waits for the countdown latch to reach 0.
     * The countdown latch is decremented when a player attacks directly, simulating a player's turn.
     * Overrides the TestInputManager.getAttackChoicePrompt method in order to decrement the latch.
     *
     * @param latch The countdown latch to wait for.
     * @return {@code gameThread} - The thread to run the game.
     */
    private Thread getThread(CountDownLatch latch) {
        Thread gameThread = new Thread(() -> {
            gameManager = new GameManager(game, outputManager, new TestInputManager(
                    inputManager.getAttackResponses(),
                    inputManager.getTargetPlayerResponses(),
                    inputManager.getAttackChoiceResponses()) {
                @Override
                public int getAttackChoicePrompt(Player player, ICard card) { // Decrement the latch when a player attacks
                    latch.countDown();
                    return super.getAttackChoicePrompt(player, card);
                }
                // override other methods to count down the latch
            });
            gameManager.start();
        });

        gameThread.start();
        return gameThread;
    }
}

