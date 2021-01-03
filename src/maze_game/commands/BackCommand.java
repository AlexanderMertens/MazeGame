package maze_game.commands;

import maze_game.gameobjects.Player;

public class BackCommand extends Command {
    public BackCommand(String argument) {
        super(argument);
    }

    public boolean execute(Player player) {
        if (!player.goBack()) {
            System.out.println("You can't go back, you are already at the beginning!");
            return false;
        }
        System.out.println(player.getLongDescription());
        return false;
    }
}
