package com.df.Startup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.df.Global.Global_Frame;
import com.df.Global.Global_Vars;

public class Handler 
{
	public Handler()
	{
		gv = new Global_Vars();
		gf = new Global_Frame();
		
		getProperty("wmic diskdrive get serialnumber", 1); //calling method with command needed to be ran and number referencing this is the first command
		getProperty("wmic bios get serialnumber", 2); //calling method with command needed to be ran and number referencing this is the second command
		getProperty("echo %username%", 3); //calling method with command needed to be ran and number referencing this is the third command
		getProperty("getmac", 4); //calling method with command needed to be ran and number referencing this is the fourth command
		
		int tmp = 0;
		for (String prop : serialNumbers)
		{
			serialNumbers.set(tmp, gv.convert(prop));
			tmp++;
		}
		
		if (compareProps(compspec(), serialNumbers))
		{
			if (new File(gv.usersDir).exists() && new File(gv.usersDir).listFiles().length != 0)
			{
				gf.goLogin();
				gf.setVisible(true);
			}
			else
			{
				gf.getAccNum();
				gf.setVisible(true);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Incorrect matching properties, please run this off your usual machine.");
			System.exit(0);
		}
	}
	
	private ArrayList<String> compspec()
	{
		ArrayList<Integer> index = new ArrayList<Integer>();
		Scanner in = null;
		try 
		{
			in = new Scanner(new File(gv.workingDirectory+"\\sys\\config\\sys_cfg.txt"));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		String id = gv.decrypt64(in.nextLine());
		
		for (int i = 0; i < id.length(); i++)
			if (id.charAt(i) == '%' || id.charAt(i) == '^' || id.charAt(i) == '&' || id.charAt(i) == '*')
				index.add(i);
		
		StringBuilder[] out = new StringBuilder[index.size()];
		
		for (int q = 0; q < out.length; q++)
			out[q] = new StringBuilder();
		
		while (in.hasNextLine())
		{
			String line = gv.decrypt64(in.nextLine());
			for (int o = 0; o < index.size(); o++)
				out[o].append(line.charAt(index.get(o)));
		}
		
		ArrayList<String> returner = new ArrayList<String>();
		
		for (StringBuilder build : out)
			returner.add(build.toString());
			
		in.close();
		
		return returner;
	}
	
	private boolean compareProps(ArrayList<String> old, ArrayList<String> nw)
	{
		Boolean[] val = new Boolean[old.size()];
		int s = 0;
		
		if (old.size() == nw.size())
			for (String o : old)
			{
				for (String n : nw)
					if (o.equals(n))
						val[s] = true;
						
				s++;
			}
		
		if (Arrays.asList(val).contains(true))
			return true;
		
		return false;
	}

	private void getProperty (String cmd, int cmdNum) //code to collect unique identifier information
	{
		try
		{
			Process p = Runtime.getRuntime().exec("cmd /c " + cmd); //running cmd command to retrieve disk serialnumbers
			p.waitFor();
			BufferedReader reader = new BufferedReader( //buffered reader to read output from cmd command
					new InputStreamReader(p.getInputStream())
			);
			
			String line;
			Pattern pat = Pattern.compile("[ \t]", Pattern.CASE_INSENSITIVE); //regex pattern to remove blank lines
			
			while ((line = reader.readLine()) != null) //ensuring that all results are covered
			{
				Matcher match = pat.matcher(line.substring(0)); //declaring matcher to check the entirety of the line
				if (cmdNum == 1) //if first command
					if (!line.equals("") && match.find() && !line.equals("SerialNumber       ")) //makes sure not a blank line or the title of the command
						serialNumbers.add(line); //will add all valid serial numbers
				if (cmdNum == 2) //if second command
					if (!line.equals("") && match.find() && !line.equals("SerialNumber  ")) //makes sure not a blank line or the title of the command
					{
						serialNumbers.add(line); //will add all valid serial numbers
					}
				if (cmdNum == 3) //if third command
				{
						serialNumbers.add(line); //will add user name as is first thing returned
				}
				if (cmdNum == 4) //if fourth command
					if (!line.equals("") && match.find() && !line.equals("Physical Address    Transport Name                                            ") && !line.equals("=================== ==========================================================")) //makes sure not blank line, title of output or separating line
						if (line.substring(0, 17).contains("N/A")) //if the line is "N/A"
							serialNumbers.add(line.substring(0, 3)); //only add "N/A" rather than extending to include excessive spacing after it
						else //else
							serialNumbers.add(line.substring(0, 17)); //only add first 17 characters of line (the user's MAC address)
			}
		}
		catch (IOException | InterruptedException e) //catch any errors that may occur
		{
			JOptionPane.showMessageDialog(null, "<html><center>Unique Identifier information not retrieved!<br></center></html>", "Warning", JOptionPane.WARNING_MESSAGE); //throw information that an error has occurred
		}
	}
	
	public static void main(String[] args) 
	{
		new Handler();
	}

	Global_Vars gv = null;
	Global_Frame gf = null;
	private ArrayList<String> serialNumbers = new ArrayList<String>();
}