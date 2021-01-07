package maze_game.mapping;

import maze_game.gameobjects.GameObject;

/**
 * A class that holds a mapping of key-value pairs consisting of object names
 * and their corresponding GameObjects.
 */
public class GameObjectMapByName<T extends GameObject> extends GameObjectMap<String, T> {

    /**
     * Adds the given object to the mapping.
     * 
     * @param object The object to be added.
     */
    public void add(T object) {
        put(object.getName().toLowerCase(), object);
    }
}