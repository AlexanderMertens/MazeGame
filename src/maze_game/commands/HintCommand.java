package maze_game.commands;

import maze_game.state.GameState;

/**
 * Executing this command prints a hint for the player.
 */
public class HintCommand extends Command {

    public HintCommand(String argument) {
        super(argument);
    }

    @Override
    public boolean execute(GameState gameState) {
        gameState.printHint();
        return false;
    }
}
