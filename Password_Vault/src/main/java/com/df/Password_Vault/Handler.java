package com.df.Password_Vault;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Handler 
{
	public Handler()
	{
		gb = new Global_Vars();
		gb.getWorkingDirectory();
		
		if (testCheck())
			System.exit(0);
		
		if (gb.usersDir.exists() && gb.usersDir.listFiles().length != 0)
			new Login().setVisible(true);
		else
			new FirstTimeRun().setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		new Handler();
	}
	
	private boolean testCheck()
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
					return true;
				}
			}
			return false;
		}
		catch (Throwable t)
		{
			t.printStackTrace();
			return true;
		}
	}

	Global_Vars gb = null;
}