public class A5Exercises {

	/*
	 * Purpose: get the number of occurrences of toFind in theList
	 * Parameters: List<T> theList - the list to search through
	 *             T toFind - the value to search for
	 * Returns: int - the number of occurrences of toFind found
	 */
	public static<T> int countMatches(List<T> theList, T toFind) {
		return countMatchesRec(theList, 0, toFind);
	}
	
	/*
	 * Purpose: get the number of occurrences of toFind
	 *          from index i onwards in theList
	 * Parameters: List<T> theList - the list to search through
	 *             int i - the current index 
	 *             T toFind - the value to search for
	 * Returns: int - the number of occurrences of toFind found
	 */
	private static<T> int countMatchesRec(List<T> theList, int i, T toFind) {
		// TODO: implement this recursive method
		
		
		if (i < theList.size()){
			
			if (theList.get(i).equals(toFind)){
			
				return 1 + countMatchesRec(theList, i + 1, toFind);
			
			} else {
			
				return 0 + countMatchesRec(theList, i + 1, toFind);
			
		    }
			
			
		} else {
			
			return 0;
			
		}

		
		
		
		
		
		
		
		
		
	}
	
	/*
	 * Purpose: determine if toFind is found in theList
	 * Parameters: List<T> theList - the list to search through
	 *             T toFind - the value to search for
	 * Returns: boolean - true if theList contains toFind
	 */
	public static<T> boolean contains(List<T> theList, T toFind) {
		return containsRec(theList, 0, toFind);
	}
	
	/*
	 * Purpose: determine if toFind is found
	 *          from index i onwards in theList
	 * Parameters: List<T> theList - the list to search through
	 *             int i - the current index 
	 *             T toFind - the value to search for
	 * Returns: boolean - true if toFind is found
	 */
	private static<T> boolean containsRec(List<T> theList, int i, T toFind) {
		// TODO: implement this recursive method
		
		if (i < theList.size()){
			
			if (theList.get(i).equals(toFind)){
			
				return true;
		
			}else {
				
				return containsRec(theList, i + 1, toFind);
				
				
			}
		}
		
		
		
		
		
		return false; // so it compiles
	}		
	
	/*
	 * Purpose: get the average number of pages of all books
	 *          found in the list of books
	 * Parameters: List<Book> books - the list of books
	 * Returns: double - the average number of pages of all books
	 */
	public static<T> double averagePages(List<Book> books) {
		if (books.size() == 0) {
			return 0.0;
		} else {
			double total = (double) totalPagesRec(books, 0);
			return total / books.size();
		} 
	}
	
	/*
	 * Purpose: get the average number of pages of all books
	 *          from index i onward found in the list of books
	 * Parameters: List<Book> books - the list of books
	 *             int i - the current index
	 * Returns: double - the average number of pages
	 */
	private static<T> int totalPagesRec(List<Book> books, int i) {
		// TODO: implement this recursive method
		
		if (i < books.size()){
			
			return books.get(i).getPages() + totalPagesRec(books, i + 1);
			
			
		} else {
			
			return 0;
			
		}
		
		
		
		
	}
	
	/*
	 * Purpose: get the largest number of books in a row with a
	 *          number of pages greater than or equal to threshold
	 * Parameters: List<Book> books - the list of books
	 *             int threshold - the minimum number of pages
	 * Returns: int - the largest number of books with enough
	 *                pages found in a row
	 */
	public static<T> int longestChainOfBigBooks(List<Book> books, int threshold) {
		return longestChainRec(books, 0, threshold, 0, 0);
	}
	
	/*
	 * Purpose: get the largest number of books in a row with a
	 *          number of pages greater than or equal to threshold
	 * Parameters: List<Book> books - the list of books
	 *             int i - the current index
	 *             int threshold - the minimum number of pages
	 *             int cur - the current number found in a row
	 *             int max - the largest number found in a row
	 * Returns: int - the largest number of books with enough
	 *                pages found in a row
	 */
	private static<T> int longestChainRec(List<Book> books, int i, int threshold, int cur, int max) {
		// TODO: implement this recursive method
		
		
		if (max < cur){
				
			max = cur;
				
		}
		
		
		if (i < books.size()){
			
			
			
		
			
			if (threshold <= books.get(i).getPages()){
				
				
				return longestChainRec(books, i + 1, threshold, cur + 1, max);
				
			} else {
				
				return longestChainRec(books, i + 1, threshold, cur - cur, max);
				
				
			}
			
			
			
			
			
		} else {
		
			return max;
		
		}
	
		
		
	}
}