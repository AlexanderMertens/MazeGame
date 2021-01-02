package maze_game.gameobjects;

import java.util.Stack;

import maze_game.directions.Direction;

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
    private Room currentRoom;
    private Stack<Room> previousRooms;

    /**
     * Creates an instance of Player with name and currentRoom as its current
     * location. Initially both the Stack of previously visited rooms and the
     * inventory of the player is empty.
     * 
     * @param name        Name of player.
     * @param description Description of the player.
     * @param currentRoom The initial location of the player.
     */
    public Player(String name, String description, Room currentRoom) {
        super(name, description);
        this.currentRoom = currentRoom;
        this.previousRooms = new Stack<>();
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

    public boolean take(String itemName) {
        return takeItemFrom(getLocation(), itemName);
    }

    public boolean drop(String itemName) {
        return giveItemTo(getLocation(), itemName);
    }

    public String getLongDescription() {
        return getLocationDescription() + "\n" + "\nThe " + getLocationName() + " contains:\n" + getLocationInventory()
                + "\nThe player's bag contains: \n" + this.getInventoryString();
    }

    private String getLocationName() {
        return getLocation().getName();
    }

    private String getLocationInventory() {
        return getLocation().getInventoryString();
    }

    private String getLocationDescription() {
        return getLocation().getRoomString();
    }

    private void setCurrentRoom(Room newRoom) {
        this.currentRoom = newRoom;
    }

    private Room getLocation() {
        return currentRoom;
    }
}