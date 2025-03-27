class LoopExercises {

	public static void main (String[] args) {

		// Q1. Complete the implementation of getHighest
		testGetHighest();

		// Q2. Write and test the method factorial
		testFactorial();

		// Q3. Write and test the method sumToN
		testSumToN();

	}
	
	public static void testGetHighest() {
		int result = 0;
		
		result = getHighest(1, 2, 3);
		// What should the result be?
		
		result = getHighest(5, 4, 3);
		// What should the result be?
		
		result = getHighest(0, 2, 1);
		// What should the result be?
		
		result = getHighest(1, 1, 1);
		// What should the result be?
	}
	
	/*
	 * Purpose: return the maximum of the three given values
	 * Parameters: int, int, int - the three values to compare
	 * Returns: int - integer with the highest value 
	 */
	public static int getHighest(int a, int b, int c) {
		return 0;
	}


	
	public static void testFactorial() {
		// Write tests here
	}

	/*
	 * factorial
	 * Purpose: Calculate n factorial (n!)
	 * Parameters: int - the value n
	 * Precondition: n >= 0
	 * Returns: int - n factorial
	 */
	 
	// Write the factorial method here
	
	


	public static void testSumToN() {
		// Write tests here
	}
	
	/*
	 * sumToN
	 * Purpose: sum the numbers from 1 to n
	 * Parameters: int - the value n
	 * Precondition: n > 0
	 * Returns: int - the sum 
	 */
	 
	// Write the sumToN method here


}
