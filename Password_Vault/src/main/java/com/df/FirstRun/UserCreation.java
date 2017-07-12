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
		
		fileCreation(fileContent());
	}
	
	private String fileContent()
	{
		rnd = new Random();
		StringBuilder sb = new StringBuilder();
		
		for (int c = 0; c < 90300; c++) //for every character outputted
		{
			char l = (char) (rnd.nextInt(127 - 33) + 33);

			if (sb.length() > 0)
				if (sb.toString().substring(sb.length() - 1).equals("\\"))
					if (l == 'n' || l == 'b' || l == 't' || l == 'r' || l == '\\' || l == 'f' || l == '\"')
						while (l == 'n' || l == 'b' || l == 't' || l == 'r' || l == '\\' || l == 'f' || l == '\"')
							l = (char) (rnd.nextInt(127 - 33) + 33);
					
			sb.append(l);
		}
		
		return sb.toString();
	}
	
	private void fileCreation (String content)
	{
		rnd = new Random();
		char fn = accInfo[0].toUpperCase().charAt(0);
		char sn = accInfo[1].toUpperCase().charAt(0);
		char color = accInfo[3].toUpperCase().charAt(0);

		StringBuilder tme = new StringBuilder();
		tme.append(Integer.toString(LocalDateTime.now().getYear()) + String.format("%02d", LocalDateTime.now().getMonthValue()) + String.format("%02d", LocalDateTime.now().getDayOfMonth()) + Integer.toString(LocalDateTime.now().getHour()) + Integer.toString(LocalDateTime.now().getMinute()));
		
		for (int t = 0; t < accInfo[4].length(); t++)
			tme.insert(rnd.nextInt(tme.length()), accInfo[4].charAt(t));
		
		File acc = new File (gv.workingDirectory + "\\sys\\acc\\" + color + tme + fn + sn + ".txt");
		
		if (!acc.getParentFile().exists())
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
	Random rnd;
}