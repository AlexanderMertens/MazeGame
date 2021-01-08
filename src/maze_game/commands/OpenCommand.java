package maze_game.commands;

import maze_game.flag.Flag;
import maze_game.state.GameState;

/**
 * Command that when executed tries to open a door with a key item in the
 * player's inventory. Prints out the Flag message.
 * 
 * @author Alexander Mertens
 */
public class OpenCommand extends Command {

    public OpenCommand(String argument) {
        super(argument);
    }

    public boolean execute(GameState gameState) {
        Flag flag = gameState.openDoor(getArgument());
        flag.printMessage();
        return false;
    }

}
