import java.awt.Graphics;

public class Snake {
	public Node head;
	public Node tail;
	public int len;
	public Direction dir;
	
	public Snake(){
		head = tail = new Node(Parameter.COLS/2,Parameter.ROWS/2,null,null);
		dir = Direction.R;
		len = 1;
	}
	
	public void move() {
		Node node;
		switch(dir) {
		case U:
			node = new Node(head.x,head.y-1,head,null);
			head.pre = node;
			head = node;
			break;
		case D:
			node = new Node(head.x,head.y+1,head,null);
			head.pre = node;
			head = node;
			break;
		case L:
			node = new Node(head.x-1,head.y,head,null);
			head.pre = node;
			head = node;
			break;
		case R:
			node = new Node(head.x+1,head.y,head,null);
			head.pre = node;
			head = node;
			break;
		}
		//删掉尾结点
		Node tmp = tail;
		tail = tmp.pre;
		tmp.pre = null;
		tail.next = null;
		if(head.x==Parameter.COLS) {
			head.x = 0;
		}
		if(head.x<0) {
			head.x = Parameter.COLS-1;
		}
		if(head.y==Parameter.ROWS) {
			head.y = 0;
		}
		if(head.y<0) {
			head.y = Parameter.ROWS-1;
		}
	}
	
	public void grow() {
		Node node;
		len++; //长度加一
		Parameter.LEN = len;
		switch(dir) {
		case U:
			node = new Node(head.x,head.y-1,head,null);
			head.pre = node;
			head = node;
			break;
		case D:
			node = new Node(head.x,head.y+1,head,null);
			head.pre = node;
			head = node;
			break;
		case L:
			node = new Node(head.x-1,head.y,head,null);
			head.pre = node;
			head = node;
			break;
		case R:
			node = new Node(head.x+1,head.y,head,null);
			head.pre = node;
			head = node;
			break;
		}
	}
	
	public boolean isDead() {
		Node tmp = head.next;
		while(tmp!=null) {
			if(tmp.x == head.x && tmp.y == head.y) {
				len = 0;
				return true;
			}
			tmp = tmp.next;
		}
		
		return false;
	}
}
