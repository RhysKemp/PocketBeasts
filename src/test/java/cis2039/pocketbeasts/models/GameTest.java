package cis2039.pocketbeasts.models;

import cis2039.pocketbeasts.gameengine.PlayerManager;
import cis2039.pocketbeasts.interfaces.Attackable;
import cis2039.pocketbeasts.utils.Config;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * GameTest class
 * <p>
 * Tests for the Game class
 *
 * @version 0.1.0
 *
 * @author Rhys Kemp
 *
 */
public class GameTest {
    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    /**
     * Test of addPlayer method, of class Game.
     */
    @Test
    public void addPlayer_AddsPlayerToGame() {
        Player player = new Player("Player1");
        game.addPlayer(player);
        assertTrue(game.getPlayers().contains(player));
    }

    /**
     * Test of addPlayers method, of class Game.
     */
    @Test
    public void addPlayers_AddsMultiplePlayersToGame() {
        // Create a PlayerManager with two players, could also use Player.SetDeck() to add a deck
        PlayerManager playerManager = new PlayerManager("Player1", "Player2");
        ArrayList<Player> players = playerManager.getPlayers();
        game.addPlayers(players);
        assertTrue(game.getPlayers().containsAll(players));
    }

    /**
     * Test of removePlayer method, of class Game.
     */
    @Test
    public void removePlayer_RemovesPlayerFromGame() {
        Player player = new Player("Player1");
        game.addPlayer(player);
        game.removePlayer(player);
        assertFalse(game.getPlayers().contains(player));
    }

    /**
     * Test of regenMana method, of class Game.
     */
    @Test
    public void regenMana_IncreasesPlayerMana() {
        Player player = new Player("Player1");
        game.addPlayer(player);
        int initialMana = player.getManaAvailable();
        game.regenMana(player);
        assertTrue(player.getManaAvailable() > initialMana);
    }

    /**
     * Test of newGame method, of class Game.
     */
    @Test
    public void newGame_DealsInitialHandToPlayers() {
        Player player = new Player("Player1", new Deck(PlayerManager.getStarterDeck()));
        game.addPlayer(player);
        game.newGame();
        assertEquals(Config.INITIAL_HAND_SIZE, player.getHand().count());
    }

    /**
     * Test of startTurn method, of class Game.
     */
    @Test
    public void startTurn_DrawsCardAndRegensMana() {
        // Two players are required to start a game
        PlayerManager playerManager = new PlayerManager("Player1", "Player2");
        ArrayList<Player> players = playerManager.getPlayers();
        Player player = players.get(0);

        game.addPlayers(players);
        game.newGame();

        int initialHandSize = player.getHand().count();
        int initialMana = player.getManaAvailable();
        game.startTurn(player);

        assertTrue(player.getHand().count() > initialHandSize);
        assertTrue(player.getManaAvailable() > initialMana);
    }

    /**
     * Test of endTurn method, of class Game.
     */
    @Test
    public void drawCard_AddsCardToPlayerHand() {
        Player player = new Player("Player1", new Deck(PlayerManager.getStarterDeck()));
        game.addPlayer(player);
        game.newGame();
        int initialHandSize = player.getHand().count();
        game.drawCard(player);
        assertTrue(player.getHand().count() > initialHandSize);
    }

    /**
     * Test of attackWithCard method, of class Game.
     */
    @Test
    public void attackWithCard_DamagesDefender() {
        Card attacker = new Card("id", "name", 5, 1, 1);
        Attackable defender = new Player("Player1");
        int initialHealth = defender.getHealth();
        Game.attackWithCard(attacker, defender);
        assertTrue(defender.getHealth() < initialHealth);
    }

    /**
     * Test of removeDeadCards method, of class Game.
     */
    @Test
    public void removeDeadCards_RemovesDeadCardsFromGame() {
        Player player = new Player("Player1");
        Card card = new Card("id", "name", 1, 1, 0);

        player.getInPlay().add(card);
        ArrayList<Player> players = new ArrayList<>();
        players.add(player);

        Game.removeDeadCards(players);
        assertFalse(player.getInPlay().getCards().contains(card));
    }

    /**
     * Test of playCardFromHand method, of class Game.
     */
    @Test
    public void playCardFromHand_MovesCardFromHandToInPlay() {
        Player player = new Player("Player1", new Deck(PlayerManager.getStarterDeck()));
        Card card = new Card("id", "name", 1, 1, 1);

        player.getHand().add(card);
        System.out.println(player.getHand().getCards());
        game.addPlayer(player);
        // Regen mana to allow card to be played
        game.regenMana(player);
        game.playCardFromHand(player, card);
        System.out.println(player.getHand().getCards());

        assertFalse(player.getHand().getCards().contains(card));
        assertTrue(player.getInPlay().getCards().contains(card));
    }
}