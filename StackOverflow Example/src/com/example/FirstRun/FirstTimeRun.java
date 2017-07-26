package com.example.FirstRun;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class FirstTimeRun extends JPanel 
{
	private static final long serialVersionUID = 1L;
	
	public FirstTimeRun()
	{
	}
	
	public FirstTimeRun(ActionListener val)
	{
		initComponents(val);
	}

	private void initComponents(ActionListener val) // method to build initial view for user for installation
	{
		pnlStart = new JPanel[1];
		btnNext = new JButton();
		pnlStart[0] = new JPanel();

		btnNext.setText("Next"); // adding text to button for starting
		btnNext.setPreferredSize(new Dimension(80, 35)); //positioning start button
		btnNext.addActionListener(val);
		pnlStart[0].add(btnNext); // adding button to JFrame
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(pnlStart[0]);
	}

	// objects used in UI
	private JPanel[] pnlStart;
	private JButton btnNext;
}