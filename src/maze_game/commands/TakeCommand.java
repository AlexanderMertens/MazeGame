package maze_game.commands;

import maze_game.flag.Flag;
import maze_game.state.GameState;

/**
 * Command that when executed instructs gameState to remove an item from the
 * room and put it in the player's inventory. Prints out a Flag message if the
 * execution of the command has failed.
 * 
 * @author Alexander Mertens
 */
public class TakeCommand extends Command {
    public TakeCommand(String argument) {
        super(argument);
    }

    @Override
    public boolean execute(GameState gameState) {
        Flag flag = gameState.playerTakes(getArgument());
        if (!flag.isSuccess()) {
            flag.printMessage();
        }
        return false;
    }
}