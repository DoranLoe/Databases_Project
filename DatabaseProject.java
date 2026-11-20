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
		IO.println("Testing Time!");
		DatabaseProject test = new DatabaseProject();
		test.createTables();
		test.createFile();
	}
	
	private void init() // initializes all values needed to make things here work.
	{
		str = "";	
		addsTables = false;
	}
	
	private void createTables()
	{
		//We don't know what is in str yet so we need to add to the beginning of it and not interfere with anything after. So large portions of this section maybe backwards?
		//but first, no need to put this in the file multiple times.
		if (addsTables)
			return;
		addsTables = true;
		String temp ="";
		
		//creates the heartphial lvl upgrades table
		// increasing heartphial level requires gaining or spending heart sand on the character.
		//anyways I'm naming this hrt_lvl because hrt can be read as a shortening for heart and because I think it's funny.
		
		//creates the XP lvl upgrades table
		// increasing xp level requires gaining or spending xp points on the character.
		
		// creates the book lvl upgrades table 
		// increasing skill level requires three componants: scrolls, codexes, and grimoires.
		
		// creates the magic lvl upgrades table
		// increasing magic lvl requires several upgrade components, including prism shard, gems, orbs, and cores, as well as elemental shards, gem, orbs, and cores.
		
		// creates the sources_table
		//only really useful as to reduce input anomallies
		temp ="";
		
		temp = "CREATE TABLE IF NOT EXISTS source_table ("+
			"\nid INTEGER AUTO_INCREMENT PRIMARY KEY,"+
			"\nsource TEXT NOT NULL"+
			"\n);\n";
		
		str = temp+str;
		
		//creates the magical_girl_table
		// magical girls have names and sources for the purposes of this project. I could store more data like wishes, witches, etc. but those aren't relevant here yet.
		temp ="";
		
		temp = "CREATE TABLE IF NOT EXISTS magical_girl_table ("+
			"\nid INTEGER AUTO_INCREMENT PRIMARY KEY,"+
			"\nname TEXT NOT NULL,"+
			"\nsource INTEGER FORIEGN KEY REFERENCES source_table,"+
			"\n);\n";
			
		str= temp+str;
		
		// creates the kiokus table
		// the characters have names, Kioku names, roles, elements, and 4-5 different forms of level
		// str = 	""+str;
		temp ="";
		
		temp=temp+"CREATE TABLE IF NOT EXISTS kiokus ("+
			"\nid INTEGER AUTO_INCREMENT PRIMARY KEY,"+
			"\nkioku_name TEXT NOT NULL,"+
			"\nmagical_girl INTEGER FORIEGN KEY REFERENCES magical_girl_table,"+
			"\nrole INTEGER FOREIGN KEY REFERENCES roles_table,"+
			"\nelement INTEGER FORIEGN KEY REFERENCES elements_table,"+
			"\nmagic_level INTEGER FOREIGN KEY REFERENCES mg_lvl,"+
			"\nmagic_level INTEGER FOREIGN KEY REFERENCES book_lvl,"+
			"\nexperience_level INTEGER FOREIGN KEY REFERENCES xp_lvl,"+
			"\nheart_phial_level INTEGER FOREIGN KEY REFERENCES hrt_lvl,"+
			"\nascension INTEGER,"+
			"\nobtained_date DATE,"+
			"\nrelease_date DATE"+
			"\n);\n";
		
		str = temp+str;
	}
	
	
	private void createFile()
	{
		StringToSQL.createSQL("ExedraSQL", str);
	}
	
	private void clearFile()
	{
		init();
	}
}

