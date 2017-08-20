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

public class Login extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	public Login()
	{}
	
	public Login(Global_Vars gb, ActionListener valLog)
	{
		gv = gb;
		
		initComponents(valLog);
	}
	
	public void initComponents(ActionListener valLog) //method to build initial view for user for installation
	{
		// instantiating elements of the GUI
		pnlLogin = new JPanel[9];
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
			pnlLogin[i] = new JPanel();

		lblWelcome.setText("Welcome to Password_Vault"); // label welcoming user
		lblWelcome.setFont(lblWelcome.getFont().deriveFont(18.0f)); // changing font size to 22
		lblWelcome.setFont(lblWelcome.getFont().deriveFont(Font.BOLD)); // changing font style to bold
		lblWelcome.setBounds(141, 37, 317, 25); // setting position and measurements
		pnlLogin[0].setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlLogin[0].add(lblWelcome); // adding label to form

		lblMain.setText("<html>Please enter a valid username and password below: </html>"); // main label that explains what happens, html used for formatting
		lblMain.setFont(lblMain.getFont().deriveFont(15.0f)); // changing font size to 16
		pnlLogin[1].add(lblMain); //adding label to JFrame

		lblUserPrompt.setFont(lblMain.getFont().deriveFont(12.0f));
		lblPassPrompt.setFont(lblUserPrompt.getFont());
		
		pnlLogin[2].setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlLogin[2].add(Box.createHorizontalStrut(2));
		pnlLogin[2].add(lblUserPrompt);
		pnlLogin[2].add(Box.createHorizontalStrut(3));
		pnlLogin[2].add(gv.txtUsername);
		pnlLogin[2].add(Box.createHorizontalStrut(0));
		
		gv.txtUsername.setPreferredSize(new Dimension(180, 23));
		gv.txtPassword.setPreferredSize(new Dimension(180, 23));
		
		pnlLogin[3].setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlLogin[3].add(Box.createHorizontalStrut(3));
		pnlLogin[3].add(lblPassPrompt);
		pnlLogin[3].add(Box.createHorizontalStrut(2));
		pnlLogin[3].add(gv.txtPassword);

		pnlLogin[4].setLayout(new BoxLayout(pnlLogin[4], BoxLayout.Y_AXIS));
		pnlLogin[4].add(pnlLogin[2]);
		pnlLogin[4].add(pnlLogin[3]);
		
		lblDivider.setText("___________________________________________________________"); //ensuring no text in label
		lblDivider.setForeground(Color.WHITE);
		lblDivider.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); // setting border to label for the dividing
		pnlLogin[5].setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlLogin[5].add(lblDivider); // adding it to JFrame
		
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
				
		pnlLogin[7].setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlLogin[7].add(btnExit); // adding button to JFrame
		pnlLogin[7].add(Box.createHorizontalStrut(170));
		pnlLogin[7].add(btnLogin); // adding button to JFrame
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(Box.createVerticalStrut(10));
		add(pnlLogin[0]);
		add(Box.createVerticalStrut(8));
		add(pnlLogin[1]);
		add(Box.createVerticalStrut(6));
		add(pnlLogin[4]);
		add(pnlLogin[5]);
		add(pnlLogin[7]);
		add(Box.createVerticalStrut(10));
	}

	//objects used in UI
	private JPanel[] pnlLogin;
	private JLabel lblWelcome;
	private JLabel lblMain;
	private JLabel lblUserPrompt;
	private JLabel lblPassPrompt;
	private JLabel lblDivider;
	private JButton btnLogin;
	private JButton btnExit;
	
	Global_Vars gv = null;
}