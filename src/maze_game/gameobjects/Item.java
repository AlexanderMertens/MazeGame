package maze_game.gameobjects;

/**
 * A simple class for items in the game.
 */
public class Item extends GameObject {
    public Item(String name, String description) {
        super(name, description);
    }

    @Override
    public String getLongDescription() {
        return getName() + ": " + getDescription();
    }
}
