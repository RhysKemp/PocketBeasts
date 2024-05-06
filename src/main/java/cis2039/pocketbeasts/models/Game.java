package cis2039.pocketbeasts.models;

import cis2039.pocketbeasts.decorators.AttackBoostCardDecorator;
import cis2039.pocketbeasts.decorators.GlobalAttackBuffCardDecorator;
import cis2039.pocketbeasts.decorators.GlobalHealthBuffCardDecorator;
import cis2039.pocketbeasts.decorators.HealthBoostCardDecorator;
import cis2039.pocketbeasts.gameengine.GameEventNotifier;
import cis2039.pocketbeasts.interfaces.*;
import cis2039.pocketbeasts.models.players.Player;
import cis2039.pocketbeasts.utils.Config;

import java.util.ArrayList;

/**
 * Represents a game of Pocket Beasts.
 * <p>
 * This class contains methods for managing the game state.
 *
 * @author Rhys Kemp
 * @see Deck
 * @see Player
 * @see Card
 * @see ICard
 * @see Attackable
 */
public class Game implements Subject {

    private boolean gameWon = false;

    private final GameEventNotifier gameEventNotifier = new GameEventNotifier();
    private final ArrayList<Player> players;

    public Game() {
        this.players = new ArrayList<>();
    }

    /**
     * Adds a player to the game.
     *
     * @param player The player to add.
     */
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    /**
     * Adds multiple players to the game.
     *
     * @param players The players to add.
     */
    public void addPlayers(ArrayList<Player> players) {
        this.players.addAll(players);
    }

    /**
     * Removes a player from the game.
     *
     * @param player The player to remove.
     */
    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    /**
     * Gets the list of players in the game.
     *
     * @return The list of players.
     */
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /**
     * Adds mana to the player's mana pool.
     * This is passive regen, not applicable to other methods of mana gain.
     */
    public void regenMana(Player player) {
        player.addMana();
    }

    /**
     * Starts a new game.
     * Shuffles the deck and deals {@value Config#INITIAL_HAND_SIZE} cards to each player.
     */
    public void newGame() {
        for (Player player : this.players) {
            player.getDeck().shuffle();
            for (int i = 0; i < Config.INITIAL_HAND_SIZE; i++) {
                drawCard(player);
            }
        }
    }

    /**
     * Starts a player's turn.
     * Regenerates mana and draws a card.
     *
     * @param player The player whose turn it is.
     */
    public void startTurn(Player player) {
        if (player.isDead()) {
            gameEventNotifier.notifyObservers("PLAYER_DEAD_AT_TURN_START", player);
        }
        // Check if the deck is not empty before drawing a card
        if (!player.getDeck().isEmpty()) {
            drawCard(player);
        }
        regenMana(player);
        gameEventNotifier.notifyObservers("TURN_STARTED", player);
    }

    /**
     * Draws a card for a player.
     *
     * @param player The player to draw a card for.
     */
    public void drawCard(Player player) {
        player.getHand().add(player.getDeck().draw());
        gameEventNotifier.notifyObservers("CARD_DRAWN");
    }

    /**
     * Attacks a defender with an attacking card.
     *
     * @param attacker The card attacking.
     * @param defender The target of the attack.
     */
    public void attackWithCard(ICard attacker, Attackable defender) {
        defender.damage(attacker.getAttack());
        gameEventNotifier.notifyObservers("ATTACK_MADE");
    }

    /**
     * removes dead cards from the game
     *
     * @param players The players to remove dead cards from.
     */
    public void removeDeadCards(ArrayList<Player> players) {
        for (Player player : players) {
            ArrayList<ICard> toRemove = new ArrayList<>();
            for (ICard card : player.getInPlay().getCards()) {
                if (card.getHealth() <= 0) {
                    toRemove.add(card);
                    player.getGraveyard().add(card);
                }
            }
            player.getInPlay().removeAll(toRemove);
            gameEventNotifier.notifyObservers("DEAD_CARDS_REMOVED");
        }
    }

    /**
     * Plays a specific card from a player's hand.
     *
     * @param player The player to play the card for.
     * @param card   The card to play.
     */
    public void playCardFromHand(Player player, ICard card) {
        if (player.getManaAvailable() >= card.getManaCost()) {
            player.getInPlay().add(card);
            applyCardDecorations(player, card);
            player.getHand().remove(card);
            player.useMana(card.getManaCost());
        }
    }

