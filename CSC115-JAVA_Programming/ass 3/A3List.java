public interface A3List {

	/* 
	 * Purpose: add s to the front of the list
	 * Parameters: String s - the string to add
	 * Returns: nothing
	 * Precondition: s is not null
	 */
	public void addFront(String s);

	/* 
	 * Purpose: add s to the back of the list
	 * Parameters: String s - the string to add
	 * Returns: nothing
	 * Precondition: s is not null
	 */
	public void addBack(String s);
	
	/* 
	 * Purpose: get the current size of the list
	 * Parameters: none
	 * Returns: int - number of elements in list
	 */
	public int size(); 
	
	/* 
	 * Purpose: determines if the list is empty
	 * Parameters: none
	 * Returns: boolean - true if empty, false otherwise
	 */
	public boolean isEmpty();
	
	/* 
	 * Purpose: removes the element from the front of the list
	 * Parameters: none
	 * Returns: nothing
	 */
	public void removeFront();
	
	/* 
	 * Purpose: removes the element from the back of the list
	 * Parameters: none
	 * Returns: nothing
	 */
	public void removeBack();
	
	/* 
	 * Purpose: move the node at the given position to the back of this list
	 * Parameters: int pos - the position of the node to move to the back
	                         (assuming 0-based positioning)
	 * Returns: void - nothing
	 *
	 * Note: If an invalid position is given, no node is moved
	 *
	 * Note: there are no pre-conditions about the size of the list
	 *       or the value of the pos parameter
	 */
	public void moveToBack(int pos);
	
	/* 
	 * Purpose: create and insert a new node with value s into the correct
	 *          position of this list
	 * Parameters: String s - the value of the node to insert
	 * Returns: void - nothing
	 *
	 * Pre-condition: the list is sorted in alphabetical order
	 * Post-condition: the list is sorted in alphabetical order
	 *                 (with an extra node containing value s inserted)
	 *
	 * HINT: Look through the Node class for helpful methods
	 * Note: there are no pre-conditions about the original size of the list
	 */
	public void insertInto(String s);
}