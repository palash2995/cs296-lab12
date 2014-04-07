package cs296JTalk2;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Gui extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextArea textArea, sendArea;
	protected JButton button;
	private final static String newline = "\n";
	public JChatComm chat;
	public Gui(JChatComm chat1) {
		super(new GridBagLayout());
		chat=chat1;
		
		textArea = new JTextArea(20, 40);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		sendArea = new JTextArea(5, 40);
		sendArea.setLineWrap(true);
		sendArea.setWrapStyleWord(true);
		
		JScrollPane sendPane = new JScrollPane(sendArea);
		sendPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		button = new JButton("Send");
		button.addActionListener(this);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		add(scrollPane, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		add(sendPane, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		add(button, c);
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == button) {
			String text = sendArea.getText();
			textArea.append("You: "+text+newline);
			sendArea.setText("");
			try {
				chat.sendMessage(this,new CompleteMsg(text,new Date(),"You","Other"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}
	}
}