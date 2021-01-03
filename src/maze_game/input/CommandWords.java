package maze_game.input;

import java.util.HashMap;

/**
 * The class CommandWords holds all the command words that can be used as input
 * in the Maze game. The class implements functionality to convert String input
 * to CommandWord objects.
 */
public class CommandWords {
    private HashMap<String, CommandWord> commands;

    public CommandWords() {
        commands = new HashMap<>();
        for (CommandWord command : CommandWord.values()) {
            if (command != CommandWord.UNKNOWN) {
                commands.put(command.toString(), command);
            }
        }
    }

    /**
     * Checks whether a given String is a valid command word.
     * 
     * @return Returns true if the given String is a valid command, false if it
     *         isn't.
     */
    public boolean isCommandWord(String potentialCommand) {
        return (commands.containsKey(potentialCommand));
    }

    /**
     * Builds a String containing all the valid command words of the game.
     * 
     * @return The String with all valid command words.
     */
    public String showCommands() {
        String result = "";
        for (String commandWord : commands.keySet()) {
            result += " " + commandWord;
        }
        return result;
    }

    /**
     * Converts a String to an instance of CommandWord if it is a valid command
     * word. Otherwise, it returns UNKNOWN.
     * 
     * @return CommandWord corresponding to the given command word String.
     */
    public CommandWord getCommandWord(String potentialCommand) {
        if (isCommandWord(potentialCommand)) {
            return commands.get(potentialCommand);
        } else {
            return CommandWord.UNKNOWN;
        }
    }
}
