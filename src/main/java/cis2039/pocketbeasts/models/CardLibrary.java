package cis2039.pocketbeasts.models;

import cis2039.pocketbeasts.interfaces.ICard;
import cis2039.pocketbeasts.models.factory.CardFactory;
import cis2039.pocketbeasts.models.players.Player;

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
    private static final CardFactory cardFactory = new CardFactory();
    /**
     * The cards that players start with.
     */
    public static final ICard[] STARTER_CARDS = new ICard[] {
            CardFactory.createCard("BaseCard", "BR", "Barn Rat", 1, 1, 1),
            CardFactory.createCard("BaseCard", "SP", "Scampering Pup", 2, 2, 1),
            CardFactory.createCard("BaseCard", "HB", "Hardshell Beetle", 2, 1, 2),
            CardFactory.createCard("BaseCard", "VHC", "Vicious House Cat", 5, 3, 2),
            CardFactory.createCard("GlobalHealthBuffCardDecorator", "GD", "Guard Dog", 3, 2, 3),
            CardFactory.createCard("BaseCard", "ARH", "All Round Hound", 3, 3, 3),
            CardFactory.createCard("GlobalAttackBuffCardDecorator", "MO", "Moor Owl", 4, 4, 2),
            CardFactory.createCard("BaseCard", "HT", "Highland Tiger", 5, 4, 4)
    };


    /**
     * The cards that are used for testing the decorator pattern and
     * essentially the cardFactory class.
     */
    public static final ICard[] TEST_CARDS = new ICard[] {
            CardFactory.createCard("BaseCard", "1", "BaseCard1", 1, 1, 1),
            CardFactory.createCard("GlobalAttackBuffCardDecorator", "2", "GlobalAttackBuffCard1", 1, 1, 1),
            CardFactory.createCard("BaseCard", "3", "BaseCard2", 1, 1, 1),
            CardFactory.createCard("BaseCard", "4", "BaseCard3", 1, 1, 1),
            CardFactory.createCard("BaseCard", "5", "BaseCard4", 1, 1, 1)
    };
}
