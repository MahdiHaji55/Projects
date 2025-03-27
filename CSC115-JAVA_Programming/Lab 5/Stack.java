public interface Stack {

	/*
	 * Purpose: insert an item onto the top of the stack
	 * Parameters: char - the item to insert 
	 * Returns: void - nothing
	 */
	public void push(char v);

	/*
	 * Purpose: removes and returns the top item from the stack
	 * Parameters: none
	 * Returns: char - the data value of the element removed
	 */
	public char pop();

	/*
	 * Purpose: determines whether the stack is empty
	 * Parameters: none
	 * Returns: boolean - true if the stack is empty, false otherwise
	 */
	public boolean isEmpty();

	/*
	 * Purpose: gets the value of the top item on the stack
	 * Parameters: none
	 * Returns: char - the data value of the top element
	 * Preconditions: stack is not empty
	 */
	public char top();
	public char peek(); // another name for top
	
	/*
	 * Purpose: remove all elements from the stack
	 * Parameters: none
	 * Returns: void - nothing
	 */
	public void popAll();
	public void makeEmpty(); // another nae for popAll
	
	/*
	 * Purpose: get the number of elements in the stack
	 * Parameters: none
	 * Returns: int - the number of elements in the stack
	 */
	public int size();

}