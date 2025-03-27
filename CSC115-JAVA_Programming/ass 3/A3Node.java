public class A3Node {
	private String data;
	protected A3Node prev;
	protected A3Node next;
	
	public A3Node(String data) {
		this.data = data;
		prev = null;
		next = null;
	}
	
	public String getData() {
		return data;
	}
	
	public A3Node getPrev() {
		return prev;
	}
	
	public A3Node getNext() {
		return next;
	}
	
	public void setNext(A3Node next) {
		this.next = next;
	}
	
	public void setPrev(A3Node prev) {
		this.prev = prev;
	}
	
	public String toString() {
		return data;
	}
	
	/*
	 * Purpose: Determines whether this node comes before the other node
	 *          when ordered alphabetically by the value of the data field
	 * Parameters: A3Node other - the other node to compare to
	 * Returns boolean - true if this node comes first alphabetically
	 */
	public boolean comesBefore(A3Node other) {
		return this.data.compareTo(other.getData()) <= 0;
	}
}