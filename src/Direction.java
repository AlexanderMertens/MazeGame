/**
 * Enumeration that represent the four cardinal directions. These objects
 * represent the directions a given room can have an exit to.
 */
public enum Direction {
    NORTH("north"), WEST("west"), EAST("east"), SOUTH("south");

    private String directionString;

    /**
     * Constructs a Direction object with the given directionString, a String
     * representation of the direction.
     * 
     * @param directionString
     */
    Direction(String directionString) {
        this.directionString = directionString;
    }

    public String toString() {
        return this.directionString;
    }
}
