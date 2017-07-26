package com.df.FirstRun;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.df.Global.Global_Vars;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class AccDetails extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	public AccDetails()
	{}
	
	public AccDetails(int accNum, Global_Vars gb, ActionListener log)
	{
		gv = gb;
		accAssets(accNum, log);
	}
	
	private void accAssets(int accNum, ActionListener log)
	{	
		// instantiating elements of the GUI
		pnlAccDetails = new JPanel[5];
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
		txtRegUsernames = new JTextField[10];
		txtRegPasswords = new JPasswordField[10][2];
		lblPrompts = new JLabel[10][8];
		pnlAccDetails = new JPanel[6];
		
		for (int i = 0; i < 5; i++)
			pnlAccDetails[i] = new JPanel();
		
		lblWelcome.setText("Welcome to Password_Vault"); // label welcoming user
		lblWelcome.setFont(lblWelcome.getFont().deriveFont(22.0f)); // changing font size to 22
		lblWelcome.setFont(lblWelcome.getFont().deriveFont(Font.BOLD)); // changing font style to bold
		pnlAccDetails[0].setLayout(new BoxLayout(pnlAccDetails[0], BoxLayout.LINE_AXIS));
		pnlAccDetails[0].add(lblWelcome); // adding label to form

		lblMain.setText("<html>The following information that is collected will be used as part of the encryption process to ensure that each user has unique encryption paths. Please fill in all areas of the following tabs:</html>"); // main label that explains what happens, html used for formatting
		lblMain.setFont(lblMain.getFont().deriveFont(16.0f)); // changing font size to 16
		pnlAccDetails[1].setLayout(new BorderLayout());
		pnlAccDetails[1].add(Box.createHorizontalStrut(20), BorderLayout.LINE_START);
		pnlAccDetails[1].add(lblMain, BorderLayout.CENTER); //adding label to JFrame
		pnlAccDetails[1].add(Box.createHorizontalStrut(20), BorderLayout.LINE_END);
		
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
							random = "0" + random;
						}
					}
					txtNumber[tabIndex].setText(random);
				}
			});
			
			txtRegUsernames[s] = new JTextField();
			txtRegUsernames[s].setPreferredSize(new Dimension(201, 23));
			
			txtRegPasswords[s][0] = new JPasswordField();
			txtRegPasswords[s][1] = new JPasswordField();
			txtRegPasswords[s][0].setPreferredSize(new Dimension(201, 23));
			txtRegPasswords[s][1].setPreferredSize(new Dimension(201, 23));
			txtRegPasswords[s][0].getDocument().addDocumentListener(new DocumentListener() 
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

					String p1 = String.valueOf(txtRegPasswords[tabIndex][0].getPassword());
					String p2 = String.valueOf(txtRegPasswords[tabIndex][1].getPassword());
					
					if (!p1.equals(p2))
						txtRegPasswords[tabIndex][1].setBorder(BorderFactory.createLineBorder(Color.RED));
					else
						txtRegPasswords[tabIndex][1].setBorder(BorderFactory.createLineBorder(Color.GREEN));
					
					p1 = null;
					p2 = null;
				}
			});
			txtRegPasswords[s][1].getDocument().addDocumentListener(new DocumentListener() 
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

					String p1 = String.valueOf(txtRegPasswords[tabIndex][0].getPassword());
					String p2 = String.valueOf(txtRegPasswords[tabIndex][1].getPassword());
					
					if (!p1.equals(p2))
						txtRegPasswords[tabIndex][1].setBorder(BorderFactory.createLineBorder(Color.RED));
					else
						txtRegPasswords[tabIndex][1].setBorder(BorderFactory.createLineBorder(Color.GREEN));
					
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
			pnlTabContain[t][4].add(txtRegUsernames[t]);

			pnlTabContain[t][6].add(Box.createHorizontalStrut(24));
			pnlTabContain[t][6].add(lblPrompts[t][6]);
			pnlTabContain[t][6].add(Box.createHorizontalStrut(2));
			pnlTabContain[t][6].add(txtRegPasswords[t][0]);
			
			pnlTabContain[t][8].setLayout(new FlowLayout());
			pnlTabContain[t][8].add(lblPrompts[t][7]);
			pnlTabContain[t][8].add(Box.createHorizontalStrut(2));
			pnlTabContain[t][8].add(txtRegPasswords[t][1]);
			
			pnlTabContain[t][9].setLayout(new GridLayout(4, 2));
			for (int u = 1; u < 9; u++)	
				pnlTabContain[t][9].add(pnlTabContain[t][u]);
			
			pnlTabContain[t][0].setLayout(new BoxLayout(pnlTabContain[t][0], BoxLayout.Y_AXIS));
			pnlTabContain[t][0].add(Box.createVerticalStrut(23));
			pnlTabContain[t][0].add(pnlTabContain[t][9]);
			pnlTabs.addTab("Account #"+(t+1), pnlTabContain[t][0]);
		}
		pnlTabs.setPreferredSize(new Dimension(720, 280));

		pnlAccDetails[2].add(pnlTabs);
		
		lblDivider.setForeground(Color.WHITE);
		lblDivider.setText("________________________________________________________________________________________________________________________"); //ensuring no text in label
		lblDivider.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); //setting border to label for the dividing
		pnlAccDetails[3].setLayout(new FlowLayout());
		pnlAccDetails[3].add(lblDivider); //adding it to JFrame

		btnExit.setText("Quit"); // adding text to button for exiting
		btnExit.setFont(lblMain.getFont().deriveFont(14.0f)); //getting font from start button
		btnExit.setPreferredSize(new Dimension(80, 35)); //positioning on form		
		btnExit.addActionListener(new ActionListener() // add listener for action to run method
		{
			public void actionPerformed(ActionEvent evt) 
			{
				gv.closingEvent(); // running cancel method (same method as hitting the "x" button on the form)
			}
		});
		pnlAccDetails[4].setLayout(new FlowLayout());
		pnlAccDetails[4].add(Box.createHorizontalStrut(1));
		pnlAccDetails[4].add(btnExit); //adding button to JFrame
		
		btnNext.setText("Next"); // adding text to button for starting
		btnNext.setFont(btnExit.getFont()); //setting font size
		btnNext.setPreferredSize(new Dimension(80, 35)); //positioning start button
		btnNext.addActionListener(log); // add listener for action to run method
		pnlAccDetails[4].add(Box.createHorizontalStrut(520));
		pnlAccDetails[4].add(btnNext); //adding button to JFrame

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(Box.createVerticalStrut(15));
		add(pnlAccDetails[0]);
		add(pnlAccDetails[1]);
		add(pnlAccDetails[2]);
		add(pnlAccDetails[3]);
		add(Box.createVerticalStrut(10));
		add(pnlAccDetails[4]);
		add(Box.createVerticalStrut(15));
		
		txtFirstName[0].requestFocusInWindow(); // setting focus on start button when everything is loaded
	}

	public void accHandler(int accNum)
	{
		gv.accInfo = new String[7];
		if (!accInfoVal(accNum))
			JOptionPane.showMessageDialog(null, "The data you are submitting is incomplete or contains errors. Please see fields marked in red.", "Error", JOptionPane.OK_OPTION);
		else
		{
			for (int s = 0; s < accNum; s++)
			{
				gv.accInfo[0] = txtFirstName[s].getText();
				gv.accInfo[1] = txtSurname[s].getText();
				gv.accInfo[2] = datePicker[s].formattedTextField.getText();
				
				if (lblColors[s][0][0].getBorder() != null)
					gv.accInfo[3] = "Red";
				else if (lblColors[s][1][0].getBorder() != null)
					gv.accInfo[3] = "Yellow";
				else if (lblColors[s][0][1].getBorder() != null)
					gv.accInfo[3] = "Green";

				gv.accInfo[4] = txtNumber[s].getText();
				gv.accInfo[5] = gv.convert(String.valueOf(txtRegUsernames[s].getText()));
				gv.accInfo[6] = gv.convert(String.valueOf(txtRegPasswords[s][0].getPassword()));
				
				UserCreation uc = new UserCreation(gv);
				
				uc.infoHandler(gv.accInfo);
			}
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
			
			if (txtRegUsernames[s].getText().equals(""))
			{
				lblPrompts[s][5].setForeground(Color.RED);
				txtRegUsernames[s].setBorder(BorderFactory.createLineBorder(Color.RED));
				failed[s][6] = true;
			}
			else
			{
				lblPrompts[s][5].setForeground(new JLabel().getForeground());
				txtRegUsernames[s].setBorder(new JTextField().getBorder());
				failed[s][6] = false;
			}
			
			if (txtRegPasswords[s][0].getPassword().length == 0)
			{
				lblPrompts[s][6].setForeground(Color.RED);
				txtRegPasswords[s][0].setBorder(BorderFactory.createLineBorder(Color.RED));
				failed[s][7] = true;
			}
			else
			{
				lblPrompts[s][6].setForeground(new JLabel().getForeground());
				txtRegPasswords[s][0].setBorder(new JPasswordField().getBorder());
				failed[s][7] = false;
			}
			
			if (txtRegPasswords[s][1].getPassword().length == 0 || txtRegPasswords[s][1].getBorder() == BorderFactory.createLineBorder(Color.RED))
			{
				lblPrompts[s][7].setForeground(Color.RED);
				txtRegPasswords[s][1].setBorder(BorderFactory.createLineBorder(Color.RED));
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
				pnlTabs.setForegroundAt(s, Color.RED);
			else
				pnlTabs.setForegroundAt(s, Color.BLACK);
		}
		boolean finalFail = false;
		for (int temp = 0; temp < 10; temp++)
			if (failed[temp][0])
			{
				finalFail = true;
				break;
			}
		
		if (finalFail)
			return false;
		else
			return true;
	}
	
	private JLabel lblWelcome;	
	private JLabel lblMain;
	private JLabel lblDivider;	
	private JButton btnNext;
	private JButton btnExit;
	private JPanel[] pnlAccDetails;
	private JPanel[][] pnlTabContain;
	private JTextField[] txtFirstName;
	private JTextField[] txtSurname;
	private UtilDateModel[] model;
	private JDatePanelImpl[] datePanel;
	private JDatePickerImpl[] datePicker;
	private JLabel[][][] lblColors;
	private JTextField[] txtNumber;
	private JButton[] btnRanNum;
	private JTextField[] txtRegUsernames;
	private JPasswordField[][] txtRegPasswords;
	private JLabel[][] lblPrompts;
	private JTabbedPane pnlTabs;
//	private String[] accInfo;  
	
	Global_Vars gv = null;
}
