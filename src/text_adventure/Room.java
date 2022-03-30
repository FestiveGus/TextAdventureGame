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
	
	String getExit(char dir) {
		switch (dir) {
		case 'n': return this.N;
		case 's': return this.S;
		case 'w': return this.W;
		case 'e': return this.E;
		default: return "";
		}
	}
	
	static void setupRooms(HashMap<String, Room> roomList) {
		Room r = new Room("Cell Room", "A dimly lit room with rough limestone walls. There is a small barred window that lets some light in. Metal bars surround the recently unlocked door and guard lies on the ground, unconscious.");
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
		
		r = new Room("Corridor (East End)", "This side of the corridor leads to a giant metal gate, which appears to be locked. There are another two cells surrounding it.");
		r.setExits("", "","","corridor2");
		roomList.put("corridor3", r);
	}
	
}
