package com.df.Password_Key;

import java.util.ArrayList;
import org.springframework.security.crypto.bcrypt.*;

public class Global_Vars 
{
	public static ArrayList<String> savedSerialProps = new ArrayList<String>(); //variable for file containing stored serial numbers
	public static String savedMacAddress = ""; //variable for MAC Address from computer (CAN BE NULL)
	public static String savedBiosProp = ""; //variable for Bios Serial number
	public static String workingDirectory; //variable for where everything is stored
	public static String runInput = ""; //variable for validating whether valid run or not
	
	public static void getWorkingDirectory()
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
	}
	
	public static String BCryptHash(String input) //BCrypt function, allows input
	{
		return BCrypt.hashpw(input, BCrypt.gensalt(10)); //returns the hash generated with a 10 salt from the inputted value
	}
}
