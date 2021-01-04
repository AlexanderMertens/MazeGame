package maze_game.commands;

import maze_game.input.CommandWord;
import maze_game.state.GameState;

public class HelpCommand extends Command {
    String commandWords;

    public HelpCommand(String argument) {
        super(argument);
        commandWords = "";
        for (CommandWord commandWord : CommandWord.values()) {
            if (commandWord != CommandWord.UNKNOWN) {
                commandWords += commandWord.toString();
            }
        }
    }

    public boolean execute(GameState gameState) {
        System.out.println("Your command words are: ");
        System.out.println(commandWords);
        return false;
    }
}
