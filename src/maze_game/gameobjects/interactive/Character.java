package maze_game.gameobjects.interactive;

/**
 * This class represents a character in the game world. A character has a string
 * of dialogue for when it meets the player, and another string of dialogue for
 * afterwards.
 * 
 * @author Alexander Mertens
 */
public class Character extends InteractiveObject {
    private String initialDialogue;
    private String defaultDialogue;
    // Records whether the character has met the player or not.
    private boolean hasMet;

    /**
     * Creates a character with given name, description and dialogue.
     * 
     * @param name            Name of character.
     * @param description     Description of character.
     * @param initialDialogue Dialogue upon meeting of the character.
     * @param defaultDialogue Dialogue after meeting of the character.
     */
    public Character(String name, String description, String initialDialogue, String defaultDialogue) {
        super(name, description);
        this.initialDialogue = initialDialogue;
        this.defaultDialogue = defaultDialogue;
        this.hasMet = false;
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
