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

    public void resetHasInteracted() {
        super.resetHasInteracted();
        resetPrevious();
    }

    public Flag interact(GameState gameState) {
        if (hasInteracted()) {
            return Flag.NO_EFFECT;
        }
        if (!hasPrevious() || (hasPrevious() && previousMechanism.hasInteracted())) {
            setHasInteracted();
        } else {
            if (trapped) {
                gameState.getHit();
            }
            resetPrevious();
        }
        System.out.println(getDialogue());
        return Flag.INTERACTED;
    }

    private void resetPrevious() {
        if (hasPrevious()) {
            previousMechanism.resetHasInteracted();
        }
    }

    private boolean hasPrevious() {
        return previousMechanism != null;
    }
}
