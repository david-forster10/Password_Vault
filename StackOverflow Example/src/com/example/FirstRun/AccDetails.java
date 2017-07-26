package com.example.FirstRun;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AccDetails extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	public AccDetails()
	{
		accAssets();
	}
	
	private void accAssets()
	{	
		// instantiating elements of the GUI
		pnlAccDetails = new JPanel[2];
		lblWelcome = new JLabel();
		lblMain = new JLabel();
		
		for (int i = 0; i < 2; i++)
			pnlAccDetails[i] = new JPanel();
		
		lblWelcome.setText("Welcome to Example_App"); // label welcoming user
		pnlAccDetails[0].setLayout(new BoxLayout(pnlAccDetails[0], BoxLayout.LINE_AXIS));
		pnlAccDetails[0].add(lblWelcome); // adding label to form

		lblMain.setText("<html>The following information that is collected will be used as part of the Example_App process to ensure that each user has unique Example_App paths. Please fill in all areas of the following tabs:</html>"); // main label that explains what happens, html used for formatting
		pnlAccDetails[1].setLayout(new BorderLayout());
		pnlAccDetails[1].add(Box.createHorizontalStrut(20), BorderLayout.LINE_START);
		pnlAccDetails[1].add(lblMain, BorderLayout.CENTER); //adding label to JFrame
		pnlAccDetails[1].add(Box.createHorizontalStrut(20), BorderLayout.LINE_END);
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(pnlAccDetails[0]);
		add(pnlAccDetails[1]);
	}
	
	private JLabel lblWelcome;	
	private JLabel lblMain;
	private JPanel[] pnlAccDetails;
}