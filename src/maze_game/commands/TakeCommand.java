package maze_game.commands;

import maze_game.state.GameState;

/**
 * Command that when executed instructs gameState to remove an item from the
 * room and put it in the player's inventory.
 * 
 * @author Alexander Mertens
 */
public class TakeCommand extends Command {
    public TakeCommand(String argument) {
        super(argument);
    }

    @Override
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