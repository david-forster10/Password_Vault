package com.df.FirstRun;

import com.df.Global.Global_Vars;

public class UserCreation 
{
	public UserCreation()
	{
		gv = new Global_Vars();
	}
	
	public void infoHandler (String[] in)
	{
		accInfo = in;
	}
	
	public void fileCreation (String userName, String Password)
	{
		
	}
	
	String[] accInfo;
	Global_Vars gv;
}