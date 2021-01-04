package maze_game.commands;

import maze_game.state.GameState;

public class LookCommand extends Command {

    public LookCommand(String argument) {
        super(argument);
    }

    public boolean execute(GameState gameState) {
        System.out.println(gameState.getStateDescription());
        return false;
    }

}
