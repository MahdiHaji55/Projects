/*
* Lab6Tester.java
*/
public class Lab6Tester {

	private static int testPassCount = 0;
	private static int testCount = 0;

	// for approximate comparison of floating point numbers
	private static final double THRESHOLD = 0.01;

	public static void main (String[] args) {

		try {
			/* tests for methods given - not to be graded */
			testAddOneRecursive();
			testSumValues();
			testDoubleAtOddPositions();

			/* tests for methods to be implemented and graded */
			testDoubleValues();
			testDoubleOddValues();
			testProductValues();
			testAllNegative();
			testIsSortedAscending();
		} catch (Exception e) {
			System.out.println("Your code threw an Exception.");
			System.out.println("Perhaps a stack trace will help:");
			e.printStackTrace(System.out);
		}	
		System.out.println("Passed " + testPassCount + " / " + testCount + " tests");
	}

	public static void testAddOneRecursive() {
		IntegerLinkedList emptyList = new IntegerLinkedList();
		IntegerLinkedList list3 = new IntegerLinkedList();

		list3.addFront(-2);
		list3.addFront(0);
		list3.addFront(7);

		emptyList.addOneRecursive();
		displayResults(emptyList.toString().equals(""), "testAddOne - empty");

		list3.addOneRecursive();
		displayResults(list3.toString().equals("8 1 -1"), "testAddOne - length3");
	}

	public static void testDoubleValues() {
		IntegerLinkedList emptyList = new IntegerLinkedList();
		IntegerLinkedList list3 = new IntegerLinkedList();

		list3.addFront(-2);
		list3.addFront(0);
		list3.addFront(7);

		emptyList.doubleValues();
		displayResults(emptyList.toString().equals(""), "testDoubleValues - empty");

		list3.doubleValues();
		displayResults(list3.toString().equals("14 0 -4"), "testDoubleValues - length3");
	}

	public static void testDoubleOddValues() {
		IntegerLinkedList emptyList = new IntegerLinkedList();
		IntegerLinkedList list3a = new IntegerLinkedList();
		IntegerLinkedList list3b = new IntegerLinkedList();

		list3a.addFront(-2);
		list3a.addFront(0);
		list3a.addFront(7);

		list3b.addFront(-1);
		list3b.addFront(5);
		list3b.addFront(7);

		emptyList.doubleOddValues();
		displayResults(emptyList.toString().equals(""), "testDoubleOddValues - empty");

		list3a.doubleOddValues();
		displayResults(list3a.toString().equals("14 0 -2"), "testDoubleOddValues - length3");

		list3b.doubleOddValues();
		displayResults(list3b.toString().equals("14 10 -2"), "testDoubleOddValues - length3");
	}

	public static void testDoubleAtOddPositions() {
		IntegerLinkedList emptyList = new IntegerLinkedList();
		IntegerLinkedList list3a = new IntegerLinkedList();

		list3a.addFront(-2);
		list3a.addFront(5);
		list3a.addFront(7);
		list3a.addFront(8);

		emptyList.doubleOddPositionValues();
		displayResults(emptyList.toString().equals(""), "testDoubleAtOddPositions - empty");

		list3a.doubleOddPositionValues();
		displayResults(list3a.toString().equals("8 14 5 -4"), "testDoubleAtOddPositions - length3");
	}

	public static void testSumValues() {
		IntegerLinkedList emptyList = new IntegerLinkedList();
		IntegerLinkedList list3 = new IntegerLinkedList();

		list3.addFront(-2);
		list3.addFront(0);
		list3.addFront(7);

		int result = emptyList.sum();
		displayResults(result == 0, "testSumValues - empty");

		result = list3.sum();
		displayResults(result == 5, "testSumValues - length3");
	}

	public static void testProductValues() {
		IntegerLinkedList emptyList = new IntegerLinkedList();
		IntegerLinkedList list3a = new IntegerLinkedList();
		IntegerLinkedList list3b = new IntegerLinkedList();

		list3a.addFront(-2);
		list3a.addFront(0);
		list3a.addFront(7);

		list3b.addFront(-2);
		list3b.addFront(5);
		list3b.addFront(3);

		int result = emptyList.product();
		displayResults(result == 1, "testProductValues - empty");

		result = list3a.product();
		displayResults(result == 0, "testProductValues - length3 0 element");

		result = list3b.product();
		displayResults(result == -30, "testProductValues - length3");
	}

	public static void testIsSortedAscending() {
		IntegerLinkedList emptyList = new IntegerLinkedList();
		IntegerLinkedList list3a = new IntegerLinkedList();
		IntegerLinkedList list3b = new IntegerLinkedList();
		IntegerLinkedList list3c = new IntegerLinkedList();

		list3a.addFront(-2);
		list3a.addFront(5);
		list3a.addFront(8);
		list3a.addFront(7);

		list3b.addFront(15);
		list3b.addFront(15);
		list3b.addFront(7);
		list3b.addFront(-1);

		list3c.addFront(10);
		list3c.addFront(9);
		list3c.addFront(17);
		list3c.addFront(15);

		boolean result = emptyList.isSortedAscending();
		displayResults(result, "testIsSortedAscending - empty");

		result = list3a.isSortedAscending();
		displayResults(!result, "testIsSortedAscending - length3");

		result = list3b.isSortedAscending();
		displayResults(result, "testIsSortedAscending - length3");

		result = list3c.isSortedAscending();
		displayResults(!result, "testIsSortedAscending - length3");
	}

	public static void testAllNegative() {
		IntegerLinkedList emptyList = new IntegerLinkedList();
		IntegerLinkedList list3a = new IntegerLinkedList();
		IntegerLinkedList list3b = new IntegerLinkedList();
		IntegerLinkedList list3c = new IntegerLinkedList();
		IntegerLinkedList list3d = new IntegerLinkedList();

		list3a.addFront(-2);

		list3b.addFront(0);

		list3c.addFront(15);
		list3c.addFront(-15);
		list3c.addFront(7);

		list3d.addFront(-15);
		list3d.addFront(-15);
		list3d.addFront(-7);

		boolean result = emptyList.allNegative();
		displayResults(result, "testAllNegative - empty - true");

		result = list3a.allNegative();
		displayResults(result, "testAllNegative - length1 - true");

		result = list3b.allNegative();
		displayResults(!result, "testAllNegative - length3 - false");

		result = list3c.allNegative();
		displayResults(!result, "testAllNegative - length3 - true");

		result = list3d.allNegative();
		displayResults(result, "testAllNegative - length3 - true");
	}

	public static void displayResults (boolean passed, String testName) {
		/* There is some magic going on here getting the line number
		* Borrowed from:
		* http://blog.taragana.com/index.php/archive/core-java-how-to-get-java-source-code-line-number-file-name-in-code/
		*/
		testCount++;
		if (passed) {
			System.out.println ("Passed test: " + testCount);
			testPassCount++;
		} else {
			System.out.println ("Failed test: " + testName + " at line "
					+ Thread.currentThread().getStackTrace()[2].getLineNumber());
		}
	}
}
