/**
 * StringToSQL.java
 * 
 * A miniture library to hold methods related to creating files.
 * Not limited to SQL files, but the context this file is being made for is SQL based.
 */

import java.util.*;
import java.io.*;

public class StringToSQL {
	
	//private static File highscore = new File("highscores.txt");
	/**
	 * createTxt
	 * Takes two strings and makes a .txt file from them.
	 * @param fileName, A String to name the file. file will be named fileName.txt
	 * @param fileContents, A String to hold the will be contents of the file.
	 */
	public static void createTxt(String fileName, String fileContents)
	{
		
		try
		{
			File newFile = new File(fileName+".txt");
		
			PrintStream fileStream = new PrintStream(newFile);
			
			fileStream.print(fileContents);
			
			IO.println("fileCreated: "+fileName);
			
		}catch (FileNotFoundException fnfe){
			IO.println("This is not how you make a file");
		}
	}
	/**
	 * createTxt
	 * Takes a string and makes a .txt file with that name.
	 * @param fileName, A String to name the file. file will be named fileName.txt
	 */
	public static void createTxt (String fileName)
	{
		createTxt(fileName,"");
	}
	
	
	/**
	 * createSQL
	 * Takes two strings and makes a .sql file from them.
	 * @param fileName, A String to name the file. file will be named fileName.sql
	 * @param fileContents, A String to hold the will be contents of the file.
	 */
	public static void createSQL(String fileName, String fileContents)
	{
		
		try
		{
			File newFile = new File(fileName+".sql");
		
			PrintStream fileStream = new PrintStream(newFile);
			
			fileStream.print(fileContents);
			
			IO.println("SQLfileCreated: "+fileName);
			
		}catch (FileNotFoundException fnfe){
			IO.println("This is not how you make a file");
		}
	}
	/**
	 * createSQL
	 * Takes a string and makes a .sql file with that name.
	 * @param fileName, A String to name the file. file will be named fileName.sql
	 */
	public static void createSQL (String fileName)
	{
		createSQL(fileName,"");
	}
	
	
	public static void main (String[] args) {
		createTxt("test","test");
		createSQL("test");
	}
}

