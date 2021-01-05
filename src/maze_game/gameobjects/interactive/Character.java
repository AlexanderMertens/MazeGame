package maze_game.gameobjects.interactive;

import maze_game.state.GameState;

/**
 * This class represents a character in the game world. A character has a string
 * of dialogue for when it meets the player, and another string of dialogue for
 * afterwards.
 * 
 * @author Alexander Mertens
 */
public class Character extends InteractiveObject {

    /**
     * Creates a character with given name, description and dialogue.
     * 
     * @param name            Name of character.
     * @param description     Description of character.
     * @param initialDialogue Dialogue upon meeting of the character.
     * @param defaultDialogue Dialogue after meeting of the character.
     */
    public Character(String name, String description, String initialDialogue, String defaultDialogue) {
        super(name, description, initialDialogue, defaultDialogue);
    }

    public void interact(GameState gameState) {
        System.out.println(getDescription());

        if (!hasInteracted()) {
            setHasInteracted();
            gameState.addInteractive(this);
        }
    }
}
