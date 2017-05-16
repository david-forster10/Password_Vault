package com.df.Password_Key;

public class Runner extends Global_Vars
{
	public static void main(String[] args) 
	{
		//application starts here
		getWorkingDirectory(); //gets working directory based on OS
		File_Handling fh = new File_Handling(); //instantiates file_handling class
		fh.Valid_Run(); //checks whether the Password_Key was ran by user or ran by Password_Vault
	}
}