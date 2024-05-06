package cis2039.pocketbeasts.decorators;

import cis2039.pocketbeasts.abstracts.CardDecorator;
import cis2039.pocketbeasts.interfaces.ICard;
import cis2039.pocketbeasts.utils.Config;

/**
 * <h1>AttackBoostCardDecorator</h1>
 * <p>
 * AttackBoostCardDecorator class provides a decorator for cards that increases their attack value by 1.
 * Realistically the card would have more interesting features, but this is a simple example.
 * It extends the CardDecorator class and overrides the getAttack method to add the additional functionality.
 * <p>
 * This class is part of the Decorator Design Pattern.
 * <p>
 * This class is a concrete decorator.
 *
 * @author Rhys Kemp
 * @see CardDecorator
 * @see ICard
 * @see Config
 */
public class AttackBoostCardDecorator extends CardDecorator {
    private final int additionalAttack;

    /**
     * Constructor for the CardDecorator class.
     *
     * @param decoratedCard The card to decorate.
     */
    public AttackBoostCardDecorator(ICard decoratedCard, int additionalAttack) {
        super(decoratedCard);
        this.additionalAttack = additionalAttack;
    }

    /**
     * Gets the decorated card's attack value.
     *
     * @return int {@code decoratedCard.getAttack()} - The card's attack value.
     */
    @Override
    public int getAttack() {
        return decoratedCard.getAttack() + additionalAttack;
    }
}
