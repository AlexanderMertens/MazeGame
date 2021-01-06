package maze_game.condition;

import java.util.Collection;

import maze_game.gameobjects.Room;
import maze_game.gameobjects.interactive.InteractiveObject;
import maze_game.state.GameState;

/**
 * This class represents the condition that needs to be met in order for the
 * player to win the game.
 */
public class VictoryCondition extends Condition {
    private GameState gameState;
    // Required objects/characters to collect in order to win.
    private Collection<InteractiveObject> requiredObjects;
    // Exit of the maze, the player needs to be at the exit in order to win.
    private Room mazeExit;
    // Message to be printed if the game is won.

    public VictoryCondition(String message, GameState gameState, Collection<InteractiveObject> objectList,
            Room mazeExit) {
        super(message);
        this.gameState = gameState;
        this.requiredObjects = objectList;
        this.mazeExit = mazeExit;
    }

    /**
     * Returns true if the player is at the exit and has all the required objects.
     * 
     * @return Returns true if the player has won.
     */
    public boolean isSatisfied() {
        return atExit() && hasAllObjects();
    }

    private boolean atExit() {
        return gameState.isAt(mazeExit);
    }

    private boolean hasAllObjects() {
        for (InteractiveObject object : requiredObjects) {
            if (!gameState.containsObject(object)) {
                return false;
            }
        }
        return true;
    }
}
