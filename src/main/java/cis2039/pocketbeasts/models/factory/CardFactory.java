package cis2039.pocketbeasts.models.factory;

import cis2039.pocketbeasts.decorators.GlobalAttackBuffCardDecorator;
import cis2039.pocketbeasts.decorators.GlobalHealthBuffCardDecorator;
import cis2039.pocketbeasts.interfaces.ICard;
import cis2039.pocketbeasts.models.Card;

/**
 * <h2>CardFactory</h2>
 * <p>
 * CardFactory class provides a factory for creating cards.
 * It creates cards based on the card type and returns the card.
 * <p>
 * This class is part of the Factory Design Pattern.
 *
 * @author Rhys Kemp
 * @see ICard
 * @see Card
 * @see cis2039.pocketbeasts.decorators
 */
public class CardFactory {

    /**
     * Creates a card based on the card type.
     *
     * @param cardType  The type of card to create.
     * @param id        The card's ID.
     * @param name      The card's name.
     * @param manaCost  The card's mana cost.
     * @param attack    The card's attack value.
     * @param health    The card's health value.
     * @return ICard The card that was created.
     */
    public static ICard createCard(String cardType, String id, String name, int manaCost, int attack, int health) {
        ICard card = new Card(id, name, manaCost, attack, health);
        switch (cardType) {
            case "BaseCard":
                return card;
            case "GlobalAttackBuffCardDecorator":
                return new GlobalAttackBuffCardDecorator(card);
            case "GlobalHealthBuffCardDecorator":
                return new GlobalHealthBuffCardDecorator(card);
            default:
                throw new IllegalArgumentException("Invalid card type");
        }

    }
}
