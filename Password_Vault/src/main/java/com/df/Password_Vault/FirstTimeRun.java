package com.df.Password_Vault;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public class FirstTimeRun extends JFrame 
{
	private static final long serialVersionUID = 1L;

	private WindowListener exitListener = new WindowAdapter() 
	{
		@Override
		public void windowClosing(WindowEvent e) 
		{
			closingEvent(); // if window closing, go to exit menu
		}
	};

	public FirstTimeRun() {
		gv = new Global_Vars();

		gv.getWorkingDirectory();

		initComponents();
	}

	private void initComponents() // method to build initial view for user for installation
	{
		// instantiating elements of the GUI
		pnlStart = new JPanel();
		lblWelcome = new JLabel();
		lblMain = new JLabel();
		lblDivider = new JLabel();
		lblTextPrompt = new JLabel();
		txtAccNum = new JTextField();
		btnNext = new JButton();
		btnExit = new JButton();

		pnlStart.setVisible(true);
		add(pnlStart); // adding the panel to the frame

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
		setLayout(null); // ensuring I can specify element positions
		setBackground(Color.WHITE); // setting background color

		lblWelcome.setText("Welcome to Password_Vault"); // label welcoming user
		lblWelcome.setFont(lblWelcome.getFont().deriveFont(22.0f)); // changing font size to 22
		lblWelcome.setFont(lblWelcome.getFont().deriveFont(Font.BOLD)); // changing font style to bold
		lblWelcome.setBounds(141, 37, 317, 25); // setting position and measurements
		add(lblWelcome); // adding label to form

		lblMain.setText("<html>Please input a number below how many accounts you would like to<br>create: </html>"); // main label that explains what happens, html used for formatting
		lblMain.setFont(lblMain.getFont().deriveFont(18.0f)); // changing font size to 16
		lblMain.setBounds(27, 60, 540, 100); // setting position and measurements
		add(lblMain); // adding label to JFrame

		lblTextPrompt.setText("Amount of accounts (1-10):");
		lblTextPrompt.setFont(lblMain.getFont().deriveFont(16.0f));
		lblTextPrompt.setBounds(166, 190, 198, 18);
		lblTextPrompt.setLabelFor(txtAccNum);
		add(lblTextPrompt);

		txtAccNum.setFont(lblMain.getFont());
		txtAccNum.setBounds(374, 187, 50, 26);
		txtAccNum.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e) 
			{
				if (txtAccNum.getText().length() >= 4) // limit textfield to 3 characters
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
		add(txtAccNum);

		lblDivider.setText(""); // ensuring no text in label
		lblDivider.setBounds(10, 285, 573, 10); // setting bounds and position of dividing line
		lblDivider.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); // setting border to label for the dividing
		add(lblDivider); // adding it to JFrame

		btnNext.setText("Next"); // adding text to button for starting
		btnNext.setFont(lblMain.getFont().deriveFont(14.0f)); // setting font size
		btnNext.setBounds(495, 315, 80, 35); // positioning start button
		btnNext.addActionListener(new ActionListener() // add listener for action to run method
		{
			public void actionPerformed(ActionEvent evt) 
			{
				valSub();
			}
		});
		add(btnNext); // adding button to JFrame

		btnExit.setText("Exit"); // adding text to button for exiting
		btnExit.setFont(btnNext.getFont()); // getting font from start button
		btnExit.setBounds(20, 315, 80, 35); // positioning on form
		btnExit.addActionListener(new ActionListener() // add listener for action to run method
		{
			public void actionPerformed(ActionEvent evt) 
			{
				closingEvent(); // running cancel method (same method as hitting the "x" button on the form)
			}
		});
		add(btnExit); // adding button to JFrame

		repaint(); // repainting what is displayed if going coming from a different form
		revalidate(); // revalidate the elements that will be displayed
		pack(); // packaging everything up to use
		setLocationRelativeTo(null); // setting form position central
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
			getContentPane().removeAll();
			repaint();
			revalidate();
			pack();
			accDetails(Integer.parseInt(txtAccNum.getText())); // running start method
		}
	}

	private void accDetails(int accNum) 
	{
		// instantiating elements of the GUI
		pnlAccDetails = new JPanel();
		lblWelcome = new JLabel();
		lblMain = new JLabel();
		lblDivider = new JLabel();
		pnlTabs = new JTabbedPane();
		btnNext = new JButton();
		btnBack = new JButton();
		
		pnlAccDetails.setVisible(true);
		add(pnlAccDetails); // adding the panel to the frame

		getContentPane().removeAll();
		removeWindowListener(exitListener);
		addWindowListener(exitListener); // removing before adding the windowlistener, ensures there is only one listener there
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); // setting "x" button to do nothing except what exitListener does
		
		resizing();
	}
	
	private void accAssets()
	{
		lblWelcome.setText("Welcome to Password_Vault"); // label welcoming user
		lblWelcome.setFont(lblWelcome.getFont().deriveFont(22.0f)); // changing font size to 22
		lblWelcome.setFont(lblWelcome.getFont().deriveFont(Font.BOLD)); // changing font style to bold
		lblWelcome.setBounds(216, 27, 317, 25); // setting position and measurements
		add(lblWelcome); // adding label to form

		lblMain.setText("<html><center>Please fill in all areas of the following tabs:</center></html>"); // main label that explains what happens, html used for formatting
		lblMain.setFont(lblMain.getFont().deriveFont(18.0f)); // changing font size to 16
		lblMain.setBounds(27, 45, 540, 100); // setting position and measurements
		add(lblMain); // adding label to JFrame

		lblDivider.setText(""); // ensuring no text in label
		lblDivider.setBounds(10, 385, 720, 10); // setting bounds and position of dividing line
		lblDivider.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); // setting border to label for the dividing
		add(lblDivider); // adding it to JFrame

		btnNext.setText("Next"); // adding text to button for starting
		btnNext.setFont(lblMain.getFont().deriveFont(14.0f)); // setting font size
		btnNext.setBounds(635, 415, 80, 35); // positioning start button
		btnNext.addActionListener(new ActionListener() // add listener for action to run method
		{
			public void actionPerformed(ActionEvent evt) {
				valSub();
			}
		});
		add(btnNext); // adding button to JFrame

		btnBack.setText("Quit"); // adding text to button for exiting
		btnBack.setFont(btnNext.getFont()); // getting font from start button
		btnBack.setBounds(20, 415, 80, 35); // positioning on form
		btnBack.addActionListener(new ActionListener() // add listener for action to run method
		{
			public void actionPerformed(ActionEvent evt) {
				closingEvent(); // running cancel method (same method as hitting the "x" button on the form)
			}
		});
		add(btnBack); // adding button to JFrame

		repaint(); // repainting what is displayed if going coming from a different form
		revalidate(); // revalidate the elements that will be displayed
		pack(); // packaging everything up to use
		setLocationRelativeTo(null); // setting form position central
		txtAccNum.requestFocusInWindow(); // setting focus on start button when everything is loaded
	}

	private void resizing()
	{
		timer = new Timer (10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				getContentPane().removeAll();
				setPreferredSize(new Dimension(sizeW, sizeH));
				pnlAccDetails.setPreferredSize(new Dimension(sizeW, sizeH));
				repaint();
				revalidate();
				pack();
				
				sizeW += 3;
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
				
				if (sizeW == 750 && sizeH == 500)
				{
					timer.stop();
					accAssets();
				}
			}
		});
		
		timer.start();
	}
	
	private void closingEvent() 
	{
		if (JOptionPane.showConfirmDialog(null, "<html><center>Are you sure you want to quit?</center></html>", "Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) 
			System.exit(0); // output warning that it would cancel installation, if accepted...
		else // if not accepted...
		{

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
			public void run() // run the class's constructor, therefore starting
								// the UI being built
			{
				new FirstTimeRun().setVisible(true);
			}
		});
	}

	// objects used in UI
	private JPanel pnlStart;
	private JPanel pnlAccDetails;
	private JLabel lblWelcome;	
	private JLabel lblMain;
	private JLabel lblDivider;
	private JLabel lblTextPrompt;
	private JTextField txtAccNum;
	private JButton btnNext;
	private JButton btnExit;
	private JButton btnBack;
	private JTabbedPane pnlTabs;

	private Timer timer;
	private int sizeW = 600;
	private int sizeH = 400;
	private boolean toggle = false;
	
	// instantiating required classes
	Global_Vars gv = null;
}