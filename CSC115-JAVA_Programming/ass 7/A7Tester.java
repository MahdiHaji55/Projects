/*
* A7Tester.java
*
* A test program for Assignment 7
*
*/

import java.util.Random;

public class A7Tester {
private static int testPassCount = 0;
private static int testCount = 0;
public static boolean testHeapSolution = true;
	
	public static void main (String[] args) {
		if (args.length != 0 && args[0].equals("linked")) {
			testHeapSolution=false;
		}
		
		System.out.println("Testing " + (testHeapSolution ? "Heap" : "Linked" ) + " implementation.");
		testSize();
		testInsertRemove();
		testExceptions();
		testDuplicates();
		testMixed();

		stressTest();
		
		System.out.println("Passed " + testPassCount + " / " + testCount + " tests");
	}
	
	
	public static void testSize() {
		System.out.println("\nTesting of size and isEmpty");
		PriorityQueue q = createNewPriorityQueue();
		
		displayResults (q.size() == 0, "size on empty Q");
		displayResults (q.isEmpty(), "isEmpty on empty Q");
		
		q.insert(10);
		displayResults (q.size() == 1, "size on 1 element Q");
		displayResults (!q.isEmpty(), "isEmpty on 1 element Q");
	
		q.insert(9);
		q.insert(10);
		displayResults (q.size() == 3, "size on 2 element Q");
	
		q.insert(7);
		displayResults (q.size() == 4, "size on 3 element Q");
	}


	public static void testInsertRemove()
	{
		System.out.println("\nBasic testing of insert and removeMin");
		
		PriorityQueue q = createNewPriorityQueue();
		int result = 0;
		
		q.insert(8);
		q.insert(9);
		q.insert(100);
		
		result = q.removeMin();
		displayResults(result == 8, "remove on multiple element Q");
		displayResults(q.size() == 2, "remove + size on multiple element Q");

		result = q.removeMin();
		// System.out.println("res: " + result + ":" + q);
		displayResults(result == 9, "remove on multiple element Q");
		displayResults(q.size() == 1, "remove + size on multiple element Q");

		result = q.removeMin();
		// System.out.println("res: " + result + ":" + q);
		displayResults(result == 100, "remove + size on 1 element Q");
		displayResults(q.isEmpty(), "remove + isEmpty on 1 element Q");


		q = createNewPriorityQueue();
		q.insert(3);
		q.insert(2);
		q.insert(1);

		result = q.removeMin();
		displayResults(result == 1, "insert + remove on multiple element Q");
		displayResults(q.size() == 2, "insert + remove + size on multiple element Q");

		result = q.removeMin();
		displayResults(result == 2, "insert + remove on multiple element Q");
		displayResults(q.size() == 1, "insert + remove + size on multiple element Q");

		result = q.removeMin();
		displayResults(result == 3, "insert + remove on 1 element Q");
		displayResults(q.isEmpty(), "insert + remove + size on 1 element Q");
	}
	
	public static void testExceptions(){
		System.out.println("\nTesting exceptions");
		PriorityQueue 	q = createNewPriorityQueue(2);
			
		try {
			q.removeMin();
			displayResults(false, "exception should have been thrown");
		} catch (HeapFullException e) {
			displayResults(false, "different exception should have been thrown");
		} catch (HeapEmptyException e) {
			displayResults(true, "HeapEmptyException should be thrown");
		} catch (Exception e) {
			displayResults(false, "The HeapEmptyException should have been thrown");
		}
		
		q.insert(10);
		q.insert(9);

		try {
			q.insert(8);
			if(testHeapSolution) {
				displayResults(false, "exception should have been thrown in heap solution");
			} else {
				displayResults(true, "exception should not have been thrown in linked version");
			}
		} catch (HeapEmptyException e) {
			displayResults(false, "different exception should have been thrown");
		} catch (HeapFullException e) {
			displayResults(true, "HeapFullException should be thrown");
		} catch (Exception e) {
			displayResults(false, "Code crashes");
		}
	}
	

