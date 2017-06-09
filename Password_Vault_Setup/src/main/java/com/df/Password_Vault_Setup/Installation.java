package com.df.Password_Vault_Setup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import org.apache.commons.io.FileUtils;

public class Installation extends JFrame implements PropertyChangeListener
{	
	/**
	 * Primary Release Version 1.0
	 */
	private static final long serialVersionUID = 1L;
	private WindowListener exitListener = new WindowAdapter()
	{
		@Override
		public void windowClosing (WindowEvent e)
		{
			btnCancelActionPerformed(); //if window closing, go to exit menu
		}
	};
	
	private WindowListener uninstallExitList = new WindowAdapter()
	{
		@Override
		public void windowClosing (WindowEvent e)
		{
			btnCancelunInstallActionPerformed(); //if window closing, go to exit menu
		}
	};
	
	public Installation() //constructor of class, start initComponents
	{
		initComponents();
	}
	
	private void initComponents () //method to build initial view for user for installation
	{
		//instantiating elements of the GUI
		pnlStart = new JPanel();
		lblArtTop = new JLabel();
		lblWelcome = new JLabel();
		lblMainTxt = new JLabel();
		lblDivider = new JLabel();
		btnStartNext = new JButton();
		btnBackCancel = new JButton();
		
		pnlStart.setVisible(true);
		add(pnlStart); //adding the panel to the frame
		
		removeWindowListener(exitListener);
		removeWindowListener(uninstallExitList);
		addWindowListener(exitListener); //removing before adding the windowlistener, ensures there is only one listener there
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //setting "x" button to do nothing except what exitListener does
		setPreferredSize(new Dimension(750, 500)); //setting measurements of jframe
		
		runDir = System.getProperty("user.dir");
		imgDir = runDir.substring(0, runDir.length()-4);
		imgDir = imgDir + "assets\\assets_pv1.0";
		appDir = imgDir.substring(0, imgDir.length()-13);
		
		try
		{
			Image frameIcon = ImageIO.read(new File(imgDir + "\\Logo.png"));
			setIconImage(frameIcon); //trying to read and add the logo to the application
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		setTitle("Password_Vault 1.0 Setup"); //setting title on JFrame
		setResizable(false); //disabling resizing
		setLayout(null); //ensuring I can specify element positions
		setBackground(Color.WHITE); //setting background color

		Icon topArt = new ImageIcon(imgDir+"\\topArt.jpg");
		lblArtTop.setIcon(topArt); //reading in Art to be displayed at top of JFrame
			
		lblArtTop.setBounds(0, 0, 750, 100); //setting position and measurements of art
		add(lblArtTop); //adding art to frame
		
		lblWelcome.setText("Password_Vault Setup Wizard"); //label welcoming user
		lblWelcome.setFont(lblWelcome.getFont().deriveFont(22.0f)); //changing font size to 22
		lblWelcome.setFont(lblWelcome.getFont().deriveFont(Font.BOLD)); //changing font style to bold
		lblWelcome.setBounds(208, 125, 334, 25); //setting position and measurements
		add(lblWelcome); //adding label to form
		
		lblMainTxt.setText("<html>The following wizard will set up the necessary files and directories on your computer to be used by the Password_Vault system."
				+ "<br><br><br>"
				+ "This installation process shouldn't take more than a few minutes and you can continue to use your computer while this takes place."
				+ "<br><br><br>"
				+ "Click 'Next' to continue."
				+ "<br><br>"
				+ "</html>"); //main label that explains what happens, html used for formatting
		lblMainTxt.setFont(lblMainTxt.getFont().deriveFont(16.0f)); //changing font size to 16
		lblMainTxt.setBounds(20, 180, 730, 194); //setting position and measurements
		add(lblMainTxt); //adding label to JFrame
		
		lblDivider.setText(""); //ensuring no text in label
		lblDivider.setBounds(10, 385, 730, 10); //setting bounds and position of dividing line
		lblDivider.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); //setting border to label for the dividing
		add(lblDivider); //adding it to JFrame
		
		btnStartNext.setText("Next"); //adding text to button for starting
		btnStartNext.setFont(lblMainTxt.getFont().deriveFont(14.0f)); //setting font size
		btnStartNext.setBounds(645, 415, 80, 35); //positioning start button
		
		btnStartNext.addActionListener(new ActionListener() //add listener for action to run method
		{
			public void actionPerformed (ActionEvent evt) 
			{
				btnNextActionPerformed(); //running start method
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
				btnCancelActionPerformed(); //running cancel method (same method as hitting the "x" button on the form)
			}
		});
		
		add(btnBackCancel); //adding button to JFrame
		
		repaint(); //repainting what is displayed if going coming from a different form
		revalidate(); //revalidate the elements that will be displayed
		pack(); //packaging everything up to use
		setLocationRelativeTo(null); //setting form position central
		btnStartNext.requestFocusInWindow(); //setting focus on start button when everything is loaded
	}
	
	private void btnNextActionPerformed () //code for second screen of installation (selects shortcuts to be installed)
	{
		pnlShortcut = new JPanel();
		lblArtTop = new JLabel();
		lblWelcome = new JLabel();
		lblMainTxt = new JLabel();
		lblDivider = new JLabel();
		btnStartNext = new JButton();
		btnBackCancel = new JButton();
		chkbxDesktopRun = new JCheckBox("Desktop");
		chkbxQuick = new JCheckBox("Quick Start Menu");
		
		getContentPane().removeAll();
		pnlShortcut.setVisible(true);
		add(pnlShortcut);

		removeWindowListener(exitListener);
		removeWindowListener(uninstallExitList);
		addWindowListener(exitListener); //removing before adding the windowlistener, ensures there is only one listener there
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //setting "x" button to do nothing except what exitListener does
		setPreferredSize(new Dimension(750, 500)); //setting measurements of jframe
		
		try
		{
			Image frameIcon = ImageIO.read(new File(imgDir + "\\Logo.png"));
			setIconImage(frameIcon); //trying to read and add the logo to the application
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		setTitle("Password_Vault 1.0 Setup"); //setting title on JFrame
		setResizable(false); //disabling resizing
		setLayout(null); //ensuring I can specify element positions
		setBackground(Color.WHITE); //setting background color

		Icon topArt = new ImageIcon(imgDir + "\\topArt.jpg");
		lblArtTop.setIcon(topArt); //reading in Art to be displayed at top of JFrame
			
		lblArtTop.setBounds(0, 0, 750, 100); //setting position and measurements of art
		add(lblArtTop); //adding art to frame
		
		lblWelcome.setText("Password_Vault Setup Wizard"); //label welcoming user
		lblWelcome.setFont(lblWelcome.getFont().deriveFont(22.0f)); //changing font size to 22
		lblWelcome.setFont(lblWelcome.getFont().deriveFont(Font.BOLD)); //changing font style to bold
		lblWelcome.setBounds(208, 125, 334, 25); //setting position and measurements
		add(lblWelcome); //adding label to form
		
		lblMainTxt.setText("<html>Please select the shortcuts you'd like installed before hitting 'Start' to begin the installation: </html>"); //changing text assigned to the label
		lblMainTxt.setFont(lblMainTxt.getFont().deriveFont(16.0f)); //ensuring same font as before
		lblMainTxt.setBounds(20, 179, 720, 19); //setting bounds and position
		add(lblMainTxt); //adding it to the form
		
		chkbxDesktopRun.setSelected(true);
		chkbxDesktopRun.setFont(lblMainTxt.getFont().deriveFont(16.0f));
		chkbxDesktopRun.setBounds(50, 225, 150, 19);
		add(chkbxDesktopRun);
		
		if (!bDesktop)
			chkbxDesktopRun.setSelected(false);
			
		chkbxQuick.setSelected(true);
		chkbxQuick.setFont(lblMainTxt.getFont());
		chkbxQuick.setBounds(50, 280, 150, 19);
		add(chkbxQuick);
		
		if (!bQuick)
			chkbxQuick.setSelected(false);
		
		lblDivider.setText(""); //ensuring no text in label
		lblDivider.setBounds(10, 385, 730, 10); //setting bounds and position of dividing line
		lblDivider.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); //setting border to label for the dividing
		add(lblDivider); //adding it to JFrame
		
		btnStartNext.setText("Start"); //adding text to button for starting
		btnStartNext.setFont(lblMainTxt.getFont().deriveFont(14.0f)); //setting font size
		btnStartNext.setBounds(645, 415, 80, 35); //positioning start button
		
		btnStartNext.addActionListener(new ActionListener() //add listener for action to run method
		{
			public void actionPerformed (ActionEvent evt) 
			{
				if (!chkbxDesktopRun.isSelected())
					bDesktop = false;
				else
					bDesktop = true;
				
				if (!chkbxQuick.isSelected())
					bQuick = false;
				else
					bQuick = true;
				
				btnStartActionPerformed(); //running next method
			}
		});
		
		add(btnStartNext); //adding button to JFrame
		
		btnBackCancel.setText("Back"); //adding text to button for exiting
		btnBackCancel.setFont(btnStartNext.getFont()); //getting font from start button
		btnBackCancel.setBounds(20, 415, 80, 35); //positioning on form
		
		btnBackCancel.addActionListener(new ActionListener() //add listener for action to run method
		{
			public void actionPerformed (ActionEvent evt)
			{
				if (!chkbxDesktopRun.isSelected())
					bDesktop = false;
				else
					bDesktop = true;
				
				if (!chkbxQuick.isSelected())
					bQuick = false;
				else
					bQuick = true;
				
				btnBackActionPerformed(); //running cancel method (same method as hitting the "x" button on the form)
			}
		});
		
		add(btnBackCancel); //adding button to JFrame
		
		repaint(); //repainting what is displayed if going coming from a different form
		revalidate(); //revalidate the elements that will be displayed
		pack(); //packaging everything up to use
		
		btnStartNext.requestFocusInWindow();
		
		setLocationRelativeTo(null); //setting form position central
	}
		
