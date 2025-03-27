public class SinglyLinkedList<T> implements List<T> {

	private Node<T> head;
	private Node<T> tail;
	private int size;

	public SinglyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	public void addFront (T data) {
		Node<T> n = new Node<T>(data);
		if (head == null) {
			tail = n;
		}
		n.next = head;
		head = n;
		size++;
	}

	public void addBack (T data){
		Node<T> n = new Node<T>(data);
		if(head == null) {
			head = n;
		} else {
			tail.next = n;
		}
		tail = n;
		size++;
	}

	public int size () {
		return size;
	}
	
	public boolean isEmpty() {
		return size==0;
	}

	public T get (int position) {
		Node<T> cur = head;
		for(int i = 0; i < position; i++) {
			cur = cur.next;
		}
		return cur.getData();
	}
	
	public T removeFront() {
		if (head == null) { // list is empty case
			return null; 
		} else if (head == tail) {
			tail = null; // one element case
		}
		
		T toReturn = head.getData();
		head = head.next;
		size--;
		return toReturn;		
	}
	
	public T removeBack() {
		if (head == null) { // list is empty case
			return null; 
		} 
		
		T toReturn = tail.getData();
		
		if (head == tail) {
			head = null;
			tail = null;
		} else {
			Node<T> cur = head;
			while (cur.next.next != null) {
				cur = cur.next;
			}
			cur.next = null; 
			tail = cur;
		}
		size--;
		return toReturn;		
	}

	/* Purpose: create a string representation of list
	 * Parameters: nothing	 
	 * Returns: String - the string representation of the list
	 */
	public String toString() {
		if (head == null) {
			return "{}";
		} else {
			return "{" + toStringRec(head) + "}";
		}
	}
	
	public String toStringRec(Node<T> cur) {
		if (cur == null) {
			return "";
		} else if (cur.next == null) {
			return cur.getData().toString();
		} else {
			return cur.getData().toString() + ", " + toStringRec(cur.next);
		}
	}

	/*
	 * Purpose: Insert all elements from array into this linked list
	 * Parameters: T[] array - the elements to add to this list
	 * Returns void - nothing
	 */
	public void buildFromArray(T[] array) {
		buildFromArrayRec(array, 0);
	}
	
	public void buildFromArrayRec(T[] array, int i) {
		if (i == array.length) {
			return;
		} else {
			addBack(array[i]);
			buildFromArrayRec(array, i+1);
		}
	}

	/*
	 * Purpose: get the number of occurrences of toFind in this list
	 * Parameters: T toFind - the value to search for
	 * Returns: int - the number of occurrences of toFind found
	 */
	public int countMatches(T toFind) {
		return countMatchesRec(head, toFind);
	}
	
	/*
	 * Purpose: get the number of occurrences of toFind
	 *          from node cur onwards in this list
	 * Parameters: T toFind - the value to search for
	 *             Node<T> cur - the current node
	 * Returns: int - the number of occurrences of toFind found
	 */
	public int countMatchesRec(Node<T> cur, T toFind) {
		// TODO: implement this recursive method
		
		
		
		if (cur == null){
			
			return 0;
			
		} else {
			
			if (cur.getData().equals(toFind)){
			
				return 1 + countMatchesRec(cur.next, toFind);
			
		    } else {
			
				return 0 + countMatchesRec(cur.next, toFind);
			
		}
			
			
		}
		
		
		
		
		
		
		
		
		
	}
	
	/*
	 * Purpose: change the value of all occurrences of original
	 *          to updated found in this list
	 * Parameters: T original - the value to change
	 *             T updated - the value to change to
	 * Returns: void - nothing
	 */
	public void changeAll(T original, T updated) {
		changeAllRec(head, original, updated);
	}
	
	/*
	 * Purpose: change the value of all occurrences of original
	 *          to updated found from cur onwards in this list
	 * Parameters: Node<T> cur - the current node
	 *             T original - the value to change
	 *             T updated - the value to change to
	 * Returns: void - nothing
	 */
	private void changeAllRec(Node<T> cur, T original, T updated) {
		// TODO: implement this recursive method
		
		if (cur != null){
			
			if (cur.getData().equals(original)){
				
				cur.setData(updated);
				
				changeAllRec(cur.next, original, updated);
				
			} else {
				
				changeAllRec(cur.next, original, updated);
				
			}
			
			
		}
		
		
	}
	
	/*
	 * Purpose: get the number of elements found before
	 *          toFind in this list
	 * Parameters: T toFind - the value to search for
	 * Returns: int - the number of elements before toFind
	 * Preconditions: toFind is in this list
	 */
	public int countBefore(T toFind) {
		return countBeforeRec(head, toFind);
	}
	
	/*
	 * Complete the design of the recursive helper below
	 */
	private int countBeforeRec(Node<T> cur, T toFind) {
		// TODO: implement this recursive method
		
		if (cur == null){
			
			return 0;
			
		} else {
			
			if (cur.getData().equals(toFind)){
				
				return 0;
				
			} else {
				
				return 1 + countBeforeRec(cur.next, toFind);
				
			}
			
			
			
			
		}
		
		
		
	}

	/*
	 * Purpose: get the number of elements found after
	 *          toFind in this list
	 * Parameters: T toFind - the value to search for
	 * Returns: int - the number of elements after toFind
	 * Preconditions: toFind is in this list
	 */
	public int countAfter(T toFind) {
		return countAfterRec(head, false, toFind);
	}
	
	/*
	 * Complete the design of the recursive helper below
	 */
	private int countAfterRec(Node<T> cur, boolean found, T toFind) {
		// TODO: implement this recursive method
		
		if (cur == null){
			
			return 0;
			
		} else {
			
			if (found == true){
				
				return 1 + countAfterRec(cur.next, found, toFind);
				
				
			} else {
				
				if (cur.getData().equals(toFind)){
				
					found = true;
					return countAfterRec(cur.next, found, toFind);
				
				} else {
				
					return 0 + countAfterRec(cur.next, found, toFind);
				
			}
				
			}
			
			
			
			
		}
		
		
	}
	
	/*
	 * Purpose: get the number of elements found between
	 *          first and second in the list
	 * Parameters: T toFind - the value to search for
	 * Returns: int - the number of elements between first and second
	 * Preconditions: first and second are both in this list
	 */
	public int countBetween(T first, T second) {
		// TODO: design a way to complete this method
		// REMEMBER: You may not use for/while loops
		
		
		
		if (head == null){
			
			
			return 0;
			
			
		} else {
			
			if (head.getData().equals(first)){
				
				return countBeforeRec(head, second) - 1;
				
			} else {
				
				removeFront();
				
				return countBetween(first, second);
				
			}
			
			
			
		}
		
		
		
	}
	

}