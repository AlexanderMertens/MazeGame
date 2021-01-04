package maze_game.commands;

import maze_game.state.GameState;

public class UnknownCommand extends Command {
    public UnknownCommand(String argument) {
        super(argument);
    }

    public boolean execute(GameState gameState) {
        System.out.println("The command can not be recognized. Please enter help for a list of known commands.");
        return false;
    }
}
