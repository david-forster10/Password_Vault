package com.df.FirstRun;

import java.io.PrintWriter;
import java.util.Random;

import com.df.Global.Global_Vars;

public class UserCreation 
{
	public UserCreation()
	{
		gv = new Global_Vars();
	}
	
	public void infoHandler(String[] in)
	{
		accInfo = in;
		
		fileCreation(fileContent());
	}
	
	public String fileContent()
	{
		String content = "";
		
		for (int c = 0; c < 1000; c++)
		{
			Random rnd = new Random();
			
		}
		
		return content;
	}
	
	public void fileCreation (String content)
	{
		PrintWriter writer;
		
		
	}
	
	String[] accInfo;
	Global_Vars gv;
}