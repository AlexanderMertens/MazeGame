package maze_game.gameobjects;

import maze_game.directions.Direction;
import maze_game.gameobjects.interactive.InteractiveObject;
import maze_game.mapping.GameObjectMap;
import maze_game.mapping.GameObjectMapByName;

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
    // A map of all exits
    private GameObjectMap<Direction, Door> exits;
    // A map of all objects in the room that the player can interact with.
    private GameObjectMapByName<InteractiveObject> objects;

    /**
     * Constructs an empty room with no exits.
     * 
     * @param name        Name of the room.
     * @param description Description of the room.
     */
    public Room(String name, String description) {
        super(name, description);
        exits = new GameObjectMap<>();
        objects = new GameObjectMapByName<>();
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
    public String getLongDescription() {
        String result = "You are in the " + getName() + ".\n\n" + getDescription() + "\n";
        if (!isInventoryEmpty()) {
            result += "\n" + getInventoryString();
        }
        if (hasObjects()) {
            result += "\nInteractive objects:" + objects.getDescription();
        }
        return result;
    }

    /**
     * @return Returns a String with all the possible exits of the room.
     */
    public String getExitsString() {
        return "There are exits in the directions: " + exits.getKeyDescription();
    }

    /**
     * @return Returns a String with the contents of the room.
     */
    public String getInventoryString() {
        return getName() + " contents:" + super.getLongDescription();
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
        description = objects.findDescription(objectName);
        if (description != null) {
            return description;
        }

        Direction direction = Direction.convertString(objectName);
        description = exits.findDescription(direction);
        return description;
    }

    /**
     * Adds object to the object map, if there's no object with that name in the map
     * already.
     * 
     * @param object The object to be added.
     */
    public void addInteractive(InteractiveObject object) {
        if (!hasObject(object.getName().toLowerCase())) {
            objects.add(object);
        }
    }

    /**
     * Returns the InteractiveObject corresponding to the given objectName.
     * 
     * @param objectName Name of the object.
     * @return The corresponding InteractiveObject.
     */
    public InteractiveObject getObject(String objectName) {
        return objects.get(objectName);
    }

    /**
     * Removes the object with the given name from the contents of the room if
     * there's such an object, and returns it. Returns null if there's no such
     * object.
     * 
     * @param objectName Name of the object to be removed.
     * 
     * @return The object that was removed.
     */
    public InteractiveObject removeObject(String objectName) {
        return objects.remove(objectName);
    }

    /**
     * @param objectName Name of the object.
     * @return Returns true if the room contains an object with the given
     *         objectName.
     */
    public boolean hasObject(String objectName) {
        return objects.containsKey(objectName);
    }

    /**
     * @return Returns true if the rooms contains any InteractiveObject.
     */
    public boolean hasObjects() {
        return !objects.isEmpty();
    }
}
