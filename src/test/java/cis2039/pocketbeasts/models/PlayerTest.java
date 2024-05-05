package cis2039.pocketbeasts.models;

import cis2039.pocketbeasts.utils.Config;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * PlayerTest class
 * <p>
 * Test class for the Player class
 * </p>
 *
 * @version 0.1.0
 *
 * @author Rhys Kemp
 *
 */
public class PlayerTest {
    private Player player;
    private Deck deck;

    /**
     * Set up the test environment
     */
    @Before
    public void setUp() {
        deck = new Deck(new ArrayList<Card>() {{
            add(new Card("id1", "name1", 1, 2, 3));
            add(new Card("id2", "name2", 4, 5, 6));
        }});
        player = new Player("Test Player", deck);
    }

    /**
     * Test of method getName, of class Player.
     */
    @Test
    public void getName_ReturnsCorrectName() {
        assertEquals("Test Player", player.getName());
    }

    /**
     * Test of method getId, of class Player.
     */
    @Test
    public void getId_ReturnsCorrectIncrementalId() {
        Player lastPlayer = new Player("New Player", deck);
        Player newPlayer = new Player("New Player 2", deck);
        int lastId = lastPlayer.getId();
        assertEquals(lastId + 1, newPlayer.getId());
    }

    /**
     * Test of method getDeck, of class Player.
     */
    @Test
    public void getDeck_ReturnsCorrectDeck() {
        assertEquals(deck, player.getDeck());
    }

    /**
     * Test of method setDeck, of class Player.
     */
    @Test
    public void setDeck_SetsCorrectDeck() {
        Deck newDeck = new Deck(new ArrayList<>());
        player.setDeck(newDeck);
        assertEquals(newDeck, player.getDeck());
    }

    /**
     * Test of method getHealth, of class Player.
     */
    @Test
    public void getHand_ReturnsHand() {
        assertNotNull(player.getHand());
    }

    /**
     * Test of method getInPlay, of class Player.
     */
    @Test
    public void getInPlay_ReturnsInPlay() {
        assertNotNull(player.getInPlay());
    }

    /**
     * Test of method getHealth, of class Player.
     */
    @Test
    public void getGraveyard_ReturnsGraveyard() {
        assertNotNull(player.getGraveyard());
    }

    /**
     * Test of method addMana, of class Player.
     */
    @Test
    public void addMana_IncreasesManaAvailableTest() {
        player.addMana();
        assertEquals(1, player.getManaAvailable());
    }

    /**
     * Test of method useMana, of class Player.
     */
    @Test
    public void useMana_DecreasesManaAvailableTest() {
        player.addMana();
        player.useMana(1);
        assertEquals(0, player.getManaAvailable());
    }

    /**
     * Test of damage method, of class Player.
     * This test should throw an exception when the given amount is negative.
     */
    @Test(expected = IllegalArgumentException.class)
    public void damage_ThrowsExceptionForNegativeAmountTest() {
        player.damage(-1);
    }

    /**
     * Test of damage method, of class Player.
     * This test should reduce the player's health by the given amount.
     */
    @Test
    public void damage_ReducesHealthTest() {
        player.damage(1);
        assertEquals(Config.INITIAL_HEALTH - 1, player.getHealth());
    }

    /**
     * Test of isDead method, of class Player.
     * This test should return true when the player's health is zero.
     */
    @Test
    public void isDead_ReturnsTrueWhenHealthIsZeroTest() {
        player.damage(Config.INITIAL_HEALTH);
        assertTrue(player.isDead());
    }

    /**
     * Test of isDead method, of class Player.
     * This test should return false when the player's health is positive.
     */
    @Test
    public void isDead_ReturnsFalseWhenHealthIsPositiveTest() {
        assertFalse(player.isDead());
    }

    @Test
    public void toString_ReturnsCorrectFormat() {
        StringBuilder expected = new StringBuilder();
        expected.append(String.format("%-9s HEALTH/%-5d MANA/%d\n", player.getName(), player.getHealth(), player.getManaAvailable()));
        expected.append("+-------+ +-------+ \n");
        expected.append("|       | |       | \n");
        expected.append("| DECK  | | GRAVE | \n");
        expected.append("| 2     | | 0     | \n");
        expected.append("|       | |       | \n");
        expected.append("+-------+ +-------+ \n");
        expected.append("0 card(s) in hand.\n");
        expected.append("\n\n\n\n\n\n\n"); // Newlines at the end of the string due to the way hand.toString() is implemented

        // print for comparison
        System.out.println("player.toString():");
        System.out.println(player.toString());
        System.out.println("expected:" + expected);

        assertEquals(expected.toString(), player.toString());
    }
}