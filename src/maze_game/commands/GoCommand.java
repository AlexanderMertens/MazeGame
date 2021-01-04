package maze_game.commands;

import maze_game.directions.Direction;
import maze_game.state.GameState;

public class GoCommand extends Command {

    public GoCommand(String argument) {
        super(argument);
    }

    public boolean execute(GameState gameState) {
        if (!hasArgument()) {
            System.out.println("Go where?");
            return false;
        }

        Direction direction = Direction.convertString(getArgument());
        if (!gameState.go(direction)) {
            System.out.println("There is no door!");
            return false;
        }
        System.out.println(gameState.getStateDescription());
        return false;
    }
}
