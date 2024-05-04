package cis2039.pocketbeasts.gameengine;

import cis2039.pocketbeasts.models.Card;
import cis2039.pocketbeasts.models.Game;
import cis2039.pocketbeasts.models.Player;
import cis2039.pocketbeasts.utils.InputHandler;

import java.util.ArrayList;
import java.util.List;

public class GameLoop {
    private final Game game;
    private boolean isRunning;
    private InputHandler inputHandler;
    private ArrayList<Player> players;

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
     * @throws IllegalArgumentException if there are no players in the game
     */
    public void start() {
        if (game.getPlayers().isEmpty()){ // Defensive programming
            throw new IllegalArgumentException("At least one player is required.");
        }

        String winningMessage = "";
        while (this.isRunning) {
            for (Player player : players) {
                // Add mana and draw card
                // TODO BUG: this throws an error when the player's deck is empty
                game.startTurn(player);

                // HACK assumes only one other player
                Player otherPlayer = null;
                for (Player iPlayer : players) {
                    if (iPlayer != player) {
                        otherPlayer = iPlayer;
                    }
                }
                if (otherPlayer == null) {
                    winningMessage = "Something has gone terribly wrong...";
                    this.stop();
                    break;
                }

                // Cycle through cards in play to attack
                for (Card card : player.getInPlay().getCards()) {
                    int index = 0;
                    System.out.println(card.toString());
                    if (inputHandler.attackWithCardPrompt(player.getName(), card.getName())) {

                        ArrayList<Player> otherPlayers = new ArrayList<>(players);
                        otherPlayers.remove(player);

                        // Get target player's ID
                        int targetPlayerId = inputHandler.attackWhichPlayerPrompt(otherPlayers);

                        for (Player iOtherPlayer : otherPlayers) {
                            if (targetPlayerId == iOtherPlayer.getId()) {
                                otherPlayer = iOtherPlayer;
                            }
                        }


                        int target = inputHandler.getAttackChoicePrompt(otherPlayer, card);
                        if (target == 1) { // Player
                            Game.attackWithCard(card, otherPlayer);
                            if (otherPlayer.isDead()) {
                                // if true returned players health <= 0
                                winningMessage = player.getName() + " wins!";
                                this.stop();
                                break;
                            }
                            System.out.println(otherPlayer.getName() + " is now at " + otherPlayer.getHealth());
                        } else { // Beast, index is `target-2`
                            Card targetCard = otherPlayer.getInPlay().getCard(target - 2);
                            targetCard.damage(card.getAttack());
                            card.damage(targetCard.getAttack());
                        }
                    }
                }

                if (!this.isRunning) {
                    break;
                }

                // Cycle through cards in play remove "dead" cards (health <= 0)
                // TODO BUG: this is not removing the card from the other player's inPlay during the attack phase
                Game.removeDeadCards(players);

                // create copy
                List<Card> playerHandCopy = new ArrayList<>(player.getHand().getCards());

                // Play cards from hand
                for (Card card : playerHandCopy) {
                    if (card.getManaCost() <= player.getManaAvailable()) {
                        System.out.println(card);

                        if (inputHandler.playCardPrompt(player.getName(), card.getName())) {
                            game.playCardFromHand(player, card);
                        } else {
                            // Handle empty deck
                            System.out.println("No cards left in the deck.");
                        }
                    }
                }

                // Print final play state
                System.out.println("\n".repeat(16));
                System.out.println(player);
            }
        }

        System.out.println(winningMessage);

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

    //
}
