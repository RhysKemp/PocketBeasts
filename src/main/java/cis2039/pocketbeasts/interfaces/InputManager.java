package cis2039.pocketbeasts.interfaces;

import cis2039.pocketbeasts.models.Card;
import cis2039.pocketbeasts.models.players.Player;

import java.util.ArrayList;

/**
 * Handles user input for the text-based interface.
 * <p>
 * The InputManager class is responsible for handling user input in the text-based interface.
 * It prompts the user for input and validates the response.
 * It also contains methods for prompting the user to play cards and attack.
 * The InputManager class is used by the GameManager class to handle user input.
 * </p>
 *
 * @author Rhys Kemp
 * @see Card
 * @see Player
 * @see cis2039.pocketbeasts.gameengine.GameManager
 */
public interface InputManager {

    /**
     * Prompts the user with a yes or no question.
     *
     * @param prompt The prompt to display to the user.
     * @return Boolean - Whether the user answered yes.
     */
    boolean yesNoPrompt(String prompt);

    /**
     * Prompts the user to play a card.
     *
     * @param player The player who is playing the card.
     * @param card   The card being played.
     * @return Boolean - Whether the card should be played.
     */
    boolean playCardPrompt(Player player, ICard card);

    /**
     * Prompts the user to attack with a card.
     *
     * @param player The player who is attacking.
     * @param card   The card being used to attack.
     * @return Boolean - Whether the card should be used to attack.
     */
    boolean attackWithCardPrompt(Player player, ICard card);

    /**
     * Prompts the user to choose a player to attack.
     *
     * @param player The player who is attacking.
     * @return Int id - The ID of the player to attack.
     */
    int attackWhichPlayerPrompt(ArrayList<Player> player);

    /**
     * Prompts the user to choose an attack.
     *
     * @param player The player who is attacking.
     * @return Int id - The ID of the attack to use.
     */
    int getAttackChoicePrompt(Player player, ICard card);

    /**
     * Waits for the user to press enter or input a command.
     */
    void waitForOkay();
}
