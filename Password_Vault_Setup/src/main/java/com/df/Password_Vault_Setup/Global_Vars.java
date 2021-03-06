package com.df.Password_Vault_Setup;

import java.util.ArrayList;

import org.apache.commons.codec.binary.Base64;

public class Global_Vars 
{
	public ArrayList<String> savedSerialProps = new ArrayList<String>(); //variable for file containing stored serial numbers
	public String savedMacAddress = ""; //variable for MAC Address from computer (CAN BE NULL)
	public String savedBiosProp = ""; //variable for Bios Serial number
	public String runInput = ""; //variable for validating whether valid run or not
	
	public String getWorkingDirectory()
	{
		String workingDirectory; //variable for where everything is stored
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
		
		return workingDirectory +"\\Password_Vault";
	}
	
	public String encrypt(String str)
	{
		// encode data using BASE64
		byte[] bytesEncoded = Base64.encodeBase64(str.getBytes());
		return new String(bytesEncoded);
	}
}
