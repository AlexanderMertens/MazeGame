package maze_game.gameobjects.interactive;

import maze_game.flag.Flag;
import maze_game.state.GameState;

/**
 * Represents a mechanism linked to a previous mechanism. The mechanism can only
 * be successfully interacted with if the previous mechanism has already been
 * interacted with, e.g. This button only takes effect if some other button has
 * already been pressed. If any of the mechanisms are interacted with in the
 * wrong order, all the mechanisms are reset.
 * 
 * The first mechanism in the series has null as the previous mechanism.
 */
public class LinkedMechanism extends InteractiveObject {
    private LinkedMechanism previousMechanism;
    // If the mechanism is trapped, the player is hurt each time they hit the wrong
    // mechanism
    private boolean trapped;

    public LinkedMechanism(String name, String description, String initialDialogue, String defaultDialogue,
            LinkedMechanism previousMechanism, boolean trapped) {
        super(name, description, initialDialogue, defaultDialogue, false);
        this.previousMechanism = previousMechanism;
        this.trapped = trapped;
    }

    /**
     * The player interacts with the mechanism and the mechanism responds
     * appropriately.
     * 
     * If the the mechanism has already been activated, the method does nothing and
     * returns a Flag containing the message that the interaction had no effect.
     * 
     * If the previous mechanism has been activated or if the mechanism has no
     * previous mechanism, the mechanism is activated. If the mechanism has a
     * previous mechanism, but it isn't activated, all the mechanisms in the
     * sequence reset and if it's trapped, the player is hurt. At last it prints out
     * the appropriate dialogue depending on whether it's active or not and returns
     * a success Flag.
     */
    public Flag interact(GameState gameState) {
        if (isActive()) {
            return Flag.NO_EFFECT;
        }
        if (!hasPrevious() || previousMechanism.isActive()) {
            activate();
        } else {
            if (trapped) {
                gameState.getHit();
            }
            resetPrevious();
        }
        System.out.println(getDialogue());
        return Flag.INTERACTED;
    }

    /**
     * Resets the mechanism along with the previous mechanism in the link if there
     * is one.
     */
    public void reset() {
        super.reset();
        resetPrevious();
    }

    private void resetPrevious() {
        if (hasPrevious()) {
            previousMechanism.reset();
        }
    }

    private boolean hasPrevious() {
        return previousMechanism != null;
    }
}
