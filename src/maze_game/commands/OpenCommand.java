package maze_game.commands;

import maze_game.flag.Flag;
import maze_game.state.GameState;

public class OpenCommand extends Command {

    public OpenCommand(String argument) {
        super(argument);
    }

    public boolean execute(GameState gameState) {
        Flag flag = gameState.openDoor(getArgument());
        if (flag.isSuccess()) {
            System.out.println("The door has been opened.");
        } else {
            flag.printMessage();
        }
        return false;
    }

}
