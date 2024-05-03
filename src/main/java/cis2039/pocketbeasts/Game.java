package cis2039.pocketbeasts;

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
     * Starts a new game.
     * Shuffles the deck and deals 4 cards to each player.
     */
    public void newGame() {
        for (Player player : this.players) {
            player.getDeck().shuffle();
            for (int i = 0; i < 4; i++) {
                drawCard(player);
            }
        }
    }

    /**
     * Draws a card for a player.
     *
     * @param player The player to draw a card for.
     */
    public void drawCard(Player player) {
        player.getHand().add(player.getDeck().draw());
    }

    /**
     * Adds mana to the player's mana pool.
     */
    public void addMana(Player player) {
        player.addMana();
    }

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