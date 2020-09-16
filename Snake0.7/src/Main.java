import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.JFrame;

public class Main extends JFrame{
	//构造函数
	public Main() {
		this.setBounds(200, 200, 1200, 720);
		this.setResizable(false);
		this.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);  //固定布局
		YardPanel yard = new YardPanel();
		yard.setSize(900,700);
		OptionPanel option = new OptionPanel();
		this.add(yard);
		this.add(option);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
