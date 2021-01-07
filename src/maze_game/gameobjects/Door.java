package maze_game.gameobjects;

/**
 * This class represents doorways in the maze game. Doors are one way only, they
 * only hold the room that they lead to. A door leading from one room to
 * another, doesn't not mean necessarily mean a door in the reverse direction
 * exists. Doors can also be locked by some key item. By default a door is
 * unlocked and has no key item.
 */
public class Door extends GameObject {

    private Room room;

    /**
     * Constructs a Door with given name and description leading to the given room.
     * 
     * @param name        Name of the door.
     * @param description Description of the door.
     * @param room        Room the door leads to.
     */
    public Door(String name, String description, Room room) {
        super(name, description);
        this.room = room;
    }

    /**
     * By default the door is unlocked, so it returns false.
     * 
     * @return Returns false.
     */
    public boolean isLocked() {
        return false;
    }

    /**
     * By default the door has no key, so it returns null.
     * 
     * @return Returns null.
     */
    public String getKeyName() {
        return null;
    }

    /**
     * By default the door is unlocked, so this method does nothing.
     * 
     * @param keyItem Potential key to the door.
     */
    public void unlock(GameObject object) {
        return;
    }

    /**
     * Checks whether the given item is equal to the key corresponding to the door.
     * By default the door has no key so this returns false.
     * 
     * @param item
     * @return false
     */
    public boolean isKey(GameObject object) {
        return false;
    }

    /**
     * Returns the room the door leads to.
     * 
     * @return Room.
     */
    public Room getRoom() {
        return room;
    }

    /**
     * @return Returns longer description of door.
     */
    public String getLongDescription() {
        return getName() + ": " + getDescription();
    }
}
