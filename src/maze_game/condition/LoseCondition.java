package maze_game.condition;

import maze_game.gameobjects.Player;

/**
 * This class represents the lose condition for the game. The player loses when
 * he dies.
 */
public class LoseCondition extends Condition {
    Player player;

    public LoseCondition(String message, Player player) {
        super(message);
        this.player = player;
    }

    /**
     * Returns true if the player is dead, i.e. their hitpoints are at 0.
     */
    public boolean isSatisfied() {
        return player.isDead();
    }
}
