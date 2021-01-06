package maze_game.gameobjects;

/**
 * GameObject class respresents all objects in the Maze game.
 * 
 * A GameObject has two String fields, name and description. This class is
 * abstract and only implements a single constructor and getters for its fields.
 * 
 * @author Alexander Mertens
 */
public abstract class GameObject {
    private String name;
    private String description;

    /**
     * Constructor for GameObject.
     * 
     * @param name        Name of GameObject.
     * @param description Description of GameObject.
     */
    public GameObject(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @return Returns the name of this object.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return Returns a description of this object.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return Returns the full description of this object.
     */
    public abstract String getLongDescription();
}