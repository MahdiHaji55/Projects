/*
 * PriorityQueue.java
 *
 * A priority queue interface.
 * 
 * This interface considers the minimum value to be the highest
 * priority.It also people to insert objects of different types into
 * the same PriorityQueue.  This will result in exceptions
 * being thrown at run-time.
 *
 * It is up to the programmer to only insert objects of one
 * type into an instance of the PriorityQueue.
 */
 
public interface PriorityQueue {
	
	/*
	 * Purpose: Adds element into the PriorityQueue at the position
	 *          according to the element's priority 	.
	 * Parameters: int element - the element to add
	 * Returns: void - nothing
	 */
	public void insert(int element);

	/*
	* Purpose: Remove highest priority element from the PriorityQueue, 
	 *	       where the smallest value will be of highest priority.
	 * Parameters: none
	 * Returns: int - the highest priority object in the Queue
	 * Throws:HeapEmptyException - if the PriorityQueue is empty.
	 */
	public int removeMin();

	/*
	 * Purpose: Determines if the PriorityQueue is empty or not.
	 * Parameters: none
	 * Returns: boolean - true if PriorityQueue is empty, false otherwise.
	 */
	public boolean isEmpty();	

	/*
	 * Purpose: Gets the number of elements in the PriorityQueue.
	 * Parameters: none
	 * Returns: int - the size of the PriorityQueue.
	 */			
	public int size();

}
 
