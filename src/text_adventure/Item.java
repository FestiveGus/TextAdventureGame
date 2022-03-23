package text_adventure;
import java.util.HashMap;

class Item {
	 String name; //unique key
	 String description; //describe the item when you look at it
	 //the location is in the Room object OR here.
	 private int foodpoints = 0;	// 0 means you cannot eat it
	 boolean isCarryable = true;
	 boolean isActivated = false; //eg flashlight off or on
	 String descrActive = ""; //description for when it's active
	 //maybe also damage points, weight, cost in gold ...
	 String readText = ""; //if there is writing on it
	 
	 //constructors
	 Item(String name, String description){
		 this.name = name;
		 this.description = description;
	 }
	 
	 //getters
	 
	 //create items
	 static void setupItems(HashMap<String,Item> itemList, HashMap<String, Room> roomList) {
		
		 Item z = new Item("large knife", "a sharp knife with a bone handle, engraved");
		 z.readText = "orcrist";
		 itemList.put("knife", z);
		 roomList.get("kitchen").items.add("knife");
	 }
}