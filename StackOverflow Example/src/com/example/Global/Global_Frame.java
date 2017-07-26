package com.example.Global;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.example.FirstRun.AccDetails;
import com.example.FirstRun.FirstTimeRun;

public class Global_Frame extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	ActionListener val = new ActionListener()
	{
	    public void actionPerformed(ActionEvent e) 
	    {
	        getUserDetails();
	    }
	};
	
	public Global_Frame()
	{
		
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // get look and feel based on OS
		} 
		catch (ClassNotFoundException ex) // catch all errors that may occur
		{
			Logger.getLogger(Global_Frame.class.getName()).log(Level.SEVERE, null, ex);
		} 
		catch (InstantiationException ex) 
		{
			Logger.getLogger(Global_Frame.class.getName()).log(Level.SEVERE, null, ex);
		} 
		catch (IllegalAccessException ex) 
		{
			Logger.getLogger(Global_Frame.class.getName()).log(Level.SEVERE, null, ex);
		} 
		catch (UnsupportedLookAndFeelException ex) 
		{
			Logger.getLogger(Global_Frame.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		EventQueue.invokeLater(new Runnable()
		{
			public void run() //run the class's constructor, therefore starting the UI being built
			{
				initComponents();
			}
		});
	}
	
	public void initComponents()
	{
		setPreferredSize(new Dimension(600, 400)); // setting measurements of jframe
		
		revalidate(); // revalidate the elements that will be displayed
		repaint(); // repainting what is displayed if going coming from a different form
		pack(); // packaging everything up to use
		setLocationRelativeTo(null); // setting form position central
	}

	public void getAccNum()
	{
		setPreferredSize(new Dimension(600, 400)); // setting measurements of jframe
		FirstTimeRun panel1 = new FirstTimeRun(val);
		add(panel1);
		
		revalidate();
		repaint();
		pack();
	}

	public void getUserDetails()
	{
		getContentPane().removeAll();
		
		setPreferredSize(new Dimension(750, 500)); // setting measurements of jframe
		AccDetails panel2 = new AccDetails();
		add(panel2);
		
		revalidate();
		repaint();
		pack();
	}
	
	//variables used for window resizing
	
	public int accNum = 0;
}