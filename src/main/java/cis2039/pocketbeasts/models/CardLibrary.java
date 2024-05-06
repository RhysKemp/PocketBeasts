package cis2039.pocketbeasts.models;

import cis2039.pocketbeasts.decorators.GlobalAttackBuffCardDecorator;
import cis2039.pocketbeasts.decorators.GlobalHealthBuffCardDecorator;
import cis2039.pocketbeasts.interfaces.ICard;

/**
 * A library of cards that players can use.
 * <p>
 * This class is a library of cards that players can use in the game. It contains
 * a list of cards that players can use to build their decks.
 * <p>
 * The cards in the library are stored as an array of Card objects. Each card has
 * a name, a cost, an attack value, and a health value.
 * <p>
 * The cards in the library are static and cannot be modified.
 * <p>
 * {@code STARTER_CARDS} is a list of cards that players start with.
 *
 * @see Card
 * @see ICard
 * @see cis2039.pocketbeasts.abstracts.CardDecorator
 * @see Deck
 * @see Player
 * @see Game
 * @author Rhys Kemp
 **/
public class CardLibrary {
    /**
     * The cards that players start with.
     */
    public static final ICard[] STARTER_CARDS = new ICard[] {
            new Card ("BR", "Barn Rat", 1, 1, 1),
            new Card ("SP", "Scampering Pup", 2, 2, 1),
            new Card ("HB", "Hardshell Beetle", 2, 1, 2),
            new Card ("VHC", "Vicious House Cat", 5, 3, 2),
            new GlobalHealthBuffCardDecorator(new Card ("GD", "Guard Dog", 3, 2, 3)),
            new Card ("ARH", "All Round Hound", 3, 3, 3),
            new GlobalAttackBuffCardDecorator (new Card ("MO", "Moor Owl", 4, 4, 2)),
            new Card ("HT", "Highland Tiger", 5, 4, 4)
    };

    public static final ICard[] DECORATOR_TEST_CARDS = new ICard[]{
            new Card("1", "BaseCard1", 1, 1, 1),
            new GlobalAttackBuffCardDecorator(new Card("2", "GlobalAttackBuffCard1", 1, 1, 1)),
            new Card("3", "BaseCard2", 1, 1, 1),
            new Card("4", "BaseCard3", 1, 1, 1),
            new Card("5", "BaseCard4", 1, 1, 1)
    };
}
