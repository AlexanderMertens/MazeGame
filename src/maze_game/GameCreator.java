package maze_game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import maze_game.directions.Direction;
import maze_game.gameobjects.Door;
import maze_game.gameobjects.LockedDoor;
import maze_game.gameobjects.Room;
import maze_game.gameobjects.interactive.InteractiveObject;
import maze_game.gameobjects.interactive.LinkedMechanism;
import maze_game.gameobjects.interactive.LockMechanism;

/**
 * Class providing methods to help create the rooms and puzzles occupying the
 * game.
 */
public class GameCreator {
    private static final String puzzleWords[] = new String[] { "arch", "back", "take", "bats", "bone", "bugs", "burn",
            "bite", "cane", "cast", "chop", "echo", "luck", "alive", "awful", "blind", "curse", "ivory" };
    private Random generator;

    public GameCreator() {
        generator = new Random();
    }

    /**
     * Links the two given rooms with doors in the given direction.
     * 
     * @param room1
     * @param room2
     * @param direction
     * @param doorName
     * @param doorDescription
     */
    public void linkRoom(Room room1, Room room2, Direction direction, String doorName, String doorDescription) {
        linkRoomsOneWay(room1, room2, direction, doorName, doorDescription);
        linkRoomsOneWay(room2, room1, direction.reverse(), doorName, doorDescription);
    }

    /**
     * Links two rooms such that the player can move from room1 to room2 via the
     * created door.
     * 
     * @param room1
     * @param room2
     * @param direction
     * @param doorName
     * @param doorDescription
     */
    public void linkRoomsOneWay(Room room1, Room room2, Direction direction, String doorName, String doorDescription) {
        Door doorTo2 = new Door(doorName, doorDescription, room2);
        room1.setExit(direction, doorTo2);
    }

    /**
     * Creates a sequence puzzle from the given word. The player must interact with
     * the mechanism in the correct order (i.e. the order of the characters in the
     * word string). A wrong interaction resets the puzzle and displays the
     * failureMessage. After interacting with the correct object, display
     * succesMessage. After interacting with the final object in the correct order,
     * display finalMessage.
     * 
     * @param word
     * @param failureMessage
     * @param succesMessage
     * @param finalMessage
     * @return
     */
    public List<InteractiveObject> createSequencePuzzle(String word, String failureMessage, String succesMessage,
            String finalMessage, String mechanismName) {
        ArrayList<InteractiveObject> mechanismList = new ArrayList<>();
        char[] charArray = word.toCharArray();
        int wordLength = charArray.length;
        LinkedMechanism previous = null;
        LinkedMechanism current;
        for (int i = 0; i < wordLength - 1; i++) {
            current = new LinkedMechanism(String.valueOf(charArray[i]), mechanismName
                    + " with an accompanying plaque with the letter " + charArray[i] + " inscribed on it.",
                    failureMessage, succesMessage, previous, true);
            mechanismList.add(current);
            previous = current;
        }

        int finalCharIndex = wordLength - 1;
        LockMechanism finalMechanism = new LockMechanism(String.valueOf(charArray[finalCharIndex]), mechanismName
                + " with an accompanying plaque with the letter " + charArray[finalCharIndex] + " inscribed on it.",
                failureMessage, finalMessage, previous, true);
        mechanismList.add(finalMechanism);
        return mechanismList;
    }

    public List<InteractiveObject> createRandomSequencePuzzle(String failureMessage, String succesMessage,
            String finalMessage, String mechanismName) {
        int randomIndex = generator.nextInt(puzzleWords.length);
        return createSequencePuzzle(puzzleWords[randomIndex], failureMessage, succesMessage, finalMessage,
                mechanismName);
    }

    /**
     * Add a randomized puzzle to the first given room. Also adds a locked door from
     * room1 to room2 in the given direction that can only be opened by solving the
     * puzzle.
     * 
     * @param room
     * @param failureMessage
     * @param succesMessage
     * @param finalMessage
     * @param mechanismName
     */
    public void addPuzzle(Room room1, Room room2, Direction direction, String doorName, String openDoorDescription,
            String closedDoorDescription, String failureMessage, String succesMessage, String finalMessage,
            String mechanismName) {
        List<InteractiveObject> puzzle = createRandomSequencePuzzle(failureMessage, succesMessage, finalMessage,
                mechanismName);
        LockMechanism key = (LockMechanism) puzzle.get(puzzle.size() - 1);
        Door doorTo1 = new LockedDoor(doorName, openDoorDescription, closedDoorDescription, room1, key);
        Door doorTo2 = new LockedDoor(doorName, openDoorDescription, closedDoorDescription, room2, key);
        key.setDoor(doorTo1);
        key.setDoor(doorTo2);
        room1.setExit(direction, doorTo2);
        room2.setExit(direction.reverse(), doorTo1);
        for (InteractiveObject object : puzzle) {
            room1.addInteractive(object);
        }
    }
}
