import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Demo extends JFrame{
	public Demo() {
		setBounds(100,100,500,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		Icon icon = new ImageIcon("body.png");
		JLabel l = new JLabel("±Í«©");
		l.setIcon(icon);
		c.add(l);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new Demo();
	}

}
