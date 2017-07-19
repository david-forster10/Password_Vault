package com.df.Startup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import com.df.FirstRun.FirstTimeRun;
import com.df.Global.Global_Vars;

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
		
		initComponents();
	}
	
	public void initComponents() //method to build initial view for user for installation
	{
		// instantiating elements of the GUI
		pnlStart = new JPanel[7];
		lblWelcome = new JLabel();
		lblMain = new JLabel();
		lblDivider = new JLabel();
		btnNext = new JButton();
		btnExit = new JButton();

		for (int i = 0; i < 7; i++)
			pnlStart[i] = new JPanel();
				
		removeWindowListener(exitListener);
		addWindowListener(exitListener); // removing before adding the windowlistener, ensures there is only one listener there
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); // setting "x" button to do nothing except what exitListener does
		setPreferredSize(new Dimension(600, 400)); // setting measurements of jframe

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
		setBackground(Color.WHITE); // setting background color

		lblWelcome.setText("Welcome to Password_Vault"); // label welcoming user
		lblWelcome.setFont(lblWelcome.getFont().deriveFont(22.0f)); // changing font size to 22
		lblWelcome.setFont(lblWelcome.getFont().deriveFont(Font.BOLD)); // changing font style to bold
		lblWelcome.setBounds(141, 37, 317, 25); // setting position and measurements
		pnlStart[1].setLayout(new BoxLayout(pnlStart[1], BoxLayout.LINE_AXIS));
		pnlStart[1].add(lblWelcome); // adding label to form

		lblMain.setText("<html>Please login below: </html>"); // main label that explains what happens, html used for formatting
		lblMain.setFont(lblMain.getFont().deriveFont(18.0f)); // changing font size to 16
		pnlStart[2].setLayout(new BorderLayout());
		pnlStart[2].add(Box.createHorizontalStrut(20), BorderLayout.LINE_START);
		pnlStart[2].add(lblMain, BorderLayout.CENTER); //adding label to JFrame
		pnlStart[2].add(Box.createHorizontalStrut(20), BorderLayout.LINE_END);

		lblDivider.setText("________________________________________________________________________________________________"); //ensuring no text in label
		lblDivider.setForeground(Color.WHITE);
		lblDivider.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); // setting border to label for the dividing
		pnlStart[5].setLayout(new FlowLayout());
		pnlStart[5].add(lblDivider); // adding it to JFrame

		btnExit.setText("Exit"); // adding text to button for exiting
		btnExit.setFont(lblMain.getFont().deriveFont(14.0f)); //getting font from start button
		btnExit.setPreferredSize(new Dimension(80, 35)); //positioning on form		
		btnExit.addActionListener(new ActionListener() // add listener for action to run method
		{
			public void actionPerformed(ActionEvent evt) 
			{
				closingEvent(); // running cancel method (same method as hitting the "x" button on the form)
			}
		});
		pnlStart[6].setLayout(new FlowLayout());
		pnlStart[6].add(Box.createHorizontalStrut(2));
		pnlStart[6].add(btnExit); // adding button to JFrame
				
		btnNext.setText("Next"); // adding text to button for starting
		btnNext.setFont(btnExit.getFont()); //setting font size
		btnNext.setPreferredSize(new Dimension(80, 35)); //positioning start button
		btnNext.addActionListener(new ActionListener() // add listener for action to run method
		{
			public void actionPerformed(ActionEvent evt) 
			{
				//valSub();
			}
		});
		pnlStart[6].add(Box.createHorizontalStrut(385));
		pnlStart[6].add(btnNext); // adding button to JFrame
		pnlStart[6].add(Box.createHorizontalStrut(1));
		
		pnlStart[0].setLayout(new BoxLayout(pnlStart[0], BoxLayout.PAGE_AXIS));
		pnlStart[0].add(Box.createVerticalStrut(36));
		pnlStart[0].add(pnlStart[1]);
		pnlStart[0].add(pnlStart[2]);
		pnlStart[0].add(pnlStart[4]);
		pnlStart[0].add(Box.createVerticalStrut(55));
		pnlStart[0].add(pnlStart[5]);
		pnlStart[0].add(Box.createVerticalStrut(10));
		pnlStart[0].add(pnlStart[6]);
		pnlStart[0].add(Box.createVerticalStrut(15));
		add(pnlStart[0]); // adding the panel to the frame
				
		revalidate(); // revalidate the elements that will be displayed
		repaint(); // repainting what is displayed if going coming from a different form
		pack(); // packaging everything up to use
		setLocationRelativeTo(null); // setting form position central
		//txtAccNum.requestFocusInWindow(); // setting focus on start button when everything is loaded
	}
	
	private void closingEvent()
	{
		if (JOptionPane.showConfirmDialog(null, "<html><center>WARNING!<br>This will cancel installation<br>Are you sure you want to do this?</center></html>", "WARNING!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
		{ //output warning that it would cancel installation, if accepted...
			System.exit(0);
		}
	}	

	public static void main(String[] args)
	{
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // get look and feel based on OS
		} 
		catch (ClassNotFoundException ex) // catch all errors that may occur
		{
			Logger.getLogger(FirstTimeRun.class.getName()).log(Level.SEVERE, null, ex);
		} 
		catch (InstantiationException ex) 
		{
			Logger.getLogger(FirstTimeRun.class.getName()).log(Level.SEVERE, null, ex);
		} 
		catch (IllegalAccessException ex) 
		{
			Logger.getLogger(FirstTimeRun.class.getName()).log(Level.SEVERE, null, ex);
		} 
		catch (UnsupportedLookAndFeelException ex) 
		{
			Logger.getLogger(FirstTimeRun.class.getName()).log(Level.SEVERE, null, ex);
		}

		EventQueue.invokeLater(new Runnable() 
		{
			public void run() // run the class's constructor, therefore starting the UI being built
			{
				new Login().setVisible(true);
			}
		});
	}

	//objects used in UI
	private JPanel[] pnlStart;
	private JLabel lblWelcome;
	private JLabel lblMain;
	private JLabel lblDivider;
	private JButton btnNext;
	private JButton btnExit;
	
	//instantiating required classes
	Global_Vars gv = null;
}