public class A4Stack<T> implements Stack<T> {
	
	private A4Node<T> head;

	public A4Stack() {
		head=null;
	}

	/*
	 * Purpose: insert an item onto the top of the stack
	 * Parameters: int value - the value to insert
	 * Returns: void - Nothing
	 */
	public void push(T value) {
		A4Node<T> newNode = new A4Node<>(value);
		if(head==null) {
			head=newNode;
		} else {
			newNode.next = head;
			head=newNode;
		}
	}

	/*
	 * Purpose: removes and returns the top item from the stack
	 * Parameters: None
	 * Returns: T - the data value of the element removed
	 *              or null if the stack is empty
	 */
	public T pop() {
		T result = null;
		if(!isEmpty()) {
			result= head.getData();
			head=head.next;
		}
		return result;
	}

	/*
	 * Purpose: removes all elements from the stack
	 * Parameters: None
	 * Returns: void - nothing
	 */
	public void popAll() {
		head=null;
	}

	/*
	 * Purpose: determines whether the stack is empty
	 * Parameters: None
	 * Returns: boolean - true if the stack is empty, false otherwise
	 */
	public boolean isEmpty() {
		return head==null;
	}

	/*
	 * Purpose: get the top item on the stack
	 * Parameters: None
	 * Returns: int - the data value of the top element
	 *                or null if the stack is empty
	 */
	public T peek() {
		T result = null;
		if(!isEmpty()) {
			result= head.getData();
		}
		return result;
	}
	
	/* The methods below have been completed for you.
	   Do not edit them in any way */

	public String toString() {
		String result = "{";
		String separator = "";
		
		A4Node<T> cur = head;
		while (cur != null) {
			result += separator + cur.getData().toString();
			separator = ", ";
			cur = cur.next;
		}
	
		result += "}";
		return result;
	}
	
	public String getWords() {
		return getWordsRec(head);
	}
	
	public String getWordsRec(A4Node<T> cur) {
		if (cur == null) {
			return "";
		} else if (cur.next == null) {
			return cur.getData().toString();
		}else {
			return getWordsRec(cur.next) + " " + cur.getData().toString();
		}
	}
}