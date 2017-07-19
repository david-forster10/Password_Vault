package com.df.Global;

import java.io.File;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Global_Vars 
{
	public String foundDir = "";
	public File usersDir = null;
	
	public Global_Vars ()
	{
		String OS = (System.getProperty("os.name")).toUpperCase(); //capitalises OS version
		
		if (OS.contains("WIN")) //if windows
		{
			workingDirectory = System.getenv("AppData"); //workingdirectory is set to appdata within computer
		}
		else //else if UNIX Based
		{
			workingDirectory = System.getProperty("user.home"); //get user's home
			workingDirectory += "\\Library\\Application Support"; //and reference to the Application files
		}
		
		workingDirectory = workingDirectory.replaceAll("\\\\", "\\\\\\\\"); //replace all \ with \\ so that the file path can be used without errors
		workingDirectory = workingDirectory + "\\Password_Vault";
		usersDir = new File (workingDirectory+"\\sys\\acc"); //declaring directory from recently found workingDir
	}
	
	public String findDir (String target) //first method called to search entire drive (file passed in is what is being searched for)
	{
		foundDir = "";
		File[] tempPaths; //File array used while putting C to the top of the search list
		File[] paths = File.listRoots(); //File array that will contain the ordered disk drives before searching 

		// returns pathnames for files and directory
		tempPaths = File.listRoots();

		int i = 0;
		File c = new File("C:\\"); //used with assigning C drive to be first searched drive as most likely location of file
		for (File temp : tempPaths) //for each drive in the available drives
			if (temp.equals(c)) //if c drive is selected
				paths[0] = temp; //place c path into first element of File array
			else //if not
			{
				i =+ 1; //counter to ensure drives don't over write older drives
				paths[i] = temp; //adding drive to last element in File array
			}
				
		
		for(File path:paths) // for each pathname in pathname array
		{
			findDir(target, path); //call overload method to search specific drive for file
			if (!foundDir.equals("")) //if null isn't returned
				return foundDir; //return the string that has the path
		}
		return null; //if nothing found return null
	}
	
	public void findDir (String target, File drive) //overload method of findDir for second iterative step of finding
	{
		ArrayList<File> contents = new ArrayList<File>(); //arraylist for handling contents of current directory being searched
		
		File[] driveList = drive.listFiles(); //File array that gets full list of drive contents
		
		if (driveList != null) //listFiles will return null if item is a single file, if files are within the drive
			for (File item : driveList) //for every item in drive
				if (System.getProperty("os.name").toUpperCase().contains("WIN")) //check on windows
					if (item.getPath().substring(1).equals(":\\Users")) //most common place for file to be stored is "Users" directory, without including drive letter allows reordering across entire system
					{
						File temp = contents.get(0); //temporarily store current value of contents element 0
						contents.set(0, item); //put "users" directory to be searched first
						contents.add(temp); //add value that was originally first to end of list
					}
					else
						contents.add(item); //just add to list if doesn't match if
				else
					contents.add(item); //just add to list if doesn't match if
		
		if (contents.size() != 0) //if there are files in the list then...
			for (File f : contents) //for every file in the list
			{
				findDir(target, f); //pass it into the current method for another iteration
				if (foundDir != "") //if file found drop out of loop
					break;
			}
				
		if (drive.getPath().length() > target.length()) //ensuring that drive is longer than target address so no errors when using substring
			if (drive.getPath().substring(drive.getPath().length() - target.length()).equals(target)) //if last part of path matches target file
			{
				foundDir = drive.getPath(); //add found directory to variable to be passed back
			}
	}

	public boolean validation (int pat, String input)
	{
		input = input.toUpperCase();
		switch (pat)
		{
			case 1:
				Pattern p1 = Pattern.compile("^([1-9][0-9]{0,2}|1000)$");
				Matcher match1 = p1.matcher(input);
			
				if (match1.matches())
					return true;
				else
					return false;
			case 2:
				Pattern p2 = Pattern.compile("^[a-z/-]+$", Pattern.CASE_INSENSITIVE);
				Matcher match2 = p2.matcher(input);
				
				if (match2.matches())
					return true;
				else
					return false;
			default:
				return false;
		}	
	}

	public String convert (String in)
	{
		MessageDigest messageDigest;
		try 
		{
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(in.getBytes(Charset.forName("UTF-8")), 0, in.length());
			in = null;
			return new BigInteger(1,messageDigest.digest()).toString(16);
		} 
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}

		return null;
	}
	
	public void closingEvent()
	{
		if (JOptionPane.showConfirmDialog(null, "<html><center>Are you sure you want to quit?</center></html>", "Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) 
			System.exit(0); // output warning that it would quit the application, if accepted...
		// if not accepted do nothing and close
	}
	
	//globally used variables
	public String workingDirectory; //variable for where everything is stored
}