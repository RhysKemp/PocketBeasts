package cis2039.pocketbeasts.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * HandTest class
 * <p>
 * JUnit tests for the Hand class
 * </p>
 *
 * @version 0.1.0
 *
 * @author Rhys Kemp
 *
 */
public class HandTest {
    private Hand hand;

    /**
     * Set up the test environment
     */
    @Before
    public void setUp() {
        hand = new Hand();
    }

    /**
     * Test of add method, of class Hand.
     * This test adds a card to the hand and checks that the card is added.
     */
    @Test
    public void add_AddsCardToHandAndSorts() {
        Card card = new Card("id1", "name1", 1, 2, 3);

        hand.add(card);

        assertEquals(1, hand.count());
        assertEquals(card, hand.getCards().get(0));
    }

    /**
     * Test of add method, of class Hand.
     * This test adds multiple cards to the hand and checks that they are sorted by mana cost.
     */
    @Test
    public void add_AddsMultipleCardsAndSortsByManaCost() {
        Card card1 = new Card("id1", "name1", 1, 2, 3);
        Card card2 = new Card("id2", "name2", 2, 3, 4);

        hand.add(card2);
        hand.add(card1);

        assertEquals(2, hand.count());
        assertEquals(card1, hand.getCards().get(0));
        assertEquals(card2, hand.getCards().get(1));
    }

    /**
     * Test of sort method, of class Hand.
     */
    @Test
    public void sort_SortsCardsByManaCost() {
        Card card1 = new Card("id1", "name1", 1, 2, 3);
        Card card2 = new Card("id2", "name2", 2, 3, 4);

        hand.add(card2);
        hand.add(card1);
        hand.sort();

        assertEquals(card1, hand.getCards().get(0));
        assertEquals(card2, hand.getCards().get(1));
    }

    /**
     * Test of toString method, of class Hand.
     */
    @Test
    public void toString_ReturnsCorrectFormat() {
        Card card = new Card("id1", "name1", 1, 2, 3);
        hand.add(card);
        // Expected format:
        String expected = "+-------+ \n|      1| \n|  id1  | \n|       | \n|2     3| \n+-------+ \n";
        assertEquals(expected, hand.toString());
    }
}
