package com.df.Password_Vault;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
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
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

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

	public FirstTimeRun() 
	{
		gv = new Global_Vars(); //instantiating new class

		gv.getWorkingDirectory(); //running method to find main working directory

		initComponents(); //running first screen if user doesn't have an account
	}

	private void initComponents() // method to build initial view for user for installation
	{
		// instantiating elements of the GUI
		pnlStart = new JPanel[7];
		lblWelcome = new JLabel();
		lblMain = new JLabel();
		lblDivider = new JLabel();
		lblTextPrompt = new JLabel();
		txtAccNum = new JTextField();
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

		lblMain.setText("<html>Please input a number below how many accounts you would like to<br>create: </html>"); // main label that explains what happens, html used for formatting
		lblMain.setFont(lblMain.getFont().deriveFont(18.0f)); // changing font size to 16
		pnlStart[2].setLayout(new BorderLayout());
		pnlStart[2].add(Box.createHorizontalStrut(20), BorderLayout.LINE_START);
		pnlStart[2].add(lblMain, BorderLayout.CENTER); //adding label to JFrame
		pnlStart[2].add(Box.createHorizontalStrut(20), BorderLayout.LINE_END);

		lblTextPrompt.setText("Amount of accounts (1-10):");
		lblTextPrompt.setFont(lblMain.getFont().deriveFont(16.0f));
		lblTextPrompt.setBounds(166, 190, 198, 18);
		lblTextPrompt.setLabelFor(txtAccNum);
		pnlStart[3].setLayout(new FlowLayout());
		pnlStart[3].add(lblTextPrompt);

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
		pnlStart[3].setLayout(new FlowLayout());
		pnlStart[4].add(pnlStart[3]);
		pnlStart[4].add(txtAccNum);
		pnlStart[4].add(Box.createHorizontalStrut(20));
		
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
				valSub();
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
			revalidate();
			repaint();
			pack();
			accDetails(Integer.parseInt(txtAccNum.getText())); // running start method
		}
	}

	private void accDetails(int accNum) 
	{
		// instantiating elements of the GUI
		pnlAccDetails = new JPanel[6];
		pnlAccDetails[0] = new JPanel();
		
		add(pnlAccDetails[0]); // adding the panel to the frame

		getContentPane().removeAll();
		removeWindowListener(exitListener);
		addWindowListener(exitListener); // removing before adding the windowlistener, ensures there is only one listener there
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); // setting "x" button to do nothing except what exitListener does
		
		resizing(accNum);
	}
	
	private void accAssets(int accNum)
	{	
		lblWelcome = new JLabel();
		lblMain = new JLabel();
		lblDivider = new JLabel();
		btnNext = new JButton();
		btnExit = new JButton();
		pnlTabs = new JTabbedPane();
		txtFirstName = new JTextField[10];
		txtSurname = new JTextField[10];
		model = new UtilDateModel[10];
		datePanel = new JDatePanelImpl[10];
		datePicker = new JDatePickerImpl[10];
		pnlTabContain = new JPanel[10][11];
		lblColors = new JLabel[10][2][2];
		txtNumber = new JTextField[10];
		btnRanNum = new JButton[10];
		txtUsername = new JTextField[10];
		txtPasswords = new JPasswordField[10][2];
		lblPrompts = new JLabel[10][8];
		pnlAccDetails = new JPanel[6];
		
		for (int i = 0; i < 6; i++)
			pnlAccDetails[i] = new JPanel();
		
		lblWelcome.setText("Welcome to Password_Vault"); // label welcoming user
		lblWelcome.setFont(lblWelcome.getFont().deriveFont(22.0f)); // changing font size to 22
		lblWelcome.setFont(lblWelcome.getFont().deriveFont(Font.BOLD)); // changing font style to bold
		pnlAccDetails[1].setLayout(new BoxLayout(pnlAccDetails[1], BoxLayout.LINE_AXIS));
		pnlAccDetails[1].add(lblWelcome); // adding label to form

		lblMain.setText("<html>The following information that is collected will be used as part of the encryption process to ensure that each user has unique encryption paths. Please fill in all areas of the following tabs:</html>"); // main label that explains what happens, html used for formatting
		lblMain.setFont(lblMain.getFont().deriveFont(16.0f)); // changing font size to 16
		pnlAccDetails[2].setLayout(new BorderLayout());
		pnlAccDetails[2].add(Box.createHorizontalStrut(20), BorderLayout.LINE_START);
		pnlAccDetails[2].add(lblMain, BorderLayout.CENTER); //adding label to JFrame
		pnlAccDetails[2].add(Box.createHorizontalStrut(20), BorderLayout.LINE_END);
		
		for (int s = 0; s < 10; s++)
		{
			for (int r = 0; r < 10; r++)
				pnlTabContain[s][r] = new JPanel();
			
			txtFirstName[s] = new JTextField();
			txtFirstName[s].setPreferredSize(new Dimension(201, 23));
			
			txtSurname[s] = new JTextField();
			txtSurname[s].setPreferredSize(new Dimension(201, 23));
			
			model[s] = new UtilDateModel();
			model[s].setDate(2000, 00, 01);
			datePanel[s] = new JDatePanelImpl(model[s]);
			datePicker[s] = new JDatePickerImpl(datePanel[s]);
			
			lblColors[s][0][0] = new JLabel(); //Red
			lblColors[s][1][0] = new JLabel(); //Yellow
			lblColors[s][0][1] = new JLabel(); //Green
			lblColors[s][0][0].setPreferredSize(new Dimension(63, 23));
			lblColors[s][1][0].setPreferredSize(new Dimension(64, 23));
			lblColors[s][0][1].setPreferredSize(new Dimension(63, 23));
			lblColors[s][0][0].setOpaque(true);
			lblColors[s][1][0].setOpaque(true);
			lblColors[s][0][1].setOpaque(true);
			lblColors[s][0][0].setBackground(Color.RED);
			lblColors[s][1][0].setBackground(Color.YELLOW);
			lblColors[s][0][1].setBackground(Color.GREEN);
			lblColors[s][0][0].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblColors[s][1][0].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblColors[s][0][1].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblColors[s][0][0].addMouseListener(new MouseAdapter() 
			{
				public void mouseClicked (MouseEvent e)
				{
					int tabIndex = pnlTabs.getSelectedIndex();
					lblColors[tabIndex][0][0].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
					lblColors[tabIndex][1][0].setBorder(null);
					lblColors[tabIndex][0][1].setBorder(null);
				}
			});
			lblColors[s][1][0].addMouseListener(new MouseAdapter() 
			{
				public void mouseClicked (MouseEvent e)
				{
					int tabIndex = pnlTabs.getSelectedIndex();
					lblColors[tabIndex][0][0].setBorder(null);
					lblColors[tabIndex][1][0].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
					lblColors[tabIndex][0][1].setBorder(null);
				}
			});
			lblColors[s][0][1].addMouseListener(new MouseAdapter() 
			{
				public void mouseClicked (MouseEvent e)
				{
					int tabIndex = pnlTabs.getSelectedIndex();
					lblColors[tabIndex][0][0].setBorder(null);
					lblColors[tabIndex][1][0].setBorder(null);
					lblColors[tabIndex][0][1].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
				}
			});
			
			txtNumber[s] = new JTextField();
			txtNumber[s].setPreferredSize(new Dimension(154, 23));
			txtNumber[s].addKeyListener(new KeyAdapter() 
			{
				public void keyTyped(KeyEvent e) 
				{
					int tabIndex = pnlTabs.getSelectedIndex();
					switch (e.getKeyChar())
					{
					case '1':
					case '2':
					case '3':
					case '4':
					case '5':
					case '6':
					case '7':
					case '8':
					case '9':
					case '0':
						break;
					
					default:
						e.consume();
						break;
					}
					
					if (txtNumber[tabIndex].getText().length() >= 6 && e.getKeyChar() != '\b' && txtNumber[tabIndex].getSelectedText() == null) // limit textfield to 6 characters, allows overwriting of selected numbers and use of backspace
						e.consume();
				}
			});
			
			btnRanNum[s] = new JButton(new String(new int[] { 0x1F500 }, 0, 1));
			btnRanNum[s].setPreferredSize(new Dimension(43,23));
			btnRanNum[s].addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					int tabIndex = pnlTabs.getSelectedIndex();
					Random rnd = new Random();
					String random = Integer.toString(rnd.nextInt(1000000));
					if (random.length() < 6)
					{
						while (random.length() != 6)
						{
							random = "0"+random;
						}
					}
					txtNumber[tabIndex].setText(random);
				}
			});
			
			txtUsername[s] = new JTextField();
			txtUsername[s].setPreferredSize(new Dimension(201, 23));
			
			txtPasswords[s][0] = new JPasswordField();
			txtPasswords[s][1] = new JPasswordField();
			txtPasswords[s][0].setPreferredSize(new Dimension(201, 23));
			txtPasswords[s][1].setPreferredSize(new Dimension(201, 23));
			txtPasswords[s][0].getDocument().addDocumentListener(new DocumentListener() 
			{
				@Override
				public void changedUpdate(DocumentEvent arg0) 
				{
					compare();
				}

				@Override
				public void insertUpdate(DocumentEvent arg0) 
				{
					compare();
				}

				@Override
				public void removeUpdate(DocumentEvent arg0) 
				{
					compare();
				}
		
				public void compare()
				{
					int tabIndex = pnlTabs.getSelectedIndex();

					String p1 = String.valueOf(txtPasswords[tabIndex][0].getPassword());
					String p2 = String.valueOf(txtPasswords[tabIndex][1].getPassword());
					
					if (!p1.equals(p2))
						txtPasswords[tabIndex][1].setBorder(BorderFactory.createLineBorder(Color.RED));
					else
						txtPasswords[tabIndex][1].setBorder(BorderFactory.createLineBorder(Color.GREEN));
					
					p1 = null;
					p2 = null;
				}
			});
			txtPasswords[s][1].getDocument().addDocumentListener(new DocumentListener() 
			{
				@Override
				public void changedUpdate(DocumentEvent arg0) 
				{
					compare();
				}

				@Override
				public void insertUpdate(DocumentEvent arg0) 
				{
					compare();
				}

				@Override
				public void removeUpdate(DocumentEvent arg0) 
				{
					compare();
				}
		
				public void compare()
				{
					int tabIndex = pnlTabs.getSelectedIndex();

					String p1 = String.valueOf(txtPasswords[tabIndex][0].getPassword());
					String p2 = String.valueOf(txtPasswords[tabIndex][1].getPassword());
					
					if (!p1.equals(p2))
						txtPasswords[tabIndex][1].setBorder(BorderFactory.createLineBorder(Color.RED));
					else
						txtPasswords[tabIndex][1].setBorder(BorderFactory.createLineBorder(Color.GREEN));
					
					p1 = null;
					p2 = null;
				}
			});
			
			lblPrompts[s][0] = new JLabel("First Name: ");
			lblPrompts[s][1] = new JLabel("Surname: ");
			lblPrompts[s][2] = new JLabel("Date of Birth: ");
			lblPrompts[s][3] = new JLabel("Select one: ");
			lblPrompts[s][4] = new JLabel("Enter a 6 digit-number:");
			lblPrompts[s][5] = new JLabel("Enter a Username: ");
			lblPrompts[s][6] = new JLabel("Enter a Password: ");
			lblPrompts[s][7] = new JLabel("Confirm your Password: ");
			
			for (int v = 0; v < 8; v++)
				lblPrompts[s][v].setFont(lblPrompts[s][v].getFont().deriveFont(12.0f));
		}
		
		if (accNum == 10)
			pnlTabs.setFont(lblMain.getFont().deriveFont(11.4f));
		else
			pnlTabs.setFont(lblMain.getFont().deriveFont(12.0f));
		
		for (int t = 0; t < accNum; t++)
		{		
			pnlTabContain[t][1].setLayout(new FlowLayout());
			pnlTabContain[t][1].add(Box.createHorizontalStrut(7));
			pnlTabContain[t][1].add(lblPrompts[t][0]);
			pnlTabContain[t][1].add(Box.createHorizontalStrut(2));
			pnlTabContain[t][1].add(txtFirstName[t]);
			pnlTabContain[t][1].add(Box.createHorizontalStrut(60));
			
			pnlTabContain[t][3].setLayout(new FlowLayout());
			pnlTabContain[t][3].add(Box.createHorizontalStrut(45));
			pnlTabContain[t][3].add(lblPrompts[t][1]);
			pnlTabContain[t][3].add(Box.createHorizontalStrut(2));
			pnlTabContain[t][3].add(txtSurname[t]);
			pnlTabContain[t][3].add(Box.createHorizontalStrut(26));
			
			pnlTabContain[t][5].setLayout(new FlowLayout());
			pnlTabContain[t][5].add(lblPrompts[t][2]);
			pnlTabContain[t][5].add(Box.createHorizontalStrut(2));
			pnlTabContain[t][5].add(datePicker[t]);
			
			pnlTabContain[t][7].setLayout(new FlowLayout());
			pnlTabContain[t][7].add(Box.createHorizontalStrut(3));
			pnlTabContain[t][7].add(lblPrompts[t][3]);
			pnlTabContain[t][7].add(Box.createHorizontalStrut(2));
			pnlTabContain[t][7].add(lblColors[t][0][0]);
			pnlTabContain[t][7].add(lblColors[t][1][0]);
			pnlTabContain[t][7].add(lblColors[t][0][1]);
			
			pnlTabContain[t][2].add(Box.createHorizontalStrut(0));
			pnlTabContain[t][2].add(lblPrompts[t][4]);
			pnlTabContain[t][2].add(Box.createHorizontalStrut(4));
			pnlTabContain[t][2].add(txtNumber[t]);
			pnlTabContain[t][2].add(btnRanNum[t]);
			pnlTabContain[t][2].add(Box.createHorizontalStrut(2));

			pnlTabContain[t][4].add(Box.createHorizontalStrut(22));
			pnlTabContain[t][4].add(lblPrompts[t][5]);
			pnlTabContain[t][4].add(Box.createHorizontalStrut(2));
			pnlTabContain[t][4].add(txtUsername[t]);

			pnlTabContain[t][6].add(Box.createHorizontalStrut(24));
			pnlTabContain[t][6].add(lblPrompts[t][6]);
			pnlTabContain[t][6].add(Box.createHorizontalStrut(2));
			pnlTabContain[t][6].add(txtPasswords[t][0]);
			
			pnlTabContain[t][8].setLayout(new FlowLayout());
			pnlTabContain[t][8].add(lblPrompts[t][7]);
			pnlTabContain[t][8].add(Box.createHorizontalStrut(2));
			pnlTabContain[t][8].add(txtPasswords[t][1]);
			
			pnlTabContain[t][9].setLayout(new GridLayout(4, 2));
			for (int u = 1; u < 9; u++)	
				pnlTabContain[t][9].add(pnlTabContain[t][u]);
			
			pnlTabContain[t][0].setLayout(new BoxLayout(pnlTabContain[t][0], BoxLayout.Y_AXIS));
			pnlTabContain[t][0].add(Box.createVerticalStrut(23));
			pnlTabContain[t][0].add(pnlTabContain[t][9]);
			pnlTabs.addTab("Account #"+(t+1), pnlTabContain[t][0]);
		}
		pnlTabs.setPreferredSize(new Dimension(720, 280));

		pnlAccDetails[3].add(pnlTabs);
		
		lblDivider.setForeground(Color.WHITE);
		lblDivider.setText("________________________________________________________________________________________________________________________"); //ensuring no text in label
		lblDivider.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); //setting border to label for the dividing
		pnlAccDetails[4].setLayout(new FlowLayout());
		pnlAccDetails[4].add(lblDivider); //adding it to JFrame

		btnExit.setText("Quit"); // adding text to button for exiting
		btnExit.setFont(lblMain.getFont().deriveFont(14.0f)); //getting font from start button
		btnExit.setPreferredSize(new Dimension(80, 35)); //positioning on form		
		btnExit.addActionListener(new ActionListener() // add listener for action to run method
		{
			public void actionPerformed(ActionEvent evt) 
			{
				closingEvent(); // running cancel method (same method as hitting the "x" button on the form)
			}
		});
		pnlAccDetails[5].setLayout(new FlowLayout());
		pnlAccDetails[5].add(Box.createHorizontalStrut(1));
		pnlAccDetails[5].add(btnExit); //adding button to JFrame
		
		btnNext.setText("Next"); // adding text to button for starting
		btnNext.setFont(btnExit.getFont()); //setting font size
		btnNext.setPreferredSize(new Dimension(80, 35)); //positioning start button
		btnNext.addActionListener(new ActionListener() // add listener for action to run method
		{
			public void actionPerformed(ActionEvent evt) 
			{
				accHandler(accNum);
			}
		});
		pnlAccDetails[5].add(Box.createHorizontalStrut(520));
		pnlAccDetails[5].add(btnNext); //adding button to JFrame

		pnlAccDetails[0].setLayout(new BoxLayout(pnlAccDetails[0], BoxLayout.PAGE_AXIS));
		pnlAccDetails[0].add(Box.createVerticalStrut(15));
		pnlAccDetails[0].add(pnlAccDetails[1]);
		pnlAccDetails[0].add(pnlAccDetails[2]);
		pnlAccDetails[0].add(pnlAccDetails[3]);
		pnlAccDetails[0].add(pnlAccDetails[4]);
		pnlAccDetails[0].add(Box.createVerticalStrut(10));
		pnlAccDetails[0].add(pnlAccDetails[5]);
		pnlAccDetails[0].add(Box.createVerticalStrut(15));
		add(pnlAccDetails[0]); // adding the panel to the frame
		
		revalidate(); // revalidate the elements that will be displayed
		repaint(); // repainting what is displayed if going coming from a different form
		pack(); // packaging everything up to use
		setLocationRelativeTo(null); // setting form position central
		txtAccNum.requestFocusInWindow(); // setting focus on start button when everything is loaded
	}

	private void resizing(int accNum)
	{
		timer = new Timer (10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				getContentPane().removeAll();
				setPreferredSize(new Dimension(sizeW, sizeH));
				pnlAccDetails[0].setPreferredSize(new Dimension(sizeW, sizeH));
				revalidate();
				repaint();
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
					accAssets(accNum);
				}
			}
		});
		
		timer.start();
	}
	
	private void accHandler(int accNum)
	{
		accInfo = new String[7];
		if (!accInfoVal(accNum))
			JOptionPane.showMessageDialog(null, "The data you are submitting is incomplete or contains errors. Please see fields marked in red.", "Error", JOptionPane.OK_OPTION);
		else
		{
			for (int s = 0; s < accNum; s++)
			{
				accInfo[0] = txtFirstName[s].getText();
				accInfo[1] = txtSurname[s].getText();
				accInfo[2] = datePicker[s].formattedTextField.getText();
				
				if (lblColors[s][0][0].getBorder() != null)
					accInfo[3] = "Red";
				else if (lblColors[s][1][0].getBorder() != null)
					accInfo[3] = "Yellow";
				else if (lblColors[s][0][1].getBorder() != null)
					accInfo[3] = "Green";

				accInfo[4] = txtNumber[s].getText();
				accInfo[5] = txtUsername[s].getText();
				accInfo[6] = gv.Convert(String.valueOf(txtPasswords[s][0].getPassword()));
			}
			MainMenu.main(null);
		}
	}
	
	private boolean accInfoVal(int accNum)
	{
		boolean[][] failed = new boolean[10][9];
		for (int s = 0; s < accNum; s++)
		{
			if (!gv.validation(2, txtFirstName[s].getText()))
			{
				lblPrompts[s][0].setForeground(Color.RED);
				txtFirstName[s].setBorder(BorderFactory.createLineBorder(Color.RED));
				failed[s][1] = true;
			}
			else
			{
				lblPrompts[s][0].setForeground(new JLabel().getForeground());
				txtFirstName[s].setBorder(new JTextField().getBorder());
				failed[s][1] = false;
			}
			
			if (!gv.validation(2, txtSurname[s].getText()))
			{
				lblPrompts[s][1].setForeground(Color.RED);
				txtSurname[s].setBorder(BorderFactory.createLineBorder(Color.RED));
				failed[s][2] = true;
			}
			else
			{
				lblPrompts[s][1].setForeground(new JLabel().getForeground());
				txtSurname[s].setBorder(new JTextField().getBorder());
				failed[s][2] = false;
			}
			
			if (datePicker[s].formattedTextField.getText().equals(""))
			{
				lblPrompts[s][2].setForeground(Color.RED);
				datePicker[s].formattedTextField.setBorder(BorderFactory.createLineBorder(Color.RED));
				datePicker[s].button.setBorder(BorderFactory.createLineBorder(Color.RED));
				failed[s][3] = true;
			}
			else
			{
				lblPrompts[s][2].setForeground(new JLabel().getForeground());
				datePicker[s].formattedTextField.setBorder(new JTextField().getBorder());
				datePicker[s].button.setBorder(new JButton().getBorder());
				failed[s][3] = false;
			}
			
			if (lblColors[s][1][0].getBorder() == null && lblColors[s][0][1].getBorder() == null && lblColors[s][0][0].getBorder() == null)
			{
				lblPrompts[s][3].setForeground(Color.RED);
				failed[s][4] = true;
			}
			else
			{
				lblPrompts[s][3].setForeground(new JLabel().getForeground());
				failed[s][4] = false;
			}
			
			if (txtNumber[s].getText().equals(""))
			{
				lblPrompts[s][4].setForeground(Color.RED);
				txtNumber[s].setBorder(BorderFactory.createLineBorder(Color.RED));
				btnRanNum[s].setBorder(BorderFactory.createLineBorder(Color.RED));
				failed[s][5] = true;
			}
			else
			{
				lblPrompts[s][4].setForeground(new JLabel().getForeground());
				txtNumber[s].setBorder(new JTextField().getBorder());
				btnRanNum[s].setBorder(new JButton().getBorder());
				failed[s][5] = false;
			}
			
			if (txtUsername[s].getText().equals(""))
			{
				lblPrompts[s][5].setForeground(Color.RED);
				txtUsername[s].setBorder(BorderFactory.createLineBorder(Color.RED));
				failed[s][6] = true;
			}
			else
			{
				lblPrompts[s][5].setForeground(new JLabel().getForeground());
				txtUsername[s].setBorder(new JTextField().getBorder());
				failed[s][6] = false;
			}
			
			if (txtPasswords[s][0].getPassword().length == 0)
			{
				lblPrompts[s][6].setForeground(Color.RED);
				txtPasswords[s][0].setBorder(BorderFactory.createLineBorder(Color.RED));
				failed[s][7] = true;
			}
			else
			{
				lblPrompts[s][6].setForeground(new JLabel().getForeground());
				txtPasswords[s][0].setBorder(new JPasswordField().getBorder());
				failed[s][7] = false;
			}
			
			if (txtPasswords[s][1].getPassword().length == 0 || txtPasswords[s][1].getBorder() == BorderFactory.createLineBorder(Color.RED))
			{
				lblPrompts[s][7].setForeground(Color.RED);
				txtPasswords[s][1].setBorder(BorderFactory.createLineBorder(Color.RED));
				failed[s][8] = true;
			}
			else
			{
				lblPrompts[s][7].setForeground(new JLabel().getForeground());
				
				failed[s][8] = false;
			}			
			
			if (failed[s][1] || failed[s][2] || failed[s][3] || failed[s][4] || failed[s][5] || failed[s][6] || failed[s][7] || failed[s][8])
				failed[s][0] = true;
			else
				failed[s][0] = false;
			
			if (failed[s][0])
				pnlTabs.setForeground(Color.RED);
			else
				pnlTabs.setForeground(Color.BLACK);
		}
		boolean finalFail = false;
		for (int temp = 0; temp > 10; temp++)
			if (failed[temp][0])
				finalFail = true;
		
		if (finalFail)
			return false;
		else
			return true;
	}
	
	private void closingEvent() 
	{
		if (JOptionPane.showConfirmDialog(null, "<html><center>Are you sure you want to quit?</center></html>", "Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) 
			System.exit(0); // output warning that it would quit the application, if accepted...
		// if not accepted do nothing and close
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
				new FirstTimeRun().setVisible(true);
			}
		});
	}

	// objects used in UI
	private JPanel[] pnlStart;
	private JPanel[] pnlAccDetails;
	private JPanel[][] pnlTabContain;
	private JLabel lblWelcome;	
	private JLabel lblMain;
	private JLabel lblDivider;
	private JLabel lblTextPrompt;
	private JTextField txtAccNum;
	private JTextField[] txtFirstName;
	private JTextField[] txtSurname;
	private UtilDateModel[] model;
	private JDatePanelImpl[] datePanel;
	private JDatePickerImpl[] datePicker;
	private JLabel[][][] lblColors;
	private JTextField[] txtNumber;
	private JButton[] btnRanNum;
	private JTextField[] txtUsername;
	private JPasswordField[][] txtPasswords;
	private JLabel[][] lblPrompts;
	private JButton btnNext;
	private JButton btnExit;
//	private JButton btnBack;
	private JTabbedPane pnlTabs;

	//variables used for window resizing
	private Timer timer;
	private int sizeW = 600;
	private int sizeH = 400;
	private boolean toggle = false;
	
	private String[] accInfo;  
	
	// declaring required classes
	Global_Vars gv = null;
}