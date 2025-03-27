public class Node<T>{
	    
	private T data;
	protected Node<T> next;

	public Node (T value) {
		this.data = value;
		this.next = null;
	}

	/*
	 * Purpose: get the data value of this Node
	 * Parameters: nothing	 
	 * Returns: T - the data value
	 */
	public T getData() {
		return data;
	}
	
	/*
	 * Purpose: get the data value of this Node
	 * Parameters: nothing	 
	 * Returns: T - the data value
	 */
	public void setData(T data) {
		this.data = data;
	}

	/*
	 * Purpose: get the next node
	 * Parameters: nothing
	 * Returns: Node<T> - the next node
	 */
	public Node<T> getNext() {
		return next;
	}

	/*
	 * Purpose: update the next reference for this node
	 * Parameters: Node<T> next - the new next
	 * Returns: void - nothing
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}

}