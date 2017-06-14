package Password_Vault;

import java.io.File;
import java.io.IOException;
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
		
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		System.out.println("                 				WELCOME TO PASSWORD_VAULT");
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
				
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
	
	private void Login()
	{
		System.out.print("Username: ");
		String user = userInput.next();
		System.out.print("Password: ");
		String pass = userInput.next();
	}
	
	private static Scanner userInput = new Scanner(System.in);
}
