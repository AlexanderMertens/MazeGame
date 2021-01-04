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

    /**
     * @return Returns a String representing the inventory of the player.
     */
    public String getInventoryString() {
        return getName() + " inventory:" + super.getInventoryString();
    }

    /**
     * Checks whether the given objectName is equal to this object's name or the
     * String "player" and returns its description if it is. Otherwise the method
     * tries to find an object with the given objectName in its inventory and return
     * its description. If no object is found, the method returns null.
     * 
     * @param objectName Name of the object to be found.
     * @return The description of the object with name objectName.
     */
    @Override
    public String findDescription(String objectName) {
        if (objectName.equals("player")) {
            return getDescription();
        } else {
            return super.findDescription(objectName);
        }
    }

}