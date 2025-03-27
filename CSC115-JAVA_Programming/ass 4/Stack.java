interface Stack<T> {
	
	/*
	 * Purpose: insert an item onto the top of the stack
	 * Parameters: int value - the value to insert 
	 * Returns: void - Nothing
	 */
	public void push(T value); 
	
	/*
	 * Purpose: removes and returns the top item from the stack
	 * Parameters: None
	 * Returns: T - the data value of the element removed
	 *              or null if the stack is empty
	 */
	public T pop();
	
	/*
	 * Purpose: determines whether the stack is empty
	 * Parameters: None
	 * Returns: boolean - true if the stack is empty, false otherwise
	 */
	public boolean isEmpty();
	
	/*
	 * Purpose: get the top item on the stack
	 * Parameters: None
	 * Returns: int - the data value of the top element
	 *                or null if the stack is empty
	 */
	public T peek();
	
	/*
	 * Purpose: removes all elements from the stack
	 * Parameters: None
	 * Returns: void - nothing
	 */
	public void popAll();

}