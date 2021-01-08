package maze_game.commands;

import maze_game.flag.Flag;
import maze_game.state.GameState;

/**
 * Command that when executed tries to open a door with a key item in the
 * player's inventory. Prints out a Flag message if the execution of the command
 * has failed.
 * 
 * @author Alexander Mertens
 */
public class OpenCommand extends Command {

    public OpenCommand(String argument) {
        super(argument);
    }

    public boolean execute(GameState gameState) {
        Flag flag = gameState.openDoor(getArgument());
        if (flag.isSuccess()) {
            System.out.println("The door has been opened.");
        } else {
            flag.printMessage();
        }
        return false;
    }

}
