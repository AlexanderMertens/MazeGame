package maze_game.gameobjects.interactive;

import maze_game.flag.Flag;
import maze_game.gameobjects.GameObject;
import maze_game.state.GameState;

/**
 * This class represents an object in the game world that the player can
 * interact with. Interacting with it can influence the gameWorld or it can
 * unlock some dialogue.
 * 
 * The class has three fields, two strings representing possible dialogue the
 * player can unlock and a boolean hasInteracted, which is true if the player
 * has recently interacted with the object.
 * 
 * It implements several methods for e.g. retrieving dialogue and most crucially
 * has an abstract method interact that determines what the object does upon
 * interaction.
 * 
 * @author Alexander Mertens
 */
public abstract class InteractiveObject extends GameObject {
    // Dialogue the player gets when first interacting.
    private String initialDialogue;
    // Dialogue the player gets when interacting with the object again.
    private String defaultDialogue;
    // Records whether the player has activated the object.
    private boolean active;
    // Records whether the player is allowed to remove this from the room.
    private boolean removable;

    /**
     * Creates a character with given name, description and dialogue. Initially sets
     * the object to inactive.
     * 
     * @param name            Name of object.
     * @param description     Description of object.
     * @param initialDialogue Dialogue upon first interacting with the object.
     * @param defaultDialogue Dialogue after interacting with the object.
     */
    public InteractiveObject(String name, String description, String initialDialogue, String defaultDialogue,
            boolean removable) {
        super(name, description);
        this.initialDialogue = initialDialogue;
        this.defaultDialogue = defaultDialogue;
        this.active = false;
        this.removable = removable;
    }

    /**
     * @return Returns true if the player has already activated with the object.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Records that the player has activated the object.
     */
    public void activate() {
        this.active = true;
    }

    /**
     * Records that the object has been reset, setting it to inactive.
     */
    public void reset() {
        this.active = false;
    }

    /**
     * @return Returns true if the object is allowed to be removed from the room it
     *         is in.
     */
    public boolean isRemovable() {
        return this.removable;
    }

    /**
     * Returns a String with the dialogue the object presents. This can vary
     * depending on whether the player has already interacted with the object.
     * 
     * @return A String containing the appropriate dialogue.
     */
    public String getDialogue() {
        if (isActive()) {
            return defaultDialogue;
        } else {
            return initialDialogue;
        }
    }

    /**
     * Returns a String containing a longer description of the object.
     */
    @Override
    public String getLongDescription() {
        return getName() + ": " + getDescription();
    }

    /**
     * The player interacts with the object and the object responds in some way by
     * e.g. printing out dialogue, opening a door, harming the player, etc.
     * 
     * @param gameState The state of the game that the object can influence.
     * @return Returns a Flag representing succes or failure and carrying a message.
     */
    public abstract Flag interact(GameState gameState);
}
