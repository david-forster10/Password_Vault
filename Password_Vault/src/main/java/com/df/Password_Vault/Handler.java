package com.df.Password_Vault;

public class Handler 
{
	public Handler()
	{
		gb = new Global_Vars();
		gb.getWorkingDirectory();
		
		if (gb.usersDir.exists() && gb.usersDir.listFiles().length != 0)
			Login.main(null);
		else
			FirstTimeRun.main(null);
	}
	
	public static void main(String[] args) 
	{
		new Handler();
	}

	Global_Vars gb = null;
}