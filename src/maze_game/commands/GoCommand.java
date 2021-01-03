package maze_game.commands;

import maze_game.directions.Direction;
import maze_game.gameobjects.Player;

public class GoCommand extends Command {

    public GoCommand(String argument) {
        super(argument);
    }

    public boolean execute(Player player) {
        if (!hasArgument()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return false;
        }

        Direction direction = Direction.convertString(getArgument());
        if (!player.go(direction)) {
            System.out.println("There is no door!");
            return false;
        }
        System.out.println(player.getLongDescription());
        return false;
    }
}
