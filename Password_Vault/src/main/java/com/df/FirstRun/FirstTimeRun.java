package com.df.FirstRun;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.df.Global.Global_Frame;
import com.df.Global.Global_Vars;

public class FirstTimeRun extends JPanel 
{
	private static final long serialVersionUID = 1L;

	public FirstTimeRun()
	{
		initComponents();
	}

	private void initComponents() // method to build initial view for user for installation
	{
		// instantiating elements of the GUI
		pnlStart = new JPanel[6];
		lblWelcome = new JLabel();
		lblMain = new JLabel();
		lblDivider = new JLabel();
		lblTextPrompt = new JLabel();
		txtAccNum = new JTextField();
		btnNext = new JButton();
		btnExit = new JButton();

		for (int i = 0; i < 6; i++)
			pnlStart[i] = new JPanel();
		
		setBackground(Color.WHITE); // setting background color

		lblWelcome.setText("Welcome to Password_Vault"); // label welcoming user
		lblWelcome.setFont(lblWelcome.getFont().deriveFont(22.0f)); // changing font size to 22
		lblWelcome.setFont(lblWelcome.getFont().deriveFont(Font.BOLD)); // changing font style to bold
		lblWelcome.setBounds(141, 37, 317, 25); // setting position and measurements
		pnlStart[0].setBackground(Color.WHITE); // setting background color
		pnlStart[0].setLayout(new BoxLayout(pnlStart[0], BoxLayout.LINE_AXIS));
		pnlStart[0].add(lblWelcome); // adding label to form

		lblMain.setText("<html>Please input a number below how many accounts you would like to<br>create: </html>"); // main label that explains what happens, html used for formatting
		lblMain.setFont(lblMain.getFont().deriveFont(18.0f)); // changing font size to 16
		pnlStart[1].setBackground(Color.WHITE); // setting background color
		pnlStart[1].setLayout(new BorderLayout());
		pnlStart[1].add(Box.createHorizontalStrut(20), BorderLayout.LINE_START);
		pnlStart[1].add(lblMain, BorderLayout.CENTER); //adding label to JFrame
		pnlStart[1].add(Box.createHorizontalStrut(20), BorderLayout.LINE_END);

		lblTextPrompt.setText("Amount of accounts (1-10):");
		lblTextPrompt.setFont(lblMain.getFont().deriveFont(16.0f));
		lblTextPrompt.setBounds(166, 190, 198, 18);
		lblTextPrompt.setLabelFor(txtAccNum);
		pnlStart[2].setBackground(Color.WHITE); // setting background color
		pnlStart[2].setLayout(new FlowLayout());
		pnlStart[2].add(lblTextPrompt);

		txtAccNum.setFont(lblMain.getFont());
		txtAccNum.setPreferredSize(new Dimension(50, 26));
		txtAccNum.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e) 
			{
				if (txtAccNum.getText().length() >= 4 && e.getKeyChar() != '\b' && txtAccNum.getSelectedText() == null) // limit textfield to 4 characters, allows overwriting highlighted text & use of backspace
					e.consume();
			}
		});
		txtAccNum.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				valSub();
			}
		});
		pnlStart[3].setBackground(Color.WHITE); // setting background color
		pnlStart[2].setLayout(new FlowLayout());
		pnlStart[3].add(pnlStart[2]);
		pnlStart[3].add(txtAccNum);
		pnlStart[3].add(Box.createHorizontalStrut(20));
		
		lblDivider.setText("________________________________________________________________________________________________"); //ensuring no text in label
		lblDivider.setForeground(Color.WHITE);
		lblDivider.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); // setting border to label for the dividing
		pnlStart[4].setBackground(Color.WHITE); // setting background color
		pnlStart[4].setLayout(new FlowLayout());
		pnlStart[4].add(lblDivider); // adding it to JFrame

		btnExit.setText("Exit"); // adding text to button for exiting
		btnExit.setFont(lblMain.getFont().deriveFont(14.0f)); //getting font from start button
		btnExit.setPreferredSize(new Dimension(80, 35)); //positioning on form		
		btnExit.addActionListener(new ActionListener() // add listener for action to run method
		{
			public void actionPerformed(ActionEvent evt) 
			{
				gv.closingEvent(); // running cancel method (same method as hitting the "x" button on the form)
			}
		});
		pnlStart[5].setBackground(Color.WHITE); // setting background color
		pnlStart[5].setLayout(new FlowLayout());
		pnlStart[5].add(Box.createHorizontalStrut(2));
		pnlStart[5].add(btnExit); // adding button to JFrame
		
		btnNext.setText("Next"); // adding text to button for starting
		btnNext.setFont(btnExit.getFont()); //setting font size
		btnNext.setPreferredSize(new Dimension(80, 35)); //positioning start button
		btnNext.addActionListener(new ActionListener() // add listener for action to run method
		{
			public void actionPerformed(ActionEvent evt) 
			{
				valSub();
			}
		});
		pnlStart[5].setBackground(Color.WHITE); // setting background color
		pnlStart[5].add(Box.createHorizontalStrut(385));
		pnlStart[5].add(btnNext); // adding button to JFrame
		pnlStart[5].add(Box.createHorizontalStrut(1));
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(Box.createVerticalStrut(36));
		add(pnlStart[0]);
		add(pnlStart[1]);
		add(pnlStart[3]);
		add(Box.createVerticalStrut(55));
		add(pnlStart[4]);
		add(Box.createVerticalStrut(10));
		add(pnlStart[5]);
		add(Box.createVerticalStrut(15));

		txtAccNum.requestFocusInWindow(); // setting focus on start button when everything is loaded
	}

	private void valSub()
	{
		if (gv.validation(1, txtAccNum.getText()) != true || Integer.parseInt(txtAccNum.getText()) > 11) 
		{
			JOptionPane.showMessageDialog(null, "Invalid number, please try again");
			txtAccNum.setText("");
			txtAccNum.requestFocusInWindow();
		}
		else
		{
			Global_Frame.accNum = Integer.parseInt(txtAccNum.getText());
		}
	}

	// objects used in UI
	private JPanel[] pnlStart;
	private JLabel lblWelcome;	
	private JLabel lblMain;
	private JLabel lblDivider;
	private JLabel lblTextPrompt;
	private JTextField txtAccNum;
	private JButton btnNext;
	private JButton btnExit;
	
	// declaring required classes
	Global_Vars gv = null;
}