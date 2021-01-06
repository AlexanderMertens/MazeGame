package maze_game.flag;

/**
 * This class represents messages send to the game engine, informing the engine
 * of succes or the way executing the command has failed.
 * 
 * @author Alexander Mertens
 */
public enum Flag {
    // general
    NO_ARGUMENT("This command requires an argument.", false),

    // finding doors
    NO_DOOR("There is no door in that direction!", false),

    // navigation
    LOCKED("The door is locked.", false), MOVED("You have moved.", true),

    // opening doors
    OPENED("You have unlocked the door.", true), WRONG_KEY("You do not have the right key in inventory.", false),
    UNLOCKED("This door is already unlocked.", false),

    // interacting
    INTERACTED("You have interacted with the object.", true), NO_OBJECT("There's no object with that name.", false),

    // moving items
    NO_ITEM("There's no such item.", false), ITEM_MOVED("The item has been moved", true),
    NO_ITEM_INV("There's no such item in your inventory.", false),
    NO_ITEM_ROOM("There's no such item in the room", false);

    private String message;
    private boolean success;

    /**
     * Constructs a Flag with the given message.
     * 
     * @param message
     */
    Flag(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    /**
     * Prints the message the flag carries.
     */
    public void printMessage() {
        System.out.println(this.message);
    }

    /**
     * @return Returns false if something went wrong.
     */
    public boolean isSuccess() {
        return this.success;
    }
}
