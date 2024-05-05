package cis2039.pocketbeasts.models;

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
 * @see Deck
 * @see Player
 * @see Game
 * @author Rhys Kemp
 **/
public class CardLibrary {
    /**
     * The cards that players start with.
     */
    public static final Card[] STARTER_CARDS = new Card[] {
            new Card ("BR", "Barn Rat", 1, 1, 1),
            new Card ("SP", "Scampering Pup", 2, 2, 1),
            new Card ("HB", "Hardshell Beetle", 2, 1, 2),
            new Card ("VHC", "Vicious House Cat", 3, 3, 2),
            new Card ("GD", "Guard Dog", 3, 2, 3),
            new Card ("ARH", "All Round Hound", 3, 3, 3),
            new Card ("MO", "Moor Owl", 4, 4, 2),
            new Card ("HT", "Highland Tiger", 5, 4, 4)
    };
}
