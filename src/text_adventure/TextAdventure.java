package text_adventure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;

public class TextAdventure {

	static int INVSIZE = 10; // size of inventory
	HashMap<String, Room> roomList = new HashMap<String, Room>();
	HashMap<String, Item> itemList = new HashMap<String, Item>();

	ArrayList inventory = new ArrayList();
	String currentRoom;
	// Player player;

	int turns = 0;

	public static void main(String[] args) {
		new TextAdventure();
	}

	TextAdventure() {

		boolean playing = true;
		String command = "";

		setup(); // create all objects needed, including map; print intro. message

		System.out.println(
				"You wake up in your cell with no memory of what happened prior. You are inside a dungeon cell. The first thing you hear is the screams of people being dragged through the corridor. Upon inspection, you realize that they are being taken to an execution room. You are shocked. Before long, a guard unlocks your door. \r\n"
						+ "\r\n" + "‘It is time, criminal,’ he says. \r\n" + "\r\n" + "\r\n"
						+ "Before the guard can reach you, he topples over and is unconscious, behind him, revealing another prisoner.\r\n"
						+ "\r\n" + "‘Let’s get out of here,’ the prisoner says before taking off.\r\n");

		lookAtRoom(true); // display information about the current room

		/***** MAIN GAME LOOP *****/
		while (playing) {

			command = getCommand();

			playing = parseCommand(command);

			// check to see if player has died (in whichever various ways the player can
			// die)

			// check to see if the player has won the game

		}

		// does anything need to be done after th emain game loop exits?

	}

	void setup() {
		Room.setupRooms(roomList);
		// Item.setupItems(itemList, roomList);
		currentRoom = "startingcell";
	}

	String getCommand() {
		Scanner sc = new Scanner(System.in);
		String text = sc.nextLine();
		if (text.length() == 0)
			text = "qwerty"; // default command
		// sc.close();
		return text;
	}

	void lookAtRoom(boolean look) {

		System.out.println(roomList.get(currentRoom).displayName);

		if (!roomList.get(currentRoom).isVisited) {
			System.out.println(roomList.get(currentRoom).description);
			roomList.get(currentRoom).isVisited = true;
		}
	}

	void moveToRoom(char dir) {
		String nextRoom = roomList.get(currentRoom).getExit(dir);
		if (nextRoom == "") {
			System.out.println("You can't progress in this direction!");
		}
		else {
			currentRoom = nextRoom;
			lookAtRoom(true);
		}
	}

	void inspect(String item) {
		if (itemList.get(item) != null) {
			System.out.println(itemList.get(item).description);
		}
	}

	boolean parseCommand(String text) {

		/***** PREPROCESSING *****/
		// P1.
		text = text.toLowerCase().trim(); // the complete string BEFORE parsing

		// handle situation where no words entered ...

		// P2. word replacement
		text = text.replaceAll(" into ", " in ");
		text = text.replaceAll(" rocks", " rock");
		text = text.replaceAll("pick up", "pickup");
		text = text.replaceAll("look at", "lookat");
		text = text.replaceAll("climb up", "climbup");

		String words[] = text.split(" ");
		String[] betterWords = new String[69];
		// P3. remove all instances of "THE" //array list of words
		for (int i = 0; i < words.length; i++) {
			if (!words[i].equals("the"))
				betterWords[i] = words[i];
		}

		String word1 = betterWords[0], word2 = betterWords[1];

		/***** MAIN PROCESSING *****/
		switch (word1) {

		/**** one word commands ****/
		case "quit":
			System.out.print("Do you really want to quit the game? ");
			String ans = getCommand().toUpperCase();
			if (ans.equals("YES") || ans.equals("Y")) {
				System.out.print("Thanks for playing. Bye.");
				return false;
			}
		case "n":
		case "s":
		case "w":
		case "e":
		case "u":
		case "d":
		case "north":
		case "south":
		case "west":
		case "east":
		case "up":
		case "down":
			moveToRoom(word1.charAt(0));
			break;
		case "inspect":
			inspect(word2);
			break;
		default:
			System.out.println("Sorry, I don't understand that command");
		}
		return true;
	}

	// tons of other methods go here ...
}
