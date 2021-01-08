package maze_game.commands;

import maze_game.state.GameState;

/**
 * This class represents a command that is given by the player and executed by
 * game.
 * 
 * Subclasses must implement the abstract execute command, such that the command
 * can be executed.
 * 
 * @author Alexander Mertens
 */
public abstract class Command {
    private String argument;

    /**
     * Create a command object.
     * 
     * @param argument The second word of the command. Can be null if the command
     *                 doesn't require an argument.
     */
    public Command(String argument) {
        this.argument = argument;
    }

    /**
     * @return Returns the argument of the command. Returns null if the command has
     *         no argument.
     */
    public String getArgument() {
        return argument;
    }

    /**
     * @return true if the command has a second word.
     */
    public boolean hasArgument() {
        return (argument != null);
    }

    /**
     * Executes the command.
     * 
     * @param player Player of the game.
     * @return Returns true if the game should terminate.
     */
    public abstract boolean execute(GameState gameState);
}
