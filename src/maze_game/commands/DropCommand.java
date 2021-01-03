package maze_game.commands;

import maze_game.gameobjects.Player;

public class DropCommand extends Command {
    public DropCommand(String argument) {
        super(argument);
    }

    public boolean isUnknown() {
        return false;
    }

    public boolean execute(Player player) {
        if (!hasArgument()) {
            System.out.println("Drop what?");
            return false;
        }
        String itemName = getArgument();
        if (!player.drop(itemName)) {
            System.out.println("You don't have item " + itemName + "!");
            return false;
        }
        System.out.println(player.getLongDescription());
        return false;
    }
}