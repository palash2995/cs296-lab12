import java.awt.BorderLayout;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

//import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

//import javax.swing.BorderFactory;
//import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.SwingUtilities;


public class TextAreaExample extends JFrame {
	private Toolkit toolkit;


    public TextAreaExample() {
        
        initUI();
    }

    private void initUI() {
    	//frame.setLayout(new GridLayout(2, 1));
    	JPanel panel = new JPanel(new GridLayout(2,1));
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		String lyrics="jrhbkjndjkvfknv\nfkvfkjffjvnkftkjnbfk\n"+"jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj" +
				"hhhhhhhhhhhhhhhhhh"+"fbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
						"jhhhhhhhhhhhhhhhhhhhhhhhhhhhh";

		JLabel label = new JLabel(lyrics);
        label.setFont(new Font("Georgia", Font.PLAIN, 14));
        label.setForeground(new Color(50, 50, 25));


        panel.add(label, BorderLayout.TOP);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        //add(panel);
        //pack();

        toolkit = getToolkit();
        Dimension screensize = toolkit.getScreenSize();
        setLocation((screensize.width - getWidth())/2, 
            (screensize.height - getHeight())/2);
        //JPanel panel1=new JPanel();
        JScrollPane pane = new JScrollPane();
        JTextArea area = new JTextArea();
        //panel1.setLayout(new BorderLayout());
        //panel1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        pane.getViewport().add(area);
        panel.add(pane);
		
        add(panel);
        
        setTitle("JTextArea");
        setSize(new Dimension(350, 300));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
       
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TextAreaExample ex = new TextAreaExample();
                ex.setVisible(true);
            }
        });
    }
}
