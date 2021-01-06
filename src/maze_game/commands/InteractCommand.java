package maze_game.commands;

import maze_game.flag.Flag;
import maze_game.state.GameState;

/**
 * Command that when executed instructs gameState to interact with the given
 * object in the argument of the command.
 * 
 * @author Alexander Mertens
 */
public class InteractCommand extends Command {

    public InteractCommand(String argument) {
        super(argument);
    }

    public boolean execute(GameState gameState) {
        Flag flag = gameState.interact(getArgument());
        if (!flag.isSuccess()) {
            flag.printMessage();
        }
        return false;
    }

}
