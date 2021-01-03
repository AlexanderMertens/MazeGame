package maze_game.commands;

import maze_game.gameobjects.Player;

public class LookCommand extends Command {

    public LookCommand(String argument) {
        super(argument);
    }

    public boolean execute(Player player) {
        System.out.println(player.getLongDescription());
        return false;
    }

}
