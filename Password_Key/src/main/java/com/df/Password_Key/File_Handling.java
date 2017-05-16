package com.df.Password_Key;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class File_Handling extends Global_Vars 
{
	public boolean Val_PropFile()
	{
		File propFile = new File (workingDirectory+"\\Password_Vault\\config.txt"); //getting location of stored system variables
		
		if (propFile.exists()) //ensuring exists
		{
			try
			{
				BufferedReader propIn = new BufferedReader(new FileReader(propFile)); //declare reader for file
				
				while (propIn.readLine() != null) //while the file has information
				{
					savedSerialProps.add(propIn.readLine()); //add each line to the arraylist declared in Global_Vars
				}
				propIn.close(); //close the connection
			}
			catch (FileNotFoundException e)
			{
				System.out.println("Error file not found"); //output message if file not found
				e.printStackTrace(); //print to console the official error message
				return false; //return false due to error
			}
			catch (IOException e)
			{
				System.out.println("Error reading file"); //output message if failed to read file
				e.printStackTrace(); //print to console the official error message
				return false; //return false due to error
			}
			
			return true; //return true if successful information retrieval
		}
		else //if doesn't exist
		{
			System.out.println("ERROR: Files are missing, application cannot be ran"); //outputs error and passes a false back to the method that called it
			return false;
		}
	}
	
	public void Valid_Run() //checking if a valid run from Password_Vault
	{	
		File runner = new File (workingDirectory+"\\Password_Vault\\run.txt"); //location of temp file created by system to prove valid run
		
		if (runner.exists()) //if file exists
		{
			try
			{
				@SuppressWarnings("resource")
				BufferedReader runnerIn = new BufferedReader(new FileReader(runner)); //create a reader for it
				
				while (runnerIn.readLine() != null)
				{
					runInput = runnerIn.readLine(); //read in the input into the variable (should only be one line)
				}
				
				String hashedRunInput = BCryptHash(runInput); //hash the input and store it in variable
				String hashedAnswer = BCryptHash("5D55C415E356DD7B59F329AA5C83A34EE10C3803516AC569FDE5E68804A7E06C"); //hash to be compared against
				
				if (hashedRunInput.equals(hashedAnswer)) //if expected is met
				{
					Val_PropFile(); //check system properties
				}
				else
				{
					System.out.println("ERROR: You are not authorised to run this application, please run Password_Vault.exe first"); //output error if hashes don't match
				}
			}
			catch (FileNotFoundException e)
			{
				System.out.println("Error file not found"); //output message if file not found
				e.printStackTrace(); //print to console the official error message
			}
			catch (IOException e)
			{
				System.out.println("Error reading file"); //output message if failed to read file
				e.printStackTrace(); //print to console the official error message
			}
		}
		else //if file doesn't exist
		{
			File appFolder = new File(workingDirectory+"\\Password_Vault");
			if (!appFolder.exists()) //if Password_Vault folder doesn't exist
			{
				//MESSAGE TO RUN INSTALLATION
			}
			else
			{
				System.out.println("ERROR: You are not authorised to run this application, please run Password_Vault.exe first"); //else error, run Password_Vault
			}
		}
	}
}
