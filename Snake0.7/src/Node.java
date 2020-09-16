
public class Node {
	public int x;
	public int y;
	public Node next;
	public Node pre;
	
	public Node() {
		
	}
	
	public Node(int x,int y,Node next,Node pre) {
		this.x = x;
		this.y = y;
		this.next = next;
		this.pre = pre;
	}
}
