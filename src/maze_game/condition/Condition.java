package maze_game.condition;

public abstract class Condition {
    private String message;

    public Condition(String message) {
        this.message = message;
    }

    public abstract boolean isSatisfied();

    public void printMessage() {
        System.out.println(message);
    }
}
