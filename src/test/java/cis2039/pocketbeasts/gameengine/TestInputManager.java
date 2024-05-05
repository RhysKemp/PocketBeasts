package cis2039.pocketbeasts.gameengine;

import cis2039.pocketbeasts.interfaces.InputManager;
import cis2039.pocketbeasts.models.Card;
import cis2039.pocketbeasts.models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * TestInputManager class
 * <p>
 * TestInputManager is a test implementation of the InputManager interface.
 * It is used to simulate user input during testing.
 * For use in testing the GameManager class.
 *
 * @author Rhys Kemp
 * @see InputManager
 * @see Card
 * @see Player
 */
public class TestInputManager implements InputManager {
    private final Queue<Boolean> attackResponses;
    private final Queue<Integer> targetPlayerResponses;
    private final Queue<Integer> attackChoiceResponses;
    private final List<String> messages = new ArrayList<>();

    /**
     * Constructor for the TestInputManager class.
     *
     * @param attackResponses       Queue of boolean responses to attack prompts.
     * @param targetPlayerResponses Queue of integer responses to target player prompts.
     * @param attackChoiceResponses Queue of integer responses to attack choice prompts.
     */
    public TestInputManager(Queue<Boolean> attackResponses, Queue<Integer> targetPlayerResponses, Queue<Integer> attackChoiceResponses) {
        this.attackResponses = attackResponses;
        this.targetPlayerResponses = targetPlayerResponses;
        this.attackChoiceResponses = attackChoiceResponses;
    }

    /**
     * Simulates a response to a yes or no prompt.
     * <p>
     * Always returns true, as the user should always answer yes in testing.
     *
     * @param prompt The prompt to display to the user.
     * @return Boolean - Whether the user answered yes.
     */
    @Override
    public boolean yesNoPrompt(String prompt) {
        return true;
    }

    /**
     * Simulates a response to a play card prompt.
     * <p>
     * Always returns true, as the card should always be played in testing.
     *
     * @param player The player who is playing the card.
     * @param card   The card being played.
     * @return Boolean - Whether the card should be played.
     */
    @Override
    public boolean playCardPrompt(Player player, Card card) {
        messages.add(player + " playCardPrompt: " + card);
        return true;
    }

    /**
     * Simulates a response to an attack with card prompt.
     *
     * @param player The player who is attacking.
     * @param card   The card being used to attack.
     * @return Boolean - Whether the card should be used to attack.
     */
    @Override
    public boolean attackWithCardPrompt(Player player, Card card) {
        messages.add(player.getName() + " attackWithCardPrompt: " + card);
        Boolean response = attackResponses.poll();
        return response != null ? response : false;
    }

    /**
     * Simulates a response to an attack which player prompt.
     *
     * @param otherPlayers The players that can be attacked.
     * @return Int {@code targetPlayerResponses.poll();} - The simulated ID of the player to attack.
     */
    @Override
    public int attackWhichPlayerPrompt(ArrayList<Player> otherPlayers) {
        Integer response = targetPlayerResponses.poll();
        return response != null ? response : -1;
    }


    /**
     * Simulates a response to an attack choice prompt.
     *
     * @param player The player to attack.
     * @param card         The card being used to attack.
     * @return Int {@code attackChoiceResponses.poll();} - The simulated index of the enemy to attack.
     */
    @Override
    public int getAttackChoicePrompt(Player player, Card card) {
        messages.add(player.getName() + " getAttackChoicePrompt: " + card);
        Integer response = attackChoiceResponses.poll();
        return response != null ? response : -1;
    }

    /**
     * Blank method supposed to wait for the user to respond.
     */
    @Override
    public void waitForOkay() {

    }

    /**
     * Gets the attack responses.
     *
     * @return The attack responses.
     */
    public Queue<Boolean> getAttackResponses() {
        return attackResponses;
    }

    /**
     * Gets the target player responses.
     *
     * @return The target player responses.
     */
    public Queue<Integer> getTargetPlayerResponses() {
        return targetPlayerResponses;
    }

    /**
     * Gets the attack choice responses.
     *
     * @return The attack choice responses.
     */
    public Queue<Integer> getAttackChoiceResponses() {
        return attackChoiceResponses;
    }

    /**
     * Gets the messages.
     *
     * @return The messages.
     */
    public List<String> getMessages() {
        return this.messages;
    }
}
