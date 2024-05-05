package cis2039.pocketbeasts.ui.textbased;

import cis2039.pocketbeasts.interfaces.OutputManager;
import cis2039.pocketbeasts.models.Player;
import cis2039.pocketbeasts.utils.Config;

/**
 * Manages output to the console.
 * <p>
 * This class is part of the text-based user interface.
 * It is used to provide feedback to the user during the game.
 * It is used by the GameManager class to print messages to the console.
 * </p>
 *
 * @author Rhys Kemp
 * @see cis2039.pocketbeasts.gameengine.GameManager
 * @see cis2039.pocketbeasts.models.Player
 * @see cis2039.pocketbeasts.utils.Config
 */
public class ConsoleOutputManager implements OutputManager {

    /**
     * Prints a welcome message to the console.
     */
    public void welcomeMessage() {
        System.out.println();
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("Welcome to PocketBeasts!");
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println();
        System.out.println("This basic console application tests our underlying software design patterns.");
        System.out.println();
        System.out.println("Here's a key for each card:");
        System.out.println();
        System.out.println("                             +-------+ ");
        System.out.println("M  = Mana Cost               |      M| ");
        System.out.println("ID = Card identifier:        |  ID   | ");
        System.out.println("A  = Attack:                 |       | ");
        System.out.println("H  = Health:                 |A     H| ");
        System.out.println("                             +-------+ ");
        System.out.println();
        System.out.println("New players each start with 15 Health and 1 Mana to spend on playing cards.");
        System.out.println("At the start of the game each player draws 4 cards from their deck to hand.");
        System.out.println();
        System.out.println("Players each take turns. Each turn consists four phases:");
        System.out.println("1. Add mana (mana increases by one each turn and replenishes in full).");
        System.out.println("2. Draw a card.");
        System.out.println("3. Cycle through your cards in play (if any), choosing whether to attack.");
        System.out.println("   a. Attacking the other player directly with your card inflicts damage to their health.");
        System.out.println("      equal to the attack power of the card.");
        System.out.println("   b. Attacking another players beast will damage both cards (equal to their attack values).");
        System.out.println("   c. Any beast with <= 0 health is removed from the play field and placed into the graveyard.");
        System.out.println("4. Play cards from hand.");
        System.out.println();
    }

    @Override
    public void displayPlayerTurn(Player player) {
        System.out.println(player.getName() + "'s turn.");
    }

    /**
     * Prints a message to the console indicating the player has taken fatigue damage if their deck is empty.
     *
     * @param player The current player.
     */
    @Override
    public void displayFatigueDamage(Player player) {
        if (player.getDeck().isEmpty()) { // Check for fatigue damage, this happens inside game.startTurn()
            System.out.println(player.getName() + "'s deck is empty, they have taken " + Config.FATIGUE_DAMAGE + " fatigue damage.");
        }
    }

    /**
     * Prints the current player's health to the console.
     *
     * @param player The current player.
     */
    @Override
    public void displayPlayerHealth(Player player) {
        if (!player.isDead()) {
            System.out.println(player.getName() + " is now at " + player.getHealth() + " HP.");
        }
    }

    /**
     * Prints that the player has won the game.
     *
     * @param player The player who has won the game.
     */
    @Override
    public void displayWinner(Player player) {
        System.out.println(player.getName() + " has won the game!");
    }

    /**
     * Prints that the player has been defeated.
     *
     * @param player The player who has been defeated.
     */
    @Override
    public void displayDefeated(Player player) {
        System.out.println(player.getName() + " has been defeated.");
    }

    /**
     * Prints the final play state of a player.
     *
     * @param player The player to print the final play state of.
     */
    @Override
    public void displayFinalPlayState(Player player) {
        System.out.println(player);
        System.out.println("\n".repeat(6));
    }
}
