package maze_game.commands;

import maze_game.state.GameState;

/**
 * Command that when executed sends a signal to the game instructing it to quit.
 */
public class QuitCommand extends Command {
    public QuitCommand(String argument) {
        super(argument);
    }

    public boolean execute(GameState gameState) {
        return true;
    }
}
