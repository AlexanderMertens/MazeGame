package maze_game.gameobjects.interactive;

import maze_game.gameobjects.Door;
import maze_game.state.GameState;

/**
 * When this mechanism is successfully interacted with (possibly after a series
 * of other mechanism), it opens a door.
 */
public class LockMechanism extends LinkedMechanism {
    private Door door;

    public LockMechanism(String name, String description, String initialDialogue, String defaultDialogue,
            LinkedMechanism previousMechanism, boolean trapped) {
        super(name, description, initialDialogue, defaultDialogue, previousMechanism, trapped);
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public void interact(GameState gameState) {
        super.interact(gameState);
        if (hasInteracted()) {
            door.unlock(this);
        }
    }
}
