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
	 
	 String loot = "";
	 
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
		 
		 z = new Item("window", "you can see the light shining through the bars");
		 z.isCarryable = false;
		 itemList.put("window", z);
		 roomList.get("startingcell").items.add("window");
		 
		 z = new Item("guard", "The guard lies on the ground, unconsious." );
		 z.isCarryable = false;
		 z.loot = "key";
		 itemList.put("guard", z);
		 roomList.get("startingcell").items.add("guard");
		 
		 z = new Item("key", "a steel key, must unlock something.");
		 itemList.put("key", z);
	 }
	 
	 @Override
	public
	 String toString() {
		 return name;
	 }
}