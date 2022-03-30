package text_adventure;
import java.util.ArrayList;
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
	 //ArrayList components = new ArrayList();
	 
	 //constructors
	 Item(String name, String description){
		 this.name = name;
		 this.description = description;
	 }
	 
	 //getters
	 
	 //create items
	 static void setupItems(HashMap<String,Item> itemList, HashMap<String, Room> roomList) {
		
		 Item z = new Item("bucket", "an old wooden bucket, smells bad");
		 itemList.put("bucket", z);
		 roomList.get("startingcell").items.add("bucket");
	 }
	 
	 @Override
	public
	 String toString() {
		 return name;
	 }
}