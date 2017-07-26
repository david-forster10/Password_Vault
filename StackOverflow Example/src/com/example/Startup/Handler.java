package com.example.Startup;

import com.example.Global.Global_Frame;

public class Handler 
{
	public Handler()
	{
		gf = new Global_Frame();
		
			gf.getAccNum();
			gf.setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		new Handler();
	}

	Global_Frame gf = null;
}