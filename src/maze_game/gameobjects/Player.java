package maze_game.gameobjects;

/**
 * This class represents the player of maze game.
 * 
 * The class extends the Container class such that the Player class has the
 * functionality to hold items.
 * 
 * @author Alexander Mertens
 */
public class Player extends Container {
    // The starting health of the player
    private static final int MAX_HEALTH = 5;
    // If the player's health reaches this value, the player dies.
    private static final int MIN_HEALTH = 0;
    // If the player's health reaches this value, he is close to death.
    private static final int TRESHOLD = 2 + MIN_HEALTH;

    // An integer representing the health of the player.
    private int health;

    /**
     * Creates an instance of Player with given name and description.
     * 
     * @param name        Name of player.
     * @param description Description of the player.
     */
    public Player(String name, String description) {
        super(name, description);
        this.health = MAX_HEALTH;
    }

    /**
     * @return Returns a String representing the inventory of the player.
     */
    @Override
    public String getLongDescription() {
        if (!isInventoryEmpty()) {
            return getName() + "'s inventory:" + super.getLongDescription();
        } else {
            return "Inventory is empty.";
        }
    }

    /**
     * Checks whether the given objectName is equal to this object's name or the
     * String "player" and returns its description if it is. Otherwise the method
     * tries to find an object with the given objectName in its inventory and return
     * its description. If no object is found, the method returns null.
     * 
     * @param objectName Name of the object to be found.
     * @return The description of the object with name objectName.
     */
    @Override
    public String findDescription(String objectName) {
        if (objectName.equals("player")) {
            return getPlayerState();
        } else if (objectName.equals("inventory")) {
            return getLongDescription();
        } else {
            return super.findDescription(objectName);
        }
    }

    /**
     * This method represents the player getting hurt by something in the game
     * world. Decrements the player's health by 1.
     */
    public void getHit() {
        this.health--;
    }

    /**
     * @return Returns true if the player's health is at or below MIN_HEALTH, i.e.
     *         the player's dead, else it returns false.
     */
    public boolean isDead() {
        return this.health <= MIN_HEALTH;
    }

    public String getPlayerState() {
        String description = getDescription() + "\n";
        if (health <= MIN_HEALTH) {
            description += "You are dead!";
        } else if (health <= TRESHOLD) {
            description += "You are critically wounded, you can't take much more.";
        } else if (health < MAX_HEALTH) {
            description += "You have been wounded, you should be careful.";
        } else {
            description += "You are a picture of health.";
        }
        return description;
    }

}