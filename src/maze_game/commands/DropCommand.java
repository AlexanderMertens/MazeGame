package maze_game.commands;

import maze_game.flag.Flag;
import maze_game.state.GameState;

/**
 * Command that when executed instructs gameState to remove an item from the
 * player's inventory and into the room. Prints out a Flag message.
 * 
 * @author Alexander Mertens
 */
public class DropCommand extends Command {
    public DropCommand(String argument) {
        super(argument);
    }

    @Override
    public boolean execute(GameState gameState) {
        Flag flag = gameState.playerDrops(getArgument());
        flag.printMessage();
        return false;
    }
}