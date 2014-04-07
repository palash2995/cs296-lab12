package cs296JTalk2;

import javax.swing.JFrame;

public class CreateAndShowGUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Gui gui;
	public CreateAndShowGUI(Gui gui1){
		gui = gui1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(gui1);
		pack();
		setVisible(true);
	}

}
