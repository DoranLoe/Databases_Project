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
	private String str;// string will serve as the contents of our file prior to the file's creation which is handled in a different class.
	boolean addsTables, fillsUpgradeTables, fillsGirlsTable;
	
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
		fillsUpgradeTables = false;
	}
	
	
	//create methods
	
	private void createTables()
	{
		//We don't know what is in str yet so we need to add to the beginning of it and not interfere with anything after. So large portions of this section maybe backwards?
		//but first, no need to put this in the file multiple times.
		if (addsTables)
			return;
		addsTables = true;
		String temp ="";
		
		//creates the in_inv table
		// stores what's currently in your inventory so that queries can use that to recommend things.
		temp ="";
		
		temp = "CREATE TABLE IF NOT EXISTS in_inv ("+
			"\nprism_cores INTEGER,"+
			"\nprism_orbs INTEGER,"+
			"\nprism_gems INTEGER,"+
			"\nprism_stones INTEGER,"+
			"\nelemental_cores INTEGER,"+
			"\nelemental_orbs INTEGER,"+
			"\nelemental_gems INTEGER,"+
			"\nelemental_stones INTEGER,"+
			"\ngrimoires INTEGER,"+
			"\ncodexes INTEGER,"+
			"\nscrolls INTEGER,"+
			"\nxp INTEGER,"+
			"\nheart_sand_large INTEGER,"+
			"\nheart_sand_small INTEGER"+
			"\n);\n";
		
		str = temp+str;
		
		//creates the heartphial lvl upgrades table
		// increasing heartphial level requires gaining or spending heart sand on the character.
		//anyways I'm naming this hrt_lvl because hrt can be read as a shortening for heart and because I think it's funny.
		temp ="";
		
		temp = "CREATE TABLE IF NOT EXISTS hrt_table ("+
			"\nid INTEGER AUTO_INCREMENT PRIMARY KEY,"+
			"\nhs_needed INTEGER"+
			"\n);\n";
		
		str = temp+str;
		
		//creates the XP lvl upgrades table
		// increasing xp level requires gaining or spending xp points on the character.
		temp ="";
		
		temp = "CREATE TABLE IF NOT EXISTS xp_table ("+
			"\nid INTEGER AUTO_INCREMENT PRIMARY KEY,"+
			"\nxp_needed INTEGER"+
			"\n);\n";
		
		str = temp+str;
		
		// creates the book lvl upgrades table 
		// increasing skill level requires three componants: scrolls, codexes, and grimoires.
		// each row here will show what items are needed to get from this level to the next.
		temp ="";
		
		temp = "CREATE TABLE IF NOT EXISTS book_lvl ("+
			"\nid INTEGER AUTO_INCREMENT PRIMARY KEY,"+ // indistinguishable from the level itself
			"\ngrimoires INTEGER,"+
			"\ncodexes INTEGER,"+
			"\nscrolls INTEGER"+
			"\n);\n";
		
		str = temp+str;
		
		// creates the magic lvl upgrades table
		// increasing magic lvl requires several upgrade components, including prism shard, gems, orbs, and cores, as well as elemental shards, gem, orbs, and cores.
		// each row here will show what items are needed to get from this level to the next.
		temp ="";
		
		temp = "CREATE TABLE IF NOT EXISTS mg_lvl ("+
			"\nid INTEGER AUTO_INCREMENT PRIMARY KEY,"+ // indistinguishable from the level itself
			"\nprism_cores INTEGER,"+
			"\nprism_orbs INTEGER,"+
			"\nprism_gems INTEGER,"+
			"\nprism_shards INTEGER,"+
			"\nelemental_cores INTEGER,"+
			"\nelemental_orbs INTEGER,"+
			"\nelemental_gems INTEGER,"+
			"\nelemental_shards INTEGER"+
			"\n);\n";
		
		str = temp+str;
		
		// creates the roles_table
		//only really useful as to reduce input anomallies
		temp ="";
		
		temp = "CREATE TABLE IF NOT EXISTS roles_table ("+
			"\nid INTEGER AUTO_INCREMENT PRIMARY KEY,"+
			"\nrole TEXT NOT NULL"+
			"\n);\n";
		
		str = temp+str;
		
		// creates the elements_table
		//only really useful as to reduce input anomallies
		temp ="";
		
		temp = "CREATE TABLE IF NOT EXISTS elements_table ("+
			"\nid INTEGER AUTO_INCREMENT PRIMARY KEY,"+
			"\nelement TEXT NOT NULL"+
			"\n);\n";
		
		str = temp+str;
		
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
			"\nskill_level INTEGER FOREIGN KEY REFERENCES book_lvl,"+
			"\nexperience_level INTEGER FOREIGN KEY REFERENCES xp_lvl,"+
			"\nheart_phial_level INTEGER FOREIGN KEY REFERENCES hrt_lvl,"+
			"\nascension INTEGER,"+
			"\nobtained_date DATE,"+
			"\nrelease_date DATE"+
			"\n);\n";
		
		str = temp+"n"+str;
	}
	
	private void fillUpgradeTables() //unfinished
	{
		if(fillsUpgradeTables)
			return;
		fillsUpgradeTables = true;
		//we don't need to do this more than once.
		// infact, this should really just be called from createTables()
		
		String temp = "";
		
		fillmg_table(); //trust me this needs to be a seperate method
		
		//fillout the book_lvl table
		
		//fillout the xp_lvl table
		
		//fillout the hrt_lvl table
		
	}
	
	private void fillmg_table()// what the fudge
	{
		String temp = "";
		
		//fillout the mg_lvl table
		//magic level currently caps out at 150 I think
		// But I only have data for the first 130 levels.
		// data from https://docs.google.com/spreadsheets/d/e/2PACX-1vSmMCjLn52tBVzDa-iaHhIGxpANKJUcRiB3yqkZSSUwYmOaH5-hvxCx4xjIMDm0GOUO7pdXDHCoA8zF/pubhtml#gid=1316531278
		for (int i =0; i<130;i++)
		{
			int pc = 0; // prism_cores. I don't have the info on the levels that use them. will have to find that.
			int po = 	(i==59)? 1://prism_orbs, one at 59,89,110-127 two at 128,129, unknown beyond.
						(i==89)? 1:
						(i>=110 && i<128)? 1:
						(i>=128)? 2:0 ;
						
			int pg = 	(i>=30)? 0: //prism_gems
						(i<89)? ((i-13)/15)*5: // why couldn't this pattern just keep?
						(i<105)? 25:(i<120)? 30:
						(i<124)? 60:
						(i<128)? 90:120; 
						
			int ps =	(i<29)? 50://prism stones
						(i<120)? (i+1)/30*100:
						(i<124)? 400:
						(i<128)? 500:700;
						
			int es =	(i==4||i==9||i==14)? 5:// elemental stones
						(i==19||i==24)? 10:
						(i==29|| i==32|| i==35||i==107)? 15:
						(i==39||i==41||i==44||i==65||i==71||i==77||i==83||i==89||i==95||i==101|| i==113)? 20: // at this point a switch case might have been a better idea
						(i==47||i==50||i==53)? 25: // yeah I think im going to do switch case for the other two elemental items
						(i==56||i==62||i==68)? 30:
						(i==74|| i==80||i==86)? 35:
						(i==92||i==98||i==104)? 40:
						(i==59|| i==110||i==116||i==119)? 50:0; // the data cuts off abrubptly at level120, might be worth checking it.
			int ec = 0; //data im using doesn't display the level it's used. find out later.
			
			int eg =0;
			switch(i)
			{
				case 50: case 53:
				case 47: eg=5; break;
				case 62: case 65: case 68: case 71: case 77: case 83: case 89: case 95: case 101: case 107: case 113:
				case 56: eg=10; break;
				case 80: case 86:
				case 74: eg=15; break;
				case 98: case 104:
				case 92: eg=20; break;
				case 110: case 116: case 119:
				case 59: eg=30; break;
				default: eg=0;
			}
			int eo=0;
			switch(i)
			{
				case 86: case 92: case 95:
				case 80: eo=1; break;
				case 101: case 104: case 107:
				case 89: eo=2; break;
				case 113: case 116: case 119:
				case 110: eo=3; break;
			}
						
			temp = "INSERT INTO mg_lvl \nVALUES("+
					""+pc+","+//prism_cores, first 130 doesn't use them
					""+po+","+
					""+pg+","+
					""+ps+","+
					""+ec+","+
					""+eo+","+
					""+eg+","+
					""+es+","+
					");\n";
			str = str+temp;
		}
	}
	
	private void updateInventory()//unfinished
	{}
	
	//file methods
	
	private void createFile()
	{
		StringToSQL.createSQL("ExedraSQL", str);
	}
	
	private void clearFile()
	{
		init();
	}
}

