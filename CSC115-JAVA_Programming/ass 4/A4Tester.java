
public class A4Tester {

	private static int testPassCount = 0;
	private static int testCount = 0;
	
	public static void main(String[] args) {
		testStackOperations();
		testStackIsGeneric();
		testWordProcessor();
		
		System.out.println("Passed " + testPassCount + " / " + testCount + " tests");
	}
	
	public static void testStackOperations() {
		System.out.println("Stack Operations Tests:");
		A4Stack<Integer> testStack = new A4Stack<Integer>();
		Integer result = 0;
		
		displayResults(testStack.isEmpty(), "stack initially empty");
				
		testStack.push(2);
		result = testStack.peek();
		displayResults(!testStack.isEmpty(), "stack no longer empty");
		displayResults(result.equals(2), "peek works after initial push");

		testStack.push(5);
		testStack.push(8);
		testStack.push(17);
		displayResults(testStack.peek()==17, "peek works after several push");
		displayResults(testStack.pop()==17, "pop works after peek");
		displayResults(testStack.pop()==8, "pop works after pop");
		displayResults(testStack.pop()==5, "pop works after several pop");
		displayResults(testStack.pop()==2, "pop works after several pop");
		displayResults(testStack.isEmpty(), "stack is empty");

		System.out.println();
	}
	
	public static void testStackIsGeneric() {
		System.out.println("Stack Generics Tests:");
		A4Stack<Integer> s1 = new A4Stack<Integer>();
		A4Stack<String> s2 = new A4Stack<String>();
		A4Stack<Double> s3 = new A4Stack<Double>();
		
		Integer result1;
		String result2;
		Double result3;
		
		s1.push(3);
		s1.push(8);
		s2.push("CSC");
		s2.push("ENGR");
		s3.push(5.5);
		s3.push(9.1);
		
		result1 = s1.pop();
		result2 = s2.pop();
		result3 = s3.pop();
		
		displayResults(result1.equals(8), "Integer Stack");
		displayResults(result2.equals("ENGR"), "String Stack");
		displayResults(result3.equals(9.1), "Double Stack");		
		
		result1 = s1.peek();
		result2 = s2.peek();
		result3 = s3.peek();
		
		displayResults(result1.equals(3), "Integer Stack");
		displayResults(result2.equals("CSC"), "String Stack");
		displayResults(result3.equals(5.5), "Double Stack");

		s1.push(5);
		s2.push("Hello");
		s3.push(5.5);
		displayResults(s1.peek()==5, "peek works in Integer Stack");
		displayResults(s2.peek().equals("Hello"), "peek works in String Stack");
		displayResults(s3.peek()==5.5, "peek works in Double Stack");

		displayResults(s1.pop()==5, "pop works in Integer Stack");
		displayResults(s2.pop().equals("Hello"), "pop works in String Stack");
		displayResults(s3.pop()==5.5, "pop works in Double Stack");

		System.out.println();
	}

	public static void testWordProcessor() {
		/* The following statement will begin the word processor
		 * simulation. You can comment it out to test the undo 
		 * and redo methods individually if you wish, or just 
		 * test them by running the simulation. */
		WordProcessor.simulateWordProcessor();
	}

	public static void displayResults (boolean passed, String testName) {
		testCount++;
		if (passed) {
			System.out.println ("Passed test: " + testName);
			testPassCount++;
		} else {
			System.out.println ("Failed test: " + testName + " at line "
								+ Thread.currentThread().getStackTrace()[2].getLineNumber());
		}
	}
	
}