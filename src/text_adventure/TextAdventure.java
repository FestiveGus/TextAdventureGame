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

	public static void main(String[] args) 
		new TextAdventure();
	}

	TextAdventure() {

		boolean playing = true;
		String command = "";

		setup(); // create all objects needed, including map; print intro. message

		System.out.println(
				"You wake up in your cell with no memory of what happened prior. You are inside a dungeon cell. The first thing you hear is the screams of people being dragged through the corridor. Upon inspection, you realize that they are being taken to an execution room. You are shocked. Before long, a guard unlocks your door. \r\n"


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
		Item.setupItems(itemList, roomList);
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
	boolean inRoom(String item) {
		ArrayList bruh = roomList.get(currentRoom).items;
		for (int i = 0; i < bruh.size(); i++) {
			if (bruh.get(i).equals(item)) {
				return true;
			}
		} 
		return false;
	}
	int inInv(String item) {
		for (int i = 0; i < inventory.size(); i++) {
			Item inInventory = (Item) inventory.get(i);
			if (inInventory.name.equals(item)) {
				return i;
			}
		} 
		return -1;
	}
	void inspect(String item) {	
		if (inRoom(item) || inInv(item) >= 0) {
			System.out.println(itemList.get(item).description);
		}
		else {
			System.out.println("You can't find " + item + ".");
		}
	}
	
	void search () {
		ArrayList searchItems = roomList.get(currentRoom).items;
		if (searchItems.size() > 0){
		for (int i = 0; i < searchItems.size(); i++) {
			System.out.print(searchItems.get(i) + " ");
		}
		System.out.println();
		}
		else {
		System.out.println("There is nothing of interest!");
		}
	}
	
	void listInventory(){
		if (inventory.size() > 0) {
			for (int i = 0; i < inventory.size(); i++) {
				Item currentItem = (Item) inventory.get(i);
				System.out.print( currentItem.name + " ");
			}
			System.out.println();
		}
		else {
			System.out.println("Your inventory is empty.");
		}
	}
	
	void drop(String item) {
			if (inventory.size() == 0) {
				System.out.println("Your inventory is empty.");
				}
			else {
			int index = inInv(item);
			if (index >= 0) {
			roomList.get(currentRoom).items.add(inventory.get(index));
			inventory.remove(index);
			System.out.println("Dropped " + item + ".");
			}
			else {
			System.out.println("Cannot find " + item + ".");
			}
			}
	}
	
	void take(String item) {
		if (inRoom(item)) {
			Item addedItem = itemList.get(item);
			if (addedItem.isCarryable) {
			inventory.add(addedItem);
			roomList.get(currentRoom).items.remove(item);
			System.out.println("Picked up " + item + ".");
			}
			else {
				System.out.println(item + " is not carriable!");
			}
		}
		else {
			System.out.println("You can't find " + item + ".");
		}
	}
	
	void loot(String item) {
		if (inRoom(item)) {
			Item addedItem = itemList.get(item);
			if (addedItem.loot != "") {
				Item lootedItem = itemList.get(addedItem.loot);
				inventory.add(lootedItem);
				System.out.println("Looted " + lootedItem + ".");
				addedItem.loot = "";
			}
			else {
				System.out.println("There is no loot!");
			}
		}
		else {
			System.out.println("You can't find " + item + ".");
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
		case "inspect": case "lookat":
			inspect(word2);
			break;
		case "search":
			search();
			break;
		case "take": case "pickup":
			take(word2);
			break;
		case "drop":
			drop(word2);
			break;
		case "inventory": case "i":
			listInventory();
			break;
		case "loot":
			loot(word2);
			break;
		default:
			System.out.println("Sorry, I don't understand that command");
		}
		return true;
	}

	// tons of other methods go here ...
}
