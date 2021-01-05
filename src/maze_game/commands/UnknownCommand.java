package maze_game.commands;

import maze_game.state.GameState;

/**
 * Represents an unknown command. Executing it simply prints an error message.
 * 
 * @author Alexander Mertens
 */
public class UnknownCommand extends Command {
    public UnknownCommand(String argument) {
        super(argument);
    }

    @Override
    public boolean execute(GameState gameState) {
        System.out.println("The command can not be recognized. Please enter help for a list of known commands.");
        return false;
    }
}
