package cis2039.pocketbeasts.abstracts;

import cis2039.pocketbeasts.interfaces.ICard;

/**
 * <h1>CardDecorator</h1>
 * <p>
 * CardDecorator abstract class provides a base for card decorators.
 * It implements the ICard interface and provides a constructor for the decorated card.
 * Decorators can override the methods of the ICard interface to add additional functionality.
 *
 * @author Rhys Kemp
 * @see ICard
 */
public abstract class CardDecorator implements ICard {
    protected ICard decoratedCard;

    /**
     * Constructor for the CardDecorator class.
     *
     * @param decoratedCard The card to decorate.
     */
    public CardDecorator(ICard decoratedCard) {
        this.decoratedCard = decoratedCard;
    }

    /**
     * Gets the decorated card's unique identifier.
     *
     * @return ICard {@code decoratedCard} - The card being decorated.
     */
    @Override
    public String getId() {
        return decoratedCard.getId();
    }

    /**
     * Gets the decorated card's name.
     *
     * @return String {@code decoratedCard.getName()} - The card's name.
     */
    @Override
    public String getName() {
        return decoratedCard.getName();
    }

    /**
     * Gets the decorated card's mana cost.
     *
     * @return int {@code decoratedCard.getManaCost()} - The card's mana cost.
     */
    @Override
    public int getManaCost() {
        return decoratedCard.getManaCost();
    }

    /**
     * Gets the decorated card's attack value.
     *
     * @return int {@code decoratedCard.getAttack()} - The card's attack value.
     */
    @Override
    public int getAttack() {
        return decoratedCard.getAttack();
    }

    /**
     * Gets the decorated card's health value.
     *
     * @return int {@code decoratedCard.getHealth()} - The card's health value.
     */
    @Override
    public int getHealth() {
        return decoratedCard.getHealth();
    }

    /**
     * Reduces the decorated card's health by the specified amount.
     *
     * @param amount the amount to reduce the health by
     */
    @Override
    public void damage(int amount) {
        decoratedCard.damage(amount);
    }

    /**
     * Checks if the decorated card is dead.
     *
     * @return boolean {@code decoratedCard.isDead()} - True if the card is dead, false otherwise.
     */
    @Override
    public boolean isDead() {
        return decoratedCard.isDead();
    }

}
