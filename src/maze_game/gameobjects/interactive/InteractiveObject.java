package maze_game.gameobjects;

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
    // Records whether the player has interacted with the object.
    private boolean hasInteracted;

    /**
     * Creates a character with given name, description and dialogue.
     * 
     * @param name            Name of object.
     * @param description     Description of object.
     * @param initialDialogue Dialogue upon first interacting with the object.
     * @param defaultDialogue Dialogue after interacting with the object.
     */
    public InteractiveObject(String name, String description, String initialDialogue, String defaultDialogue) {
        super(name, description);
        this.initialDialogue = initialDialogue;
        this.defaultDialogue = defaultDialogue;
        this.hasInteracted = false;
    }

    /**
     * Returns a String with the dialogue the character says. This can vary
     * depending on whether the character has already met the player.
     * 
     * @return A String containing the appropriate dialogue.
     */
    public String getDialogue() {
        if (hasMet) {
            return defaultDialogue;
        } else {
            return initialDialogue;
        }
    }

    /**
     * @return Returns true if the character has already met the player, else it
     *         returns false.
     */
    public boolean hasMetPlayer() {
        return hasMet;
    }

    public void meet() {
        this.hasMet = true;
    }
}
