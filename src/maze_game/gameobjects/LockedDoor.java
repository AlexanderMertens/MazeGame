package maze_game.gameobjects;

public class LockedDoor extends Door {
    private Item keyItem;
    private boolean locked;

    public LockedDoor(String name, String description, Room room, Item keyItem) {
        super(name, description, room);
        this.keyItem = keyItem;
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
        return this.keyItem.getName();
    }

    /**
     * Unlocks the door if it's locked and the given item is the correct key.
     */
    @Override
    public void unlock(Item item) {
        if (isLocked() && isKey(item)) {
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
    public boolean isKey(Item item) {
        return this.keyItem == item;
    }
}
