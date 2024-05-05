package cis2039.pocketbeasts.ui.textbased;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

import cis2039.pocketbeasts.models.Card;
import cis2039.pocketbeasts.models.Player;

/**
 * Handles user input for the text-based interface.
 * <p>
 * The InputHandler class is responsible for handling user input in the text-based interface.
 * It prompts the user for input and validates the response.
 * It also contains methods for prompting the user to play cards and attack.
 * The InputHandler class is used by the GameManager class to handle user input.
 * </p>
 *
 * @author Rhys Kemp
 * @see Card
 * @see Player
 * @see cis2039.pocketbeasts.gameengine.GameManager
 */
public class InputHandler {

    public Scanner scanner;

    /**
     * Constructor for the InputHandler class.
     */
    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prompts the user for input and returns the response.
     *
     * @param prompt The prompt to display to the user.
     * @return The user's response.
     */
    public String getPrompt(String prompt, String[] validResponse) {
        System.out.println(prompt);

        String response = this.scanner.nextLine();

        if (Arrays.asList(validResponse).contains(response)) {
            return response;
        }

        return getPrompt(prompt, validResponse);
    }

    /**
     * Prompts the user for a yes or no response.
     *
     * @param prompt The prompt to display to the user.
     * @return The user's response.
     */
    private boolean yesNoPrompt(String prompt) {
        String response = getPrompt(prompt, new String[]{"Yes", "yes", "y", "No", "no", "n"});
        return response.equals("Yes") || response.equals("yes") || response.equals("y");
    }

    /**
     * Prompts the user to play a card.
     *
     * @param player The player who is playing the card.
     * @param card   The card being played.
     * @return Boolean - Whether the card should be played.
     */
    public boolean playCardPrompt(String player, String card) {
        return yesNoPrompt(player + " play " + card + "? (Yes/No) ");
    }

    /**
     * Prompts the user to attack with a card.
     *
     * @param player The player who is attacking.
     * @param card   The card being used to attack.
     * @return Boolean - Whether the card should be used to attack.
     */
    public boolean attackWithCardPrompt(String player, String card) {
        return yesNoPrompt(player + " attack with " + card + "? (Yes/No) ");
    }

    /**
     * Prompts the user to attack a player.
     *
     * @param players The players to choose from.
     * @return Int id - The ID of the player to attack.
     */
    public int attackWhichPlayerPrompt(ArrayList<Player> players) {
        System.out.println("Who would you like to attack?");

        // Create a list of prompts for the user to choose from
        ArrayList<String> prompts = new ArrayList<>();
        for (int i = 1; i < players.size() + 1; i++) {
            prompts.add(String.valueOf(i));
        }

        // Display the list of players to the user
        for (int i = 0; i < players.size(); i++) {
            System.out.println(i + 1 + ": " + players.get(i).getName());
        }

        // Get the user's choice and return the ID of the chosen player.
        // TODO - error handling
        int choice = Integer.parseInt(getPrompt("Choose a number: ", prompts.toArray(new String[0]))) - 1;
        return players.get(choice).getId();
    }

    /**
     * Prompts the user to choose a target to attack.
     *
     * @param player        The player who is attacking.
     * @param attackingCard The card being used to attack.
     * @return Int id - The ID of the target to attack.
     */
    public int getAttackChoicePrompt(Player player, Card attackingCard) {
        System.out.println("Attacking with: " + attackingCard.getName() + "    " + attackingCard.getHealth() + "HP | " + attackingCard.getAttack() + "ATK");
        System.out.println("What would you like to attack?");

        ArrayList<String> prompts = new ArrayList<>();
        System.out.println("1. " + player.getName());
        prompts.add("1");

        for (int i = 0; i < player.getInPlay().getCards().size(); i++) {
            System.out.println(i + 2 + ". " + player.getInPlay().getCard(i).getName() + "    " + player.getInPlay().getCard(i).getHealth() + "HP | " + player.getInPlay().getCard(i).getAttack() + "ATK");
            prompts.add(String.valueOf(i + 2));
        }

        // TODO - error handling
        String target = getPrompt("Choose a number: ", prompts.toArray(new String[0]));
        return Integer.parseInt(target);
    }

    /**
     * Waits for the user to press ENTER.
     */
    public void waitForInput() {
        System.out.println("Press ENTER to continue...");
        this.scanner.nextLine();
    }
}
