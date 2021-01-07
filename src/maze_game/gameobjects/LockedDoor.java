package maze_game.gameobjects;

/**
 * This class represents a locked door. It holds a boolean locked that is true
 * whenever the door is locked. It also holds an Item keyItem that can open the
 * door.
 * 
 * @author Alexander Mertens
 */
public class LockedDoor extends Door {
    private GameObject keyObject;
    // Description of the door when it is closed.
    private String closedDescription;
    private boolean locked;

    /**
     * Constructs a LockedDoor with given name and description. At first the door is
     * locked.
     * 
     * @param name              Name of the door.
     * @param openDescription   Description of the door when it is opened.
     * @param closedDescription Description of the door when it is locked.
     * @param room              The room the door leads to.
     * @param keyItem           The item that can open the door.
     */
    public LockedDoor(String name, String openDescription, String closedDescription, Room room, GameObject keyObject) {
        super(name, openDescription, room);
        this.closedDescription = closedDescription;
        this.keyObject = keyObject;
        this.locked = true;
    }

    /**
     * @return Returns true if the door is locked, else it returns false.
     */
    @Override
    public boolean isLocked() {
        return locked;
    }

    /**
     * @return Returns the name of the corresponding keyItem.
     */
    @Override
    public String getKeyName() {
        return this.keyObject.getName();
    }

    /**
     * Unlocks the door if it's locked and the given item is the correct key.
     */
    @Override
    public void unlock(GameObject object) {
        if (isLocked() && isKey(object)) {
            this.locked = false;
        }
    }

    /**
     * @return Returns the room the door leads to if it's unlocked, else it returns
     *         null.
     */
    @Override
    public Room getRoom() {
        if (isLocked()) {
            return null;
        } else {
            return super.getRoom();
        }
    }

    /**
     * @return Returns true if and only if the given item is equal to the key of the
     *         door.
     */
    @Override
    public boolean isKey(GameObject object) {
        return this.keyObject == object;
    }

    /**
     * @return Returns a different description of the door depending on whether it
     *         is open or closed.
     */
    @Override
    public String getDescription() {
        if (isLocked()) {
            return closedDescription;
        } else {
            return super.getDescription();
        }
    }
}
