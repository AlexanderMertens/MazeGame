package maze_game.commands;

import maze_game.gameobjects.Player;

public class TakeCommand extends Command {
    public TakeCommand(String argument) {
        super(argument);
    }

    public boolean execute(Player player) {
        if (!hasArgument()) {
            System.out.println("Take what?");
            return false;
        }
        String itemName = getArgument();
        if (!player.take(itemName)) {
            System.out.println("There is no item here with the name " + itemName);
        }
        System.out.println(player.getLongDescription());
        return false;
    }
}