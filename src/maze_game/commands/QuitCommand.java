package maze_game.commands;

import maze_game.state.GameState;

public class QuitCommand extends Command {
    public QuitCommand(String argument) {
        super(argument);
    }

    public boolean execute(GameState gameState) {
        return true;
    }
}
