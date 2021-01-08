package maze_game.commands;

import maze_game.state.GameState;

/**
 * Command that when executed prints out a description of the given item, or
 * prints a description of the current state if no argument is given. Prints out
 * a message if the execution of the command has failed.
 * 
 * @author Alexander Mertens
 */
public class LookCommand extends Command {

    public LookCommand(String argument) {
        super(argument);
    }

    @Override
    public boolean execute(GameState gameState) {
        if (hasArgument()) {
            String description = gameState.findDescription(getArgument());
            if (description != null) {
                System.out.println(description);
            } else {
                System.out.println("There's no such object.");
            }
        } else {
            System.out.println(gameState.getStateDescription());
        }
        return false;
    }

}
