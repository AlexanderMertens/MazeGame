package maze_game.mapping;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import maze_game.gameobjects.GameObject;

/**
 * A class that holds a mapping of key-value pairs consisting of object names
 * and their corresponding GameObjects.
 */
public class GameObjectMap<K, V extends GameObject> {
    private Map<K, V> objectMap;

    /**
     * Constructs a new mapping.
     */
    public GameObjectMap() {
        objectMap = new HashMap<>();
    }

    /**
     * Adds the given object to the mapping.
     * 
     * @param object The object to be added.
     */
    public void put(K key, V object) {
        objectMap.put(key, object);
    }

    /**
     * Removes the object with the given key from the mapping and returns it. If
     * there's no such object, the method does nothing and returns null.
     * 
     * @param key The name of the object to be removed.
     * @return Returns the removed object or null if there's no such object.
     */
    public V remove(K key) {
        return objectMap.remove(key);
    }

    /**
     * Finds the object with the given key and returns it. Returns null if there's
     * no such object.
     * 
     * @param key The name of the object to be found.
     * @return Returns the object to be found or null if there's no such object.
     */
    public V get(K key) {
        return objectMap.get(key);
    }

    /**
     * @return Returns a collection with all the values in the mapping.
     */
    public Collection<V> values() {
        return objectMap.values();
    }

    /**
     * @return Returns true if the mapping is empty, else returns false.
     */
    public boolean isEmpty() {
        return objectMap.isEmpty();
    }

    /**
     * Finds the description of the object with the given key and returns it.
     * Returns null if there's no such object.
     * 
     * @param key The key of the object to be found.
     * @return Returns the description of the object to be found or null if there's
     *         no such object.
     */
    public String findDescription(K key) {
        V object = get(key);
        if (object == null) {
            return null;
        } else {
            return object.getDescription();
        }
    }

    /**
     * @return Returns a String containing all the descriptions of the values the
     *         mapping contains.
     */
    public String getDescription() {
        String result = "";
        for (V object : objectMap.values()) {
            result += "  " + object.getLongDescription();
        }
        return result;
    }

    /**
     * @return Returns a String containing all the keys in the mapping.
     */
    public String getKeyDescription() {
        String result = "";
        for (K key : objectMap.keySet()) {
            result += key.toString() + " ";
        }
        return result;
    }
}
