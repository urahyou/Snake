import java.util.Random;

public class Food {
	public int x;
	public int y;
	
	Random random = new Random();
	public Food() {
		x = random.nextInt(Parameter.COLS-1);
		y = random.nextInt(Parameter.ROWS-1);
	}
	
	//��������ʳ�ﲻ�ܳ������ߵ�������
	public void nextFood(Snake snake) {
		Node tmp = snake.head;
		x = random.nextInt(Parameter.COLS-1);
		y = random.nextInt(Parameter.ROWS-1);
		while(tmp!=null) {
			if(x==tmp.x && y == tmp.y) {
				x = random.nextInt(Parameter.COLS-1);
				y = random.nextInt(Parameter.ROWS-1);
			}
			tmp = tmp.next;
		}
	}
}
