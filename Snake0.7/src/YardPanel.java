import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class YardPanel extends JPanel implements ActionListener,KeyListener{
	final int ROWS = 24;  
	final int COLS = 34;
	final int BLOCK_SIZE = 25;
	final int LEFT_OFFSET = 25;
	final int RIGHT_OFFSET = 25;
	final int BOTTOM_OFFSET = 25;
	final int UP_OFFSET = 75;
	
	
	//һЩͼƬ��Դ
	ImageIcon title;
	ImageIcon body;
	ImageIcon up;
	ImageIcon down;
	ImageIcon left;
	ImageIcon right;
	ImageIcon food;
	
	//��ʱ��
	Timer timer = new Timer(200,this);
	
	//Ժ�����һ����
	Snake snake;
	Food theFood;
	
	//��Ϸ״̬��
	boolean isGameOver;
	boolean isStarted;
	
	public YardPanel() {
		init();
		this.setBackground(Color.CYAN);
		this.setFocusable(true);
		this.addKeyListener(this);  //һ��Ҫ��Ӽ��̼�����
	}

	public void init() {
		loadImages();
		snake = new Snake();
		theFood = new Food();
		isGameOver = false;
		isStarted = true;
		timer.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);  //Ҫ��������ſ��Ի�������ɫ
		
		title.paintIcon(this, g, 25, 11);
		g.setColor(Color.BLACK);
		g.fillRect(LEFT_OFFSET, UP_OFFSET, COLS*BLOCK_SIZE, ROWS*BLOCK_SIZE);
		
		//���߻�����
		drawSnake(g);
		//��ʳ�ﻭ����
		drawFood(g);
	}
	
	private void drawFood(Graphics g) {
		//ʳ�ﲻ�ܳ������ߵ���������
		food.paintIcon(this, g, theFood.x*BLOCK_SIZE+LEFT_OFFSET, theFood.y*BLOCK_SIZE+UP_OFFSET);
	}
	
	private void drawSnake(Graphics g) {
		Node tmp = snake.head;
		//�Ȼ�ͷ
		switch(snake.dir) {
		case R:
			right.paintIcon(this, g, snake.head.x*BLOCK_SIZE+LEFT_OFFSET, snake.head.y*BLOCK_SIZE+UP_OFFSET);
			break;
		case L:
			left.paintIcon(this, g, snake.head.x*BLOCK_SIZE+LEFT_OFFSET, snake.head.y*BLOCK_SIZE+UP_OFFSET);
			break;
		case U:
			up.paintIcon(this, g, snake.head.x*BLOCK_SIZE+LEFT_OFFSET, snake.head.y*BLOCK_SIZE+UP_OFFSET);
			break;
		case D:
			down.paintIcon(this, g, snake.head.x*BLOCK_SIZE+LEFT_OFFSET, snake.head.y*BLOCK_SIZE+UP_OFFSET);
			break;
		}
		//�ڻ�����
		while(tmp.next != null) {
			tmp = tmp.next;
			body.paintIcon(this, g, tmp.x*BLOCK_SIZE+LEFT_OFFSET, tmp.y*BLOCK_SIZE+UP_OFFSET);
		}
	}
	
	private void loadImages() {
		InputStream is;
		try {
			is = this.getClass().getClassLoader().getResourceAsStream("images/title.jpg");
			title = new ImageIcon(ImageIO.read(is));
			is = this.getClass().getClassLoader().getResourceAsStream("images/up.png");
			up = new ImageIcon(ImageIO.read(is));
			is = this.getClass().getClassLoader().getResourceAsStream("images/down.png");
			down = new ImageIcon(ImageIO.read(is));
			is = this.getClass().getClassLoader().getResourceAsStream("images/left.png");
			left = new ImageIcon(ImageIO.read(is));
			is = this.getClass().getClassLoader().getResourceAsStream("images/right.png");
			right = new ImageIcon(ImageIO.read(is));
			is = this.getClass().getClassLoader().getResourceAsStream("images/body.png");
			body = new ImageIcon(ImageIO.read(is));
			is = this.getClass().getClassLoader().getResourceAsStream("images/food.png");
			food = new ImageIcon(ImageIO.read(is));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("������ɣ�");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(isStarted && !isGameOver) {
			if(snake.isDead()) {
				isGameOver = true;
				return;
			}
			//���ƶ�
			snake.move();
			//�ж��Ƿ�ٵ�ʳ��
			if(snake.head.x == theFood.x && snake.head.y == theFood.y) {
				//�����µ�ʳ��
				System.out.println("�Ե���");
				snake.grow();
				theFood.nextFood(snake);
			}
			repaint();
		}
		else if(isGameOver){
			JOptionPane.showMessageDialog(this,"���Ѿ�����");
			isGameOver = false;
			isStarted = false;
		}else if(!isStarted) {
			JOptionPane.showMessageDialog(this,"���㿪ʼ��Ϸ");
			isStarted = true;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode==KeyEvent.VK_UP) {
			if(snake.dir!=Direction.D)
			snake.dir = Direction.U;
		}
		else if(keyCode==KeyEvent.VK_DOWN) {
			if(snake.dir!=Direction.U)
			snake.dir = Direction.D;
		}
		
		else if(keyCode==KeyEvent.VK_LEFT) {
			if(snake.dir!=Direction.R)
			snake.dir = Direction.L;
		}
		else if(keyCode==KeyEvent.VK_RIGHT) {
			if(snake.dir!=Direction.L)
			snake.dir = Direction.R;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