	private void btnStartActionPerformed () //code for third screen of installation (instigates installation)
	{	
		pnlDownload = new JPanel();
		lblArtTop = new JLabel();
		lblWelcome = new JLabel();
		lblMainTxt = new JLabel();
		lblProgress = new JLabel();
		lblSearching = new JLabel();
		lblDivider = new JLabel();
		btnStartNext = new JButton();
		btnBackCancel = new JButton();
		prbrInstall = new JProgressBar();
		
		getContentPane().removeAll();
		pnlDownload.setVisible(true);
		add(pnlDownload);

		removeWindowListener(exitListener);
		removeWindowListener(uninstallExitList);
		addWindowListener(uninstallExitList); //removing before adding the windowlistener, ensures there is only one listener there
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //setting "x" button to do nothing except what exitListener does
		setPreferredSize(new Dimension(750, 500)); //setting measurements of jframe
		
		try
		{
			Image frameIcon = ImageIO.read(new File(imgDir+"\\Logo.png"));
			setIconImage(frameIcon); //trying to read and add the logo to the application
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		setTitle("Password_Vault 1.0 Setup"); //setting title on JFrame
		setResizable(false); //disabling resizing
		setLayout(null); //ensuring I can specify element positions
		setBackground(Color.WHITE); //setting background color

		Icon topArt = new ImageIcon(imgDir+"\\topArt.jpg");
		lblArtTop.setIcon(topArt); //reading in Art to be displayed at top of JFrame
			
		lblArtTop.setBounds(0, 0, 750, 100); //setting position and measurements of art
		add(lblArtTop); //adding art to frame
		
		lblWelcome.setText("Password_Vault Setup Wizard"); //label welcoming user
		lblWelcome.setFont(lblWelcome.getFont().deriveFont(22.0f)); //changing font size to 22
		lblWelcome.setFont(lblWelcome.getFont().deriveFont(Font.BOLD)); //changing font style to bold
		lblWelcome.setBounds(208, 125, 334, 25); //setting position and measurements
		add(lblWelcome); //adding label to form
		
		lblMainTxt.setText("<html>The setup wizard is detecting your system settings and setting up files on your local hard drive used in the running of Password_Vault."
				+ "<br><br>"
				+ "Please do not close the application, installation in progress...</html>"); //changing text assigned to the label
		lblMainTxt.setFont(lblMainTxt.getFont().deriveFont(16.0f)); //ensuring same font as before
		lblMainTxt.setBounds(10, 179, 730, 77); //setting bounds and position
		add(lblMainTxt); //adding it to the form
		
    	prbrInstall.setValue(progressVal); //setting the progress bar to start at 0
		prbrInstall.setStringPainted(true); 
		prbrInstall.setBounds(75, 315, 600, 27); //positioning the bar
		prbrInstall.setMinimum(0); //setting the minimum value on the bar
		prbrInstall.setMaximum(100); //setting the maximum value on the bar
		add(prbrInstall); //adding the bar to the form
		
		lblProgress.setText(progressTxt); //setting progress label to starting
		
		lblProgress.setFont(lblMainTxt.getFont().deriveFont(12.0f)); //ensuring smaller font size than main text
		lblProgress.setBounds(75, 296, 300, 15); //setting position above the progress bar
		add(lblProgress); //adding the progress bar to the frame
		
		lblSearching.setText(""); //setting progress label to starting
		lblSearching.setFont(lblMainTxt.getFont().deriveFont(12.0f)); //ensuring smaller font size than main text
		lblSearching.setBounds(210, 296, 200, 15); //setting position above the progress bar
		add(lblSearching); //adding the progress bar to the frame
		
		lblDivider.setText(""); //ensuring no text in label
		lblDivider.setBounds(10, 385, 730, 10); //setting bounds and position of dividing line
		lblDivider.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); //setting border to label for the dividing
		add(lblDivider); //adding it to JFrame
		
		btnStartNext.setText("Next"); //adding text to button for starting
		btnStartNext.setFont(lblMainTxt.getFont().deriveFont(14.0f)); //setting font size
		btnStartNext.setBounds(645, 415, 80, 35); //positioning start button
		btnStartNext.setEnabled(false); //prevents user from continuing before installation is complete
		
		btnStartNext.addActionListener(new ActionListener() //add listener for action to run method
		{
			public void actionPerformed (ActionEvent evt) 
			{
				btnFinalActionPerformed(); //running next method
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
				btnBackInstallActionPerformed(); //running cancel method (same method as hitting the "x" button on the form)
			}
		});
		
		add(btnBackCancel); //adding button to JFrame
		
		repaint(); //repainting what is displayed if going coming from a different form
		revalidate(); //revalidate the elements that will be displayed
		pack(); //packaging everything up to use
		
		setLocationRelativeTo(null); //setting form position central

		if (!tskDone) //if the task hadn't finished
		{
			if (task == null)
			{
				task = new Task(); //instantiate a new task
				task.addPropertyChangeListener((PropertyChangeListener) this);
				task.execute(); //execute the task to start it again
			}
		}
		else //else if had finished
		{
			lblProgress.setText("Done!"); //ensure progress label was set to "Done!"
			
			if (tskCancelled) //if the user had backed off of the installation page
				if (JOptionPane.showConfirmDialog(null, "<html><center>Initial installation was cancelled<br>Would you like to start again?</center></html>", "Restart?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION);
				{ //offering user to start installation again, if so then
					task = new Task(); //declaring new task
					task.addPropertyChangeListener((PropertyChangeListener) this);
					task.execute(); //executing the new task to run in the background
				}
		}
		
		if (task.isPaused())
			task.resume();
	}
	
	private void btnFinalActionPerformed () //code for final screen of installation
	{
		pnlFinal = new JPanel();
		lblArtTop = new JLabel();
		lblWelcome = new JLabel();
		lblMainTxt = new JLabel();
		lblDivider = new JLabel();
		btnStartNext = new JButton();
		chkbxDesktopRun = new JCheckBox("Run Password_Vault");
		
		getContentPane().removeAll();
		pnlFinal.setVisible(true);
		add(pnlFinal);

		removeWindowListener(exitListener);
		removeWindowListener(uninstallExitList);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //setting "x" button to do nothing except what exitListener does
		setPreferredSize(new Dimension(750, 500)); //setting measurements of jframe
		
		try
		{
			Image frameIcon = ImageIO.read(new File(imgDir+"\\Logo.png"));
			setIconImage(frameIcon); //trying to read and add the logo to the application
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		setTitle("Password_Vault 1.0 Setup"); //setting title on JFrame
		setResizable(false); //disabling resizing
		setLayout(null); //ensuring I can specify element positions
		setBackground(Color.WHITE); //setting background color

		Icon topArt = new ImageIcon(imgDir+"\\topArt.jpg");
		lblArtTop.setIcon(topArt); //reading in Art to be displayed at top of JFrame
			
		lblArtTop.setBounds(0, 0, 750, 100); //setting position and measurements of art
		add(lblArtTop); //adding art to frame
		
		lblWelcome.setText("Password_Vault Setup Wizard"); //label welcoming user
		lblWelcome.setFont(lblWelcome.getFont().deriveFont(22.0f)); //changing font size to 22
		lblWelcome.setFont(lblWelcome.getFont().deriveFont(Font.BOLD)); //changing font style to bold
		lblWelcome.setBounds(208, 125, 334, 25); //setting position and measurements
		add(lblWelcome); //adding label to form
		
		lblMainTxt.setText("<html>Setup has finished installing Password_Vault on your computer please press \"Finish\"."
				+ "<br><br>"
				+ "Please select the options below as appropriate:</html>"); //changing text assigned to the label
		lblMainTxt.setFont(lblMainTxt.getFont().deriveFont(16.0f)); //ensuring same font as before
		lblMainTxt.setBounds(10, 179, 730, 77); //setting bounds and position
		add(lblMainTxt); //adding it to the form
		
		chkbxDesktopRun.setSelected(true);
		chkbxDesktopRun.setFont(lblMainTxt.getFont().deriveFont(16.0f));
		chkbxDesktopRun.setBounds(50, 275, 250, 19);
		add(chkbxDesktopRun);
		
		lblDivider.setText(""); //ensuring no text in label
		lblDivider.setBounds(10, 385, 730, 10); //setting bounds and position of dividing line
		lblDivider.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); //setting border to label for the dividing
		add(lblDivider); //adding it to JFrame
		
		btnStartNext.setText("Finish"); //adding text to button for starting
		btnStartNext.setFont(lblMainTxt.getFont().deriveFont(14.0f)); //setting font size
		btnStartNext.setBounds(645, 415, 80, 35); //positioning start button
		
		btnStartNext.addActionListener(new ActionListener() //add listener for action to run method
		{
			public void actionPerformed (ActionEvent evt) 
			{
				if (chkbxDesktopRun.isSelected())
					btnEndActionPerformed(true); //running next method
				else
					btnEndActionPerformed(false); //running next method
			}
		});
		
		add(btnStartNext); //adding button to JFrame
		
		repaint(); //repainting what is displayed if going coming from a different form
		revalidate(); //revalidate the elements that will be displayed
		pack(); //packaging everything up to use
		
		setLocationRelativeTo(null); //setting form position central
	}
	
	private void btnEndActionPerformed(boolean run)
	{
		if (run)
		{
			try
			{
				Process p = Runtime.getRuntime().exec("cmd /c start \"\" \""+exec.getAbsolutePath()+"\"");
				p.waitFor();
			}
			catch (Throwable t)
			{
				JOptionPane.showMessageDialog(null, "Failed to run Password_Vault! Please manually run the application.", "Error!", JOptionPane.OK_OPTION);
			}
		}
		
		System.exit(0);
	}
	
	private void btnBackActionPerformed ()
	{
		getContentPane().removeAll(); //removing everything from the frame
		initComponents();
	}
	
	@SuppressWarnings("static-access")
	private void btnBackInstallActionPerformed () //code for returning to the first screen from the installation section
	{
		backMsg = new JOptionPane(); //linking the option pane to a variable so that it can be referenced at a later point
		if (backMsg.showConfirmDialog(null, "<html><center>WARNING!<br>This will cancel installation<br>Are you sure you want to do this?</center></html>", "WARNING!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
		{ //output warning that it would cancel installation, if accepted...
			task.cancel(true); //cancel the task
			tskCancelled = true; //set the boolean variable to true incase the user returns to the installation screen after hitting back
			backMsg = null; //reassigning the variable to null for future reference
			getContentPane().removeAll(); //removing everything from the frame
			initComponents(); //calling the method that builds the first screen of the installation
		}
		else //if not accepted...
			backMsg = null; //reassigning the variable to null for future reference
	}
	
	private void btnCancelActionPerformed()
	{
		if (JOptionPane.showConfirmDialog(null, "<html><center>WARNING!<br>This will cancel installation<br>Are you sure you want to do this?</center></html>", "WARNING!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
		{ //output warning that it would cancel installation, if accepted...
			System.exit(0);
		}
		else //if not accepted...
		{
			if (lblMainTxt.getText().substring(0, 9).equals("<html>The"))
			{
				getContentPane().removeAll();
				initComponents();
			}
			else if (lblMainTxt.getText().substring(0, 9).equals("<html>Ple"))
			{
				if (!chkbxDesktopRun.isSelected())
					bDesktop = false;
				else
					bDesktop = true;
				
				if (!chkbxQuick.isSelected())
					bQuick = false;
				else
					bQuick = true;
				
				getContentPane().removeAll();
				btnNextActionPerformed();
			}
		}
	}
	
	@SuppressWarnings("static-access")
	private void btnCancelunInstallActionPerformed () //code for when the user presses the "Cancel" button or the "x" on the form
	{
		task.pause();
		
		mainDirectory = new File(Global_Vars.getWorkingDirectory()); //declaring a variable for the main directory of where the app is stored.
		exitMsg = new JOptionPane(); //linking the option pane to a variable so it can be referenced at a later point
		if (exitMsg.showConfirmDialog(null, "<html><center>Are you sure you wish to cancel installation?<br>Please note doing this will close all open cmd windows due to the removal of our assets!</center></html>", "Confirm?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
		{ //offering user choice to leave, if they select yes...

			if (mainDirectory.exists()) //check if some of the installation has started already, if so...
			{
				deleteDir(mainDirectory); //delete the entirety of the Password_Vault app folder
				
				if (bDesktop && dsktpShortcut != "")
				{
					File shortcut = new File(dsktpShortcut); //declare file for desktop location
				
					if (shortcut.exists()) //if desktop shortcut exists
						shortcut.delete(); //delete it
					else
					{
						if (JOptionPane.showConfirmDialog(null, "<html><center>App could not find Desktop based on home directory!<br>Do you want the app to search for the Desktop shortcut? (This can take a while if you have multiple hard drives)</center></html>", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) //if can't find desktop shortcut
						{
							findDir(userTemp+"\\Desktop", false); //find location of desktop (nothing outputted to user)
							shortcut = new File(foundDir+"\\Password_Vault.lnk"); //create variable based on found desktop icon
							if (shortcut.exists()) //if exists
								shortcut.delete(); //delete directory
							else //else
								JOptionPane.showMessageDialog(null, "<html><center>App could not find Desktop!<br>Desktop shortcut not deleted!</center></html>", "Warning", JOptionPane. OK_OPTION); //warn user of remaining desktop icon as could not find it.
						}
					}
				}
				
				if (quickShortcut != "")
				{
					File shortcut = new File(quickShortcut); //declare file for desktop location
					
					if (shortcut.exists()) //if desktop shortcut exists
						shortcut.delete(); //delete it
					else
					{
						if (JOptionPane.showConfirmDialog(null, "<html><center>App could not find Start Menu files!<br>Do you want the app to search for the Start Menu shortcut? (This can take a while if you have multiple hard drives)</center></html>", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) //if can't find desktop shortcut
						{
							findDir("\\Start Menu\\Programs\\Password_Vault.lnk", false); //find location of desktop (nothing outputted to user)
							shortcut = new File(foundDir + "\\Start Menu\\Programs\\Password_Vault.lnk"); //create variable based on found desktop icon
							if (shortcut.exists()) //if exists
								shortcut.delete(); //delete directory
							else //else
								JOptionPane.showMessageDialog(null, "<html><center>App could not find Desktop!<br>Desktop shortcut not deleted!</center></html>", "Warning", JOptionPane. OK_OPTION); //warn user of remaining desktop icon as could not find it.
						}
					}
				}
			}
					
			try
			{
				Runtime.getRuntime().exec("taskkill /f /im cmd.exe"); //try to close all open cmd windows
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			System.exit(0); //shutdown the system
		}
		else //if they don't want to cancel then...
		{
			exitMsg = null; //reset the variable name to null for use in the future
			progressVal = prbrInstall.getValue(); //saving value on progress bar
			progressTxt = lblProgress.getText();
			getContentPane().removeAll(); //removing assets on form
			btnStartActionPerformed(); //calling method to continue task
		}
	}

	public void propertyChange (PropertyChangeEvent evt) //code to detect update for progress bar
	{
		if ("progress" == evt.getPropertyName()) //detecting when a change from the background thread is pushed
		{
			int progress = (Integer) evt.getNewValue();
			
			prbrInstall.setValue(progress); //sets progress bar to new value based on what was retreaved from the background thread
		}
	}
	
	public void deleteDir (File file) //code for deleting an entire directory
	{
		File[] contents = file.listFiles(); //gets all file into array of files
		if (contents != null) //if there are files in the list then...
			for (File f : contents) //for every file in the list
				deleteDir(f); //pass it into the current method for another iteration
		
		file.delete(); //if the list is empty (no files in a directory) then delete the file
	}
		
	public static void main (String args[]) //method for setting the look of the UI and first part that is ran in the class
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //get look and feel based on OS
		}
		catch (ClassNotFoundException ex) //catch all errors that may occur
		{
			Logger.getLogger(Installation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (InstantiationException ex)
		{
			Logger.getLogger(Installation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (IllegalAccessException ex)
		{
			Logger.getLogger(Installation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (UnsupportedLookAndFeelException ex)
		{
			Logger.getLogger(Installation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		
		EventQueue.invokeLater(new Runnable()
		{
			public void run() //run the class's constructor, therefore starting the UI being built
			{
				new Installation().setVisible(true);
			}
		});
	}
		
	public void progressStop () //method that can be called from the thread that runs in background to stop itself
	{
		task.cancel(true); //cancels the background task
	}
		
	public boolean dirExists () //check if the mainDirectory exists again
	{
		if (backMsg != null || exitMsg != null) //uses variables linked to JOptionPanes to see if any are displaying, if one is showing...
			while (true) //constantly run
			{
				if (backMsg == null && exitMsg == null) //as soon as the JOptionPanes are not open
					break; //leave loop
				else //if still open
				{
					try
					{
						Thread.sleep(10); //thread waits before continuing loop
					}
					catch (Exception ex)
					{}
				}
			}
		
		if (JOptionPane.showConfirmDialog(null, "<html><center>App directory already exists!<br>Do you wish to carry on with installation? (All previous data will be removed)</center></html>", "Confirm?", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
		{ //Detected the directory already exists, asks if user wants to continue, if not...
			progressStop(); //call method to stop the task
			btnBackCancel.setEnabled(false); //disable the back button to prevent them starting the installation again
			return false; //returned false as not continuing
		}
		return true; //return true to continue
	}

	private void randWait () //method for pausing the thread for a random amount of time (max is 2 seconds)
	{			
		Random rnd = new Random(); //declaring random for use with task lengths

		try
		{
			Thread.sleep(rnd.nextInt(2000)); //randomly increasing the length of the task
		}
		catch (InterruptedException ignore)
		{}
	}
	
	public String findDir (String target, boolean output) //first method called to search entire drive (file passed in is what is being searched for)
	{
		foundDir = "";
		File[] tempPaths; //File array used while putting C to the top of the search list
		File[] paths = File.listRoots(); //File array that will contain the ordered disk drives before searching 

		// returns pathnames for files and directory
		tempPaths = File.listRoots();

		int i = 0;
		File c = new File("C:\\"); //used with assigning C drive to be first searched drive as most likely location of file
		for (File temp : tempPaths) //for each drive in the available drives
			if (temp.equals(c)) //if c drive is selected
				paths[0] = temp; //place c path into first element of File array
			else //if not
			{
				i =+ 1; //counter to ensure drives don't over write older drives
				paths[i] = temp; //adding drive to last element in File array
			}
				
		
		for(File path:paths) // for each pathname in pathname array
		{
			findDir(target, path, output); //call overload method to search specific drive for file
			if (!foundDir.equals(null)) //if null isn't returned
				return foundDir; //return the string that has the path
		}
		return null; //if nothing found return null
	}
	
	public void findDir (String target, File drive, boolean output) //overload method of findDir for second iterative step of finding
	{
		ArrayList<File> contents = new ArrayList<File>(); //arraylist for handling contents of current directory being searched
		
		File[] driveList = drive.listFiles(); //File array that gets full list of drive contents
		
		if (driveList != null) //listFiles will return null if item is a single file, if files are within the drive
			for (File item : driveList) //for every item in drive
				if (System.getProperty("os.name").toUpperCase().contains("WIN")) //check on windows
					if (item.getPath().substring(1).equals(":\\Users")) //most common place for file to be stored is "Users" directory, without including drive letter allows reordering across entire system
					{
						File temp = contents.get(0); //temporarily store current value of contents element 0
						contents.set(0, item); //put "users" directory to be searched first
						contents.add(temp); //add value that was originally first to end of list
					}
					else
						contents.add(item); //just add to list if doesn't match if
				else
					contents.add(item); //just add to list if doesn't match if
		
		if (contents.size() != 0) //if there are files in the list then...
			for (File f : contents) //for every file in the list
			{
				findDir(target, f, output); //pass it into the current method for another iteration
				if (foundDir != "") //if file found drop out of loop
					break;
			}
				
		if (output = true)
			if (foundDir == "")
				lblSearching.setText(drive.getPath());
		
		if (drive.getPath().length() > target.length()) //ensuring that drive is longer than target address so no errors when using substring
			if (drive.getPath().substring(drive.getPath().length() - target.length()).equals(target)) //if last part of path matches target file
			{
				foundDir = drive.getPath(); //add found directory to variable to be passed back
				lblSearching.setText("");
			}
	}
	
	public void getProperty (String cmd, int cmdNum) //code to collect unique identifier information
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
						userTemp = line;
					}
				if (cmdNum == 3) //if third command
				{
						serialNumbers.add(line); //will add user name as is first thing returned
						userTemp = line;
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
			lblProgress.setText("Error...");
		}
	}

	public String SHAHash (String input) throws NoSuchAlgorithmException //method for hashing information using SHA-256
	{
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256"); //message digest finding hash information
		messageDigest.update(input.getBytes(Charset.forName("UTF-8")), 0, input.length()); //updating message digest with necessary information for hash
		return new BigInteger(1, messageDigest.digest()).toString(16); //returning input as a hashed value
	}

	public void dqShortcut (boolean Desktop) //method for creating either desktop shortcut or shortcut for the start menu
	{		
		File dsktpShortcutFile = null;
		try
		{
			PrintWriter writer = new PrintWriter("createShortcut.bat", "UTF-8"); //declaring print writer, uses file location & char-set
			
			writer.println ("@echo off"); //print writer is outputting to the previously specified file
			writer.println("echo Set oWS = WScript.CreateObject(\"WScript.Shell\") > CreateShortcut.vbs"); //print writer is outputting to the previously specified file
			if (bDesktop)
				writer.println("echo sLinkFile = \"%HOMEDRIVE%%HOMEPATH%\\Desktop\\Password_Vault.lnk\" >> CreateShortcut.vbs"); //print writer is outputting to the previously specified file
			else
				writer.println("echo sLinkFile = \"%CSIDL_COMMON_STARTMENU%\\Password_Vault.lnk\" >> CreateShortcut.vbs"); //print writer is outputting to the previously specified file
			writer.println("echo Set oLink = oWS.CreateShortcut(sLinkFile) >> CreateShortcut.vbs"); //print writer is outputting to the previously specified file
			writer.println("echo oLink.TargetPath = \"" + exec.getAbsolutePath() + "\" >> CreateShortcut.vbs"); //print writer is outputting to the previously specified file
			writer.println("echo oLink.IconLocation = \""+ newAssetDir+"\\Logo.ico\"  >> CreateShortcut.vbs");
			writer.println("echo oLink.Save >> CreateShortcut.vbs"); //print writer is outputting to the previously specified file
			writer.println("cscript CreateShortcut.vbs"); //print writer is outputting to the previously specified file
			writer.println("del CreateShortcut.vbs"); //print writer is outputting to the previously specified file
			writer.println("exit");
			
			writer.close(); //close print writer to commit information to txt file.
			Thread.sleep(1000);
		}
		catch(Throwable t)
		{}
		
		try
		{
			Process p = Runtime.getRuntime().exec("cmd /c echo %HOMEDRIVE%%HOMEPATH%\\Desktop\\Password_Vault.lnk"); //running cmd command to retrieve disk serialnumbers
			p.waitFor();
			BufferedReader reader = new BufferedReader( //buffered reader to read output from cmd command
					new InputStreamReader(p.getInputStream())
			);
			
			String line;
			
			while ((line = reader.readLine()) != null) //ensuring that all results are covered
			{
				if (line.contains("Password_Vault.lnk")) //ensuring correct directory
				{
					dsktpShortcutFile = new File (line); //declaring variable of where desktop shortcut is
					dsktpShortcut = line;
				}
			}
			
			if (dsktpShortcutFile == null)
			{
				findDir("Desktop\\Password_Vault.lnk", false);
				dsktpShortcutFile = new File (foundDir);
			}
		}
		catch (IOException | InterruptedException e)
		{}
		
		try 
		{
			Process p = Runtime.getRuntime().exec("cmd /c "+runDir+"\\createShortcut.bat\""); //running created batch file
			p.waitFor();
		} 
		catch (IOException | InterruptedException e) 
		{
			e.printStackTrace();
		}

		File bat = new File ("createShortcut.bat"); //creating variable to remove trace of batch file that was created during setup
		
		bat.delete(); //deleting temp file
		
		//copies desktop shortcut to start menu - requires Admin permissions
		if (!Desktop)
		{
			quickShortcut = "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\Password_Vault.lnk";
			File quickShortcutFile = new File (quickShortcut);
			try 
			{
				@SuppressWarnings("unused")
				Path vaultBytes = Files.copy( //try copying the entire file from:
						dsktpShortcutFile.toPath(), //current position
						quickShortcutFile.toPath(), //to start menu position
						StandardCopyOption.REPLACE_EXISTING, //replace any duplicates in there
						StandardCopyOption.COPY_ATTRIBUTES, //ensure all attributes are the same
						LinkOption.NOFOLLOW_LINKS);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
				quickShortcut = "";
			}
			
			dsktpShortcutFile.delete(); //deleting desktop icon, if icon is desired, will be created in next instance
		}
	}
	
	//objects used in UI
	private JPanel pnlStart;
	private JPanel pnlShortcut;
	private JPanel pnlDownload;
	private JPanel pnlFinal;
	private JLabel lblArtTop;
	private JLabel lblWelcome;
	private JLabel lblMainTxt;
	private JLabel lblDivider;
	private JLabel lblProgress;
	private JLabel lblSearching;
	private JButton btnStartNext;
	private JButton btnBackCancel;
	private JCheckBox chkbxDesktopRun;
	private JCheckBox chkbxQuick;
	private JProgressBar prbrInstall;
	private static JOptionPane backMsg;
	private static JOptionPane exitMsg;
	
	//declaring task background task
	private static Task task;
	
	//variables used when declaring File
	private String runDir = "";
	private String imgDir = "";
	private String appDir = "";
	private String foundDir = "";
	private String userTemp = "";
	private File mainDirectory;
	private File vaultDir;
	private File keyDir;
	private File assetDir;
	private File newVaultDir;
	private File newKeyDir;
	private File newAssetDir;
	private File exec;
	
	//boolean variables for decision making
	private boolean winOS;
	private boolean tskDone = false;
	private boolean tskCancelled = false;
	private boolean bDesktop = true;
	private boolean bQuick = true;
	
	//variables for main installation
	private int progressVal = 0;
	private String dsktpShortcut;
	private String quickShortcut;
	private String progressTxt = "Starting...";
	private ArrayList<String> serialNumbers = new ArrayList<String>();

	
	class Task extends SwingWorker<Void, Void> //swing worker to handle the background task
	{	
		int h = 0;
		int b = 0;
		int u = 0;
		int m = 0;
		String userTemp = "";
		
	    private volatile boolean isPaused;
	    
	    public final void pause() {
	        if (!isPaused() && !isDone()) {
	            isPaused = true;
	            firePropertyChange("paused", false, true);
	        }
	    }

	    public final void resume() {
	        if (isPaused() && !isDone()) {
	            isPaused = false;
	            firePropertyChange("paused", true, false);
	        }
	    }

	    public final boolean isPaused() {
	        return isPaused;
	    }
		
		@Override
		public Void doInBackground () //overriding background thread which is method that handles the tasks to be completed
		{			
			while (!isCancelled()) //while task hasn't been cancelled
			{		
				if (lblProgress.getText().equals("Done!")) //if task being restarted
				{
					prbrInstall.setValue(0); //reset progress bar
					lblProgress.setText("Starting..."); //reset progress label
				}
									
				//making the user think the system is starting
				try
				{
					Thread.sleep(2000);
				}
				catch (Exception ex)
				{}
				
				//informing the user of new task
				lblProgress.setText("Detecting system settings...");
					
				//finding out system OS
				String OS = (System.getProperty("os.name")).toUpperCase(); //capitalises OS version
				
				if (OS.contains("WIN")) //if windows
					winOS = true;
				else //else if UNIX Based
					winOS = false;
					
				//method for pausing the thread for a random amount of time (max is 2 seconds)
				randWait();
					
				if (increment(prbrInstall.getValue(), 10) == true) //incrementing the progress bar to represent that task has been complete for the user
					return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
					
				//second section, ensuring there isn't a previous installation in place that had been previously missed
				lblProgress.setText("Checking for previous installations...");
				if (increment(prbrInstall.getValue(), 2) == true) //incrementing the progress bar to represent that task has been complete for the user
					return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
					
				mainDirectory = new File(Global_Vars.getWorkingDirectory()); //getting main directory for use in this thread
				exec = new File(mainDirectory+"\\Password_Vault.bat");
					
				if (increment(prbrInstall.getValue(), 2) == true) //incrementing the progress bar to represent that task has been complete for the user
					return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
					
				randWait();
					
				if (mainDirectory.exists()) //if main directory exists
				{
					if (dirExists()) //giving user option to overwrite previous installation
					{
						if (increment(prbrInstall.getValue(), 2) == true) //incrementing the progress bar to represent that task has been complete for the user
							return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
										
						lblProgress.setText("Removing old installation...");
							
						deleteDir(mainDirectory);
							
						if (increment(prbrInstall.getValue(), 4) == true) //incrementing the progress bar to represent that task has been complete for the user
							return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
							
						randWait();
					}
				}
				else
					if (increment(prbrInstall.getValue(), 6) == true) //incrementing the progress bar to represent that task has been complete for the user
						return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
					
				lblProgress.setText("Creating base directory..."); //informing the user of a task change
					
				//create directory
				mainDirectory.mkdir();
				
				if (increment(prbrInstall.getValue(), 5) == true) //incrementing the progress bar to represent that task has been complete for the user
					return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
					
				//defining where the files will be moved to in the Appdata directory
				newVaultDir = new File (mainDirectory+"\\apps\\Password_Vault.jar");
				newKeyDir = new File (mainDirectory+"\\apps\\Password_Key.jar");
				newAssetDir = new File (mainDirectory+"\\apps\\assets_pv1.0");
					
				lblProgress.setText("Finding dependancies..."); //informing the user of a task change
					
				if (runDir.contains("setup"))
				{
					if (increment(prbrInstall.getValue(), 5) == true) //incrementing the progress bar to represent that task has been complete for the user
						return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
						
					//if the user has ran the setup from the install file, add further directory stuff to get to jars
					vaultDir = new File (appDir+"\\Password_Vault.jar");
					keyDir = new File (appDir+"\\Password_Key.jar");
					assetDir = new File (imgDir);
					
					//validating the files exist before using them and possible causing an error
					if (!vaultDir.exists() || !keyDir.exists() || !assetDir.exists())
					{
						//if .jars don't exist then output message for user to reinstall 
						lblProgress.setText("Error...");
						JOptionPane.showMessageDialog(null, "<html><center>Files are missing from setup folder!<br>Please re-download the setup folder and re-run the setup application, following the instructions in the \"Readme\"</center></html>", "Warning", JOptionPane.WARNING_MESSAGE);
						System.exit(0);
					}
				}
				else //if not in setup directory, hopefully still have necessary files on their drive somewhere
				{
					String vaultDirString = findDir("Password_Vault.jar", true); //calls method to search all hard drives for needed file
					if (vaultDirString.equals(null)) //if nothing is returned
					{
						//informing user the first .jar can't be found, meaning failed installation,  re-download installation and follow steps in readme.txt
						lblProgress.setText("Error...");
						JOptionPane.showMessageDialog(null, "<html><center>Installation files cannot be found!<br>Please re-download the setup folder and re-run the setup application, following the instructions in the \"Readme\"</center></html>", "Warning", JOptionPane.WARNING_MESSAGE);
						System.exit(0);
					}
					else
					{
						//if not null then found file
						if (increment(prbrInstall.getValue(), 2) == true) //incrementing the progress bar to represent that task has been complete for the user
							return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
						
						vaultDir = new File(vaultDirString); //creating file object from Path that was found in the search
					}
						
					File tempKeyDir = new File(vaultDirString.substring(0, vaultDirString.length()-9)+"Key.jar"); //if one jar is at the path, seeing if the other is there as well
					keyDir = new File ("");
							
					if (!tempKeyDir.exists()) //if file isn't with other jar
					{
						String keyDirString = findDir("Password_Key.jar", true); //calls method to search all hard drives for needed file
						if (keyDirString.equals(null)) //if nothing is returned
						{
							//informing user the second .jar can't be found, meaning failed installation, re-download installation and follow steps in readme.txt 
							lblProgress.setText("Error...");
							JOptionPane.showMessageDialog(null, "<html><center>Installation files cannot be found!<br>Please re-download the setup folder and re-run the setup application, following the instructions in the \"Readme\"</center></html>", "Warning", JOptionPane.WARNING_MESSAGE);
							System.exit(0);
						}
						else //if file is found				
							keyDir = new File (keyDirString); //create file object from Path that was found in the search
					}
					else //if the file was with the other jar
						keyDir = tempKeyDir; //create file object from Path
						
					if (increment(prbrInstall.getValue(), 1) == true) //incrementing the progress bar to represent that task has been complete for the user
						return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
						
					File tempAssetDir = new File(vaultDir.getParentFile()+"\\assets_pv1.0");
						
					assetDir = new File ("");
						
					if (!tempAssetDir.exists()) //if file isn't with other jar
					{
						String assetDirString = findDir("\\assets_pv1.0", true); //calls method to search all hard drives for needed file
						if (assetDirString.equals(null)) //if nothing is returned
						{
							//informing user the second .jar can't be found, meaning failed installation, re-download installation and follow steps in readme.txt 
							lblProgress.setText("Error...");
							JOptionPane.showMessageDialog(null, "<html><center>Installation files cannot be found!<br>Please re-download the setup folder and re-run the setup application, following the instructions in the \"Readme\"</center></html>", "Warning", JOptionPane.WARNING_MESSAGE);
							System.exit(0);
						}
						else //if file is found				
							assetDir = new File (assetDirString); //create file object from Path that was found in the search
					}
					else //if the file was with the other jar
						assetDir = tempAssetDir; //create file object from Path
						
					if (increment(prbrInstall.getValue(), 2) == true) //incrementing the progress bar to represent that task has been complete for the user
						return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
				}
					
				lblProgress.setText("Moving dependancies..."); //informing the user of a task change
					
				boolean btemp1 = false; //variables ensuring move is complete
				boolean btemp2 = false;
				boolean btemp3 = false;
					
				newVaultDir.getParentFile().mkdir();
				newKeyDir.getParentFile().mkdir();
					
				long startTime = System.currentTimeMillis(); //defining a variable for current time to act as a timeout
				
				while (!btemp1 && !btemp2 && !btemp3 || (System.currentTimeMillis()-startTime) < 10000) //while the tasks aren't complete and it hasn't been 10 seconds
				{
					if (!btemp1) //ensuring the task isn't ran more than once
						try 
						{
							@SuppressWarnings("unused")
							Path vaultBytes = Files.copy( //try copying the entire file from:
									vaultDir.toPath(), //current position
									newVaultDir.toPath(), //to Appdata position
									StandardCopyOption.REPLACE_EXISTING, //replace any duplicates in there
									StandardCopyOption.COPY_ATTRIBUTES, //ensure all attributes are the same
									LinkOption.NOFOLLOW_LINKS);
							btemp1 = true; //when task complete set true so not reran
								
							if (increment(prbrInstall.getValue(), 3) == true) //incrementing the progress bar to represent that task has been complete for the user
								return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}
						
					if (!btemp2) //ensuring the task isn't ran more than once
						try 
						{
							@SuppressWarnings("unused")
							Path vaultBytes = Files.copy( //try copying the entire file from:
									keyDir.toPath(), //current position
									newKeyDir.toPath(), //to Appdata position
									StandardCopyOption.REPLACE_EXISTING, //replace any duplicates in there
									StandardCopyOption.COPY_ATTRIBUTES, //ensure all attributes are the same
									LinkOption.NOFOLLOW_LINKS);
							btemp2 = true; //when task complete set true so not reran
								
							if (increment(prbrInstall.getValue(), 3) == true) //incrementing the progress bar to represent that task has been complete for the user
								return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
						}
						catch (IOException e) 
						{
							e.printStackTrace();
						}
						
					if (!btemp3)
						try 
						{
							FileUtils.copyDirectory(assetDir, newAssetDir);
							btemp3 = true; //when task complete set true so not reran
						
							if (increment(prbrInstall.getValue(), 4) == true) //incrementing the progress bar to represent that task has been complete for the user
								return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}
				}
					
				if (!btemp1 || !btemp2 || !btemp3) //if task wasn't complete and timed out
				{
					//informing user of error and getting them to start again
					lblProgress.setText("Error...");
					JOptionPane.showMessageDialog(null, "<html><center>Files failed to move!<br>Please ensure the files are present and re-run the setup application, following the instructions in the \"Readme\"</center></html>", "Warning", JOptionPane.WARNING_MESSAGE);
					System.exit(0); 
				}
					
				lblProgress.setText("Retrieving configuration information..."); //informing the user of a task change
									
				getProperty("wmic diskdrive get serialnumber", 1); //calling method with command needed to be ran and number referencing this is the first command
					
				h = serialNumbers.size(); //variable to get current size of arraylist (used as boundaries later on)
					
				if (increment(prbrInstall.getValue(), 5) == true) //incrementing the progress bar to represent that task has been complete for the user
					return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
					
				getProperty("wmic bios get serialnumber", 2); //calling method with command needed to be ran and number referencing this is the second command
					
				b = serialNumbers.size(); //variable to get current size of arraylist (used as boundaries later on)
					
				if (increment(prbrInstall.getValue(), 5) == true) //incrementing the progress bar to represent that task has been complete for the user
					return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
					
				getProperty("echo %username%", 3); //calling method with command needed to be ran and number referencing this is the third command
					
				u = serialNumbers.size(); //variable to get current size of arraylist (used as boundaries later on)
					
				if (increment(prbrInstall.getValue(), 5) == true) //incrementing the progress bar to represent that task has been complete for the user
					return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
					
				getProperty("getmac", 4); //calling method with command needed to be ran and number referencing this is the fourth command
					
				m = serialNumbers.size(); //variable to get current size of arraylist (used as boundaries later on)
					
				if (increment(prbrInstall.getValue(), 5) == true) //incrementing the progress bar to represent that task has been complete for the user
					return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
				
				lblProgress.setText("Securing information..."); //informing the user of a task change
	
				for (int tmp = 0; tmp < 250; tmp ++) //ensuring 15 "mac" addresses are generated to obscure original values
				{
					Long rndLong = ThreadLocalRandom.current().nextLong(100100100100100L, 255255255255255L); //declaring range of mac address number
					String temp = Long.toString(rndLong); //putting into string variable so substring can be used
					
					serialNumbers.add(temp.substring(0,2)+"."+temp.substring(3,5)+"."+temp.substring(6,8)+"."+temp.substring(9,11)+"."+temp.substring(12,14)); //formatting and adding number to ArrayList to make it look like mac address
				}
					
				int ele = 0; //element number in relation to arraylist
				for (String prop : serialNumbers) //for every variable in the arraylist
				{
					try 
					{
						String sha = SHAHash(prop); //hash the variable
						if (ele < h) //if the current element is in the first boundary
							serialNumbers.set(ele, "%"+sha); //set corresponding element to "%" & hashed value -  "%" means disk drive number
						else if (ele < b) //if the current element is in the second boundary
							serialNumbers.set(ele, "^"+sha); //set corresponding element to "^" & hashed value -  "^" means bios number
						else if (ele < u) //if the current element is in the third boundary
							serialNumbers.set(ele, "&"+sha); //set corresponding element to "&" & hashed value -  "&" means username
						else if (ele < m) //if the current element is in the fourth boundary
							serialNumbers.set(ele, "*"+sha); //set corresponding element to "*" & hashed value -  "*" means mac address
						else if (ele < serialNumbers.size()) //if the current element is past the other boundaries (the auto-generated MAC addresses)
						{
							Random rnd = new Random(); //declaring a random for assigning a random symbol
								
							switch (rnd.nextInt(6)) //get next random number (max is 6)
							{
							case 0:
								serialNumbers.set(ele, "!"+sha); //set corresponding element to "!" & hashed value -  "!" means random generated address
								break;
							case 1:
								serialNumbers.set(ele, "\""+sha); //set corresponding element to "\" & hashed value -  "\" means random generated address
								break;
							case 2:
								serialNumbers.set(ele, "£"+sha); //set corresponding element to "£" & hashed value -  "£" means random generated address
								break;
							case 3:
								serialNumbers.set(ele, "$"+sha); //set corresponding element to "$" & hashed value -  "$" means random generated address
								break; 
							case 4:
								serialNumbers.set(ele, "@"+sha); //set corresponding element to "@" & hashed value -  "@" means random generated address
								break;
							default:
								serialNumbers.set(ele, "#"+sha); //set corresponding element to "#" & hashed value -  "#" means random generated address
							}
						}
					} 
					catch (NoSuchAlgorithmException e) 
					{
						e.printStackTrace();
					}
						
					ele += 1;
				}
					
				if (increment(prbrInstall.getValue(), 4) == true) //incrementing the progress bar to represent that task has been complete for the user
					return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
	
				File cfgFile = new File(mainDirectory+"\\sys\\config\\sys_cfg.txt"); //declaring location of config file
				while (!cfgFile.getParentFile().getParentFile().exists()) //while the "sys" directory doesn't exist
					cfgFile.getParentFile().getParentFile().mkdir(); //create directory
					
				while (!cfgFile.getParentFile().exists()) //while the "config" directory doesn't exist
					cfgFile.getParentFile().mkdir(); //create directory
					
				//shuffle the arraylist twice so that the information is in a different order (reason for keys that were assigned above)
				Collections.shuffle(serialNumbers);
				Collections.shuffle(serialNumbers);
					
				String largest = ""; //will hold longest variable - variable length used later
				String temp = ""; //name of variable that holds what is written into the text file
					
				try
				{
					PrintWriter writer = new PrintWriter(cfgFile, "UTF-8"); //declaring print writer, uses file location & char-set
					for (String i : serialNumbers) //for every variable in array list
						if (i.length() > largest.length()) //if current variable longer than length of largest...
							largest = i; //set current variable to "largest" - used to find longest string in array list...
					
					for (int j = 0; j < largest.length(); j++) //counts up to largest character length
					{
						for (String k : serialNumbers) //for every variable in array list
							if (j < k.length()) //if character is within string length
								temp = temp + k.charAt(j); //add single character to contents of temp
							else
								temp = temp + "-"; //if variable is shorter than others, add "-" in place to preserve hash
								
						writer.println(temp); //print writer is outputting to the previously specified file
						temp = ""; //resetting value of temp
					}		
					writer.close(); //close print writer to commit information to txt file.
				}
				catch(Throwable t)
				{}
					
				if (increment(prbrInstall.getValue(), 4) == true) //incrementing the progress bar to represent that task has been complete for the user
					return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
					
				if (winOS == true) //if on a windows system
					try
					{
						Process p = Runtime.getRuntime().exec("attrib +H +R" + cfgFile.getPath()); //use cmd to protect the config file with hidden and read only tags
						p.waitFor();
					}
					catch (Throwable t)
					{}
				else //if Unix based system
					cfgFile.renameTo(new File(cfgFile.getParent(), "." + cfgFile.getName())); //add "." in front of file to hide it
					
				if (increment(prbrInstall.getValue(), 2) == true) //incrementing the progress bar to represent that task has been complete for the user
					return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
					
				lblProgress.setText("Validating files..."); //informing the user of a task change
					
				//checking all created files exist, if not outputting error message
				if (!mainDirectory.exists() || !newVaultDir.exists() || !newKeyDir.exists())
					JOptionPane.showMessageDialog(null, "<html><center>Failed to find created files<br>Please ensure you have enough disk space available before re-running the setup application!</center></html>", "Warning", JOptionPane.WARNING_MESSAGE);
					
				randWait(); //increasing length of task by random time
					
				if (increment(prbrInstall.getValue(), 5) == true) //incrementing the progress bar to represent that task has been complete for the user
					return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
					
				if (winOS == true) //checking for OS to determine how file was hidden
				{
					if (!cfgFile.exists())
						JOptionPane.showMessageDialog(null, "<html><center>Failed to find created files<br>Please ensure you have enough disk space available before re-running the setup application!</center></html>", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else
					if (!(new File(cfgFile.getParent(), "."+cfgFile.getName())).exists())
						JOptionPane.showMessageDialog(null, "<html><center>Failed to find created files<br>Please ensure you have enough disk space available before re-running the setup application!</center></html>", "Warning", JOptionPane.WARNING_MESSAGE);
							
				randWait(); //increasing length of task by random time
					
				if (increment(prbrInstall.getValue(), 5) == true) //incrementing the progress bar to represent that task has been complete for the user
					return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
					
				if (bDesktop || bQuick)
					lblProgress.setText("Creating executable and shortcuts...");
				else
					lblProgress.setText("Creating executable...");
					
				if (bQuick)
					dqShortcut(false); //false for quick start shortcut
					
				if (bDesktop)
					dqShortcut(true); //true for desktop shortcut
					
				if (increment(prbrInstall.getValue(), 2) == true) //incrementing the progress bar to represent that task has been complete for the user
					return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
					
				try
				{
					PrintWriter writer = new PrintWriter(exec, "UTF-8"); //declaring print writer, uses file location & char-set
					writer.println("@echo off\nTITLE Password_Vault\njava -jar \""+newVaultDir+"\"\npause"); //print writer is outputting to the previously specified file
				
					writer.close(); //close print writer to commit information to txt file.
				}
				catch(Throwable t)
				{}
					
				randWait();
					
				if (increment(prbrInstall.getValue(), 3) == true) //incrementing the progress bar to represent that task has been complete for the user
					return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
					
				lblProgress.setText("Testing Password_Vault...");
					
				JOptionPane.showMessageDialog(null, "<html><center>The setup application is about to test the installed files.<br>Please do not be alarmed by opening windows and ensure you don't have any cmd windows open as they will be closed!</center></html>", "Warning", JOptionPane.WARNING_MESSAGE);				
					
				if (increment(prbrInstall.getValue(), 1) == true) //incrementing the progress bar to represent that task has been complete for the user
					return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
					
				randWait();
					
				try
				{
					Process p = Runtime.getRuntime().exec("cmd /c start \"\" \""+exec.getAbsolutePath()+"\"");
					p.waitFor();
				}
				catch (Throwable t)
				{}
					
				if (increment(prbrInstall.getValue(), 2) == true) //incrementing the progress bar to represent that task has been complete for the user
					return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
			
				startTime = System.currentTimeMillis(); //defining a variable for current time to act as a timeout
					
				while ((System.currentTimeMillis()-startTime) < 1000) //while the tasks aren't complete and it hasn't been 1 seconds
				{}
					
				if (increment(prbrInstall.getValue(), 1) == true) //incrementing the progress bar to represent that task has been complete for the user
					return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
					
				try
				{
					Runtime.getRuntime().exec("taskkill /f /im cmd.exe"); //try to close all open cmd windows
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
					
				if (increment(prbrInstall.getValue(), 1) == true) //incrementing the progress bar to represent that task has been complete for the user
					return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
					
				randWait();
									
				lblProgress.setText("Testing Password_Key...");
					
				if (increment(prbrInstall.getValue(), 1) == true) //incrementing the progress bar to represent that task has been complete for the user
					return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
					
				if (!keyTest())
					return null;
					
				if (increment(prbrInstall.getValue(), 1) == true) //incrementing the progress bar to represent that task has been complete for the user
					return null; //if true is returned, the task has been cancelled so return null to complete task and trigger the "done" method
					
				//CREATE PASS FILE TO MAKE A VALID RUN
					
				if (!keyTest())
					return null;
					
				//very last things to happen (prevents user going back and restarting install, ends current task)
				btnBackCancel.setEnabled(false);
				return null;	
			}
			return null;
		}
		
		@Override
		public void done () //method for what happens upon completion of the task running in background
		{
			Toolkit.getDefaultToolkit().beep(); //plays sound to alert user that it is complete
			lblProgress.setText("Done!"); //changes progress label to: Done!
			tskDone = true; //Completes the task so that it won't be restarted if the user returns to the page
			progressVal = prbrInstall.getValue(); //remembers progress value on progress bar in case it has stopped prematurely and the user returns to page, the bar will display same progress.
			btnStartNext.setEnabled(true); //enables Next button to continue with the installation
			progressStop(); //stops the current task
		}
		
		public boolean increment (int progress, int increment) //method for triggering progress property change and adding the new value to it
		{
			int preProgress = progress; //getting current value of progress bar
			progress += increment; //working out new value for progress bar
			
			while (preProgress != progress) //loop to increment the progress bar gradually
			{
				if (isCancelled()) //ensuring that at each step it ensures that task hasn't been cancelled, if so return true so the "doInBackground" task will then return null and finish
					return true;
				
				if (isPaused())
					pausedWait();
				
				preProgress += 1; //slowly incrementing the progress bar so it doesn't just do one massive jump
				setProgress(preProgress); //triggering the property change within the application
				
				try
				{
					Thread.sleep(250); //having a small gap between each increment so that it's not instantaneous 
				}
				catch (Exception ex)
				{}
			}
			return false; //return false when complete
		}
	
		public boolean keyTest ()
		{
			try
			{
				Process p = Runtime.getRuntime().exec("cmd /c start cmd.exe /k java -jar \"" + newKeyDir + "\"");
				p.waitFor();
			}
			catch (Throwable t)
			{}
			
			if (increment(prbrInstall.getValue(), 2) == true) //incrementing the progress bar to represent that task has been complete for the user
				return false; //if true is returned, the task has been cancelled so return false to complete task and trigger the "done" method
			
			long startTime = System.currentTimeMillis(); //defining a variable for current time to act as a timeout
			
			while ((System.currentTimeMillis()-startTime) < 1000) //while the tasks aren't complete and it hasn't been 1 seconds
			{}
			
			if (increment(prbrInstall.getValue(), 1) == true) //incrementing the progress bar to represent that task has been complete for the user
				return false; //if true is returned, the task has been cancelled so return false to complete task and trigger the "done" method
			
			try
			{
				Runtime.getRuntime().exec("taskkill /f /im cmd.exe"); //try to close all open cmd windows
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			if (increment(prbrInstall.getValue(), 1) == true) //incrementing the progress bar to represent that task has been complete for the user
				return false; //if true is returned, the task has been cancelled so return false to complete task and trigger the "done" method
			
			return true;
		}
	
		public void pausedWait()
		{
			while (isPaused())
			{
				try
				{
					Thread.sleep(200);
				}
				catch (Throwable t)
				{}
			}
		}
	}
}