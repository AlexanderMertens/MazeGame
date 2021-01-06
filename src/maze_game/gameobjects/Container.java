package maze_game.gameobjects;

import maze_game.flag.Flag;
import maze_game.mapping.Inventory;

/**
 * The Container class models a GameObject that can contain items. It extends
 * the GameObject class. A container has a field inventory, an ArrayList that
 * holds all the items it contains. Examples of containers include rooms, player
 * character and other characters.
 * 
 * This class implements methods to add, remove, retrieve and exchange items.
 * 
 * @author Alexander Mertens
 */
public abstract class Container extends GameObject {
    private Inventory inventory;

    /**
     * Constructs an empty container with given name and description.
     * 
     * @param name        Name of the Container.
     * @param description Description of Container.
     */
    public Container(String name, String description) {
        super(name, description);
        inventory = new Inventory();
    }

    /**
     * Adds item to inventory of this container.
     * 
     * @param item Item to be added.
     */
    public void addItem(Item item) {
        inventory.add(item);
    }

    /**
     * Returns Item object in the inventory corresponding to the given itemName.
     * Returns null if there's no item with itemName in the inventory.
     * 
     * @param itemName Name of the item to be retrieved.
     * @return The item to be returned.
     */
    public Item getItem(String itemName) {
        return inventory.get(itemName);
    }

    /**
     * Removes Item object in the inventory corresponding to the given itemName and
     * returns it. Returns null if there's no item with itemName in the inventory.
     * 
     * @param itemName Name of the item to be retrieved.
     * @return The item to be returned.
     */
    public Item removeItem(String itemName) {
        Item item = getItem(itemName);
        inventory.remove(itemName);
        return item;
    }

    /**
     * Removes Item object in the inventory corresponding to the given itemName and
     * adds it to the other container.
     * 
     * @param other    Container object to which the item is given to.
     * @param itemName The name of the item to be exchanged.
     * 
     * @return Returns a Flag containing a message with what happened.
     */
    public Flag giveItemTo(Container other, String itemName) {
        if (itemName == null) {
            return Flag.NO_ARGUMENT;
        }

        Item item = removeItem(itemName);
        if (item != null) {
            other.addItem(item);
            return Flag.ITEM_MOVED;
        } else {
            return Flag.NO_ITEM;
        }
    }

    /**
     * Removes Item object in the inventory of other Container corresponding to the
     * given itemName and adds it to the this container.
     * 
     * @param other    Container object from which the item is taken.
     * @param itemName The name of the item to be exchanged.
     * 
     * @return Returns a Flag containing a message with what happened.
     */
    public Flag takeItemFrom(Container other, String itemName) {
        return other.giveItemTo(this, itemName);
    }

    /**
     * @return Returns true when the Container is empty, false if not.
     */
    public boolean isInventoryEmpty() {
        return inventory.isEmpty();
    }

    /**
     * @return Returns a String representing the inventory of the GameObject.
     */
    @Override
    public String getLongDescription() {
        return inventory.getDescription();
    }

    /**
     * Checks whether the given objectName is equal to this object's name and
     * returns its description if it is. Otherwise the method tries to find an
     * object with the given objectName in its inventory and return its description.
     * If no object is found, the method returns null.
     * 
     * @param objectName Name of the object to be found.
     * @return The description of the object with name objectName.
     */
    public String findDescription(String objectName) {
        if (objectName.equals(getName())) {
            return getDescription();
        }

        return inventory.findDescription(objectName);
    }
}
