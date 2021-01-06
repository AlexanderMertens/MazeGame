package maze_game.state;

import java.util.Stack;

import maze_game.directions.Direction;
import maze_game.flag.Flag;
import maze_game.gameobjects.Door;
import maze_game.gameobjects.Item;
import maze_game.gameobjects.Player;
import maze_game.gameobjects.Room;
import maze_game.gameobjects.interactive.InteractiveObject;
import maze_game.mapping.GameObjectMapByName;

/**
 * Class that represents the current state of the gameworld. The class has
 * fields that hold the room the current player is in, the history of previously
 * visited rooms, the player object, a list of all rescued characters.
 * 
 * @author Alexander Mertens
 */
public class GameState {
    private Room currentRoom;
    private Stack<Room> previousRooms;
    private Player player;
    // A mapping of characters and objects that are in the party
    // The player can interact with these via the interact command
    private GameObjectMapByName<InteractiveObject> objects;

    /**
     * Creates a new GameState with given player and currentRoom.
     * 
     * @param player
     * @param currentRoom
     */
    public GameState(Player player, Room currentRoom) {
        this.player = player;
        this.currentRoom = currentRoom;
        previousRooms = new Stack<>();
        objects = new GameObjectMapByName<>();
    }

    /**
     * Attempts to move player in a certain direction. Returns false if currentRoom
     * contains no Room in that direction. Else it moves the player in that
     * direction, pushes the last currentRoom onto the Stack and return True.
     * 
     * @param direction The direction the player wishes to move.
     * @return Returns a success flag if the player is successfully moved, else it
     *         returns a flag containing a message describing what went wrong.
     */
    public Flag go(String directionString) {
        if (directionString == null) {
            return Flag.NO_ARGUMENT;
        }
        Direction direction = Direction.convertString(directionString);
        Door door = currentRoom.getExit(direction);

        if (door == null) {
            return Flag.NO_DOOR;
        } else if (door.isLocked()) {
            return Flag.LOCKED;
        } else {
            previousRooms.push(currentRoom);
            setCurrentRoom(door.getRoom());
            return Flag.MOVED;
        }
    }

    /**
     * Attempts to move the player to the previously visited location and returns
     * true if succesfull. If there's no last known location, the method does
     * nothing and return false.
     * 
     * @return Returns true if the player went back to the previous location.
     */
    public boolean goBack() {
        if (!previousRooms.isEmpty()) {
            setCurrentRoom(previousRooms.pop());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Looks for an Item with the name itemName in the currentRoom. If there is such
     * an item, it is removed from the room, put in the player's inventory and
     * returns true. If there's no such item, it simply returns false.
     * 
     * @param itemName Name of the item to be removed from the room.
     * @return Returns true if the item is exchanged successfully, else it return
     *         false.
     */
    public boolean playerTakes(String itemName) {
        return player.takeItemFrom(currentRoom, itemName);
    }

    /**
     * Looks for an Item with the name itemName in the player's inventory. If there
     * is such an item, it is removed from the inventory, put in the room's
     * inventory and returns true. If there's no such item, it simply returns false.
     * 
     * @param itemName Name of the item to be removed from the player's inventory.
     * @return Returns true if the item is exchanged successfully, else it return
     *         false.
     */
    public boolean playerDrops(String itemName) {
        return player.giveItemTo(currentRoom, itemName);
    }

    public Flag openDoor(String directionString) {
        if (directionString == null) {
            return Flag.NO_ARGUMENT;
        }
        Direction direction = Direction.convertString(directionString);
        Door door = currentRoom.getExit(direction);

        if (door == null) {
            return Flag.NO_DOOR;
        } else if (!door.isLocked()) {
            return Flag.UNLOCKED;
        } else {
            Item item = player.getItem(door.getKeyName());
            if (item == null || !door.isKey(item)) {
                return Flag.WRONG_KEY;
            } else {
                door.unlock(item);
                return Flag.OPENED;
            }
        }
    }

    /**
     * Adds InteractiveObject to the party. The player can interact with this object
     * again with command interact.
     * 
     * @param object The object to be added.
     */
    public void addInteractive(InteractiveObject object) {
        if (!containsInteractive(object.getName())) {
            objects.put(object.getName(), object);
        }
    }

    /**
     * Removes the object from the contents of the current room if there's an object
     * with the given name.
     * 
     * @param objectName The name of the object to be removed.
     */
    public void removeFromRoom(String objectName) {
        currentRoom.removeObject(objectName);
    }

    public Flag interact(String objectName) {
        if (objectName == null) {
            return Flag.NO_ARGUMENT;
        }
        InteractiveObject object = currentRoom.getObject(objectName);
        if (object == null) {
            object = objects.get(objectName);
        }

        if (object == null) {
            return Flag.NO_OBJECT;
        }

        object.interact(this);
        return Flag.INTERACTED;
    }

    /**
     * @return Returns a String with the full description of the Room the player is
     *         in, its contents and the contents of the player's inventory.
     */
    public String getStateDescription() {
        String result = currentRoom.getLongDescription() + "\n";
        if (!player.isInventoryEmpty()) {
            result += player.getLongDescription() + "\n";
        }
        result += currentRoom.getExitsString() + "\n";
        return result;
    }

    /**
     * Checks whether the given objectName is equal to any object's name in the
     * current gameState. The method checks whether the given name is the room, the
     * player or in the player or room's inventory. Returns null if there's no such
     * object.
     * 
     * @param objectName Name of the object to be found.
     * @return The description of the object with name objectName;
     */
    public String findDescription(String objectName) {
        String description = player.findDescription(objectName);
        if (description == null) {
            description = currentRoom.findDescription(objectName);
        }
        return description;
    }

    private void setCurrentRoom(Room newRoom) {
        this.currentRoom = newRoom;
    }

    private boolean containsInteractive(String objectName) {
        return objects.containsKey(objectName);
    }
}
