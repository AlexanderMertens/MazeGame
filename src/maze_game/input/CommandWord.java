package maze_game.input;

/**
 * Enumeration class CommandWord models a command input from the user. The class
 * contains a field commandString representing the command.
 * 
 * @author Alexander Mertens
 */
public enum CommandWord {
    GO("go"), TAKE("take"), DROP("drop"), BACK("back"), LOOK("look"), OPEN("open"), INTERACT("interact"), QUIT("quit"),
    HELP("help"), HINT("hint"), UNKNOWN("?");

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
