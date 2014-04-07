//import java.awt.BorderLayout;

import java.awt.*;

import javax.swing.*;


//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.Toolkit;

//import javax.swing.BorderFactory;
//import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.SwingUtilities;

public class MyGui extends JFrame{
	public MyGui() {
        
        initUI();
    }
    private void initUI(){
		JPanel panel = new JPanel(new GridLayout(3,1));
		String lyrics="<html> jrhbkjndjkvfknv<br>fkvfkjffjvnkftkjnbfk</html>";
		JLabel label = new JLabel(lyrics);
        label.setFont(new Font("Georgia", Font.PLAIN, 14));
        label.setForeground(new Color(50, 50, 25));
        
        panel.add(label);
        
        JScrollPane pane = new JScrollPane();
        JTextArea area = new JTextArea();
        //panel1.setLayout(new BorderLayout());
        //panel1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        pane.getViewport().add(area);
        panel.add(pane);
        
        JButton btn = new JButton("Send");
        panel.add(btn);
        
        getContentPane().add(panel);
        //setVisible(true);
		setTitle("JTextArea");
        setSize(new Dimension(350, 300));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
    
/*public static void main(String [] args) {
        JFrame frame = new JFrame("My Frame");
          JPanel panel = new JPanel(new GridLayout(1,2)); // split the panel in 1 rows and 2 cols


          JTextArea userField = new JTextArea("User:");
          userField.setEditable(false);

          JTextArea user = new JTextArea("myuser");
          user.setEditable(true);

          panel.add(userField);
          panel.add(user);

          frame.getContentPane().add(panel);

          frame.setVisible(true);
          frame.setSize(500, 500);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }*/
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MyGui ex = new MyGui();
                ex.setVisible(true);
            }
        });
    }
    }
