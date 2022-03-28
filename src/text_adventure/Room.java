package text_adventure;
import java.util.ArrayList;
import java.util.HashMap;

class Room {

	//String name;
	String displayName;
	String description;
	boolean isVisited = false;
	boolean isDark;
	String N,S,E,W;	
	ArrayList items = new ArrayList();
	
	//constructor
	Room(String displayName, String descr) {
		this.displayName = displayName;
		this.description = descr;		
	}
	
	void setExits(String N, String S, String E, String W) {
		this.N=N;
		this.E=E;
		this.W=W;
		this.S=S;
	}
	
	String getExit(String dir) {
		switch (dir) {
		case "n": return this.N;
		case "s": return this.S;
		case "w": return this.W;
		case "e": return this.E;
		default: return "";
		}
	}
	
	static void setupRooms(HashMap<String, Room> roomList) {
		Room r = new Room("Cell Room", "You wake up in your cell with no memory of what happened prior. You are inside a dungeon cell. The first thing you hear is the screams of people being dragged through the corridor. Upon inspection, you realize that they are being taken to an execution room. You are shocked. Before long, a guard unlocks your door. \r\n"
				+ "\r\n"
				+ "“It is time, criminal,” he says. \r\n"
				+ "\r\n"
				+ "\r\n"
				+ "Before the guard can reach you, he topples over and is unconscious, behind him, revealing another prisoner.\r\n"
				+ "\r\n"
				+ "“Let’s get out of here,” the prisoner says before taking off.\r\n"
				+ "");
		//N  S  E  W 
		r.setExits("", "corridor2","","");
		roomList.put("startingcell", r);
		
		r = new Room("Corridor","You stand in the middle of a long corridor, surrounded by the dungeon cells.");
		//N  S  E  W 
		r.setExits("startingcell", "","corridor3","corridor1");
		roomList.put("corridor2", r);
		
		r = new Room("Corridor (West End)", "This side of the corridor leads to the Execution Room. It is surrounded by two other dungeon cells.");
		//N  S  E  W 
		r.setExits("", "","corridor2","executionroom");
		roomList.put("corridor1", r);
		
		r = new Room("Execution Room", "This place stinks of death. There are axes on the walls and blood on the ground. You see a brutish man, the Executioner, stand up. \n\n 'Now you die, criminal!' he grunts as he shines his axe.");
		r.setExits("", "","corridor1","");
		roomList.put("executionroom", r);
		
		r = new Room("Corridor (East End)", "This side of the corridor leads to a giant metal gate, which appears to be lock. There are another two cells surrounding it.");
		r.setExits("", "","","corridor2");
		roomList.put("corridor3", r);
	}
	
}
