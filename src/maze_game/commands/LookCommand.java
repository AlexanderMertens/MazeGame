package maze_game.commands;

import maze_game.state.GameState;

/**
 * Command that when executed prints out a description of the given item, or
 * prints a description of the current state if no argument is given.
 */
public class LookCommand extends Command {

    public LookCommand(String argument) {
        super(argument);
    }

    public boolean execute(GameState gameState) {
        if (hasArgument()) {
            System.out.println(gameState.findDescription(getArgument()));
        } else {
            System.out.println(gameState.getStateDescription());
        }
        return false;
    }

}
