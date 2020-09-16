import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionPanel extends JPanel{
	JLabel score;
	JLabel len;
	JButton restart;
	JComboBox hard;
	
	int mylen;
	int myscore;
	
	public OptionPanel() {
		this.setBounds(900,0,300,700);
		this.setBackground(Color.CYAN);
		this.setLayout(null);
		score = new JLabel("������");
		score.setBounds(25,125,200,30);
		score.setFont(new Font("����",Font.BOLD,23));
		
		len = new JLabel("���ȣ�");
		len.setBounds(25,75,200, 30);
		len.setFont(new Font("����",Font.BOLD,23));
		
		hard = new JComboBox();
		hard.setFont(new Font("����",Font.BOLD,23));
		hard.setBounds(25, 250, 200, 30);;
		hard.addItem("��");
		hard.addItem("����");
		hard.addItem("��̬");
		
		restart = new JButton("���¿�ʼ");
		restart.setFont(new Font("����",Font.BOLD,23));
		restart.setBounds(25, 400, 180, 30);
		this.add(len);
		this.add(score);
		this.add(hard);
		this.add(restart);
		
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		while(mylen!=Parameter.LEN) {
			mylen = Parameter.LEN;
			len.setText(len.getText()+mylen);
		}
		//g.drawString("���ȣ�", 30, 75);
	}
	
}
