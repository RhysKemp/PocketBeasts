/*
 * This file is part of PocketBeasts.
 *
 * PocketBeasts is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PocketBeasts is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <https://www.gnu.org/licenses/>.
 */
package cis2039.pocketbeasts;

import cis2039.pocketbeasts.gameengine.GameManager;
import cis2039.pocketbeasts.gameengine.PlayerManager;
import cis2039.pocketbeasts.interfaces.ICard;
import cis2039.pocketbeasts.models.Card;
import cis2039.pocketbeasts.decorators.AttackBoostCardDecorator;
import cis2039.pocketbeasts.models.Game;
import cis2039.pocketbeasts.models.Player;
import cis2039.pocketbeasts.ui.textbased.ConsoleInputManager;
import cis2039.pocketbeasts.ui.textbased.ConsoleOutputManager;

import java.util.ArrayList;

/**
 *
 * @author Steven Mead
 * @author Chris Curry
 * @author Rhys Kemp
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("PocketBeasts decorated card tests");

        Card card = new Card("1", "Test Card", 1, 1, 1);
        System.out.println("Card: " + card.getName() + " Attack: " + card.getAttack());

        System.out.println(card);

        ICard attackCard = new AttackBoostCardDecorator(card);
        System.out.println("Card: " + attackCard.getName() + " Attack: " + attackCard.getAttack());

        System.out.println(attackCard);

//        // SEE README.md FOR INSTRUCTIONS ON HOW TO RUN & TEST THE APPLICATION
//
//        // Initialise game and populate with players
//        PlayerManager playerManager = new PlayerManager("Steve", "Chris");
//        ArrayList<Player> players = playerManager.getPlayers();
//
//        // Start game
//        Game game = new Game();
//        game.addPlayers(players);
//        game.newGame();
//
//        // Start game loop in text-based mode
//        ConsoleOutputManager consoleOutputManager = new ConsoleOutputManager();
//        ConsoleInputManager consoleInputManager = new ConsoleInputManager();
//        GameManager gameManager = new GameManager(game, consoleOutputManager, consoleInputManager);
//        gameManager.start();

    }
}
