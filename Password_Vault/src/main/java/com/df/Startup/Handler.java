package com.df.Startup;

import com.df.Global.Global_Frame;
import com.df.Global.Global_Vars;

public class Handler 
{
	public Handler()
	{
		gv = new Global_Vars();
		gf = new Global_Frame();
		
		if (gv.usersDir.exists() && gv.usersDir.listFiles().length != 0)
			Login.main(null);
		else
		{
			gf.getAccNum();
			gf.setVisible(true);
		}
	}
	
	public static void main(String[] args) 
	{
		new Handler();
	}

	Global_Vars gv = null;
	Global_Frame gf = null;
}