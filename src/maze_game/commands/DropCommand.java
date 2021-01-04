package maze_game.commands;

import maze_game.state.GameState;

public class DropCommand extends Command {
    public DropCommand(String argument) {
        super(argument);
    }

    public boolean execute(GameState gameState) {
        if (!hasArgument()) {
            System.out.println("Drop what?");
            return false;
        }
        String itemName = getArgument();
        if (!gameState.playerDrops(itemName)) {
            System.out.println("You don't have item " + itemName + "!");
            return false;
        }
        System.out.println(gameState.getStateDescription());
        return false;
    }
}