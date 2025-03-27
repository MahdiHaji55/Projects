public interface List<T> {
    
	/*
	 * Purpose:  add val to the front of the list
	 * Parameters: T val - the value to insert
	 * Returns: void - nothing
	 */
	void addFront (T val);

	/*
	 * Purpose:  add val to the back of the list
	 * Parameters: T val - the value to insert
	 * Returns: void - nothing
	 */
	void addBack (T val);
	
	/*
	 * Purpose: remove and return the front element from the list
	 * Parameters: none
	 * Returns: T - the value of the element removed
	 * Throws: ListEmptyException - if there is nothing to remove
	 */
	T removeFront() throws ListEmptyException;
	
	/*
	 * Purpose: remove and return the back element from the list
	 * Parameters: none
	 * Returns: T - the value of the element removed
	 * Throws: ListEmptyException - if there is nothing to remove
	 */
	T removeBack() throws ListEmptyException;
	
	/*
	 * Purpose: get the number of elements in the list
	 * Parameters: none
	 * Returns: int - number of elements in list
	 */
	int size ();
	
	/* Purpose: insert val into the specified position while
	 *          maintaining the order of all other elements
	 * Parameters: int position - the 0-based position to insert
	 *             T val - the value to insert
	 * Returns: void - nothing
	 * Throws: InvalidPositionException - if the given position
	 *         is less than 0 or greater than the number of elements
	 */
	void insertAt (int position, T val) throws InvalidPositionException;
    
}

