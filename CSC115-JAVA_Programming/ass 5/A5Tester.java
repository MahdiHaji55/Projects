public class A5Tester {
	private static int testPassCount = 0;
	private static int testCount = 0;
	private static double THRESHOLD = 0.1; // allowable margin of error for floating point results
	
    public static void main(String[] args) {
		
		/* Linked List Recursion Exercises: */
		testCountMatches();
		testChangeAll();
		testCountBefore();
		testCountAfter();
		testCountBetween();

		/* Using the List ADT Recursion Exercises */
		testCountListMatches();
		testContains();
		testAveragePages();
		testLongestChainOfBigBooks();
		
		System.out.println("Passed " + testPassCount + " / " + testCount + " tests");
	}
	
	public static void testCountMatches() {
		System.out.println("Testing countMatches tests");
		SinglyLinkedList<Book> list0 = new SinglyLinkedList<Book>();
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<Integer>();
		SinglyLinkedList<String> list2 = new SinglyLinkedList<String>();
		Integer[] arr1 = {9, 3, 1, 2, 3, 3, 4, 2, 1, 2, 1, 1};
		String[]  arr2 = {"test", "the", "test", "to", "test", "the", "test"};
		
		list1.buildFromArray(arr1); // list1: {9, 3, 1, 2, 3, 3, 4, 2, 1, 2, 1, 1}
		list2.buildFromArray(arr2); // list2: {"test", "the", "test", "to", "test", "the", "test"};
		Book b1 = new Book("Gone Girl", "Gillian Flynn", 432);
		
		int result = 0;
		int expected = 0;
		
		result = list0.countMatches(b1);
		expected = 0;
		displayResults(result==expected, "countMatches in empty list");
		
		result = list1.countMatches(5);
		expected = 0;
		displayResults(result==expected, "countMatches, no matches found");
		
		result = list1.countMatches(4);
		expected = 1;
		displayResults(result==expected, "countMatches, one found");
		
		result = list1.countMatches(2);
		expected = 3;
		displayResults(result==expected, "countMatches, found in middle");
		
		result = list1.countMatches(2);
		expected = 3;
		displayResults(result==expected, "countMatches, found in a row");
		
		result = list1.countMatches(9);
		expected = 1;
		displayResults(result==expected, "countMatches, found at beginning");
		
		result = list1.countMatches(1);
		expected = 4;
		displayResults(result==expected, "countMatches, found at end");
		
		
		result = list2.countMatches(new String("the"));
		expected = 2;
		displayResults(result==expected, "countMatches, found with other object type");
		
		result = list2.countMatches(new String("test"));
		expected = 4;
		displayResults(result==expected, "countMatches, found at both ends");
		
		
		System.out.println();
	}
	
	public static void testChangeAll() {
		System.out.println("Testing changeAll");
		SinglyLinkedList<Book> list0 = new SinglyLinkedList<Book>();
		SinglyLinkedList<Integer> list1 = new SinglyLinkedList<Integer>();
		SinglyLinkedList<String> list2 = new SinglyLinkedList<String>();
		Integer[] arr1 = {9, 3, 1, 2, 3, 3, 4, 2, 1, 2, 1, 1};
		String[]  arr2 = {"test", "the", "test", "to", "test", "the", "test"};
		
		list1.buildFromArray(arr1); // list1: {9, 3, 1, 2, 3, 3, 4, 2, 1, 2, 1, 1}
		list2.buildFromArray(arr2); // list2: {"test", "the", "test", "to", "test", "the", "test"};
		Book b1 = new Book("Gone Girl", "Gillian Flynn", 432);
		Book b2 = new Book("Hunger Games", "Suzanne Collins", 374);
		
		String   result = "";
		String expected = "";
		
		list0.changeAll(b1, b2);
		result = list0.toString();
		expected = "{}";
		displayResults(result.equals(expected), "change all in empty list");
		
		list0.addBack(b1);
		list0.addBack(b2);
		list0.changeAll(b1, b2);
		result = list0.toString();
		expected = "{Hunger Games - Suzanne Collins, Hunger Games - Suzanne Collins}";
		// System.out.println(result);
		displayResults(result.equals(expected), "change all "+b1+" to "+b2);
		
		list1.changeAll(2, 4);
		result = list1.toString();
		expected = "{9, 3, 1, 4, 3, 3, 4, 4, 1, 4, 1, 1}";
		displayResults(result.equals(expected), "change all 2s to 4s");
		
		list1.changeAll(1, 3);
		result = list1.toString();
		expected = "{9, 3, 3, 4, 3, 3, 4, 4, 3, 4, 3, 3}";
		displayResults(result.equals(expected), "change all 1s to 3s");
		
		list1.changeAll(9, 3);
		result = list1.toString();
		expected = "{3, 3, 3, 4, 3, 3, 4, 4, 3, 4, 3, 3}";
		displayResults(result.equals(expected), "change all 9s to 3s");
		
		list1.changeAll(3, 4);
		result = list1.toString();
		expected = "{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}";
		displayResults(result.equals(expected), "change all 3s to 4s");
		
		
		list2.changeAll("test", "to");
		result = list2.toString();
		expected = "{to, the, to, to, to, the, to}";
		displayResults(result.equals(expected), "change all test to to");
		
		list2.changeAll("to", "the");
		result = list2.toString();
		expected = "{the, the, the, the, the, the, the}";
		displayResults(result.equals(expected), "change all to to the");
		
		System.out.println();
	}
	
	public static void testCountBefore() {
		System.out.println("Testing countBefore");
		SinglyLinkedList<Integer> list0 = new SinglyLinkedList<Integer>();
		SinglyLinkedList<String> list1 = new SinglyLinkedList<String>();
		SinglyLinkedList<Book> list2 = new SinglyLinkedList<Book>();
		
		String[] arr1 = {"abc", "de", "fghi", "jkl", "mnop", "qr", "stuv", "xyz"};
		list1.buildFromArray(arr1); // list1: {"abc", "de", "fghi", "jkl", "mnop", "qr", "stuv", "xyz"};
		
		Book b1 = new Book("Gone Girl", "Gillian Flynn", 432);
		Book b2 = new Book("Divergent", "Veronica Roth", 487);
		Book b3 = new Book("Allegiant", "Veronica Roth", 526);
		Book b4 = new Book("Hunger Games", "Suzanne Collins", 374);
		Book b5 = new Book("Mockingjay", "Suzanne Collins", 390);
		Book b6 = new Book("Jade City", "Fonda Lee", 560);
		Book[] books = {b1, b2, b3, b4, b5, b6};
		list2.buildFromArray(books);
		
		int   result = 0;
		int expected = 0;
		
		result = list0.countBefore(5);
		expected = 0;
		displayResults(result==expected, "countBefore, empty list");
		
		result = list1.countBefore(new String("abc"));
		expected = 0;
		displayResults(result==expected, "countBefore, first element (0 before)");
		
		result = list1.countBefore(new String("de"));
		expected = 1;
		displayResults(result==expected, "countBefore, second element (1 before)");
		
		result = list1.countBefore(new String("mnop"));
		expected = 4;
		displayResults(result==expected, "countBefore, middle element");
		
		result = list1.countBefore(new String("xyz"));
		expected = 7;
		displayResults(result==expected, "countBefore, last element");
		
		result = list2.countBefore(b1);
		expected = 0;
		displayResults(result==expected, "countBefore, first element (0 before)");

		result = list2.countBefore(b2);
		expected = 1;
		displayResults(result==expected, "countBefore, second element (1 before)");

		result = list2.countBefore(b3);
		expected = 2;
		displayResults(result==expected, "countBefore, middle element");
		
		result = list2.countBefore(b6);
		expected = 5;
		displayResults(result==expected, "countBefore, last element");
		
		System.out.println();
	}
	
	public static void testCountAfter() {
		System.out.println("Testing countAfter");
		SinglyLinkedList<Integer> list0 = new SinglyLinkedList<Integer>();
		SinglyLinkedList<String> list1 = new SinglyLinkedList<String>();
		SinglyLinkedList<Book> list2 = new SinglyLinkedList<Book>();
		
		String[] arr1 = {"abc", "de", "fghi", "jkl", "mnop", "qr", "stuv", "xyz"};
		list1.buildFromArray(arr1); // list1: {"abc", "de", "fghi", "jkl", "mnop", "qr", "stuv", "xyz"};
		
		Book b1 = new Book("Gone Girl", "Gillian Flynn", 432);
		Book b2 = new Book("Divergent", "Veronica Roth", 487);
		Book b3 = new Book("Allegiant", "Veronica Roth", 526);
		Book b4 = new Book("Hunger Games", "Suzanne Collins", 374);
		Book b5 = new Book("Mockingjay", "Suzanne Collins", 390);
		Book b6 = new Book("Jade City", "Fonda Lee", 560);
		Book[] books = {b1, b2, b3, b4, b5, b6};
		list2.buildFromArray(books);
		
		int   result = 0;
		int expected = 0;
		
		result = list0.countAfter(5);
		expected = 0;
		displayResults(result==expected, "countAfter, empty list");
		
		result = list1.countAfter(new String("abc"));
		expected = 7;
		displayResults(result==expected, "countAfter, first element (0 before)");
		
		result = list1.countAfter(new String("de"));
		expected = 6;
		displayResults(result==expected, "countAfter, second element (1 before)");
		
		result = list1.countAfter(new String("mnop"));
		expected = 3;
		displayResults(result==expected, "countAfter, middle element");
		
		result = list1.countAfter(new String("xyz"));
		expected = 0;
		displayResults(result==expected, "countAfter, last element");
		
		result = list2.countAfter(b1);
		expected = 5;
		displayResults(result==expected, "countAfter, first element (0 before)");

		result = list2.countAfter(b2);
		expected = 4;
		displayResults(result==expected, "countAfter, second element (1 before)");

		result = list2.countAfter(b3);
		expected = 3;
		displayResults(result==expected, "countAfter, middle element");
		
		result = list2.countAfter(b6);
		expected = 0;
		displayResults(result==expected, "countAfter, last element");
		
		System.out.println();
	}
	
	public static void testCountBetween() {
		System.out.println("Testing countBetween");
		SinglyLinkedList<String> list1 = new SinglyLinkedList<String>();
		SinglyLinkedList<Book> list2 = new SinglyLinkedList<Book>();
		
		String[] arr1 = {"abc", "de", "fghi", "jkl", "mnop", "qr", "stuv", "xyz"};
		list1.buildFromArray(arr1); // list1: {"abc", "de", "fghi", "jkl", "mnop", "qr", "stuv", "xyz"};
		
		Book b1 = new Book("Gone Girl", "Gillian Flynn", 432);
		Book b2 = new Book("Divergent", "Veronica Roth", 487);
		Book b3 = new Book("Allegiant", "Veronica Roth", 526);
		Book b4 = new Book("Hunger Games", "Suzanne Collins", 374);
		Book b5 = new Book("Mockingjay", "Suzanne Collins", 390);
		Book b6 = new Book("Jade City", "Fonda Lee", 560);
		Book[] books = {b1, b2, b3, b4, b5, b6};
		list2.buildFromArray(books);
		
		int result = 0;
		int expected = 0;
		
		result = list1.countBetween("abc", "de");
		expected = 0; //nothing in between "abc" and "de"
		System.out.println(result);
		displayResults(result==expected, "countBetween('abc', 'de') in list1");
		
		result = list1.countBetween("abc", "fghi");
		expected = 1; //"de" is in between
		System.out.println(result);
		displayResults(result==expected, "countBetween('abc', 'fghi') in list1");
		
		result = list1.countBetween("abc", "xyz");
		expected = 6; //"de", "fghi", "jkl", "mnop", "qr", "stuv" are in between
		System.out.println(result);
		displayResults(result==expected, "countBetween('abc', 'xyz') in list1");
		
		result = list1.countBetween("de", "fghi");
		expected = 0; //nothing in between "de" and "fghi"
		System.out.println(result);
		displayResults(result==expected, "countBetween('de', 'fghi') in list1");
		
		result = list1.countBetween("de", "qr");
		expected = 3; //"fghi", "jkl", "mnop" are in between
		System.out.println(result);
		displayResults(result==expected, "countBetween('de', 'qr') in list1");
		
		result = list2.countBetween(b1, b2);
		expected = 0; //nothing in between b1 and b2
		System.out.println(result);
		displayResults(result==expected, "countBetween b1 and b2 in list2");
		
		result = list2.countBetween(b1, b6);
		expected = 4; //4 in between b1 and b6
		System.out.println(result);
		displayResults(result==expected, "countBetween b1 and b6 in list2");
		
		result = list2.countBetween(b1, b2);
		expected = 0; //nothing in between b1 and b2
		System.out.println(result);
		displayResults(result==expected, "countBetween b1 and b2 in list2");
		
		result = list2.countBetween(b2, b5);
		expected = 2; //2 in between b2 and b5
		System.out.println(result);
		displayResults(result==expected, "countBetween b2 and b5 in list2");
		
		System.out.println();
	}
	
	public static void testCountListMatches() {
		System.out.println("Testing countMatches (List version)");
		List<Book> list0 = new SinglyLinkedList<Book>();
		List<Integer> list1 = new SinglyLinkedList<Integer>();
		List<String> list2 = new SinglyLinkedList<String>();
		Integer[] arr1 = {9, 3, 1, 2, 3, 3, 4, 2, 1, 2, 1, 1};
		String[]  arr2 = {"test", "the", "test", "to", "test", "the", "test"};
		
		((SinglyLinkedList<Integer>)list1).buildFromArray(arr1); // list1: {9, 3, 1, 2, 3, 3, 4, 2, 1, 2, 1, 1}
		((SinglyLinkedList<String>)list2).buildFromArray(arr2); // list2: {"test", "the", "test", "to", "test", "the", "test"};
		Book b1 = new Book("Gone Girl", "Gillian Flynn", 432);
		
		int result = 0;
		int expected = 0;
		
		result = A5Exercises.countMatches(list0, b1);
		expected = 0;
		displayResults(result==expected, "countMatches in empty list");
		
		result = A5Exercises.countMatches(list1, 5);
		expected = 0;
		displayResults(result==expected, "countMatches, no matches found");
		
		result = A5Exercises.countMatches(list1, 4);
		expected = 1;
		displayResults(result==expected, "countMatches, one found");
		
		result = A5Exercises.countMatches(list1, 2);
		expected = 3;
		displayResults(result==expected, "countMatches, found in middle");
		
		result = A5Exercises.countMatches(list1, 2);
		expected = 3;
		displayResults(result==expected, "countMatches, found in a row");
		
		result = A5Exercises.countMatches(list1, 9);
		expected = 1;
		displayResults(result==expected, "countMatches, found at beginning");
		
		result = A5Exercises.countMatches(list1, 1);
		expected = 4;
		displayResults(result==expected, "countMatches, found at end");
		
		
		result = A5Exercises.countMatches(list2, new String("the"));
		expected = 2;
		displayResults(result==expected, "countMatches, found with other object type");
		
		result = A5Exercises.countMatches(list2, new String("test"));
		expected = 4;
		displayResults(result==expected, "countMatches, found at both ends");
		
		System.out.println();
	}
	
	public static void testContains() {
		System.out.println("Testing contains");
		List<Book> list0 = new SinglyLinkedList<Book>();
		List<Integer> list1 = new SinglyLinkedList<Integer>();
		List<String> list2 = new SinglyLinkedList<String>();
		Integer[] arr1 = {9, 3, 1, 2, 3, 3, 4, 2, 1, 2, 1, 1, 5};
		String[]  arr2 = {"test", "the", "test", "to", "test", "the", "test"};
		
		((SinglyLinkedList<Integer>)list1).buildFromArray(arr1); // list1: {9, 3, 1, 2, 3, 3, 4, 2, 1, 2, 1, 1, 5}
		((SinglyLinkedList<String>)list2).buildFromArray(arr2); // list2: {"test", "the", "test", "to", "test", "the", "test"};
		
		List<Book> list3 = new SinglyLinkedList<Book>();
		Book b1 = new Book("Gone Girl", "Gillian Flynn", 432);
		Book b2 = new Book("Divergent", "Veronica Roth", 487);
		Book b3 = new Book("Allegiant", "Veronica Roth", 526);
		Book b4 = new Book("Hunger Games", "Suzanne Collins", 374);
		Book b5 = new Book("Mockingjay", "Suzanne Collins", 390);
		Book b6 = new Book("Jade City", "Fonda Lee", 560);
		Book[] books = {b1, b2, b3, b4};
		((SinglyLinkedList<Book>)list3).buildFromArray(books);
		
		boolean result = false;
		boolean expected = false;
		
		result = A5Exercises.contains(list0, b1);
		expected = false;
		displayResults(result==expected, "contains in empty list");
		
		result = A5Exercises.contains(list1, 9);
		expected = true;
		displayResults(result==expected, "list1 contains - first element");
		
		result = A5Exercises.contains(list1, 3);
		expected = true;
		displayResults(result==expected, "list1 contains - second element");
		
		result = A5Exercises.contains(list1, 4);
		expected = true;
		displayResults(result==expected, "list1 contains - middle element");
		
		result = A5Exercises.contains(list1, 5);
		expected = true;
		displayResults(result==expected, "list1 contains - last element");
		
		result = A5Exercises.contains(list1, 6);
		expected = false;
		displayResults(result==expected, "list1 contains - not found");
		
		result = A5Exercises.contains(list2, new String("test"));
		expected = true;
		displayResults(result==expected, "list2 contains - first element");
		
		result = A5Exercises.contains(list2, new String("to"));
		expected = true;
		displayResults(result==expected, "list2 contains - middle element");
		
		result = A5Exercises.contains(list2, new String("Anthony"));
		expected = false;
		displayResults(result==expected, "list2 contains - not found");
		
		result = A5Exercises.contains(list3, b1);
		expected = true;
		displayResults(result==expected, "list3 contains - first element");
		
		result = A5Exercises.contains(list3, b3);
		expected = true;
		displayResults(result==expected, "list3 contains - middle element");
		
		result = A5Exercises.contains(list3, b4);
		expected = true;
		displayResults(result==expected, "list3 contains - last element");
		
		result = A5Exercises.contains(list3, b5);
		expected = false;
		displayResults(result==expected, "list3 contains - not found");
		
		System.out.println();
	}
	
	public static void testAveragePages() {
		System.out.println("Testing averagePages");
		List<Book> list0 = new SinglyLinkedList<Book>();
		List<Book> list1 = new SinglyLinkedList<Book>();
		List<Book> list2 = new SinglyLinkedList<Book>();
		List<Book> list3 = new SinglyLinkedList<Book>();
		Book b1 = new Book("Gone Girl", "Gillian Flynn", 432);
		Book b2 = new Book("Divergent", "Veronica Roth", 487);
		Book b3 = new Book("Allegiant", "Veronica Roth", 526);
		Book b4 = new Book("Hunger Games", "Suzanne Collins", 374);
		Book b5 = new Book("Mockingjay", "Suzanne Collins", 390);
		Book b6 = new Book("Jade City", "Fonda Lee", 560);
		Book[] books1 = {b1};
		Book[] books2 = {b1, b2, b3, b4};
		Book[] books3 = {b1, b2, b3, b4, b5, b6};
		((SinglyLinkedList<Book>)list1).buildFromArray(books1);
		((SinglyLinkedList<Book>)list2).buildFromArray(books2);
		((SinglyLinkedList<Book>)list3).buildFromArray(books3);
		
		double result = 0.0;
		double expected = 0.0;
		
		result = A5Exercises.averagePages(list0);
		expected = 0.0;
		displayResults(Math.abs(result-expected)<0.1, "averagePages of empty list");
		
		result = A5Exercises.averagePages(list1);
		expected = 432.0;
		displayResults(Math.abs(result-expected)<0.1, "averagePages in list1");
		
		result = A5Exercises.averagePages(list2);
		expected = (432+487+526+374)/4.0;
		displayResults(Math.abs(result-expected)<0.1, "averagePages in list2");
		
		result = A5Exercises.averagePages(list3);
		expected = (432+487+526+374+390+560)/6.0;
		displayResults(Math.abs(result-expected)<0.1, "averagePages in list3");
		
		System.out.println();
	}
	
	public static void testLongestChainOfBigBooks() {
		System.out.println("Testing longestChainOfBigBooks");
		
		List<Book> list0 = new SinglyLinkedList<Book>();
		List<Book> list1 = new SinglyLinkedList<Book>();
		
		Book b1 = new Book("Hunger Games", "Suzanne Collins", 374);
		Book b2 = new Book("Divergent", "Veronica Roth", 487);
		Book b3 = new Book("Allegiant", "Veronica Roth", 526);
		Book b4 = new Book("Gone Girl", "Gillian Flynn", 432);
		Book b5 = new Book("Mockingjay", "Suzanne Collins", 390);
		Book b6 = new Book("Jade City", "Fonda Lee", 560);
		Book[] books1 = {b1, b2, b3, b4, b5, b6};
		((SinglyLinkedList<Book>)list1).buildFromArray(books1);
		
		int result = 0;
		int expected = 0;
		
		result = A5Exercises.longestChainOfBigBooks(list0, 100);
		expected = 0;
		System.out.println(result);
		displayResults(result==expected, "longestChain on empty list");
		
		result = A5Exercises.longestChainOfBigBooks(list1, 480);
		expected = 2;
		System.out.println(result);
		displayResults(result==expected, "longestChain of 480+ pages in list1");
		
		result = A5Exercises.longestChainOfBigBooks(list1, 400);
		expected = 3;
		System.out.println(result);
		displayResults(result==expected, "longestChain of 400+ pages in list1");
		
		result = A5Exercises.longestChainOfBigBooks(list1, 380);
		expected = 5;
		System.out.println(result);
		displayResults(result==expected, "longestChain of 380+ pages in list1");
		
		result = A5Exercises.longestChainOfBigBooks(list1, 350);
		expected = 6;
		System.out.println(result);
		displayResults(result==expected, "longestChain of 350+ pages in list1");
		
		result = A5Exercises.longestChainOfBigBooks(list1, 500);
		expected = 1;
		System.out.println(result);
		displayResults(result==expected, "longestChain of 500+ pages in list1");
		
		result = A5Exercises.longestChainOfBigBooks(list1, 550);
		expected = 1;
		System.out.println(result);
		displayResults(result==expected, "longestChain of 550+ pages in list1");
		
		System.out.println();
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