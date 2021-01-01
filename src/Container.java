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
     * @param itemName The name of the
     */
    public void giveItemTo(Container other, String itemName) {
        Item item = removeItem(itemName);
        other.addItem(item);
    }
}
