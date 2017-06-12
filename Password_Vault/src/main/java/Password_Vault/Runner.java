package Password_Vault;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner extends Global_Vars
{
	public Runner()
	{
		FirstTimeRun ftr = new FirstTimeRun();
		String workingDir = getWorkingDirectory();
		File workingDirFile = new File (workingDir);
		
		if (!workingDirFile.exists() || workingDirFile.listFiles().length == 0)
			installer();
		
		System.out.println("-------------------------------------------------------------");
		System.out.println("                   Welcome to Password_Vault");
		System.out.println("-------------------------------------------------------------");
		
		File usersDir = new File (workingDirFile.getAbsolutePath()+"\\users");
		
		if (usersDir.exists() && usersDir.listFiles().length != 0)
			Login();
		else
			ftr.firstRun();
	}
	
	public static void main(String[] args) 
	{
		new Runner();
	}
	
	private void installer()
	{
		System.err.println("ERROR: Please run the installation wizard before trying to run this application");
		System.err.flush();
		System.out.println("Would you like to run the installer now? Y/N");
		String resp = userInput.nextLine();
		
		
		while (!resp.toLowerCase().equals("n") || resp.toLowerCase().equals("y"))
		{
			System.out.println("Invalid response, please try again");
			resp = userInput.nextLine();
		}
		
		if (resp.toLowerCase().equals("y"))
		{
			findDir("setup\\setup.lnk");
			try
			{
				Process p = Runtime.getRuntime().exec("cmd /c start \"\" \""+foundDir+"\"");
				p.waitFor();
			}
			catch (IOException | InterruptedException e)
			{
				System.err.println("ERROR: Failed to run setup application, please run it manually.");
			}
			System.exit(0);
		}
		else if (resp.toLowerCase().equals("n"))
		{
			System.exit(0);
		}

	}
		
	private String findDir (String target) //first method called to search entire drive (file passed in is what is being searched for)
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
	
	private void findDir (String target, File drive) //overload method of findDir for second iterative step of finding
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
	
	private void Login()
	{
		System.out.print("Username: ");
		String user = userInput.next();
		System.out.print("Password: ");
		String pass = userInput.next();
	}
	
	private static Scanner userInput = new Scanner(System.in);
	private static String foundDir = "";
}
