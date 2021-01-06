package maze_game.input;

import java.util.Scanner;

import maze_game.commands.Command;
import maze_game.commands.CommandFactory;

/**
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and tries
 * to interpret the line as a two word command. It returns the command as an
 * object of class Command.
 *
 * The parser has a set of known command words. It checks user input against the
 * known commands, and if the input is not one of the known commands, it returns
 * a command object that is marked as an unknown command.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Parser {
    private CommandWords commands; // holds all valid command words
    private Scanner reader; // source of command input
    private CommandFactory commandFactory;

    /**
     * Create a parser to read from the terminal window.
     */
    public Parser() {
        commandFactory = new CommandFactory();
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * @return The next command from the user.
     */
    public Command getCommand() {
        String inputLine; // will hold the full input line
        CommandWord commandWord = null;
        String argument = null;

        System.out.print("> "); // print prompt

        inputLine = reader.nextLine();

        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        if (tokenizer.hasNext()) {
            commandWord = commands.getCommandWord(tokenizer.next()); // get first word
            if (tokenizer.hasNext()) {
                argument = tokenizer.next();
                while (tokenizer.hasNext())
                    argument += " " + tokenizer.next(); // get argument
            }
        }
        tokenizer.close();
        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        return commandFactory.getCommand(commandWord, argument);
    }

    public String showCommands() {
        return commands.showCommands();
    }
}
