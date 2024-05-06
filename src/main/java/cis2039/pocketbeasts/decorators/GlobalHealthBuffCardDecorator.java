package cis2039.pocketbeasts.decorators;

import cis2039.pocketbeasts.abstracts.CardDecorator;
import cis2039.pocketbeasts.interfaces.ICard;
import cis2039.pocketbeasts.utils.Config;

/**
 * <h1>GlobalHealthBuffCardDecorator</h1>
 * <p>
 * GlobalHealthBuffCardDecorator class provides a decorator for cards that increases the health value of all cards by 1.
 * It extends the CardDecorator class and adds the {@link #getBoostAmount()} method to add the additional functionality.
 * <p>
 * This class is part of the Decorator Design Pattern
 *
 * @author Rhys Kemp
 * @see CardDecorator
 * @see ICard
 */
public class GlobalHealthBuffCardDecorator extends CardDecorator {
    private final int boostAmount;

    /**
     * Constructor for the CardDecorator class.
     *
     * @param decoratedCard The card to decorate.
     */
    public GlobalHealthBuffCardDecorator(ICard decoratedCard) {
        super(decoratedCard);
        this.boostAmount = Config.GLOBAL_HEALTH_BUFF;
    }

    /**
     * Gets the amount to boost the health value of all cards by.
     *
     * @return int {@code boostAmount} - The amount to boost the health value of all cards by.
     */
    public int getBoostAmount() {
        return boostAmount;
    }
}
