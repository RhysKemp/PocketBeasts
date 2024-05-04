package cis2039.pocketbeasts.models;

import cis2039.pocketbeasts.Config;
import cis2039.pocketbeasts.interfaces.Attackable;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a game of Pocket Beasts.
 *
 * @author Rhys Kemp
 */
public class Game {

    private final List<Player> players;

    public Game() {
        this.players = new ArrayList<>();
    }

    // PLAYER METHODS

    /**
     * Adds a player to the game.
     *
     * @param player The player to add.
     */
    public void addPlayer(Player player) {
        this.players.add(player);
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
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Gets a player by their index in the list of players.
     *
     * @param index The index of the player to get.
     * @return The player at the given index.
     */
    public Player getPlayer(int index) {
        return this.players.get(index);
    }

    /**
     * Adds mana to the player's mana pool.
     * This is passive regen, not applicable to other methods of mana gain.
     */
    public void regenMana(Player player) {
        player.addMana();
    }

    // GAME AND TURN METHODS

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
        regenMana(player);
        drawCard(player);
        System.out.println(player);
    }

    // CARD METHODS

    /**
     * Draws a card for a player.
     *
     * @param player The player to draw a card for.
     */
    public void drawCard(Player player) {
        player.getHand().add(player.getDeck().draw());
    }

    /**
     * Attacks a defender with an attacking card.
     *
     * @param attacker The card attacking.
     * @param defender The target of the attack.
     */
    public static void attackWithCard(Card attacker, Attackable defender) {
        defender.damage(attacker.getAttack());
    }

    /**
     * removes dead cards from the game
     *
     * @param players The players to remove dead cards from.
     */
    public static void removeDeadCards(Player[] players) {
        for (Player player : players) {
            ArrayList<Card> toRemove = new ArrayList<>();
            for (Card card : player.getInPlay().getCards()) {
                if (card.getHealth() <= 0) {
                    toRemove.add(card);
                    player.getGraveyard().add(card);
                }
            }
            player.getInPlay().removeAll(toRemove);
        }
    }

    /**
     * Plays a specific card from a player's hand.
     *
     * @param player The player to play the card for.
     * @param card   The card to play.
     */
    public void playCardFromHand(Player player, Card card) {
        if (player.getManaAvailable() >= card.getManaCost()) {
            player.getInPlay().add(card);
            player.getHand().remove(card);
            player.useMana(card.getManaCost());
        }
    }


//    TODO - Implement this method and show the game state in the console
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(String.format("%-9s HEALTH/%-5d MANA/%d\n", player.getName(), player.getHealth(), this.manaAvailable));
//
//        for (int i=0; i<player.getInPlay().count()+2; i++) {
//            sb.append("+-------+ ");
//        }
//        sb.append("\n");
//
//        for (int i=0; i<2; i++) {
//            sb.append("|       | ");
//        }
//        for (int i=0; i<player.getInPlay().count(); i++) {
//            sb.append(String.format("|%7d| ", player.getInPlay().getCard(i).getManaCost()));
//        }
//        sb.append("\n");
//
//        sb.append("| DECK  | ");
//        sb.append("| GRAVE | ");
//        for (int i=0; i<player.getInPlay().count(); i++) {
//            sb.append(String.format("|  %-5s| ", player.getInPlay().getCard(i).getId()));
//        }
//        sb.append("\n");
//
//        sb.append(String.format("| %-6d| ", this.deck.count()));
//        sb.append(String.format("| %-6d| ", player.getGraveyard().count()));
//        for (int i=0; i<player.getInPlay().count(); i++) {
//            sb.append("|       | ");
//        }
//        sb.append("\n");
//
//        for (int i=0; i<2; i++) {
//            sb.append("|       | ");
//        }
//        for (int i=0; i<player.getInPlay().count(); i++) {
//            sb.append(String.format("|%-2d %4d| ", player.getInPlay().getCard(i).getAttack(), player.getInPlay().getCard(i).getHealth()));
//        }
//        sb.append("\n");
//
//        for (int i=0; i<player.getInPlay().count()+2; i++) {
//            sb.append("+-------+ ");
//        }
//        sb.append("\n");
//        sb.append(String.format("%d card(s) in hand.\n", player.getHand().count()));
//        sb.append("\n");
//
//        sb.append(player.getHand().toString());
//
//        return sb.toString();
//    }
}