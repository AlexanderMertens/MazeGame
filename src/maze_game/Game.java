package maze_game;

import java.util.ArrayList;
import java.util.Collection;

import maze_game.directions.Direction;
import maze_game.gameobjects.Door;
import maze_game.gameobjects.Item;
import maze_game.gameobjects.LockedDoor;
import maze_game.gameobjects.Player;
import maze_game.gameobjects.Room;
import maze_game.gameobjects.interactive.Character;
import maze_game.gameobjects.interactive.InteractiveObject;
import maze_game.commands.Command;
import maze_game.condition.Condition;
import maze_game.condition.LoseCondition;
import maze_game.condition.VictoryCondition;
import maze_game.input.CommandWord;
import maze_game.input.Parser;
import maze_game.state.GameState;

/**
 * This class represents the Game, initializes the state of the game and start
 * the gameplay loop.
 */
public class Game {
    private Parser parser;
    private GameState gameState;
    private Condition victoryCondition;
    private Condition loseCondition;

    /**
     * Creates the game and initializes all the rooms and their contents;
     */
    public Game() {
        createRooms();
        parser = new Parser();
    }

    /**
     * Creates all the rooms, links their exits and sets their contents.
     */
    private void createRooms() {
        // Dialogue and descriptions that will be used.
        String incorrectButton = "As you press the button, you hear a disappointing *clunk* indicating the mechanism has reset.";
        String poisonDialogue = incorrectButton
                + "\nAs the room keeps filling with poisonous gas, your head starts to feel lighter and lighter.";
        String electricityDialogue = "When your hands touch the button, you are met with a burst of electricity."
                + "\nYou are unsure how much more of this you can take, better make sure the next one is correct.";
        String correctButton = "As you press the button, you hear a satisfying *click*.";
        String finalCorrectButton = "As you press the button, you hear a *click* and a door swings open.";

        String fireDialogue = "As you pull the lever, more hot steam rises from the vents below you, slowly but surely boiling you alive.";
        String finalFireLever = "After pulling the lever, the bars retreat into the ground, allowing you to continue.";

        String crossbowDialogue = "As you pull the lever, the crossbow fires directly at you, wounding you severely.";
        String finalCrossbowLever = "After pulling the lever, the locked door opens.";

        String correctLever = "You pull the lever and you faintly hear some mechanism inside the wall coming to life."
                + "\nSeems like you have chosen the right lever!";

        String simpleDoorName = "door";
        String simpleDoorDescription = "A simple door leading to a hallway, nothing special about it.";
        String metalDoorName = "metal door";
        String metalDoorDescription = "A heavy metal door.";
        String brokenDoorName = "broken door";
        String brokenDoorDescription = "There is only a frame here, where there used to be a door.";
        String rottenDoorName = "rotten door";
        String rottenDoorDescription = "This door has started to rot and is barely hanging onto the frame.";

        // create the rooms
        Room entrance, tortureRoom, electricityRoom, fireRoom, poisonRoom, crossbowRoom, trashRoom, bedroom, kitchen,
                hall, chapel, dungeon, muralRoom, museumRoom, lukeRoom, yasmineRoom, henrikRoom;
        entrance = new Room("entrance", "The room is empty except for one broken down door leading to a dark hallway.");
        tortureRoom = new Room("torture room",
                "On the walls of this room hang a variety of nefarious devices used to inflict pain."
                        + "\nIn the middle of the room stands a gurney."
                        + "\nBy the guts and blood spread around this room you can imagine some of the horrors these walls have seen.");
        electricityRoom = new Room("electrical room",
                "Inside this room are a collection of machines whirring and buzzing."
                        + "\nOn the floor wires cross the room from all directions."
                        + "\nAt the end of the room you see a device with some buttons atop it.");
        fireRoom = new Room("steam room",
                "The floor of this room is covered by grating that release hot steam periodically."
                        + "\nInside the temperature is dangerously high."
                        + "\nIn the corner of the room stand a set of important looking levers.");
        poisonRoom = new Room("poison room", "This room is mostly empty except for some suspicious looking vents."
                + "\nThe air in the room smells of some poisonous gas, you can only assume you don't want to be doing too much breathing in here.");
        crossbowRoom = new Room("hallway",
                "This rooms holds several exit, above one there is scary looking crossbow attached to the wall."
                        + "\nAs if by magic, the crossbow follows you anywhere you stand in the hall."
                        + "\nNext to the exit there are some levers attached to the wall.");
        trashRoom = new Room("trash room",
                "It's unclear what this room used to be, but now it's only used to store trash."
                        + "\nIt's so full of useless junk, you can barely find the next exit.");
        bedroom = new Room("bedroom", "In the middle of this room is what was once a gorgeous kingsize bed."
                + "\nThe frame and mattress are falling apart, and so is almost all the rest of the furniture in the room."
                + "\nAgainst the wall is a desk upon which lay a heap of papers with arcane symbols drawn on them.");
        kitchen = new Room("kitchen", "This room hasn't been used as a kitchen in quite a while."
                + "\nUnfortunately remains of food still litter room, filling the room with a suffocating odor."
                + "\nIn the western part of the room, there's a hole you could go into, but you're unsure you could get back out.");
        hall = new Room("hall",
                "Your footsteps echo as you enter this enormous room with a high ceiling."
                        + "\nThere's a painting of a demon spanning across the ceiling."
                        + "\nHis eyes seem to follow you as you cross the hall.");
        chapel = new Room("chapel",
                "As you enter this chapel, the first thing you notice is the blood smeared altar."
                        + "\nAt either side of the rooms stand a row of pews, their wood already mostly rotten."
                        + "\nBehind the altar looms a big red statue of a demon."
                        + "\nPresumably, the wizard and his minions make some twisted offering to this creature here.");
        dungeon = new Room("dungeon", "A moldy room where prisoners get thrown in to never return."
                + "\nYou can't go back into the hole where you came from, it's too steep to climb.");
        muralRoom = new Room("mural room",
                "The walls of this room are covered in murals depicting the wizard summoning some demon king.");
        museumRoom = new Room("museum", "This room holds a variety of ancient artifacts kept in displays.");
        lukeRoom = new Room("cell", "This room is used as a cell of some sort."
                + "\nAttached to the walls are rusty manacles that can hold prisoners.");
        yasmineRoom = new Room("skeleton room",
                "The floor of this room is covered with skeletons."
                        + "\nYou are unsure of what the purpose of this rooms could be,"
                        + "\nbut you are certain you don't want to remain here for much longer.");
        henrikRoom = new Room("bathroom", "An old bathroom, one you do not wish to use judging by the stains.");

        // create the characters
        Character yasmine = new Character("Yasmine", "Your most trusted friend.",
                "Thank god you have found me, let us get out of here!",
                "This place has a dark energy to it, let us get out of here as soon as possible.");
        Character luke = new Character("Luke", "The smartest person you know.",
                "That wizard has messed with the wrong person! Thank you for rescuing me.",
                "It seems the puzzles in this place require you to spell out the correct word.");
        Character henrik = new Character("Henrik", "Your extremely tall friend.",
                "Haha, I know you would find me! Now where's that wizard, so I can tear him a new one!",
                "This wizard is some slippery fiend, why won't he show himself?");
        Collection<InteractiveObject> characterList = new ArrayList<>();
        lukeRoom.addInteractive(luke);
        yasmineRoom.addInteractive(yasmine);
        henrikRoom.addInteractive(henrik);
        characterList.add(yasmine);
        characterList.add(luke);
        characterList.add(henrik);

        // Add generic doorways
        GameCreator creator = new GameCreator();
        creator.linkRoom(entrance, hall, Direction.NORTH, simpleDoorName, simpleDoorDescription);
        creator.linkRoom(hall, crossbowRoom, Direction.EAST, metalDoorName, metalDoorDescription);
        creator.linkRoom(muralRoom, chapel, Direction.SOUTH, brokenDoorName, brokenDoorDescription);
        creator.linkRoom(bedroom, henrikRoom, Direction.NORTH, rottenDoorName, rottenDoorDescription);
        creator.linkRoom(yasmineRoom, fireRoom, Direction.EAST, metalDoorName, metalDoorDescription);
        creator.linkRoom(dungeon, trashRoom, Direction.NORTH, rottenDoorName, rottenDoorDescription);
        creator.linkRoom(trashRoom, lukeRoom, Direction.NORTH, simpleDoorName, simpleDoorDescription);
        creator.linkRoom(museumRoom, fireRoom, Direction.NORTH, metalDoorName, metalDoorDescription);

        // Create keys
        Item crowbar = new Item("crowbar", "a handy tool");
        Item goldenKey = new Item("key", "a golden key");
        Item lever = new Item("lever", "a lever to open things with");

        Item idol = new Item("idol", "an idol of the demon king");
        Item incantation = new Item("incantation", "a piece of paper with the words for an incantation on it");

        // Create locked doors
        Door barricadedDoor = new LockedDoor("barricaded door",
                "Now the planks have been removed, you can open this door again.",
                "This door has been barred with planks, you would need some tool to remove them.", poisonRoom, crowbar);
        Door otherBarricaded = new Door(simpleDoorName, simpleDoorDescription, trashRoom);
        trashRoom.setExit(Direction.EAST, barricadedDoor);
        poisonRoom.setExit(Direction.WEST, otherBarricaded);

        Door lockedDoor = new LockedDoor("locked door", "The door has been unlocked, you can pass now.",
                "This door is locked, you need a key.", kitchen, goldenKey);
        Door kitchenToHall = new Door(simpleDoorName, simpleDoorDescription, hall);
        hall.setExit(Direction.WEST, lockedDoor);
        kitchen.setExit(Direction.EAST, kitchenToHall);

        Door gate = new LockedDoor("gate", "The gate has been lifted.",
                "A big gate bars the doorway that can only be lifted by some mechanism."
                        + "\nThe mechanism seems to be missing a lever.",
                tortureRoom, lever);
        Door otherGate = new Door("gate", "An open doorway that leads back to the dungeon.", dungeon);
        dungeon.setExit(Direction.SOUTH, gate);
        tortureRoom.setExit(Direction.NORTH, otherGate);

        String decoration = "A beautifully decorated door with several sinister figures carved in it.";
        Door decoratedDoor = new Door("bedroom door", decoration, muralRoom);
        Door lockedDecoratedDoor = new LockedDoor("bedroom door",
                decoration + "\nOne of the figures is now holding an idol and a vulgar smile rests upon its face.",
                decoration + "\nOne of the figures is holding out their hands, seemingly asking for something.",
                bedroom, idol);
        muralRoom.setExit(Direction.NORTH, lockedDecoratedDoor);
        bedroom.setExit(Direction.SOUTH, decoratedDoor);

        Door lockedMagicDoor = new LockedDoor("magic door", "The door has been opened by the incantation.",
                "This door has no lock or handle and is sealed shut.\nYou can feel the magic coming off of it.",
                museumRoom, incantation);
        Door magicDoor = new Door("magic door", "This door radiates magic energy.", crossbowRoom);
        crossbowRoom.setExit(Direction.NORTH, lockedMagicDoor);
        museumRoom.setExit(Direction.SOUTH, magicDoor);

        // add trapdoor
        Door trapdoor = new Door("hole", "a dark hole leading to an unknown location", dungeon);
        kitchen.setExit(Direction.WEST, trapdoor);

        // create puzzles
        creator.addPuzzle(poisonRoom, electricityRoom, Direction.EAST, "metal door",
                "Pressing the correct buttons has opened the door",
                "This door is hermetically sealed. There is no handle or lock", poisonDialogue, correctButton,
                finalCorrectButton, "A button");
        creator.addPuzzle(electricityRoom, hall, Direction.SOUTH, "electrified door", "The door stands open.",
                "A strange looking door that doesn't seem to open.", electricityDialogue, correctButton,
                finalCorrectButton, "A button");
        creator.addPuzzle(fireRoom, yasmineRoom, Direction.WEST, "bars", "The bars have retreated into the ground",
                "Bars block this doorway", fireDialogue, correctLever, finalFireLever, "A Lever");
        creator.addPuzzle(crossbowRoom, muralRoom, Direction.EAST, "metal door",
                "The big metal door is finally open, allowing you to continue.",
                "This door is locked tight with seemingly no lock", crossbowDialogue, correctLever, finalCrossbowLever,
                "A Lever");

        // add items
        tortureRoom.addItem(new Item("saw", "a bloodied instrument"));
        tortureRoom.addItem(new Item("spoon", "a simple harmless item if it weren't found in the torture room"));
        tortureRoom.addItem(crowbar);
        tortureRoom.addItem(new Item("bucket", "you don't dare to look inside"));
        tortureRoom.addItem(new Item("knife", "a sharp blade that has had countless victims"));
        tortureRoom.addItem(new Item("whip", "a long whip with barbs attached to the end"));

        hall.addItem(new Item("stone", "a perfectly round stone"));
        hall.addItem(new Item("plank", "an ordinary plank of wood"));
        hall.addItem(goldenKey);

        trashRoom.addItem(new Item("paper", "an old crumpled piece of paper with nothing of note on it"));
        trashRoom.addItem(new Item("sack", "a sack with some rotten food inside"));
        trashRoom.addItem(lever);
        trashRoom.addItem(new Item("soaked book", "a book that has been soaked, making its contents illegible"));
        trashRoom.addItem(new Item("fishbone", "as it says on the tin"));
        trashRoom.addItem(idol);
        trashRoom.addItem(new Item("cup", "a dented gold cup"));
        trashRoom.addItem(new Item("ball", "an old ball that has seen better days"));

        electricityRoom.addItem(new Item("wire", "a long piece of electrical wire"));

        kitchen.addItem(new Item("pan", "a pan with a lot of soot in it"));
        kitchen.addItem(new Item("pot", "a stained pot that you wouldn't cook anything in"));
        kitchen.addItem(new Item("fork", "a lonely fork"));

        chapel.addItem(new Item("lamp", "an oil lamp without any oil"));
        chapel.addItem(new Item("candle", "just a stub left"));
        chapel.addItem(new Item("ritual book", "a book containing instructions for some devious rituals"));
        chapel.addItem(new Item("scarf", "an ordinary scarf, seems warm"));

        muralRoom.addItem(new Item("mural piece", "a piece of the mural that has come loose"));
        muralRoom.addItem(new Item("drawing", "a drawing made of one of the murals"));

        museumRoom.addItem(new Item("egg", "an egg shaped rock"));
        museumRoom.addItem(new Item("sword", "an antique sword with an accompanying scabbard"));
        museumRoom.addItem(new Item("drawings", "collection of drawings made by Astaroth himself"));
        museumRoom.addItem(new Item("stick", "an ancient stick with a function you are unaware of"));
        museumRoom.addItem(new Item("dodecahedron", "a very symmetric shape"));
        museumRoom.addItem(new Item("map", "a detailed map of an ancient kingdom"));

        bedroom.addItem(new Item("blanket", "a blanket with quite a few holes in it"));
        bedroom.addItem(new Item("pen", "a practical looking pen with little flair to it"));
        bedroom.addItem(new Item("blank paper", "a blank piece of paper"));
        bedroom.addItem(new Item("tome", "a huge tome containing descriptions of unearthly horrors"));
        bedroom.addItem(incantation);
        bedroom.addItem(new Item("painting", "a picture depicting some arcane ritual"));
        bedroom.addItem(new Item("watch", "ticking away"));

        henrikRoom.addItem(new Item("brush", "an old brush that you really shouldn't use"));
        henrikRoom.addItem(
                new Item("mirror", "a small mirror, your reflection in it is a twisted and hideous version of you"));
        henrikRoom.addItem(new Item("scissors", "a rusty pair of scissors"));
        henrikRoom.addItem(new Item("glass", "a piece of glass, be careful not to cut yourself"));

        Player player = new Player("Alexander", "Beautiful");
        gameState = new GameState(player, entrance);
        victoryCondition = new VictoryCondition("You won the game!", gameState, characterList, entrance);
        loseCondition = new LoseCondition("You lost the game, too bad!", player);
    }

    /**
     * Main play routine. Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop. Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------");
            finished = command.execute(gameState) || victoryCondition.isSatisfied() || loseCondition.isSatisfied();
            System.out.println(
                    "------------------------------------------------------------------------------------------------------------------------");
        }
        if (loseCondition.isSatisfied()) {
            loseCondition.printMessage();
            return;
        }

        if (victoryCondition.isSatisfied()) {
            victoryCondition.printMessage();
            return;
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println("***************************************************");
        System.out.println("*                MAZE OF ASTAROTH                 *");
        System.out.println("***************************************************");
        System.out.println("The goal of the game is to rescue you your friends from the maze.");
        System.out.println("Many obstacles will hinder you from completing the game.");
        System.out.println("You need to find all the key objects to open any locked doors.");
        System.out.println("There are also a variety of puzzles strewn about the maze, that you need to solve.");
        System.out.println("The puzzles are also trapped,");
        System.out.println("so don't get the answer wrong too many times or you will lose!");
        System.out.println("Once you get all your friends back to the exit, you have won!");
        System.out.println("Type " + CommandWord.HELP.toString() + " if you need help.");
        System.out.println();
        printLocationInfo();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }

    private void printLocationInfo() {
        System.out.println(gameState.getStateDescription());
    }
}