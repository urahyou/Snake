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
	
	
	//一些图片资源
	ImageIcon title;
	ImageIcon body;
	ImageIcon up;
	ImageIcon down;
	ImageIcon left;
	ImageIcon right;
	ImageIcon food;
	
	//计时器
	Timer timer = new Timer(200,this);
	
	//院子里的一条蛇
	Snake snake;
	Food theFood;
	
	//游戏状态量
	boolean isGameOver;
	boolean isStarted;
	
	public YardPanel() {
		init();
		this.setBackground(Color.CYAN);
		this.setFocusable(true);
		this.addKeyListener(this);  //一定要添加键盘监听器
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
		super.paintComponent(g);  //要调用这个才可以画出背景色
		
		title.paintIcon(this, g, 25, 11);
		g.setColor(Color.BLACK);
		g.fillRect(LEFT_OFFSET, UP_OFFSET, COLS*BLOCK_SIZE, ROWS*BLOCK_SIZE);
		
		//把蛇画出来
		drawSnake(g);
		//把食物画出来
		drawFood(g);
	}
	
	private void drawFood(Graphics g) {
		//食物不能出现在蛇的身体里面
		food.paintIcon(this, g, theFood.x*BLOCK_SIZE+LEFT_OFFSET, theFood.y*BLOCK_SIZE+UP_OFFSET);
	}
	
	private void drawSnake(Graphics g) {
		Node tmp = snake.head;
		//先画头
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
		//在画身体
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
		System.out.println("加载完成！");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(isStarted && !isGameOver) {
			if(snake.isDead()) {
				isGameOver = true;
				return;
			}
			//蛇移动
			snake.move();
			//判断是否迟到食物
			if(snake.head.x == theFood.x && snake.head.y == theFood.y) {
				//生成新的食物
				System.out.println("吃到啦");
				snake.grow();
				theFood.nextFood(snake);
			}
			repaint();
		}
		else if(isGameOver){
			JOptionPane.showMessageDialog(this,"你已经死了");
			isGameOver = false;
			isStarted = false;
		}else if(!isStarted) {
			JOptionPane.showMessageDialog(this,"请你开始游戏");
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
