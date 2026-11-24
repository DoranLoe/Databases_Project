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
		//test.createTables();
		test.fillUpgradeTables();
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
		
		fillmg_table(); //trust me this needs to be a seperate method
		
		//fillout the book_lvl table
		// thankfully this only has 10 levels.
		// treats all kioku as 5* because That would mean putting more data in the kioku table and making more tables here.
		// most are 5* anyways so no big loss.
		String temp = "";
		
		for (int i=1; i<10;i++)
		{
			int scrll = (i<4)? i*10:
						(i<7)? (i-3)*20+30: (i-6)*40+80;
			int cdx = 0;
			switch(i)
			{
				case 2: cdx =3; break;
				case 3: cdx =5; break;
				case 4: cdx =9; break;
				case 5: cdx =12; break;
				case 6: cdx =18; break;
				case 7: cdx =24; break;
				case 8: cdx =36; break;
				case 9: cdx =48; break;
				default: cdx=0;
			}
			int gmr = 	(i<3)? 0:
						(i<6)? i-2:
						(i<8)? (i-5)*2 +3:
						(i==8)? 10:12;
			
			temp = 	"INSERT INTO book_lvl VALUES("+
					scrll+","+
					cdx+","+
					gmr+");\n";
			str = str+ temp;
		}
		
		
		//fillout the xp_lvl table
		temp ="";
		int xpA =20; // amount of xp needed to go up to next level
		int xpB =10; // increase in xp needed, 
		
		for (int i=2; i<=100; i++)// change later, levels go above 100 now.
		{
			xpA += xpB;
			temp=	"INSERT INTO xp_lvl VALUES("+xpA+");\n";
			str = str+temp;
			if (i<14) xpB+= ((i-1)/2%2 ==0)? 2:3; //For the first 14 levels xpB increases every level by 2 or 3, alternating every two levels after the first.
			else if (i==14) xpB =100; //suddenly jumps to 100
			else if (i<50) xpB += ((i-2)/2%2 ==0)? 2:3; // back to 2 and 3 alternating but now on a different alternation point
			else if (i ==50) xpB =300; // sudden jump to 300
			else if (i<69)xpB+=5; 
			else if (i==69) xpB= 365;// why the decrease? was 390 before this
			else if (i==70) xpB= 500; //WHY??
			else if (i<99) xpB+=10;
			else xpB = 800;
		}
		
		//fillout the hrt_lvl table
		// theres 50 levels to these ones. unfortunately each level has it's own cost in an increasing amount. I can do something somewhat clever here.
		temp ="";
		int hrtA=0; // the thing I noticed is that the amount of heartsand needed is 100*the level you're at +50 + the hrtamount needed at the previous level.
		for (int i=2; i<=50; i++)
		{
			hrtA += ((100*(i-2))+50); //and tadaa! we have a summation.
			temp=	"INSERT INTO hrt_lvl VALUES("+hrtA+");\n";
			str = str+temp;
		}
		
	}
	
	private void fillmg_table()// what the fudge still needs levels 120-150
	{
		String temp = "";
		
		//fillout the mg_lvl table
		//magic level currently caps out at 150 I think
		// data from https://docs.google.com/spreadsheets/d/e/2PACX-1vSmMCjLn52tBVzDa-iaHhIGxpANKJUcRiB3yqkZSSUwYmOaH5-hvxCx4xjIMDm0GOUO7pdXDHCoA8zF/pubhtml#gid=1316531278
		// But I only have data for the first 130 levels.
		// I'll have to fill out some more on my own.
		for (int i =2; i<=130;i++) // my table is one off of theirs, but I can use their numbers here by off settign the loop
		{
			int pc = 0; // prism_cores. I don't have the info on the levels that use them. will have to find that.
			int po = 	(i==60)? 1://prism_orbs, one at 59,89,110-127 two at 128,129, unknown beyond.
						(i==90)? 1:
						(i>=111 && i<=128)? 1:
						(i>128)? 2:0 ;
						
			int pg = 	(i<31)? 0: //prism_gems
						(i<90)? ((i-13)/15)*5: // why couldn't this pattern just keep?
						(i<106)? 25:(i<=120)? 30:
						(i<125)? 60:
						(i<129)? 90:120; 
						
			int ps =	(i<30)? 50://prism stones
						(i<121)? (i-1)/30*100:
						(i<125)? 400:
						(i<129)? 500:700;
			
			//the elemental ones all are needed on the same levels. an observation I wish I made earlier
			
			int es =0;	/*(i==5||i==10||i==15)? 5:// elemental stones
						(i==20||i==25)? 10:
						(i==30|| i==33|| i==36||i==108)? 15:
						(i==39||i==42||i==45||i==66||i==72||i==78||i==84||i==90||i==96||i==102|| i==114)? 20: // at this point a switch case might have been a better idea
						(i==48||i==51||i==54)? 25: // yeah I think im going to do switch case for the other two elemental items
						(i==57||i==63||i==69)? 30:
						(i==75|| i==81||i==87)? 35:
						(i==93||i==99||i==105)? 40:
						(i==60|| i==111||i==117||i==120)? 50:0; // the data cuts off abrubptly at level120, might be worth checking it.*/
			
			int ec = 0; //data im using doesn't display the levels it's used. find out later.
			int eo=0;
			int eg =0;
			switch(i)
			{
				case 10: case 15:
				case 5: es = 5; break;
				
				case 108: eo=2; //no break
				case 25:
				case 20: es =10; break;
				
				case 33: case 36:
				case 30: es=15; break;
				
				case 102: eo=2; //no break
				case 90: case 84: case 78: case 72: case 66: eg=10; //no break
				case 42: case 45:
				case 39: es =20; break;
				
				case 51: case 54:
				case 48: es=25; eg=5; break;
				
				case 63: case 69:
				case 57: es=30; eg=10; break;
				
				case 60: es =50; eg=30; break;
				
				case 87: case 81: eo=1; //no break
				case 75: es=35; eg=15; break;
				
				case 105:
				case 99: es=40; eg=20; eo=2; break;
				
				case 117: case 120:
				case 111: es=50; eg=30; eo=3; break;
				
				case 114: es=20; eg=10; eo=3; break;
				
				default: eg=0; es=0; eo=0;
			}
						
			temp = "INSERT INTO mg_lvl \nVALUES("+
					""+pc+","+//prism_cores, first 130 doesn't use them
					""+po+","+
					""+pg+","+
					""+ps+","+
					""+ec+","+
					""+eo+","+
					""+eg+","+
					""+es+
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

