package com.df.Password_Vault;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public class Login extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private WindowListener exitListener = new WindowAdapter()
	{
		@Override
		public void windowClosing (WindowEvent e)
		{
			closingEvent(); //if window closing, go to exit menu
		}
	};
	
	public Login()
	{
		gv = new Global_Vars();
		
		gv.getWorkingDirectory();
		
		initComponents();
	}
	
	private void initComponents () //method to build initial view for user for installation
	{
		//instantiating elements of the GUI
		pnlStart = new JPanel();
		Welcome = new JLabel();
		MainTxt = new JLabel();
		Divider = new JLabel();
		btnStartNext = new JButton();
		btnBackCancel = new JButton();
		
		pnlStart.setVisible(true);
		add(pnlStart); //adding the panel to the frame
		
		removeWindowListener(exitListener);
		addWindowListener(exitListener); //removing before adding the windowlistener, ensures there is only one listener there
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //setting "x" button to do nothing except what exitListener does
		setPreferredSize(new Dimension(500, 300)); //setting measurements of jframe
		
		try
		{
			Image frameIcon = ImageIO.read(new File(gv.workingDirectory + "\\apps\\assets_pv1.0\\Logo.png"));
			setIconImage(frameIcon); //trying to read and add the logo to the application
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		setTitle("Password_Vault 1.0"); //setting title on JFrame
		setResizable(false); //disabling resizing
		setLayout(null); //ensuring I can specify element positions
		setBackground(Color.WHITE); //setting background color
	
		Welcome.setText("Welcome to Password_Vault"); //label welcoming user
		Welcome.setFont(Welcome.getFont().deriveFont(22.0f)); //changing font size to 22
		Welcome.setFont(Welcome.getFont().deriveFont(Font.BOLD)); //changing font style to bold
		Welcome.setBounds(91, 30, 317, 25); //setting position and measurements
		add(Welcome); //adding label to form
		
		MainTxt.setText("<html></html>"); //main label that explains what happens, html used for formatting
		MainTxt.setFont(MainTxt.getFont().deriveFont(16.0f)); //changing font size to 16
		MainTxt.setBounds(20, 180, 730, 194); //setting position and measurements
		add(MainTxt); //adding label to JFrame
		
		Divider.setText(""); //ensuring no text in label
		Divider.setBounds(10, 385, 730, 10); //setting bounds and position of dividing line
		Divider.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); //setting border to label for the dividing
		add(Divider); //adding it to JFrame
		
		btnStartNext.setText("Next"); //adding text to button for starting
		btnStartNext.setFont(MainTxt.getFont().deriveFont(14.0f)); //setting font size
		btnStartNext.setBounds(645, 415, 80, 35); //positioning start button		
		btnStartNext.addActionListener(new ActionListener() //add listener for action to run method
		{
			public void actionPerformed (ActionEvent evt) 
			{
			//	btnNextActionPerformed(); //running start method
			}
		});		
		add(btnStartNext); //adding button to JFrame
		
		btnBackCancel.setText("Cancel"); //adding text to button for exiting
		btnBackCancel.setFont(btnStartNext.getFont()); //getting font from start button
		btnBackCancel.setBounds(20, 415, 80, 35); //positioning on form
		btnBackCancel.addActionListener(new ActionListener() //add listener for action to run method
		{
			public void actionPerformed (ActionEvent evt)
			{
				closingEvent(); //running cancel method (same method as hitting the "x" button on the form)
			}
		});
		add(btnBackCancel); //adding button to JFrame
		
		repaint(); //repainting what is displayed if going coming from a different form
		revalidate(); //revalidate the elements that will be displayed
		pack(); //packaging everything up to use
		setLocationRelativeTo(null); //setting form position central
		btnStartNext.requestFocusInWindow(); //setting focus on start button when everything is loaded
	}
	
	private void closingEvent()
	{
		if (JOptionPane.showConfirmDialog(null, "<html><center>WARNING!<br>This will cancel installation<br>Are you sure you want to do this?</center></html>", "WARNING!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
		{ //output warning that it would cancel installation, if accepted...
			System.exit(0);
		}
		else //if not accepted...
		{
			if (MainTxt.getText().substring(0, 9).equals("<html>The"))
			{
				getContentPane().removeAll();
				initComponents();
			}
		}
	}
	
	public static void main(String[] args) 
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //get look and feel based on OS
		}
		catch (ClassNotFoundException ex) //catch all errors that may occur
		{
			Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (InstantiationException ex)
		{
			Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (IllegalAccessException ex)
		{
			Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (UnsupportedLookAndFeelException ex)
		{
			Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		
		EventQueue.invokeLater(new Runnable()
		{
			public void run() //run the class's constructor, therefore starting the UI being built
			{
				new Login().setVisible(true);
			}
		});
	}

	//objects used in UI
	private JPanel pnlStart;
	private JLabel Welcome;
	private JLabel MainTxt;
	private JLabel Divider;
	private JButton btnStartNext;
	private JButton btnBackCancel;
	
	//instantiating required classes
	Global_Vars gv = null;
}