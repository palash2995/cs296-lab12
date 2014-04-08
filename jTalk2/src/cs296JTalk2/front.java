package cs296JTalk2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class front
		extends 	JFrame
 {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Instance attributes used in this example
	private	JPanel		topPanel;
	private	JList		listbox;
	public String	listData[] =
		{
			"Item 1",
			"Item 2",
			"Item 3",
			"Item 4"
		};
	// Constructor of main frame
	public front()
	{
		// Set the frame characteristics
		setTitle( "Simple ListBox Application" );
		setSize( 300, 100 );
		setBackground( Color.gray );

		// Create a panel to hold all other components
		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );

		// Create some items to add to the list
		

		// Create a new listbox control
		listbox = new JList( listData );
		topPanel.add( listbox, BorderLayout.CENTER );
		this.setVisible(true);
	}
}
