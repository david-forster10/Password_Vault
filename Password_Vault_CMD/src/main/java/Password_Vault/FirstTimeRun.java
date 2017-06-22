package Password_Vault;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class FirstTimeRun extends Global_Vars
{
	public FirstTimeRun()
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
	}
	
	public void firstRun()
	{		
		if (reRun)
		{
			System.out.println("--------------------------------------------------------------------------------------------------------------------");
			System.out.println("                 				WELCOME TO PASSWORD_VAULT");
			System.out.println("--------------------------------------------------------------------------------------------------------------------");
		}
		
		String accNum = "";
		System.out.println("First Time Setup:\n");
		System.out.println("Please input between 1-10 for how many accounts would you like to create? (accounts can be created at a later point)");
		
		while (validation(1, (accNum = userInput.nextLine())) != true || Integer.parseInt(accNum) > 11)
		{
			System.out.println("Invalid response, please try again");
		}
	
		for (int i = 1; i < (Integer.parseInt(accNum) + 1); i++)
			accReg(i);

		
		if (Integer.parseInt(accNum) == 0)
		{
			System.out.println("An account is required to use Password_Vault, would you like to re-enter the amount of accounts you would like? Y/N");
			accNum = userInput.nextLine().toUpperCase();
			
			while (!accNum.equals("Y") && !accNum.equals("N"))
			{
				System.out.println("Invalid response, please try again");
				accNum = userInput.nextLine();
			}
			
			if (accNum.equals("Y"))
			{
				cls();
				reRun = true;
				firstRun();
			}
			else
				System.exit(0);
		}
	}
	
	private void accReg(int i)
	{
		boolean used = false;
		String tempVal = "";
		ArrayList<String> accTemp = new ArrayList<String>();
		cls();
		if (i == 1)
			System.out.println("The following information that is collected will be used as part of the encryption process to ensure that each user has unique encryption paths");
		
		System.out.println("User #"+i+" Account Creation:");
		System.out.print("First Name: ");
		while (validation(2, (tempVal = userInput.nextLine())) != true)
		{
			System.out.println("Invalid response, please try again - names cannot contain special characters (excluding \"-\"), spaces or numbers");
		}
		accTemp.add(tempVal);
		System.out.print("Surname: ");
		while (validation(2, (tempVal = userInput.nextLine())) != true)
		{
			System.out.println("Invalid response, please try again - names cannot contain special characters (excluding \"-\"), spaces or numbers");
		}
		accTemp.add(tempVal);
		System.out.print("Birth Month (e.g. Jan, NOV, jun): ");
		while (validation(3, (tempVal = userInput.nextLine())) != true)
		{
			System.out.println("Invalid response, please try again - months should only be 3 characters long and not include special characters or numbers");
		}
		accTemp.add(tempVal.toUpperCase());
		System.out.print("Blue, Red or Green: ");
		while (validation(4, (tempVal = userInput.nextLine())) != true)
		{
			System.out.println("Invalid response, please try again - your answer should only be one of the options above, and not include special characters or numbers");
		}
		accTemp.add(tempVal);
		System.out.print("Pick any whole number between 1 & 1000: ");
		while (validation(1, (tempVal = userInput.nextLine())) != true || Integer.parseInt(tempVal) > 1001)
		{
			System.out.println("Invalid response, please try again - your answer should only be a number between 1 & 1000");
		}
		accTemp.add(tempVal);
		System.out.print("Please enter a username: ");
		while ((tempVal = userInput.nextLine()) != null)
		{
			for (ArrayList<String> temp : accDetails)
				if (temp.get(5).equals(tempVal))
				{
					System.out.println("Invalid username, this username is already in use on this computer, please use another");
					used = true;
				}
			
			if (used != true)
			{
				break;
			}
			used = false;
		}
		
		if (!used)
			accTemp.add(tempVal);
		
		Console cons = System.console();
		char[] passwd = cons.readPassword("%s", "Please enter a password: ");
		accTemp.add(new String(passwd));

		accDetails.add(accTemp);
	}
	
	private boolean reRun = false;
	private Scanner userInput = new Scanner(System.in);
	private ArrayList<ArrayList<String>> accDetails = new ArrayList<ArrayList<String>>();
}