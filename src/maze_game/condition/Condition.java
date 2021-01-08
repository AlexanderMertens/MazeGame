package maze_game.condition;

/**
 * This class represents a condition that can be either true or false. It also
 * holds a message to be printed when the condition is satisfied.
 */
public abstract class Condition {
    private final String message;

    public Condition(String message) {
        this.message = message;
    }

    /**
     * @return Returns true if the condition is satisfied.
     */
    public abstract boolean isSatisfied();

    /**
     * Prints message.
     */
    public void printMessage() {
        System.out.println(message);
    }
}
