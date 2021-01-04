package maze_game.commands;

import maze_game.state.GameState;

public class TakeCommand extends Command {
    public TakeCommand(String argument) {
        super(argument);
    }

    public boolean execute(GameState gameState) {
        if (!hasArgument()) {
            System.out.println("Take what?");
            return false;
        }
        String itemName = getArgument();
        if (!gameState.playerTakes(itemName)) {
            System.out.println("There is no item here with the name " + itemName);
        }
        System.out.println(gameState.getStateDescription());
        return false;
    }
}