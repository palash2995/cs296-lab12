
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.SwingUtilities;

public class SimpleExample extends JFrame{
private JTextArea ta;
private int count;
private String pad;
    public SimpleExample() {
        
       super("Chat Box");
       setSize(600, 600);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       Container pane = getContentPane();
       JButton btn = new JButton("Send");
      btn.setMnemonic(KeyEvent.VK_C); // Now you can hit the button with
    pane.setLayout(new BorderLayout());
    count = 0;
    pad = " ";
    ta = new JTextArea(); //textarea
    ta.setLineWrap(true);
    ta.setWrapStyleWord(true);
		setVisible(true);
    }
    

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SimpleExample ex = new SimpleExample();
                ex.setVisible(true);
            }
        });
    }
}
