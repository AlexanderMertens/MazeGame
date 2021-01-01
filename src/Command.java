/**
 * 
 */
public class Command {
    private CommandWord command;
    private String argument;

    /**
     * Create a command object.
     * 
     * @param command  The first word of the command. Is UNKNOWN if the command
     *                 wasn't understood.
     * @param argument The second word of the command. Can be null if the command
     *                 doesn't require an argument.
     */
    public Command(CommandWord command, String argument) {
        this.command = command;
        this.argument = argument;
    }

    /**
     * Return the commandWord of this command. If the command was not understood,
     * the result is UNKNOWN.
     * 
     * @return The command word.
     */
    public CommandWord getCommandWord() {
        return command;
    }

    /**
     * @return The second word of this command. Returns null if there was no second
     *         word.
     */
    public String getArgument() {
        return argument;
    }

    /**
     * @return true if this command was not understood.
     */
    public boolean isUnknown() {
        return (command == CommandWord.UNKNOWN);
    }

    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord() {
        return (argument != null);
    }
}
