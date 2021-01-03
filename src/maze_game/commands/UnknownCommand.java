package maze_game.commands;

import maze_game.gameobjects.Player;

public class UnknownCommand extends Command {
    public UnknownCommand(String argument) {
        super(argument);
    }

    public boolean isUnknown() {
        return true;
    }

    public boolean execute(Player player) {
        System.out.println("The command can not be recognized. Please enter help for a list of known commands.");
        return false;
    }
}
