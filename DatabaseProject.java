/**
 * DatabaseProject.java
 * 
 * Java program that creates an SQL file that creates, updates, or queries A database.
 * Database is made to store data on playable characters in the game Madoka Magica Magia Exedra.
 * Specifically will hold tables for the characters and their upgrade costs.
 * 
 * 
 */


public class DatabaseProject {
	String str;// string will serve as the contents of our file prior to the file's creation which will be handled in a seperate FileCreator class.
	boolean addsTables;
	
	public static void main (String[] args) {
		
	}
	
	private static void init() // initializes all values needed to make things here work.
	{
		str = "";	
		addsTables = false;
	}
	
	private static void createTables()
	{
		//We don't know what is in str yet so we need to add to the beginning of it and not interfere with anything after. So large portions of this section maybe backwards?
		//but first, no need to put this in the file multiple times.
		if (addsTables)
			return;
		addsTables = true;
		
		//creates the XP lvl upgrades table
		
		// creates the book lvl upgrades table 
		
		// creates the magic lvl upgrades table
		// increasing magic lvl requires several upgrade components, including prism shard, gems, orbs, and cores, as well as elemental shards, gem, orbs, and cores.
		
		// creates the kiokus table
		// the characters have names, Kioku names, roles, elements, and 4-5 different forms of level
		// str = 	""+str;
		str = 	");"+str;
		str = 	"/nascension INTEGER,"+str;
		str = 	"/nheart_phial_level INTEGER FOREIGN KEY REFERENCES hrt_lvl,"+str;
		str = 	"/nexperience_level INTEGER FOREIGN KEY REFERENCES xp_lvl,"+str;
		str = 	"/nmagic_level INTEGER FOREIGN KEY REFERENCES book_lvl,"+str;
		str = 	"/nmagic_level INTEGER FOREIGN KEY REFERENCES mg_lvl,"+str;
		str = 	"/nelement TEXT NOT NULL,"+str;
		str = 	"/nrole TEXT NOT NULL,"+str;
		str = 	"/nmagical_girl TEXT NOT NULL,"+str;
		str = 	"/nkioku_name TEXT NOT NULL,"+str;
		str = 	"/nid INTEGER AUTO_INCREMENT PRIMARY KEY,"+str;
		str = 	"CREATE TABLE IF NOT EXISTS kiokus ("+str;
	}
	
	private static void clearFile()
	{
		init();
	}
}

