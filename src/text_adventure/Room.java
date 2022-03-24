package text_adventure;
import java.util.ArrayList;
import java.util.HashMap;

class Room {

	//String name;
	String displayName;
	String description;
	boolean isVisited;
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
		case 'N': return this.N;
		case 'S': return this.S;
		case 'W': return this.W;
		case 'E': return this.E;
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
	}
	
}
