package maze_game.state;

import java.util.Stack;

import maze_game.directions.Direction;
import maze_game.flag.Flag;
import maze_game.gameobjects.Door;
import maze_game.gameobjects.Item;
import maze_game.gameobjects.Player;
import maze_game.gameobjects.Room;
import maze_game.gameobjects.interactive.InteractiveObject;
import maze_game.mapping.InteractiveObjectMapping;

/**
 * Class that represents the current state of the gameworld. The class has
 * fields that hold the room the current player is in, the history of previously
 * visited rooms, the player object, a list of all rescued characters.
 * 
 * @author Alexander Mertens
 */
public class GameState {
    private Room currentRoom;
    // Holds Stack with the previous directions the player went with
    // Used to reconstruct the player's path
    private Stack<Direction> directionHistory;
    private Player player;
    // A mapping of characters and objects that are in the party
    // The player can interact with these via the interact command
    private InteractiveObjectMapping objects;

    /**
     * Creates a new GameState with given player and currentRoom.
     * 
     * @param player
     * @param currentRoom
     */
    public GameState(Player player, Room currentRoom) {
        this.player = player;
        this.currentRoom = currentRoom;
        directionHistory = new Stack<>();
        objects = new InteractiveObjectMapping();
    }

    /**
     * Attempts to move player in a certain direction. Returns appropriate Flag if
     * currentRoom contains no Room in that direction. Else it moves the player in
     * that direction, pushes the direction onto the Stack and returns a success
     * Flag.
     * 
     * @param directionString The direction the player wishes to move in String
     *                        form.
     * @return Returns a success flag if the player is successfully moved, else it
     *         returns a flag containing a message describing what went wrong.
     */
    public Flag go(String directionString) {
        if (directionString == null) {
            return Flag.NO_ARGUMENT;
        }
        Direction direction = Direction.convertString(directionString);
        Flag flag = go(direction);
        if (flag.isSuccess()) {
            directionHistory.push(direction);
        }
        return flag;
    }

    /**
     * Attempts to move player in a certain direction. Returns an appropriate Flag
     * if currentRoom contains no Room in that direction. Else it moves the player
     * in that direction and returns a success Flag.
     * 
     * @param direction The direction the player wishes to move.
     * @return Returns a success flag if the player is successfully moved, else it
     *         returns a flag containing a message describing what went wrong.
     */
    public Flag go(Direction direction) {
        Door door = currentRoom.getExit(direction);

        if (door == null) {
            return Flag.NO_DOOR;
        } else if (door.isLocked()) {
            return Flag.LOCKED;
        } else {
            setCurrentRoom(door.getRoom());
            return Flag.MOVED;
        }
    }

    /**
     * Attempts to move the player to the previously visited location and returns a
     * success Flag if succesfull. If there's no last known location, the method
     * does nothing and returns a Flag with appropriate message.
     * 
     * @return Returns a success flag if the player is successfully moved, else it
     *         returns a flag containing a message describing what went wrong.
     */
    public Flag goBack() {
        if (directionHistory.isEmpty()) {
            return Flag.NO_HISTORY;
        } else {
            // Takes the last known direction from the stack and reverses it to reverse path
            Flag flag = go(directionHistory.peek().reverse());
            if (flag.isSuccess()) {
                directionHistory.pop();
            }
            return flag;
        }
    }

    /**
     * Looks for an Item with the name itemName in the currentRoom. If there is such
     * an item, it is removed from the room, put in the player's inventory and
     * returns true. If there's no such item, it simply returns false.
     * 
     * @param itemName Name of the item to be removed from the room.
     * @return Returns a succes Flag if the item is successfully dropped into the
     *         room, else it returns a Flag containing a message with what went
     *         wrong.
     */
    public Flag playerTakes(String itemName) {
        Flag flag = player.takeItemFrom(currentRoom, itemName);
        if (flag == Flag.NO_ITEM) {
            return Flag.NO_ITEM_ROOM;
        } else {
            return flag;
        }
    }

    /**
     * Looks for an Item with the name itemName in the player's inventory. If there
     * is such an item, it is removed from the inventory, put in the room's
     * inventory and returns a succesfull Flag. Else it does nothing and returns a
     * Flag containing an appropriate message.
     * 
     * @param itemName Name of the item to be removed from the player's inventory.
     * @return Returns a succes Flag if the item is successfully dropped into the
     *         room, else it returns a Flag containing a message with what went
     *         wrong.
     */
    public Flag playerDrops(String itemName) {
        Flag flag = player.giveItemTo(currentRoom, itemName);
        if (flag == Flag.NO_ITEM) {
            return Flag.NO_ITEM_INV;
        } else {
            return flag;
        }
    }

    /**
     * Attempts to unlock a door in a given direction.
     * 
     * @param directionString The direction of the door.
     * @return Returns a success Flag if the door is successfully opened, else
     *         returns a Flag containing a message with what went wrong.
     */
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

    /**
     * Attempts interact with an object with the given objectName.
     * 
     * @param objectName Name of the object to be interacted with.
     * @return Returns a success Flag if the object is successfully interacted with,
     *         else returns a Flag containing a message with what went wrong.
     */
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
