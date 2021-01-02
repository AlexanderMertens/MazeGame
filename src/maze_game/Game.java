package maze_game;

import maze_game.directions.Direction;
import maze_game.gameobjects.Item;
import maze_game.gameobjects.Player;
import maze_game.gameobjects.Room;
import maze_game.input.Command;
import maze_game.input.CommandWord;
import maze_game.input.Parser;

public class Game {
    private Parser parser;
    private Player player;

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

        player = new Player("Alexander", "", outside);
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
            finished = processCommand(command);
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

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();
        switch (commandWord) {
            case UNKNOWN -> {
                System.out.println("I don't know what you mean...");
                return false;
            }
            case GO -> goRoom(command);
            case TAKE -> take(command);
            case DROP -> drop(command);
            case QUIT -> wantToQuit = quit(command);
            case HELP -> printHelp();
            case LOOK -> look();
            case BACK -> goBack();
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information. Here we print some stupid, cryptic message
     * and a list of the command words.
     */
    private void printHelp() {
        System.out.println("Player " + player.getName() + "is lost and alone, and wanders");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
        System.out.println();
    }

    /**
     * Try to go in one direction. If there is an exit, enter the new room,
     * otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        Direction direction = Direction.convertString(command.getArgument());
        if (!player.go(direction)) {
            System.out.println("There is no door!");
        }
        printLocationInfo();
    }

    private void goBack() {
        if (!player.goBack()) {
            System.out.println("You can't go back, you are already at the beginning!");
            return;
        }
        printLocationInfo();
    }

    private void take(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }

        String itemName = command.getArgument();
        if (player.take(itemName)) {
            printLocationInfo();
        } else {
            System.out.println("There is no item here with the name " + itemName);
        }
    }

    private void drop(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Drop what?");
            return;
        }
        String itemName = command.getArgument();
        if (!player.drop(itemName)) {
            System.out.println("You don't have item " + itemName + "!");
        }
        printLocationInfo();
    }

    /**
     * "Quit" was entered. Check the rest of the command to see whether we really
     * quit the game.
     * 
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true; // signal that we want to quit
        }
    }

    private void look() {
        System.out.println(player.getLongDescription());
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }

    private void printLocationInfo() {
        System.out.println(player.getLongDescription());
    }
}