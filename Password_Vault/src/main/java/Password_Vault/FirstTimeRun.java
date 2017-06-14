package Password_Vault;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstTimeRun extends Global_Vars
{
	public void firstRun()
	{
		try
		{
			Process p = Runtime.getRuntime().exec("cmd /c tasklist /v /fi \"WINDOWTITLE eq Password_Vault 1.0 Setup\"");
			BufferedReader proc = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			String i = "";
			while ((i = proc.readLine()) != null)
			{
				if (!i.equals("INFO: No tasks are running which match the specified criteria."))
				{
					System.exit(0);
				}
			}
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
		
		String accNum = "";
		System.out.println("First Time Setup:\n");
		System.out.println("Please input between 1-10 for how many accounts would you like to create? (accounts can be created at a later point)");
		
		Pattern p = Pattern.compile("[0-9]");
		Matcher match = p.matcher((accNum = userInput.nextLine()));
		
		while (!match.matches() || Integer.parseInt(accNum) > 10)
		{
			System.out.println("Invalid response, please try again");
			match = p.matcher((accNum = userInput.nextLine()));
		}
		
		for (int i = 1; i < (Integer.parseInt(accNum) + 1); i++)
		{
			if ((Integer.parseInt(accNum)+1) > 1)
				accReg(i, true);
			else
				accReg(i, false);
		}
	}
	
	private void accReg(int i, boolean multi)
	{
		ArrayList<String> accTemp = new ArrayList<String>();
		cls();
		if (i == 1)
			System.out.println("The following information that is collected will be used as part of the encryption process to ensure that each user has unique encryption paths");
		
		System.out.println("User #"+i+" Account Creation:");
		System.out.print("First Name: ");
		accTemp.add(userInput.nextLine());
		System.out.print("Surname: ");
		accTemp.add(userInput.nextLine());
		System.out.print("Birth Month: ");
		accTemp.add(userInput.nextLine());
		
	}
	
	private Scanner userInput = new Scanner(System.in);
	private ArrayList<ArrayList<String>> accDetails = new ArrayList<ArrayList<String>>();
}