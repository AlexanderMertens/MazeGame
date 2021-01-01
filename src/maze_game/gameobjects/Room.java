package maze_game.gameobjects;

import java.util.HashMap;

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
    private HashMap<Direction, Room> exits;

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
     * @param exit      Room object that represents the exit.
     */
    public void setExit(Direction direction, Room exit) {
        exits.put(direction, exit);
    }

    /**
     * Retrieves an exit of this room in the given direction. Returns null if
     * there's no Room in the given direction.
     * 
     * @param direction Direction of the Room to be retrieved.
     * @return Returns Room object in the given direction.
     */
    public Room getExit(Direction direction) {
        return exits.get(direction);
    }
}
