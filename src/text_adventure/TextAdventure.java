package text_adventure;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;

public class TextAdventure {
	
	static int INVSIZE = 10; //size of inventory	
	HashMap<String,Room> roomList = new HashMap<String,Room>();
	HashMap<String, Item> itemList = new HashMap<String,Item>(); 



	ArrayList inventory = new ArrayList();
	String currentRoom;
	//Player player;
	
	int turns = 0;

	public static void main(String[]args){
		new TextAdventure();
	}

	TextAdventure() {

		boolean playing = true;
		String command = "";

		setup(); //create all objects needed, including map; print intro. message
		
		lookAtRoom(true); //display information about the current room

		/***** MAIN GAME LOOP *****/
		while (playing) {

			command = getCommand();

			playing = parseCommand(command);

			//check to see if player has died (in whichever various ways the player can die)

			//check to see if the player has won the game
			
		}

		// does anything need to be done after th emain game loop exits?

	}

	void setup() {
		Room.setupRooms(roomList);
		// ... more stuff ...
		currentRoom = "startingcell";
	}

	String getCommand() {
		Scanner sc = new Scanner(System.in);		
		String text = sc.nextLine();
		if (text.length() == 0) text = "qwerty"; //default command
		//sc.close();
		return text;
	}
	
	void lookAtRoom() {
		System.out.println(roomList.get(currentRoom).displayName);
		System.out.println(roomList.get(currentRoom).description);
	}
	
	void moveToRoom(char dir) {
		//
	}
	
	boolean parseCommand(String text) {

		/***** PREPROCESSING *****/
		//P1. 
		text = text.toLowerCase().trim();	//the complete string BEFORE parsing
		

		//handle situation where no words entered ...

		
		//P2. word replacement
		text = text.replaceAll(" into ", " in ");
		text = text.replaceAll(" rocks", " rock");
		text = text.replaceAll("pick up", "pickup");
		text = text.replaceAll("look at", "lookat");
		text = text.replaceAll("climb up", "climbup");
		
		String words[] = text.split(" ");
		
		//P3. remove all instances of "THE"
		ArrayList wordlist = new ArrayList(Arrays.asList(words));		//array list of words
		for(int i=0; i< wordlist.size(); i++) {
			if (wordlist.get(i).equals("the")) wordlist.remove(i--);			
		}

		words = (String[]) wordlist.toArray();
		
		String word1 = words[0], word2 = words[1];

		/***** MAIN PROCESSING *****/
		switch(word1) {
		
		/**** one word commands ****/
		case "quit":
			System.out.print("Do you really want to quit the game? ");
			String ans = getCommand().toUpperCase();
			if (ans.equals("YES") || ans.equals("Y")) {
				System.out.print("Thanks for playing. Bye.");
				return false;
			}			
		case "n": case "s": case "w": case "e": case "u": case "d":
		case "north": case "south": case "west": case "east": case "up": case "down":
			moveToRoom(word1.charAt(0));
			break;
		case "i": case "inventory":
			//showInventory();
			break;
		case "sleep":
			//sleep();			
			break;	
		case "help":
			//printHelp();
			break;
			
		/**** two word commands ****/		
		case "read":
			//readObject(word2);
			break;
		case "eat":
			//eatItem(word2);
			break;		
			
		/**** SPECIAL COMMANDS ****/
		// ...		

		default: 
			System.out.println("Sorry, I don't understand that command");
		}
		return true;
	}	

	//tons of other methods go here ...	
}
