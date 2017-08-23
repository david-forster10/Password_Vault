package com.df.Startup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.df.Global.Global_Vars;

public class MainMenu extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	public MainMenu()
	{}
	
	public MainMenu(Global_Vars gb, ActionListener valLog)
	{
		gv = gb;
		
		initComponents(valLog);
	}
	
	public void initComponents(ActionListener valLog) //method to build initial view for user for installation
	{
		// instantiating elements of the GUI
		pnlMainMenu = new JPanel[9];
		lblWelcome = new JLabel();
		lblMain = new JLabel();
		lblUserPrompt = new JLabel("Username: ");
		lblPassPrompt = new JLabel("Password: ");
		gv.txtUsername = new JTextField();
		gv.txtPassword = new JPasswordField();
		lblDivider = new JLabel();
		btnLogin = new JButton();
		btnExit = new JButton();

		for (int i = 0; i < 9; i++)
			pnlMainMenu[i] = new JPanel();

		lblWelcome.setText("Welcome to Password_Vault"); // label welcoming user
		lblWelcome.setFont(lblWelcome.getFont().deriveFont(18.0f)); // changing font size to 22
		lblWelcome.setFont(lblWelcome.getFont().deriveFont(Font.BOLD)); // changing font style to bold
		lblWelcome.setBounds(141, 37, 317, 25); // setting position and measurements
		pnlMainMenu[0].setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlMainMenu[0].add(lblWelcome); // adding label to form

		lblMain.setText("<html>Please enter a valid username and password below: </html>"); // main label that explains what happens, html used for formatting
		lblMain.setFont(lblMain.getFont().deriveFont(15.0f)); // changing font size to 16
		pnlMainMenu[1].add(lblMain); //adding label to JFrame

		lblUserPrompt.setFont(lblMain.getFont().deriveFont(12.0f));
		lblPassPrompt.setFont(lblUserPrompt.getFont());
		
		pnlMainMenu[2].setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlMainMenu[2].add(Box.createHorizontalStrut(2));
		pnlMainMenu[2].add(lblUserPrompt);
		pnlMainMenu[2].add(Box.createHorizontalStrut(3));
		pnlMainMenu[2].add(gv.txtUsername);
		pnlMainMenu[2].add(Box.createHorizontalStrut(0));
		
		gv.txtUsername.setPreferredSize(new Dimension(180, 23));
		gv.txtPassword.setPreferredSize(new Dimension(180, 23));
		
		pnlMainMenu[3].setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlMainMenu[3].add(Box.createHorizontalStrut(3));
		pnlMainMenu[3].add(lblPassPrompt);
		pnlMainMenu[3].add(Box.createHorizontalStrut(2));
		pnlMainMenu[3].add(gv.txtPassword);

		pnlMainMenu[4].setLayout(new BoxLayout(pnlMainMenu[4], BoxLayout.Y_AXIS));
		pnlMainMenu[4].add(pnlMainMenu[2]);
		pnlMainMenu[4].add(pnlMainMenu[3]);
		
		lblDivider.setText("___________________________________________________________"); //ensuring no text in label
		lblDivider.setForeground(Color.WHITE);
		lblDivider.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); // setting border to label for the dividing
		pnlMainMenu[5].setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlMainMenu[5].add(lblDivider); // adding it to JFrame
		
		btnLogin.setText("Login"); // adding text to button for starting
		btnLogin.setFont(lblUserPrompt.getFont().deriveFont(14f)); //setting font size
		btnLogin.setPreferredSize(new Dimension(75, 30)); //positioning exit button
		btnLogin.addActionListener(valLog);		
		btnExit.setText("Exit"); // adding text to button for exiting
		btnExit.setFont(btnLogin.getFont()); //getting font from start button
		btnExit.setPreferredSize(new Dimension(75, 30)); //positioning exit button
		btnExit.addActionListener(new ActionListener() // add listener for action to run method
		{
			public void actionPerformed(ActionEvent evt) 
			{
				gv.closingEvent(); // running cancel method (same method as hitting the "x" button on the form)
			}
		});
				
		pnlMainMenu[7].setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlMainMenu[7].add(btnExit); // adding button to JFrame
		pnlMainMenu[7].add(Box.createHorizontalStrut(170));
		pnlMainMenu[7].add(btnLogin); // adding button to JFrame
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(Box.createVerticalStrut(10));
		add(pnlMainMenu[0]);
		add(Box.createVerticalStrut(8));
		add(pnlMainMenu[1]);
		add(Box.createVerticalStrut(6));
		add(pnlMainMenu[4]);
		add(pnlMainMenu[5]);
		add(pnlMainMenu[7]);
		add(Box.createVerticalStrut(10));
	}

	//objects used in UI
	private JPanel[] pnlMainMenu;
	private JLabel lblWelcome;
	private JLabel lblMain;
	private JLabel lblUserPrompt;
	private JLabel lblPassPrompt;
	private JLabel lblDivider;
	private JButton btnLogin;
	private JButton btnExit;
	
	Global_Vars gv = null;
}