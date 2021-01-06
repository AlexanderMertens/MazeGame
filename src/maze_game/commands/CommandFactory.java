package maze_game.commands;

import maze_game.input.CommandWord;

/**
 * This class creates Command objects from given commandWords and argument.
 * 
 * @author Alexander Mertens
 */
public class CommandFactory {
    public Command getCommand(CommandWord commandWord, String argument) {
        switch (commandWord) {
            case DROP:
                return new DropCommand(argument);
            case BACK:
                return new BackCommand(argument);
            case GO:
                return new GoCommand(argument);
            case HELP:
                return new HelpCommand(argument);
            case LOOK:
                return new LookCommand(argument);
            case TAKE:
                return new TakeCommand(argument);
            case QUIT:
                return new QuitCommand(argument);
            case OPEN:
                return new OpenCommand(argument);
            case INTERACT:
                return new InteractCommand(argument);
            default:
                return new UnknownCommand(argument);
        }
    }
}
