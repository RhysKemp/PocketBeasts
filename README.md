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

This project is licensed under the GNU General Public License v3.0. For more details, see the [LICENSE](https://www.gnu.org/licenses/) file.
