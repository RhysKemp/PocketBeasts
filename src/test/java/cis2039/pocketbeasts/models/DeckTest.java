package cis2039.pocketbeasts.models;

import cis2039.pocketbeasts.interfaces.ICard;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * DeckTest class
 * <p>
 * Tests for the Deck class
 *
 * @version 0.1.0
 *
 * @author Rhys Kemp
 *
 */
public class DeckTest {
    private Deck deck;
    private ArrayList<Card> cards;

    /**
     * Set up the test environment
     */
    @Before
    public void setUp() {
        cards = new ArrayList<>();
        cards.add(new Card("id1", "name1", 1, 2, 3));
        cards.add(new Card("id2", "name2", 4, 5, 6));
        deck = new Deck(cards);
    }

    /**
     * Test of draw method, of class Deck.
     * This test should remove the first card from the deck
     */
    @Test
    public void draw_RemovesCardFromDeck() {
        ICard drawnCard = deck.draw();
        assertEquals(1, deck.count());
        assertFalse(deck.getCards().contains(drawnCard));
    }

    /**
     * Test of draw method, of class Deck.
     * This test should return the first card in the deck
     */
    @Test
    public void draw_ReturnsCorrectCard() {
        ICard drawnCard = deck.draw();
        assertEquals("id1", drawnCard.getId());
    }

    /**
     * Test of draw method, of class Deck.
     * This test should throw an exception as the deck is empty
     */
    @Test(expected = IllegalStateException.class)
    public void draw_ThrowsExceptionWhenDeckIsEmpty() {
        deck.draw();
        deck.draw();
        deck.draw(); // This should throw an exception
    }

    /**
     * Test of shuffle method, of class Deck.
     */
//    @Test
    public void shuffle_ChangesOrderOfCards() {
        // This test is not deterministic, so it is commented out
        ICard firstCardBeforeShuffle = deck.getCards().get(0);
        deck.shuffle();
        ICard firstCardAfterShuffle = deck.getCards().get(0);
        assertNotEquals(firstCardBeforeShuffle, firstCardAfterShuffle);
    }


    // The following tests were originally a method of the Deck class, but were moved to the abstract class AbstractCardGroup
    // However, I have chosen to include them here to increase the test coverage.
    /**
     * Test of getCards method, of class Deck.
     */
    @Test
    public void count_ReturnsCorrectNumberOfCards() {
        assertEquals(2, deck.count());
    }

    /**
     * Test of isEmpty method, of class Deck.
     */
    @Test
    public void isEmpty_ReturnsTrueWhenDeckIsEmpty() {
        deck.draw();
        deck.draw();
        assertTrue(deck.isEmpty());
    }

    /**
     * Test of isEmpty method, of class Deck.
     */
    @Test
    public void isEmpty_ReturnsFalseWhenDeckIsNotEmpty() {
        assertFalse(deck.isEmpty());
    }
}
