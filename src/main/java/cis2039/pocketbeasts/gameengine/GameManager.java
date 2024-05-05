package cis2039.pocketbeasts.gameengine;

import cis2039.pocketbeasts.interfaces.InputManager;
import cis2039.pocketbeasts.interfaces.OutputManager;
import cis2039.pocketbeasts.models.Card;
import cis2039.pocketbeasts.models.Game;
import cis2039.pocketbeasts.models.Player;
import cis2039.pocketbeasts.ui.textbased.ConsoleInputManager;
import cis2039.pocketbeasts.ui.textbased.ConsoleOutputManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the game loop.
 * <p>
 * This class contains methods for running the game loop.
 * It allows players to take turns and play cards.
 * It also checks for a winner and removes defeated players from the game.
 * The game loop can be run in text-based mode.
 * </p>
 *
 * @author Rhys Kemp
 * @see Game
 * @see Player
 * @see Card
 * @see InputManager
 * @see OutputManager
 * @see ConsoleInputManager
 * @see ConsoleOutputManager
 */
public class GameManager {
    private final Game game;
    private boolean isRunning;
    private final InputManager inputManager;
    private final OutputManager outputManager;
    private final ArrayList<Player> players;


    /**
     * Default constructor for the GameManager class.
     * <p>
     * This constructor creates a new GameManager object with default text-based input and output.
     * It sets the game to loop and the output and input managers to ConsoleOutputManager and ConsoleInputManager respectively.
     * The players list is set to the players in the game.
     * The game loop is started by calling the {@link #start() start} method.
     * The game loop is halted by calling the {@link #stop() stop} method.
     * </p>
     *
     * @param game The game to loop.
     */
    public GameManager(Game game) {
        this(game, new ConsoleOutputManager(), new ConsoleInputManager());
    }

    /**
     * Overloaded Constructor for the GameManager class.
     * <p>
     * This constructor creates a new GameManager object.
     * It sets the game to loop and the output and input managers to use.
     * The players list is set to the players in the game.
     * The game loop is started by calling the {@link #start() start} method.
     * The game loop is halted by calling the {@link #stop() stop} method.
     * </p>
     *
     * @param game          The game to loop.
     * @param outputManager The output manager to use.
     * @param inputManager  The input manager to use.
     */
    public GameManager(Game game, OutputManager outputManager, InputManager inputManager) {
        this.game = game;
        this.isRunning = true;
        this.inputManager = inputManager;
        this.outputManager = outputManager;
        this.players = game.getPlayers();
    }

    /**
     * Starts the game in text-based mode.
     *
     * @throws IllegalArgumentException if there are no players in the game
     */
    public void start() {
        if (game.getPlayers().isEmpty()) { // Defensive programming
            throw new IllegalArgumentException("At least one player is required.");
        }
        outputManager.welcomeMessage();
        inputManager.waitForOkay();

        while (isRunning()) {
            for (Player player : new ArrayList<>(players)) {
                playTurn(player);
            }
        }
    }

    /**
     * Stops the game loop.
     * <p>
     * This method sets this.isRunning to false.
     */
    public void stop() {
        this.isRunning = false;
    }

    /**
     * Checks whether the game loop is running.
     *
     * @return Boolean this.isRunning - Whether the game loop is running.
     */
    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * Checks for a winner.
     * <p>
     * If there is only one player left in the game, they are declared the winner.
     */
    private boolean checkForWinner() {
        if (game.getPlayers().size() <= 1) {
            this.stop();
            return true;
        }
        return false;
    }

    /**
     * Removes a dead player from the game.
     *
     * @param player The player to remove.
     * @return Boolean - Whether the player was removed.
     */
    private boolean checkForDeadPlayer(Player player) {
        if (player.isDead()) {
            game.removePlayer(player);
            return true;
        }
        return false;
    }

    /**
     * Allows a player to attack with cards in play.
     *
     * @param player The player to attack with cards.
     */
    private void cardAttacks(Player player) {
        List<Card> playerInPlayCopy = new ArrayList<>(player.getInPlay().getCards());
        for (Card card : playerInPlayCopy) {
            if (checkForWinner()) {
                break;
            }
            if (inputManager.attackWithCardPrompt(player, card)) {
                ArrayList<Player> otherPlayers = new ArrayList<>(players);
                otherPlayers.remove(player);
                int targetPlayerId = inputManager.attackWhichPlayerPrompt(otherPlayers);
                Player targetPlayer = players.stream()
                        .filter(p -> p.getId() == targetPlayerId)
                        .findFirst()
                        .orElse(null);
                if (targetPlayer == null) {
                    // Change to exception
                    System.out.println("Invalid player chosen.");
                    continue;
                }
                int target = inputManager.getAttackChoicePrompt(targetPlayer, card);
                if (target == 1) {
                    Game.attackWithCard(card, targetPlayer);
                    checkForDeadPlayer(targetPlayer);
                    if (checkForWinner()) {
                        break;
                    }
                } else {
                    Card targetCard = targetPlayer.getInPlay().getCard(target - 2);
                    targetCard.damage(card.getAttack());
                    card.damage(targetCard.getAttack());
                }
            }
            Game.removeDeadCards(players);
        }
    }

    /**
     * Allows a player to play cards from their hand.
     *
     * @param player The player to play cards from.
     */
    private void cardPlays(Player player) {
        List<Card> playerHandCopy = new ArrayList<>(player.getHand().getCards());
        for (Card card : playerHandCopy) {
            if (card.getManaCost() <= player.getManaAvailable()) {
                if (inputManager.playCardPrompt(player, card)) {
                    game.playCardFromHand(player, card);
                }
            }
        }
    }

    /**
     * Plays a turn for a player.
     * <p>
     * This method plays a turn for a player.
     * It checks for a winner and removes defeated players from the game.
     * It also checks for dead players and displays the final play state.
     * If there is a winner, the game is stopped.
     * If the player is dead, the turn is skipped.
     * If the player is not dead, the player is prompted to play cards and attack.
     *
     * @param player The player to play a turn for.
     * @see #checkForWinner()
     * @see #checkForDeadPlayer(Player)
     * @see #cardAttacks(Player)
     * @see #cardPlays(Player)
     * @see #stop()
     * @see #isRunning()
     */
    private void playTurn(Player player) {
        if (checkForWinner()) {
            outputManager.displayWinner(players.get(0));
            stop();
            return;
        }

        if (player.isDead()) {
            return;
        }

        outputManager.displayFatigueDamage(player);
        game.startTurn(player);

        if (checkForDeadPlayer(player)) {
            outputManager.displayDefeated(player);
            return;
        }

        cardAttacks(player);

        if (checkForWinner()) {
            outputManager.displayWinner(players.get(0));
            stop();
            return;
        }

        cardPlays(player);
        outputManager.displayFinalPlayState(player);
    }

}
