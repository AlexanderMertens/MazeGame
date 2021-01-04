package maze_game.state;

import java.util.Stack;

import maze_game.directions.Direction;
import maze_game.gameobjects.Player;
import maze_game.gameobjects.Room;

public class GameState {
    private Room currentRoom;
    private Stack<Room> previousRooms;
    private Player player;

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
    }

    /**
     * Attempts to move player in a certain direction. Returns false if currentRoom
     * contains no Room in that direction. Else it moves the player in that
     * direction, pushes the last currentRoom onto the Stack and return True.
     * 
     * @param direction The direction the player wishes to move.
     * @return Returns true if the attempt is successful, else returns false.
     */
    public boolean go(Direction direction) {
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            return false;
        } else {
            previousRooms.push(currentRoom);
            setCurrentRoom(nextRoom);
            return true;
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

    /**
     * @return Returns a String with the full description of the Room the player is
     *         in, its contents and the contents of the player's inventory.
     */
    public String getStateDescription() {
        String result = currentRoom.getRoomString() + "\n";
        if (!player.isEmpty()) {
            result += player.getInventoryString() + "\n";
        }
        result += currentRoom.getExitsString() + "\n";
        return result;
    }

    private void setCurrentRoom(Room newRoom) {
        this.currentRoom = newRoom;
    }
}
