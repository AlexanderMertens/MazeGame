package maze_game.commands;

import maze_game.flag.Flag;
import maze_game.state.GameState;

/**
 * Command that when executed instructs the gameState to move the player in a
 * given direction. Prints out a Flag message if the execution failed.
 * 
 * @author Alexander Mertens
 */
public class GoCommand extends Command {

    public GoCommand(String argument) {
        super(argument);
    }

    @Override
    public boolean execute(GameState gameState) {
        Flag flag = gameState.go(getArgument());
        if (flag.isSuccess()) {
            System.out.println(gameState.getStateDescription());
        } else {
            flag.printMessage();
        }
        return false;
    }
}
