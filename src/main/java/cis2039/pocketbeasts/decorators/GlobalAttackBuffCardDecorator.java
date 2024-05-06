package cis2039.pocketbeasts.decorators;

import cis2039.pocketbeasts.abstracts.CardDecorator;
import cis2039.pocketbeasts.interfaces.ICard;
import cis2039.pocketbeasts.utils.Config;

public class GlobalAttackBuffCardDecorator extends CardDecorator {
    private final int boostAmount;

    /**
     * Constructor for the CardDecorator class.
     *
     * @param decoratedCard The card to decorate.
     */
    public GlobalAttackBuffCardDecorator(ICard decoratedCard) {
        super(decoratedCard);
        this.boostAmount = Config.GLOBAL_ATTACK_BUFF;
    }


}
