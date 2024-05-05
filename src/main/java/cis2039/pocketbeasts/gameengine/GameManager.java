package cis2039.pocketbeasts.gameengine;

import cis2039.pocketbeasts.models.Card;
import cis2039.pocketbeasts.models.Game;
import cis2039.pocketbeasts.models.Player;
import cis2039.pocketbeasts.ui.textbased.ConsoleOutputManager;
import cis2039.pocketbeasts.ui.textbased.InputHandler;

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
 * @see Game
 * @see Player
 * @see Card
 * @see InputHandler
 * @see ConsoleOutputManager
 *
 * @author Rhys Kemp
 */
public class GameManager {
    private final Game game;
    private boolean isRunning;
    private final InputHandler inputHandler;
    private final ConsoleOutputManager consoleOutputManager;
    private final ArrayList<Player> players;

    /**
     * Creates a new game loop.
     *
     * @param game The game to loop.
     */
    public GameManager(Game game) {
        this.game = game;
        this.isRunning = true;
        this.inputHandler = new InputHandler();
        this.consoleOutputManager = new ConsoleOutputManager();
        this.players = game.getPlayers();
    }

    /**
     * Starts the game in text-based mode.
     *
     * @throws IllegalArgumentException if there are no players in the game
     */
    public void startTextBased() {
        if (game.getPlayers().isEmpty()) { // Defensive programming
            throw new IllegalArgumentException("At least one player is required.");
        }
        consoleOutputManager.printWelcomeMessage();
        inputHandler.waitForInput();

        while (this.isRunning) {
            List<Player> playersCopy = new ArrayList<>(players);
            for (Player player : playersCopy) {
                if (checkForWinner()) {
                    consoleOutputManager.printWinner(players.get(0));
                    break;
                }
                if (player.isDead()) {
                    continue;
                }
                consoleOutputManager.takenFatigueDamage(player);
                game.startTurn(player);
                if (checkForDeadPlayer(player)) {
                    consoleOutputManager.printDefeated(player);
                    continue;
                }
                cardAttacks(player);
                if (checkForWinner()) {
                    consoleOutputManager.printWinner(players.get(0));
                    break;
                }
                cardPlays(player);
                consoleOutputManager.printFinalPlayState(player);
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
            if (inputHandler.attackWithCardPrompt(player.getName(), card.getName())) {
                ArrayList<Player> otherPlayers = new ArrayList<>(players);
                otherPlayers.remove(player);
                int targetPlayerId = inputHandler.attackWhichPlayerPrompt(otherPlayers);
                Player targetPlayer = players.stream()
                        .filter(p -> p.getId() == targetPlayerId)
                        .findFirst()
                        .orElse(null);
                if (targetPlayer == null) {
                    // Change to exception
                    System.out.println("Invalid player chosen.");
                    continue;
                }
                int target = inputHandler.getAttackChoicePrompt(targetPlayer, card);
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
                if (inputHandler.playCardPrompt(player.getName(), card.getName())) {
                    game.playCardFromHand(player, card);
                }
            }
        }
    }


}
