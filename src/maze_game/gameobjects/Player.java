package maze_game.gameobjects;

/**
 * This class represents the player of maze game.
 * 
 * The class extends the Container class such that the Player class has the
 * functionality to hold items.
 * 
 * The class also has fields currentRoom to record the location of the player
 * and a Stack of Room objects to recall the previous rooms the player has
 * visited.
 * 
 * @author Alexander Mertens
 */
public class Player extends Container {
    /**
     * Creates an instance of Player with name and currentRoom as its current
     * location. Initially both the Stack of previously visited rooms and the
     * inventory of the player is empty.
     * 
     * @param name        Name of player.
     * @param description Description of the player.
     */
    public Player(String name, String description) {
        super(name, description);
    }

    public String getInventoryString() {
        return getName() + " inventory:" + super.getInventoryString();
    }

}