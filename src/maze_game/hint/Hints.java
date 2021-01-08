package maze_game.hint;

/**
 * Class that holds hints for the game. As the player asks for more hints, the
 * hints change to reveal more and more information.
 * 
 * If the player has exhausted all the hints and asks for more, it prints all
 * the hints.
 */
public class Hints {
    private static final String[] hints = new String[] { "You can look at doors by entering 'look <direction>'."
            + "\nLooking at locked doors gives you an idea of what their key might be, then use 'open <direction>' once you find it.",
            "The puzzle in the rooms require you to interact with the mechanisms in the correct order."
                    + "\nIf you get the order wrong, the puzzle resets.",
            "The correct order of the puzzles spells out an English word.",
            "You should look out for the following items: key, crowbar, idol, lever and incantation" };

    // Holds the amount of hints the player has asked.
    private int hintAmount;

    public Hints() {
        this.hintAmount = 0;
    }

    public void printHint() {
        if (this.hintAmount >= hints.length) {
            for (String hint : hints) {
                System.out.println(hint);
            }
        } else {
            System.out.println(hints[hintAmount]);
            hintAmount++;
        }
    }
}
