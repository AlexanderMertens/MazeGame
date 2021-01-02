package maze_game.directions;

/**
 * Enumeration that represent the four cardinal directions. These objects
 * represent the directions a given room can have an exit to.
 * 
 * @author Alexander Mertens
 */
public enum Direction {
    NORTH("north"), WEST("west"), EAST("east"), SOUTH("south"), UNKNOWN("?");

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

    public static Direction convertString(String directionInput) {
        try {
            return Direction.valueOf(directionInput.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Direction.UNKNOWN;
        }
    }

    public String toString() {
        return this.directionString;
    }
}
