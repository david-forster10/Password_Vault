package Password_Vault;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstTimeRun 
{
	public void firstRun()
	{
		
		String temp = "";
		System.out.println("First Time Setup:\n");
		System.out.println("How many accounts would you like to create? (accounts can be created at a later point, max number of accounts is 10)");
		
		Pattern p = Pattern.compile("[0-9]");
		Matcher match = p.matcher((temp = userInput.nextLine()));
		
		while (!match.matches() || Integer.parseInt(temp) > 10)
		{
			System.out.println("Invalid response, please try again");
			match = p.matcher((temp = userInput.nextLine()));
		}
		
		
	}
	private static Scanner userInput = new Scanner(System.in);
}
