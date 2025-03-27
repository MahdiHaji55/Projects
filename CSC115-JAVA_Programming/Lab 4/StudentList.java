public interface StudentList {
    
    
    /*
     * Purpose: adds Student s to back of this list
     * Parameters: Student - s
     * Returns: nothing
	 * Precondition: s is not null, and not already in the list
     */
    public void add(Student s);
    
    /*
     * Purpose: returns the number of elements in this list
     * Parameters: none
     * Returns: int - the number of elements
     */
    public int size();
    
    /*
     * Purpose: returns a String reprensentation of the elements
     *      in this list separated by newlines
     * Parameters: none
     * Returns: String - the representation
     */
    public String toString();
    
    /*
     * Purpose: removes the first element in the list
     * Parameters: none
     * Returns: nothing
     */
    public void removeFront();
    
    /*
     * Purpose: determines whether a Student which is equivalent to s
     *      is contained in this list
     * Parameters: Student - s
     * Returns: boolean - true if s is found in the list, false otherwise
	 * Precondition: s is not null
     */
    public boolean contains(Student s);
    
}
