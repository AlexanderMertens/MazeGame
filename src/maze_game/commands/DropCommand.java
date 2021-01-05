package maze_game.commands;

import maze_game.state.GameState;

/**
 * Command that when executed instructs gameState to remove an item from the
 * player's inventory and into the room.
 * 
 * @author Alexander Mertens
 */
public class DropCommand extends Command {
    public DropCommand(String argument) {
        super(argument);
    }

    @Override
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