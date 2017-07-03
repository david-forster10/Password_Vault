package com.df.FirstRun;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
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
		
		fileCreation(fileContent(), in[0], in[1]);
	}
	
	public String fileContent()
	{
		StringBuilder sb = new StringBuilder();
		
		for (int r = 0; r < 300; r++) //for every line required
		{
			for (int c = 0; c < 301; c++) //for every character outputted
			{
				Random rnd = new Random();
			
				char l = (char) (rnd.nextInt(127 - 33) + 33);

				if (sb.length() > 0)
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
		}
		
		return sb.toString();
	}
	
	public void fileCreation (String content, String fn, String sn)
	{
		String tme = Integer.toString(LocalDateTime.now().getYear()) + String.format("%02d", LocalDateTime.now().getMonthValue()) + String.format("%02d", LocalDateTime.now().getDayOfMonth()) + Integer.toString(LocalDateTime.now().getHour()) + Integer.toString(LocalDateTime.now().getMinute());
		File acc = new File (gv.workingDirectory + "\\sys\\acc\\" + tme + fn.substring(0, 1).toUpperCase() + sn.substring(0, 1).toUpperCase() + ".txt");
		
		acc.getParentFile().mkdirs();
		
		PrintWriter writer = null;
		
		try 
		{
			writer = new PrintWriter(acc);
			writer.println(content);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		writer.close();
	}
	
	String[] accInfo;
	Global_Vars gv;
}