package maze_game.gameobjects.interactive;

import maze_game.flag.Flag;
import maze_game.gameobjects.Door;
import maze_game.state.GameState;

/**
 * When this mechanism is successfully interacted with (possibly after a series
 * of other mechanism), it opens a doorway). Because doorways are constructed
 * with two doors (a single door only goes in one direction), this class has two
 * fields that holds a door. Both fields can be null, in which case the
 * mechanism does nothing.
 */
public class LockMechanism extends LinkedMechanism {
    private Door door1;
    private Door door2;

    public LockMechanism(String name, String description, String initialDialogue, String defaultDialogue,
            LinkedMechanism previousMechanism, boolean trapped) {
        super(name, description, initialDialogue, defaultDialogue, previousMechanism, trapped);
        this.door1 = null;
        this.door2 = null;
    }

    public void setDoor(Door door) {
        if (door1 == null) {
            this.door1 = door;
        } else {
            this.door2 = door;
        }
    }

    /**
     * The mechanism is set to active if all the previous mechanisms in the link
     * have been set to active.
     * 
     * If the mechanism is successfully activated, unlock the door(s) connected to
     * it.
     * 
     * @return Returns an appropriate Flag with a message describing failure or
     *         success.
     */
    public Flag interact(GameState gameState) {
        Flag flag = super.interact(gameState);
        if (isActive()) {
            if (door1 != null) {
                door1.unlock(this);
            }
            if (door2 != null) {
                door2.unlock(this);
            }
        }
        return flag;
    }
}
