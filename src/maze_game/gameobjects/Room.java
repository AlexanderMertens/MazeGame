package maze_game.gameobjects;

import java.util.HashMap;
import java.util.Map;

import maze_game.directions.Direction;

/**
 * The Room class models a GameObject that represents a single room in the maze.
 * The Room class extends the Container class, because a Room can contain items.
 * An instance of Room has a HashMap field named exits with Directions as keys
 * and other instances of Room as values. The class implements methods to set an
 * exit in a certain direction or retrieve a room that is located in a direction
 * with respect to this room.
 * 
 * @author Alexander Mertens
 */
public class Room extends Container {
    private Map<Direction, Door> exits;

    /**
     * Constructs an empty room with no exits.
     * 
     * @param name        Name of the room.
     * @param description Description of the room.
     */
    public Room(String name, String description) {
        super(name, description);
        exits = new HashMap<>();
    }

    /**
     * Adds or overwrites an exit of this room. The direction of the exit is set
     * with the given parameter.
     * 
     * @param direction Direction of the exit.
     * @param exit      Door object that represents the exit.
     */
    public void setExit(Direction direction, Door exit) {
        exits.put(direction, exit);
    }

    /**
     * Retrieves an exit of this room in the given direction. Returns null if
     * there's no Door in the given direction.
     * 
     * @param direction Direction of the Door to be retrieved.
     * @return Returns Door object in the given direction.
     */
    public Door getExit(Direction direction) {
        return exits.get(direction);
    }

    /**
     * @return Returns a String describing the room and its contents.
     */
    public String getRoomString() {
        String result = "\nYou are in " + getName() + ".\n" + getDescription();
        if (!isEmpty()) {
            result += "\n" + getInventoryString();
        }
        return result;
    }

    /**
     * @return Returns a String with all the possible exits of the room.
     */
    public String getExitsString() {
        String result = "There are exits in the directions:";
        for (Direction direction : exits.keySet()) {
            result += "  " + direction.toString();
        }
        return result;
    }

    /**
     * @return Returns a String with the contents of the room.
     */
    @Override
    public String getInventoryString() {
        return getName() + " contents:\n" + super.getInventoryString();
    }

    /**
     * @return Returns a description of the object with name objectName, if there is
     *         such an object. If there's no such object, return null.
     */
    @Override
    public String findDescription(String objectName) {
        String description = super.findDescription(objectName);
        if (description != null) {
            return description;
        }

        Direction direction = Direction.convertString(objectName);
        Door door = getExit(direction);
        if (door != null) {
            return door.getDescription();
        } else {
            return null;
        }
    }
}
