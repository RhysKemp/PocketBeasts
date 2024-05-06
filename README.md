# PocketBeasts Game

PocketBeasts is a card game where players take turns to play cards from their hand and attack their opponents. The game continues until there is only one player left, who is declared the winner.

## Prerequisites

- Java Development Kit (JDK) 8 or later
- Maven

## How to Run the Application

1. Open a terminal window.
2. Navigate to the root directory of the project.
3. Run the following command to compile the project:

```bash
mvn clean compile
```

4. Run the following command to start the game:

```bash
mvn exec:java -Dexec.mainClass="cis2039.pocketbeasts.Main"
```

## How to Test the Application

The project includes a `TestInputManager` class that simulates user input during testing. This class is used in the `GameManager` class to simulate a game loop.

1. Open a terminal window.
2. Navigate to the root directory of the project.
3. Run the following command to run the tests:

```bash
mvn test
```

## Game Rules

- Each player starts with a hand of cards and a certain amount of mana.
- On their turn, a player can play a card from their hand if they have enough mana.
- Players can attack each other with their cards.
- The game continues until there is only one player left.

## Configurations

The game configurations are stored in the `Config` class. This class contains constants that are used throughout the game. Here are some of the configurations:

- `MAX_MANA`: The maximum amount of mana a player can have.
- `INITIAL_HEALTH`: The initial health of a player.
- `INITIAL_HAND_SIZE`: The initial number of cards in a player's hand.
- `FATIGUE_DAMAGE`: The amount of damage a player takes when their deck is empty.
- `GLOBAL_ATTACK_BUFF`: The amount of attack buff applied globally to all cards in play.
- `GLOBAL_HEALTH_BUFF`: The amount of health buff applied globally to all cards in play.

These configurations can be adjusted to change the game dynamics. For example, increasing the `INITIAL_HAND_SIZE` will give players more options at the start of the game, potentially leading to more strategic gameplay. Similarly, adjusting the `FATIGUE_DAMAGE` can change how players manage their decks throughout the game. Changing the `INITIAL_HEALTH` constant to 1 can be very useful for testing purposes.

## Game Classes

- `Main`: The entry point of the application.
- `Game`: Represents a game of Pocket Beasts.
- `GameManager`: Manages the game loop.
- `PlayerManager`: Manages the players in the game.
- `Player`: Represents a player in the game.
- `Card`: Represents a card in the game.
- `Deck`: Represents a deck of cards.
- `Hand`: Represents a player's hand of cards.
- `ConsoleInputManager` and `ConsoleOutputManager`: Handle input and output for the text-based version of the game.

## License

This project is licensed under the GNU General Public License v3.0. For more details, see the [LICENSE](https://www.gnu.org/licenses/).
