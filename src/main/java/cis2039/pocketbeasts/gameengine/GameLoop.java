package cis2039.pocketbeasts.gameengine;

import cis2039.pocketbeasts.models.Card;
import cis2039.pocketbeasts.models.Game;
import cis2039.pocketbeasts.models.Player;
import cis2039.pocketbeasts.utils.Config;
import cis2039.pocketbeasts.utils.InputHandler;

import java.util.ArrayList;
import java.util.List;

public class GameLoop {
    private final Game game;
    private boolean isRunning;
    private final InputHandler inputHandler;
    private final ArrayList<Player> players;

    /**
     * Creates a new game loop.
     *
     * @param game The game to loop.
     */
    public GameLoop(Game game) {
        this.game = game;
        this.isRunning = true;
        this.inputHandler = new InputHandler();
        this.players = game.getPlayers();
    }

    /**
     * Starts the game loop.
     *
     * @throws IllegalArgumentException if there are no players in the game
     */
    public void start() {
        if (game.getPlayers().isEmpty()) { // Defensive programming
            throw new IllegalArgumentException("At least one player is required.");
        }

        String message = "";
        while (this.isRunning) {
            List<Player> playersCopy = new ArrayList<>(players);
            for (Player player : playersCopy) {
                if (player.isDead()){ // Skip dead players
                    continue;
                }

                if (player.getDeck().isEmpty()) { // Check for fatigue damage, this happens inside game.startTurn()
                    System.out.println(player.getName() + "'s deck is empty, they have taken " + Config.FATIGUE_DAMAGE + " fatigue damage.");
                }

                // Add mana and draw card
                game.startTurn(player);

                checkAndRemoveDeadPlayer(player);
                checkForWinner();


                // Create inPlay copy
                List<Card> playerInPlayCopy = new ArrayList<>(player.getInPlay().getCards());

                // Cycle through cards in play to attack
                for (Card card : playerInPlayCopy) {
                    Game.removeDeadCards(players);
                    System.out.println(card.toString());
                    if (inputHandler.attackWithCardPrompt(player.getName(), card.getName())) {

                        ArrayList<Player> otherPlayers = new ArrayList<>(players);
                        otherPlayers.remove(player);

                        // Get target player's ID
                        int targetPlayerId = inputHandler.attackWhichPlayerPrompt(otherPlayers);

                        // Get target player
                        Player targetPlayer = players.stream()
                                .filter(p -> p.getId() == targetPlayerId)
                                .findFirst()
                                .orElse(null);
                        if (targetPlayer == null) {
                            System.out.println("Invalid player chosen.");
                            continue;
                        }

                        // Attack player or card
                        int target = inputHandler.getAttackChoicePrompt(targetPlayer, card);
                        if (target == 1) { // Player
                            Game.attackWithCard(card, targetPlayer);
                            checkAndRemoveDeadPlayer(targetPlayer);
                            checkForWinner();
                            if (!this.isRunning) {
                                break;
                            }
                            if (!targetPlayer.isDead()) {
                                System.out.println(targetPlayer.getName() + " is now at " + targetPlayer.getHealth() + " HP.");
                            }
                        } else { // Beast, index is `target-2`
                            Card targetCard = targetPlayer.getInPlay().getCard(target - 2);
                            targetCard.damage(card.getAttack());
                            card.damage(targetCard.getAttack());
                        }
                    }
                }

                if (!this.isRunning) {
                    break;
                }


                // Cycle through cards in play remove "dead" cards (health <= 0)
                Game.removeDeadCards(players);

                List<Card> playerHandCopy = new ArrayList<>(player.getHand().getCards());

                // Play cards from hand
                for (Card card : playerHandCopy) {
                    if (card.getManaCost() <= player.getManaAvailable()) {
                        System.out.println(card);

                        if (inputHandler.playCardPrompt(player.getName(), card.getName())) {
                            game.playCardFromHand(player, card);
                        }
                    }
                }

                // Print final play state
                System.out.println(player);
                System.out.println("\n".repeat(6));
            }
        }

        System.out.println(message);

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
    private void checkForWinner() {
        if (game.getPlayers().size() <= 1) {
            game.getPlayers().forEach(p -> System.out.println(p.getName() + " is the winner!"));
            this.stop();
        }
    }

    /**
     * Removes a dead player from the game.
     *
     * @param player The player to remove.
     */
    private void checkAndRemoveDeadPlayer(Player player){
        if (player.isDead()) {
            System.out.println(player.getName() + " is defeated!");
            game.removePlayer(player);
        }
    }

}
