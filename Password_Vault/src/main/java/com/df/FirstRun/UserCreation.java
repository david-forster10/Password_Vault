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
		StringBuilder sb = new StringBuilder();
		
		String content = "";
		
		for (int c = 0; c < 301; c++)
		{
			Random rnd = new Random();
			
			char l = (char) (rnd.nextInt(127 - 33) + 33);

			if (sb.toString().substring(sb.length() - 1).equals("\\"))
				if (l == 'n' || l == 'b' || l == 't' || l == 'r' || l == '\\' || l == 'f' || l == '\"')
					while (l == 'n' || l == 'b' || l == 't' || l == 'r' || l == '\\' || l == 'f' || l == '\"')
					{
						l = (char) (rnd.nextInt(127 - 33) + 33);
					}
					
			sb.append(l);
		}
		if (sb.toString().substring(sb.length() - 1).equals("\\"))
			sb.replace(sb.length() - 1, sb.length(), "a");
		
		sb.append("\n");
		
		return content;
	}
	
	public void fileCreation (String content)
	{
		PrintWriter writer;
		
		
	}
	
	String[] accInfo;
	Global_Vars gv;
}