package com.df.Global;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import com.df.FirstRun.AccDetails;
import com.df.FirstRun.FirstTimeRun;

public class Global_Frame extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	private WindowListener exitListener = new WindowAdapter()
	{
		@Override
		public void windowClosing(WindowEvent e) 
		{
			gv.closingEvent(); // if window closing, go to exit menu
		}
	};
	
	public Global_Frame()
	{
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // get look and feel based on OS
		} 
		catch (ClassNotFoundException ex) // catch all errors that may occur
		{
			Logger.getLogger(Global_Frame.class.getName()).log(Level.SEVERE, null, ex);
		} 
		catch (InstantiationException ex) 
		{
			Logger.getLogger(Global_Frame.class.getName()).log(Level.SEVERE, null, ex);
		} 
		catch (IllegalAccessException ex) 
		{
			Logger.getLogger(Global_Frame.class.getName()).log(Level.SEVERE, null, ex);
		} 
		catch (UnsupportedLookAndFeelException ex) 
		{
			Logger.getLogger(Global_Frame.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		gv = new Global_Vars(); //instantiating new class
		
		initComponents();
	}
	
	public void initComponents()
	{
		removeWindowListener(exitListener);
		addWindowListener(exitListener); // removing before adding the windowlistener, ensures there is only one listener there
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); // setting "x" button to do nothing except what exitListener does
		setBackground(Color.WHITE);
		
		try 
		{
			Image frameIcon = ImageIO.read(new File(gv.workingDirectory + "\\apps\\assets_pv1.0\\Logo.png"));
			setIconImage(frameIcon); // trying to read and add the logo to the application
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		setTitle("Password_Vault 1.0"); // setting title on JFrame
		setResizable(false); // disabling resizing
		setPreferredSize(new Dimension(600, 400)); // setting measurements of jframe
		
		revalidate(); // revalidate the elements that will be displayed
		repaint(); // repainting what is displayed if going coming from a different form
		pack(); // packaging everything up to use
		setLocationRelativeTo(null); // setting form position central
	}

	public void getAccNum()
	{
		getContentPane().removeAll();
		
		setPreferredSize(new Dimension(600, 400)); // setting measurements of jframe
		add(new FirstTimeRun());
		
		repaint();
		revalidate();
		pack();
	}
	
	public void getUserDetails()
	{
		resizing(750, 500);

		add(new AccDetails(accNum));
	}
	
	private void resizing(int width, int height)
	{
		timer = new Timer (10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				getContentPane().removeAll();
				setPreferredSize(new Dimension(sizeW, sizeH));

				revalidate();
				repaint();
				pack();
				
				sizeW += 2;
				sizeH += 2;
				
				if (toggle)
				{
					setLocationRelativeTo(null);
					toggle = false;
				}
				else
				{
					toggle = true;
				}
				
				if (sizeW == width && sizeH == height)
				{
					timer.stop();
				}
			}
		});
		
		timer.start();
	}
	
	//variables used for window resizing
	private Timer timer;
	private int sizeW = 600;
	private int sizeH = 400;
	private boolean toggle = false;
	
	public static int accNum = 0;
	Global_Vars gv;
}
