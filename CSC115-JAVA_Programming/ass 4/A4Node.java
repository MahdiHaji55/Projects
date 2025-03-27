public class A4Node<T>{
	    
	private T data;
	protected A4Node<T> next;

	public A4Node (T value) {
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
	 * Purpose: get the next node
	 * Parameters: nothing
	 * Returns: A4Node<T> - the next node
	 */
	public A4Node<T> getNext() {
		return next;
	}

	/*
	 * Purpose: update the next reference for this node
	 * Parameters: A4Node<T> next - the new next
	 * Returns: void - nothing
	 */
	public void setNext(A4Node<T> next) {
		this.next = next;
	}

}