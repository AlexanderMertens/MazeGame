package maze_game.state;

import java.util.Stack;

import maze_game.directions.Direction;
import maze_game.flag.Flag;
import maze_game.gameobjects.Door;
import maze_game.gameobjects.Item;
import maze_game.gameobjects.Player;
import maze_game.gameobjects.Room;
import maze_game.gameobjects.interactive.InteractiveObject;
import maze_game.hint.Hints;
import maze_game.mapping.GameObjectMapByName;

/**
 * Class that represents the current state of the gameworld. The class has
 * fields that hold the room the current player is in, the history of previously
 * visited rooms, the player object, a list of all rescued characters.
 * 
 * @author Alexander Mertens
 */
public class GameState {
    // The current room the player occupies
    private Room currentRoom;
    // Holds Stack with the previous directions the player went with
    // Used to reconstruct the player's path
    private Stack<Direction> directionHistory;
    // The player character
    private Player player;
    // A mapping of characters and objects that are in the party
    // The player can interact with these via the interact command
    private GameObjectMapByName<InteractiveObject> objects;
    // Holds hints for the player
    private final Hints hints;

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
        objects = new GameObjectMapByName<>();
        this.hints = new Hints();
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
     * Looks for an object with the name objectName in the currentRoom. If there is
     * such an object, it is removed from the room, put in the player's inventory or
     * the party's interactive objects map and returns a success Flag. If there's no
     * such item, it returns an appropriate Flag.
     * 
     * @param objectName Name of the item to be removed from the room.
     * @return Returns a succes Flag if the item is successfully dropped into the
     *         room, else it returns a Flag containing a message with what went
     *         wrong.
     */
    public Flag playerTakes(String objectName) {
        if (objectName == null) {
            return Flag.NO_ARGUMENT;
        }

        Flag flag;
        flag = player.takeItemFrom(currentRoom, objectName);
        if (flag.isSuccess()) {
            return Flag.ITEM_TAKEN;
        }
        flag = this.moveObjectFrom(currentRoom, objectName);
        if (flag.isSuccess()) {
            return Flag.CHARACTER_TAKEN;
        }
        return flag;
    }

    /**
     * Looks for an object with the name objectName in the player's inventory or in
     * the party's interactive object map. If there is such an object, it is removed
     * from the inventory or the mapping, put in the room's contents and returns a
     * succesfull Flag. Else it does nothing and returns a Flag containing an
     * appropriate message.
     * 
     * @param objectName Name of the item to be removed from the player's inventory
     *                   or party.
     * @return Returns a succes Flag if the item is successfully dropped into the
     *         room, else it returns a Flag containing a message with what went
     *         wrong.
     */
    public Flag playerDrops(String objectName) {
        if (objectName == null) {
            return Flag.NO_ARGUMENT;
        }

        Flag flag;
        flag = player.giveItemTo(currentRoom, objectName);
        if (flag.isSuccess()) {
            return Flag.ITEM_DROPPED;
        }
        flag = moveObjectTo(currentRoom, objectName);
        if (flag.isSuccess()) {
            return Flag.CHARACTER_DROPPED;
        }
        return flag;
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
                player.removeItem(item.getName());
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
        objects.add(object);
    }

    /**
     * Removes the object from the contents of the room if there's an object with
     * the given name and puts it in the party. Returns a success Flag if there's
     * such an object in the room and it can be taken, else it returns an
     * appropriate Flag.
     * 
     * @param room       The Room the object is to be moved to.
     * @param objectName The name of the object to be removed.
     * 
     * @return Returns a Flag containing a message detailing what happened.
     */
    public Flag moveObjectFrom(Room room, String objectName) {
        InteractiveObject object = room.getObject(objectName);
        if (object == null) {
            return Flag.NO_OBJECT_ROOM;
        } else if (!object.isRemovable()) {
            return Flag.IMMOVABLE;
        }
        room.removeObject(objectName);
        addInteractive(object);
        return Flag.OBJECT_MOVED;
    }

    /**
     * Removes the object from the party if there's an object with the given name
     * and puts it in the party. Returns a success Flag if there's such an object,
     * else it does nothing and returns an appropriate Flag.
     * 
     * @param room       The Room the object is to be removed from.
     * @param objectName The object to be moved.
     * @return Returns a Flag containing a message detailing what happened.
     */
    public Flag moveObjectTo(Room room, String objectName) {
        InteractiveObject object = objects.remove(objectName);
        if (object == null) {
            return Flag.NO_OBJECT_PARTY;
        }
        room.addInteractive(object);
        return Flag.OBJECT_MOVED;
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
            return Flag.NO_INTERACTIVE;
        }

        return object.interact(this);
    }

    /**
     * @return Returns a String with the full description of the Room the player is
     *         in, its contents and the contents of the player's inventory.
     */
    public String getStateDescription() {
        return currentRoom.getLongDescription() + "\n" + currentRoom.getExitsString();
    }

    /**
     * @return Returns a String containing descriptions of everything in the party
     *         and everything in the inventory.
     */
    public String getPartyDescription() {
        String description = "";
        if (!player.isInventoryEmpty()) {
            description += player.getLongDescription();
        }

        if (!objects.isEmpty()) {
            description += "\nIn the party:" + objects.getDescription();
        }

        if (description.equals("")) {
            description = "You are all alone in the party.";
        }
        return description;
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
        if (objectName.equals("party")) {
            return getPartyDescription();
        } else if (objectName.equals("room")) {
            return getStateDescription();
        }

        String description = player.findDescription(objectName);
        if (description == null) {
            description = currentRoom.findDescription(objectName);
        }
        if (description == null) {
            description = objects.findDescription(objectName);
        }
        return description;
    }

    /**
     * @param room The room to be checked.
     * @return Returns true if the player is currently at the given room.
     */
    public boolean isAt(Room room) {
        return room == currentRoom;
    }

    /**
     * @param object The object to be checked.
     * @return Returns true if the party currently contains the given object.
     */
    public boolean containsObject(InteractiveObject object) {
        return objects.contains(object);
    }

    /**
     * The player takes a hit to their health.
     */
    public void getHit() {
        player.getHit();
    }

    /**
     * Prints a hint for the player.
     */
    public void printHint() {
        hints.printHint();
    }

    private void setCurrentRoom(Room newRoom) {
        this.currentRoom = newRoom;
    }
}
