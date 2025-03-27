/*
 * Lab4Tester.java
 *
 * A tester for the methods in Rectangle and ShapeList
 *
 */

public class Lab4Tester {
    
    private static int testPassCount = 0;
    private static int testCount = 0;
    
    // for approximate comparison of floating point numbers
    private static final double THRESHOLD = 0.01;
    
	public static void main(String[] args) {

		testListAddSize();
		testListAddRemove();
		testListContains();

		System.out.println("Passed " + testPassCount + " / " + testCount + " tests");
	}

	public static void testListAddSize() {
		Student s0  = new Student("abc", 50);
		Student s1  = new Student("def", 56);
		Student s2  = new Student("xyz", 99);
		Student s2sameID  = new Student("xyz", 29);

		StudentList list = null;
		String actualTraveral = null;
		String expectedTraversal = null;

		list = new StudentLinkedList();
		displayResults(list.size() == 0, "test constructor + size, empty list");
		
		list.add(s0);
		displayResults(list.size() == 1, "test constructor + add + size, 1 element list");

		actualTraveral = list.toString();
		expectedTraversal = s0 + "\n";
		displayResults(actualTraveral.equals(expectedTraversal), "test add, 1 element list");
		

		list.add(s1);
		list.add(s2);
		list.add(s2sameID);
		displayResults(list.size() == 4, "test add + size, 4 element list");
		
		actualTraveral = list.toString();
		expectedTraversal = s0 + "\n" + s1 + "\n" + s2 + "\n" + s2sameID + "\n";
		displayResults(actualTraveral.equals(expectedTraversal), "test add, 4 element list");

	}


	public static void testListAddRemove() {
		Student s0  = new Student("abc", 50);
		Student s1  = new Student("def", 56);
		Student s2  = new Student("xyz", 99);
		Student s2sameID  = new Student("xyz", 29);

		StudentList list = new StudentLinkedList();
		String actualTraveral;
		String expectedTraversal;

		list.removeFront();
		actualTraveral = list.toString();
		expectedTraversal = "";
		displayResults(list.size() == 0, "test remove + size, empty list");
		displayResults(actualTraveral.equals(expectedTraversal), "test remove empty list");
		
		list.add(s0);
		list.add(s1);
		list.add(s2);
		list.add(s2sameID);
		displayResults(list.size() == 4, "test add + size, 1 element list");
		
		actualTraveral = list.toString();
		expectedTraversal = s0 + "\n" + s1 + "\n" + s2 + "\n" + s2sameID + "\n";
		displayResults(actualTraveral.equals(expectedTraversal), "test add, 4 element list");
		
		list.removeFront();
		displayResults(list.size() == 3, "test removeFront + size, non-empty list");
		
		actualTraveral = list.toString();
		expectedTraversal = s1 + "\n" + s2 + "\n" + s2sameID + "\n";
		displayResults(actualTraveral.equals(expectedTraversal), "test removeFront non-empty list");
		
		list.removeFront();
		displayResults(list.size() == 2, "test removeFront + size, non-empty list");
		
		actualTraveral = list.toString();
		expectedTraversal = s2 + "\n" + s2sameID + "\n";
		displayResults(actualTraveral.equals(expectedTraversal), "test removeFront non-empty list");

		list.removeFront();
		displayResults(list.size() == 1, "test removeFront + size, non-empty list");

		actualTraveral = list.toString();
		expectedTraversal = s2sameID + "\n";
		displayResults(actualTraveral.equals(expectedTraversal), "test removeFront non-empty list");
		
		list.removeFront();
		displayResults(list.size() == 0, "test removeFront + size, non-empty list");
		
		actualTraveral = list.toString();
		expectedTraversal = "";
		displayResults(actualTraveral.equals(expectedTraversal), "test removeFront non-empty list");
		
		list.removeFront();
		displayResults(list.size() == 0, "test removeFront + size, empty list");

		actualTraveral = list.toString();
		expectedTraversal = "";
		displayResults(actualTraveral.equals(expectedTraversal), "test removeFront empty list");

	}

	public static void testListContains() {
		Student s0  = new Student("abc", 50);
		Student s1  = new Student("def", 56);
		Student s2  = new Student("xyz", 99);
		Student s0sameID  = new Student("abc", 90);
		Student s1sameID  = new Student("def", 96);
		Student s2sameID  = new Student("xyz", 29);
		Student noMatch  = new Student("wxyz", 99);

		StudentList list = new StudentLinkedList();
		boolean actualResult;

		actualResult = list.contains(s0);
		displayResults(actualResult==false, "test contains empty list");
		
		list.add(s0);
		list.add(s1);
		list.add(s2);

		actualResult = list.contains(s0);
		displayResults(actualResult==true, "test contains non-empty list");
		
		actualResult = list.contains(s0sameID);
		displayResults(actualResult==true, "test contains front of non-empty list");
		
		actualResult = list.contains(s1sameID);
		displayResults(actualResult==true, "test contains middle of non-empty list");
		
		actualResult = list.contains(s2sameID);
		displayResults(actualResult==true, "test contains end of non-empty list");

		actualResult = list.contains(noMatch);
		displayResults(actualResult==false, "test contains not found non-empty list");
	}


    
    public static void displayResults (boolean passed, String testName){
		/* There is some magic going on here getting the line number
		 * Borrowed from:
		 * http://blog.taragana.com/index.php/archive/core-java-how-to-get-java-source-code-line-number-file-name-in-code/
		 */
		
		testCount++;
		if (passed) {
			System.out.println ("Passed test: " + testName);
			testPassCount++;
		} else  {
			System.out.println ("Failed test: " + testName + " at line "
								+ Thread.currentThread().getStackTrace()[2].getLineNumber());
		}        
	}
}
