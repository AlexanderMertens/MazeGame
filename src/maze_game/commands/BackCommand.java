package maze_game.commands;

import maze_game.flag.Flag;
import maze_game.state.GameState;

/**
 * Command that when executed instructs gameState to move the player back to the
 * previous room.
 * 
 * @author Alexander Mertens
 */
public class BackCommand extends Command {
    public BackCommand(String argument) {
        super(argument);
    }

    @Override
    public boolean execute(GameState gameState) {
        Flag flag = gameState.goBack();
        if (!flag.isSuccess()) {
            flag.printMessage();
        }
        System.out.println(gameState.getStateDescription());
        return false;
    }
}