	public static void testDuplicates()
	{		
		System.out.println("\nTesting duplicates.");
		PriorityQueue q = createNewPriorityQueue();
		int result;

		q.insert(4);
		q.insert(1);
		q.insert(1);
		q.insert(5);
		q.insert(0);
		// System.out.println("q after insert 4 1 1 5 0:" + q);
		
		result = q.removeMin();
		displayResults(result == 0, "add duplicates + remove single");
		
		
		q.insert(4);
		q.insert(1);
		q.insert(4);
		q.insert(4);

		q.insert(0);
		q.insert(5);
		q.insert(0);
		q.insert(5);

		result = q.removeMin();
		displayResults(result == 0, "add duplicates + remove duplicates");
		result = q.removeMin();
		displayResults(result == 0, "add duplicates + remove duplicates");
		
		result = q.removeMin();
		displayResults(result == 1, "add duplicates + remove duplicates");
		result = q.removeMin();
		displayResults(result == 1, "add duplicates + remove duplicates");
		result = q.removeMin();
		displayResults(result == 1, "add duplicates + remove duplicates");
		
		result = q.removeMin();
		displayResults(result == 4, "add duplicates + remove duplicates");
		result = q.removeMin();
		displayResults(result == 4, "add duplicates + remove duplicates");
		result = q.removeMin();
		displayResults(result == 4, "add duplicates + remove duplicates");
		result = q.removeMin();
		displayResults(result == 4, "add duplicates + remove duplicates");
		
		result = q.removeMin();
		displayResults(result == 5, "add duplicates + remove duplicates");
		result = q.removeMin();
		displayResults(result == 5, "add duplicates + remove duplicates");
		result = q.removeMin();
		displayResults(result == 5, "add duplicates + remove duplicates");
		
		displayResults(q.isEmpty(), "insert/remove + isEmpty");     
	}


	public static void testMixed()
	{
		System.out.println("Testing insert mixed with removeMin.");
		PriorityQueue q = createNewPriorityQueue();
		int result;
		
		q.insert(2);
		q.insert(0);
		q.insert(5);
		q.insert(7);

		result = q.removeMin();
		displayResults( result == 0, "inserts + remove");
		
		q.insert(4);
		result = q.removeMin();
		displayResults(result == 2, "inserts + remove + insert + remove");
		
		q.insert(11);
		q.insert(2);
		q.insert(3);
		q.insert(1);
		result = q.removeMin();
		displayResults(result == 1, "inserts + remove");
		result = q.removeMin();
		displayResults(result == 2, "inserts + remove");
		result = q.removeMin();
		displayResults(result == 3, "inserts + remove");
		result = q.removeMin();
		displayResults(result == 4, "inserts + remove");
		
		q.insert(1);
		result = q.removeMin();
		displayResults(result == 1, "inserts + remove");
		displayResults(q.size() == 3, "inserts + remove + size");
	}

	public static boolean testRandomArray (int count) {		/* These tests are effectively sorting the random values
			- for the heap, this is O (n log n)
			- for the linked list, this is O (n^2)
		*/
		PriorityQueue q = createNewPriorityQueue(count);
		System.out.println("Testing size: " + count);
		Random r = new Random();
		for ( int i = 0; i < count; i++ ) {
			int val = r.nextInt(1000000);
			q.insert (val);
		}

		int oldVal = 0; //smallest possible value val could be
		int i = 0;
		while (!q.isEmpty()) {
			int val = q.removeMin(); // or a bug
			if ( val < oldVal ) {
				return false;
			}
			oldVal = val;
			i++;
		}
		return true;
	}

	public static void stressTest() {
		System.out.println("Stress Tests.");
		displayResults(testRandomArray(100), "inserts + removes");
		displayResults(testRandomArray(10000), "inserts + removes");
		displayResults(testRandomArray(100000), "inserts + removes");

		//This takes too long using the linked list.
		if (testHeapSolution) {
			displayResults(testRandomArray(1000000), "inserts + removes");
		}
	}

	public static PriorityQueue createNewPriorityQueue()  {
		if (testHeapSolution) {
			return new HeapPriorityQueue();
		}
		else {
			return new LinkedPriorityQueue();
		}
	}

	public static PriorityQueue createNewPriorityQueue(int size)  {
		if (testHeapSolution) {
			return new HeapPriorityQueue(size);
		}
		else {
			return new LinkedPriorityQueue();
		}
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
