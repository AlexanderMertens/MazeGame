package maze_game.input;

/**
 * Enumeration class CommandWord models a command input from the user. The class
 * contains a field commandString representing the command.
 * 
 * @author Alexander Mertens
 */
public enum CommandWord {
    GO("go"), TAKE("take"), DROP("drop"), BACK("back"), QUIT("quit"), HELP("help"), LOOK("look"), OPEN("open"),
    INTERACT("interact"), UNKNOWN("?");

    private String commandString;

    /**
     * Constructs a CommandWord with the given commandString.
     * 
     * @param commandString
     */
    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    public String toString() {
        return commandString;
    }
}
