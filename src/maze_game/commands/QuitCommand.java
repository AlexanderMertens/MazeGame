package maze_game.commands;

import maze_game.gameobjects.Player;

public class QuitCommand extends Command {
    public QuitCommand(String argument) {
        super(argument);
    }

    public boolean execute(Player player) {
        return true;
    }
}
