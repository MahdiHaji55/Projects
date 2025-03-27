public class A7Node {
	private int data;
	public  A7Node next;
	public  A7Node prev;

	public A7Node(int data) {
		this.data = data;
		next = null;
		prev = null;
	}

	public A7Node(int data, A7Node next, A7Node prev) {
		this.data = data;
		this.next = next;
		this.prev = prev;
	}
	
	public int getData() {
		return this.data;
	}
	
	public void setData(int data) {
		this.data = data;
	}
}

