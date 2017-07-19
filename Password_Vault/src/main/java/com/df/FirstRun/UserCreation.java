package com.df.FirstRun;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Random;

import com.df.Global.Global_Vars;

public class UserCreation extends FirstTimeRun
{
	private static final long serialVersionUID = 1L;
	
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
		
		int[] triggers = {rnd.nextInt(15050), rnd.nextInt(30100 - 15050) + 15050, rnd.nextInt(45150 - 30100) + 30100, rnd.nextInt(60200 - 45150) + 45150, rnd.nextInt(75250 - 60200) + 60200, rnd.nextInt(90300 - 75250) + 75250};
		
		for (int hex : triggers)
		{
			char ran = '.';
			switch (rnd.nextInt(4))
			{
				case 0:
					ran = '(';
					break;
				case 1:
					ran = '#';
					break;
				case 2:
					ran = '.';
					break;
				case 3:
					ran = ']';
					break;
				default:
					ran = '~';
			}
			sb.append(ran + hexConvert(hex));
		}
		
		for (int c = 0; c < 90300; c++) //for every character outputted
		{
			char l = (char) (rnd.nextInt(127 - 33) + 33);

			if (sb.length() > 0)
				if (sb.toString().substring(sb.length() - 1).equals("\\"))
					if (l == 'n' || l == 'b' || l == 't' || l == 'r' || l == '\\' || l == 'f' || l == '\"')
						while (l == 'n' || l == 'b' || l == 't' || l == 'r' || l == '\\' || l == 'f' || l == '\"')
							l = (char) (rnd.nextInt(127 - 33) + 33);
			
			if (c == triggers[0])
				sb.append(charReplace(accInfo[0]));
			else if (c > triggers[0])
				if (c == triggers[1])
					sb.append(charReplace(accInfo[1]));
				else if (c > triggers[1])
					if (c == triggers[2])
						sb.append(charReplace(accInfo[2]));
					else if (c > triggers[2])
						if (c == triggers[3])
							sb.append(charReplace(accInfo[3]));
						else if (c > triggers[3])
							if (c == triggers[4])
								sb.append(charReplace(accInfo[4]));
							else if (c > triggers[4])
								if (c == triggers[5])
									sb.append(charReplace(accInfo[5]));
					
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
	
	private String charReplace (String in)
	{
		in = in.toUpperCase();
		
		StringBuilder out = new StringBuilder();

		for (int i = 0; i < in.length(); i++)
		{
			char c = in.charAt(i);
			
			switch (c)
			{
			case 'I':
				out.append("1");
				break;
			case 'E':
				out.append("3");
				break;
			case 'A':
				out.append("4");
				break;
			case 'L':
				out.append("7");
				break;
			case 'B':
				out.append("8");
				break;
			case 'O':
				out.append("0");
				break;
			case 'Q':
				out.append("&");
				break;
			case 'G':
				out.append("6");
				break;
			case 'N':
				out.append("^");
				break;
			default:
				out.append(c);
			}
		}
		return out.toString();
	}
	
	private String hexConvert(int num)
	{
	     int rem;
	     String str2=""; 
	 
	     char hex[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	 
	     while(num>0)
	     {
	       rem=num%16; 
	       str2=hex[rem]+str2; 
	       num=num/16;
	     }
	     
	     return str2;
	}
	
	String[] accInfo;
	Global_Vars gv;
	Random rnd;
	FirstTimeRun ftr;
}