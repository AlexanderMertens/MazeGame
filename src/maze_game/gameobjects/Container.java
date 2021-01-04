package maze_game.gameobjects;

import java.util.ArrayList;
import java.util.Iterator;

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
    private ArrayList<Item> inventory;

    /**
     * Constructs an empty container with given name and description.
     * 
     * @param name        Name of the Container.
     * @param description Description of Container.
     */
    public Container(String name, String description) {
        super(name, description);
        inventory = new ArrayList<>();
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
        for (Item item : inventory) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Removes Item object in the inventory corresponding to the given itemName and
     * returns it. Returns null if there's no item with itemName in the inventory.
     * 
     * @param itemName Name of the item to be retrieved.
     * @return The item to be returned.
     */
    public Item removeItem(String itemName) {
        Item item;
        Iterator<Item> it = inventory.iterator();
        while (it.hasNext()) {
            item = it.next();
            if (item.getName().equals(itemName)) {
                it.remove();
                return item;
            }
        }
        return null;
    }

    /**
     * Removes Item object in the inventory corresponding to the given itemName and
     * adds it to the other container.
     * 
     * @param other    Container object to which the item is given to.
     * @param itemName The name of the item to be exchanged.
     * 
     * @return Returns true if this container contains the item.
     */
    public boolean giveItemTo(Container other, String itemName) {
        Item item = removeItem(itemName);
        if (item != null) {
            other.addItem(item);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes Item object in the inventory of other Container corresponding to the
     * given itemName and adds it to the this container.
     * 
     * @param other    Container object from which the item is taken.
     * @param itemName The name of the item to be exchanged.
     * 
     * @return Returns true if the other container contains the item.
     */
    public boolean takeItemFrom(Container other, String itemName) {
        return other.giveItemTo(this, itemName);
    }

    /**
     * @return Returns true when the Container is empty, false if not.
     */
    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    /**
     * @return Returns a String representing the inventory of the GameObject.
     */
    public String getInventoryString() {
        String result = "";
        for (Item item : inventory) {
            result += "  " + item.getName() + " has weight of " + item.getWeight() + "\n";
        }
        return result;
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

        Item item = getItem(objectName);
        if (item == null) {
            return null;
        } else {
            return item.getDescription();
        }
    }
}
