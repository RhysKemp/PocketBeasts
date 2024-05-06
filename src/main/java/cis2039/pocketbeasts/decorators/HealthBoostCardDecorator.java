package cis2039.pocketbeasts.decorators;

import cis2039.pocketbeasts.abstracts.CardDecorator;
import cis2039.pocketbeasts.interfaces.ICard;
import cis2039.pocketbeasts.utils.Config;

/**
 * <h1>HealthBoostCardDecorator</h1>
 * <p>
 * HealthBoostCardDecorator class provides a decorator for cards that increases their health value by 2.
 * Realistically the card would have more interesting features, but this is a simple example.
 * It extends the CardDecorator class and overrides the getHealth method to add the additional functionality.
 * <p>
 * This class is part of the Decorator Design Pattern
 *
 * @author Rhys Kemp
 * @see CardDecorator
 * @see ICard
 * @see Config
 */
public class HealthBoostCardDecorator extends CardDecorator {
    private final int additionalHealth;

    /**
     * Constructor for the CardDecorator class.
     *
     * @param decoratedCard The card to decorate.
     */
    public HealthBoostCardDecorator(ICard decoratedCard) {
        super(decoratedCard);
        this.additionalHealth = Config.DEFENSE_CARD_HEALTH;
    }

    /**
     * Gets the decorated card's health value.
     *
     * @return int {@code decoratedCard.getHealth()} - The card's health value.
     */
    @Override
    public int getHealth() {
        return decoratedCard.getHealth() + additionalHealth;
    }

}
