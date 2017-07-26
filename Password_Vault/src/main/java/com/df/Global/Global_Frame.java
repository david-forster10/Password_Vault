package com.df.Global;

import java.awt.Dimension;
import java.awt.EventQueue;
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
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import com.df.FirstRun.AccDetails;
import com.df.FirstRun.FirstTimeRun;
import com.df.Startup.Login;

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
	
	ActionListener val = new ActionListener()
	{
		public void actionPerformed(ActionEvent e) 
		{
			valSub();
		}
	};
	
	ActionListener toLog = new ActionListener()
	{
		public void actionPerformed(ActionEvent e) 
		{
			panel_2.accHandler(accNum);
			
			getContentPane().removeAll();
			resizing(450, 250, 2);
		}
	};
	
	ActionListener valLog = new ActionListener()
	{
		public void actionPerformed(ActionEvent e) 
		{
			String[] in = new String[2];
			in[0] = gv.convert(gv.txtUsername.getText());
			in[1] = gv.convert(gv.txtPassword.getPassword().toString());
			
			for (String[] orig : gv.comparison)
				if (in == orig)
					System.out.println("YAY");
			
			getContentPane().removeAll();
			resizing(450, 250, 2);
		}
	};
	
	public Global_Frame()
	{
		gv = new Global_Vars(); //instantiating new class
		
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
		
		EventQueue.invokeLater(new Runnable()
		{
			public void run() //run the class's constructor, therefore starting the UI being built
			{
				initComponents();
			}
		});
	}
	
	public void initComponents()
	{
		removeWindowListener(exitListener);
		addWindowListener(exitListener); // removing before adding the windowlistener, ensures there is only one listener there
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); // setting "x" button to do nothing except what exitListener does
		
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
		
		revalidate(); // revalidate the elements that will be displayed
		repaint(); // repainting what is displayed if going coming from a different form
		pack(); // packaging everything up to use
		setLocationRelativeTo(null); // setting form position central
	}

	public void getAccNum()
	{
		setPreferredSize(new Dimension(600, 400)); // setting measurements of jframe
		FirstTimeRun panel_1 = new FirstTimeRun(val, gv);
		add(panel_1);
		
		revalidate();
		repaint();
		pack();
	}

	private void valSub()
	{
		if (gv.validation(1, gv.txtAccNum.getText()) != true || Integer.parseInt(gv.txtAccNum.getText()) > 11) 
		{
			JOptionPane.showMessageDialog(null, "Invalid number, please try again");
			gv.txtAccNum.setText("");
			gv.txtAccNum.requestFocusInWindow();
		}
		else
		{
			accNum = Integer.parseInt(gv.txtAccNum.getText());
			getContentPane().removeAll();
			
			resizing(750, 500, 1);
		}
	}
	
	private void getUserDetails()
	{
		panel_2 = new AccDetails(accNum, gv, toLog);
		add(panel_2);
		
		revalidate();
		repaint();
		pack();
	}
	
	public void goLogin()
	{
		setPreferredSize(new Dimension(400, 260)); // setting measurements of jframe
		setLocationRelativeTo(null); // setting form position central
		
		panel1 = new Login(gv, valLog);
		add(panel1);
		
		revalidate();
		repaint();
		pack();
	}
	
	private void resizing(int width, int height, int destination)
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
				
				if (!wToggle && sizeW < width)
					sizeW += 2;
				else if (!wToggle && sizeW > width)
					sizeW -= 2;
				
				if (!hToggle && sizeH < height)
					sizeH += 2;
				else if (!hToggle && sizeH > height)
					sizeH -= 2;

				if (toggle)
				{
					setLocationRelativeTo(null);
					toggle = false;
				}
				else
					toggle = true;
				
				if (sizeW == width)
					wToggle = true;
				
				if (sizeH == height)
					hToggle = true;
				
				if (hToggle && wToggle)
				{
					timer.stop();
					
					hToggle = false;
					wToggle = false;
					
					switch (destination)
					{
					case 1:
						getUserDetails();
						break;
					case 2:
						goLogin();
						break;
					}
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
	private boolean wToggle = false;
	private boolean hToggle = false;
	
	private AccDetails panel_2;
	private Login panel1;
	
	public int accNum = 0;
	Global_Vars gv;
}