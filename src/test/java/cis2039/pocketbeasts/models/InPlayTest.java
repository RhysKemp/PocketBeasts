package cis2039.pocketbeasts.models;

import cis2039.pocketbeasts.interfaces.ICard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InPlayTest {
    private InPlay inPlay;

    @Before
    public void setUp() {
        inPlay = new InPlay();
        inPlay.add(new Card("id1", "name1", 1, 2, 3));
        inPlay.add(new Card("id2", "name2", 4, 5, 6));
    }

    @Test
    public void getCard_ReturnsCorrectCard() {
        ICard card = inPlay.getCard(0);
        assertEquals("id1", card.getId());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getCard_ThrowsExceptionForInvalidIndex() {
        inPlay.getCard(10);
    }
}