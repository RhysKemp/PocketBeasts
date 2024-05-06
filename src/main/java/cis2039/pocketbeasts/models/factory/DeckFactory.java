package cis2039.pocketbeasts.models.factory;

import cis2039.pocketbeasts.models.CardLibrary;
import cis2039.pocketbeasts.models.Deck;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <h2>DeckFactory</h2>
 * <p>
 * DeckFactory class provides a factory for creating decks.
 * It creates decks based on the deck type and returns the deck.
 * <p>
 * This class is part of the Factory Design Pattern.
 *
 * @author Rhys Kemp
 * @see Deck
 * @see CardLibrary
 */
public class DeckFactory {

    /**
     * Creates a deck based on the deck type.
     *
     * @param deckType The type of deck to create.
     * @return Deck The deck that was created.
     */
    public static Deck createDeck(String deckType) {
        switch (deckType) {
            case "StarterDeck":
                return new Deck(new ArrayList<>(Arrays.asList(CardLibrary.STARTER_CARDS)));
            case "TestDeck":
                return new Deck(new ArrayList<>(Arrays.asList(CardLibrary.TEST_CARDS)));
            case "CustomDeck":
                return new Deck(new ArrayList<>());
            default:
                throw new IllegalArgumentException("Invalid deck type");
        }
    }
}
