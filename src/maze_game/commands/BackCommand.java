package maze_game.commands;

import maze_game.state.GameState;

/**
 * Command that when executed instructs gameState to move the player back to the
 * previous room.
 */
public class BackCommand extends Command {
    public BackCommand(String argument) {
        super(argument);
    }

    public boolean execute(GameState gameState) {
        if (!gameState.goBack()) {
            System.out.println("You can't go back, you are already at the beginning!");
            return false;
        }
        System.out.println(gameState.getStateDescription());
        return false;
    }
}
