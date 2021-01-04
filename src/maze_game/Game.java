package maze_game;

import maze_game.directions.Direction;
import maze_game.gameobjects.Item;
import maze_game.gameobjects.Player;
import maze_game.gameobjects.Room;
import maze_game.commands.Command;
import maze_game.input.CommandWord;
import maze_game.input.Parser;
import maze_game.state.GameState;

public class Game {
    private Parser parser;
    private GameState gameState;

    /**
     * Creates the game and initializes all the rooms and their contents;
     */
    public Game() {
        createRooms();
        parser = new Parser();
    }

    /**
     * Creates all the rooms, links their exits and sets their contents.
     */
    private void createRooms() {
        Room outside, theater, pub, lab, office;

        // create the rooms
        outside = new Room("outside", "outside the main entrance of the university");
        theater = new Room("theater", "in a lecture theater");
        pub = new Room("pub", "in the campus pub");
        lab = new Room("lab", "in a computing lab");
        office = new Room("office", "in the computing admin office");

        // initialize room exits
        outside.setExit(Direction.EAST, theater);
        outside.setExit(Direction.SOUTH, lab);
        outside.setExit(Direction.WEST, pub);
        theater.setExit(Direction.WEST, outside);
        pub.setExit(Direction.EAST, outside);
        lab.setExit(Direction.NORTH, outside);
        lab.setExit(Direction.EAST, office);
        office.setExit(Direction.WEST, lab);

        // add items to rooms
        theater.addItem(new Item("Microphone", "", 1.5));
        pub.addItem(new Item("Glass", "", 0.2));
        pub.addItem(new Item("Chair", "", 2));
        office.addItem(new Item("Desk", "", 52.5));
        office.addItem(new Item("Laptop", "", 15.3));

        Player player = new Player("Alexander", "");
        gameState = new GameState(player, outside);
    }

    /**
     * Main play routine. Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop. Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = command.execute(gameState);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type " + CommandWord.HELP.toString() + " if you need help.");
        System.out.println();
        printLocationInfo();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }

    private void printLocationInfo() {
        System.out.println(gameState.getStateDescription());
    }
}