    /**
     * Damage a player with fatigue damage if their deck is empty.
     *
     * @param player The player to damage.
     */
    public void fatigueDamage(Player player) {
        if (player.getDeck().isEmpty()) {
            player.damage(Config.FATIGUE_DAMAGE);
            gameEventNotifier.notifyObservers("FATIGUE_DAMAGE_TAKEN", player);
        }
    }

    /**
     * Checks if a player is dead.
     *
     * @param player The player to check.
     * @return boolean {@code true} if the player is dead, {@code false} otherwise.
     */
    public boolean checkForDeadPlayer(Player player) {
        if (player.isDead()) {
            gameEventNotifier.notifyObservers("PLAYER_DEFEATED", player);
            this.removePlayer(player);
            return true;
        }
        return false;
    }

    /**
     * Checks if there is a winner.
     *
     * @return boolean {@code true} if there is a winner, {@code false} otherwise.
     */
    public boolean checkForWinner() {
        if (this.players.size() == 1) {
            if (!gameWon) {
                gameEventNotifier.notifyObservers("GAME_WON", this.players.get(0));
                gameWon = true;
            }
            return true;
        }
        return false;
    }

    /**
     * Apply card decorations
     * <p>
     * This method applies the decorations from a card to a player.
     * It checks for specific decorators and applies their effects.
     * Reason for this is to the apply open-closed principle.
     *
     * @param player The player to apply the decorations to.
     * @param card   The card to apply the decorations from.
     */
    public void applyCardDecorations(Player player, ICard card) {
        // Would notifyObserver each decorator applied to the card for fancy GUI effects
        if (card instanceof GlobalAttackBuffCardDecorator) {
            globalAttackBuff(player, card);
        }
        if (card instanceof GlobalHealthBuffCardDecorator) {
            globalHealthBuff(player, card);
        }
        // other decorators
    }

    /**
     * Apply a global attack buff to all cards in play.
     *
     * @param player The player to apply the buff to.
     * @param card   The card to apply the buff from.
     */
    public void globalAttackBuff(Player player, ICard card) {
        int boostAmount = ((GlobalAttackBuffCardDecorator) card).getBoostAmount();
        for (int i = 0; i < player.getInPlay().getCards().size(); i++) { // Apply the buff to all cards in play bar the buffing card
            ICard c = player.getInPlay().getCard(i);
            if (c != card) {
                player.getInPlay().getCards().set(i, new AttackBoostCardDecorator(c, boostAmount));
            }
        }
    }

    /**
     * Apply a global health buff to all cards in play.
     *
     * @param player The player to apply the buff to.
     * @param card   The card to apply the buff from.
     */
    public void globalHealthBuff(Player player, ICard card) {
        int boostAmount = ((GlobalHealthBuffCardDecorator) card).getBoostAmount();
        for (int i = 0; i < player.getInPlay().getCards().size(); i++) { // Apply the buff to all cards in play bar the buffing card
            ICard c = player.getInPlay().getCard(i);
            if (c != card) {
                player.getInPlay().getCards().set(i, new HealthBoostCardDecorator(c, boostAmount));
            }
        }
    }

    /**
     * Registers an observer with the game.
     *
     * @param observer The observer to register.
     */
    @Override
    public void registerObserver(Observer observer) {
        gameEventNotifier.registerObserver(observer);
    }

    /**
     * Removes an observer from the game.
     *
     * @param observer The observer to remove.
     */
    @Override
    public void removeObserver(Observer observer) {
        gameEventNotifier.removeObserver(observer);
    }

    /**
     * Notifies observers of an event.
     *
     * @param event The event to notify observers of.
     */
    @Override
    public void notifyObservers(String event) {
        gameEventNotifier.notifyObservers(event);
    }

    /**
     * Notifies observers of an event with an object.
     *
     * @param event  The event to notify observers of.
     * @param object The object to pass to the observers.
     */
    @Override
    public void notifyObservers(String event, Object... object) {
        gameEventNotifier.notifyObservers(event, object);
    }
}