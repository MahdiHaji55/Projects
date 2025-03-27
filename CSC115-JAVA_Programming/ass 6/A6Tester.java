public class A6Tester {	
	private static int testPassCount = 0;
	private static int testCount = 0;	
	
	public static void main(String[] args) {
		listTests();
		disneyTests();
		
		System.out.println("Passed " + testPassCount + " / " + testCount + " tests");
	}
	
	public static void listTests() {
		System.out.println("\nTesting linked list implementation");
		
		LinkedList<Integer> list1 = new LinkedList<Integer>();
		LinkedList<Integer> list2 = new LinkedList<Integer>();
		LinkedList<Integer> list3 = new LinkedList<Integer>();
		LinkedList<Integer> list4 = new LinkedList<Integer>();
		
		String expected = "";
		String result = "";
		
		displayResults(list1.size()==0, "size of empty list");
		
		list1.addFront(2);
		list2.addFront(2);
		displayResults(list1.size()==1, "size after inserting to front");
		
		list3.addBack(3);
		list4.addBack(3);
		displayResults(list3.size()==1, "size after inserting to back");
		
		list1.addFront(1);
		result = list1.toString();
		expected = "{1 2}";
		displayResults(result.equals(expected), "elements in list correct");
		
		list2.addBack(3);
		result = list2.toString();
		expected = "{2 3}";
		displayResults(result.equals(expected), "elements in list correct");
	
		list3.addFront(2);
		result = list3.toString();
		expected = "{2 3}";
		displayResults(result.equals(expected), "elements in list correct");
		
		list4.addBack(4);
		result = list4.toString();
		expected = "{3 4}";
		displayResults(result.equals(expected), "elements in list correct");
		
		try {
			list1.insertAt(0, 8);
			displayResults(list1.size()==3, "size after third insert");
			result = list1.toString();
			expected = "{8 1 2}";
			displayResults(result.equals(expected), "contents after third insert");
		} catch (InvalidPositionException e) {
			System.out.println("exception thrown");
			displayResults(false, "size after third insert");
			displayResults(false, "contents after third insert");
		}
		
		try {
			list2.insertAt(1, 8);
			displayResults(list2.size()==3, "size after third insert");
			result = list2.toString();
			expected = "{2 8 3}";
			displayResults(result.equals(expected), "contents after third insert");
		} catch (InvalidPositionException e) {
			System.out.println("exception thrown");
			displayResults(false, "size after third insert");
			displayResults(false, "contents after third insert");
		}
		
		try {
			list3.insertAt(2, 8);
			displayResults(list3.size()==3, "size after third insert");
			result = list3.toString();
			expected = "{2 3 8}";
			displayResults(result.equals(expected), "contents after third insert");
		} catch (InvalidPositionException e) {
			System.out.println("exception thrown");
			displayResults(false, "size after third insert");
			displayResults(false, "contents after third insert");
		}
		
		try {
			list4.insertAt(3, 8);
			displayResults(false, "size after third insert");
			displayResults(false, "contents after third insert");
		} catch (InvalidPositionException e) {
			System.out.println("exception thrown");
			displayResults(list4.size()==2, "size after third insert");
			result = list4.toString();
			expected = "{3 4}";
			displayResults(result.equals(expected), "contents after third insert");
		}
		
		
		Integer removed = null;
		
		try {
			removed = list1.removeFront();
			removed = list1.removeFront();
			removed = list1.removeFront();
			displayResults(list1.size()==0, "size after removeFronts");
			displayResults(removed.equals(2), "element returned after removeFronts");
		} catch (ListEmptyException e) {
			System.out.println("exception thrown");
			displayResults(false, "size after removeFronts");
			displayResults(false, "element returned after removeFronts");
		}

		try {
			removed = list2.removeBack();
			removed = list2.removeBack();
			removed = list2.removeBack();
			displayResults(list2.size()==0, "size after removeBacks");
			displayResults(removed.equals(2), "element returned after removeBacks");
		} catch (ListEmptyException e) {
			System.out.println("exception thrown");
			displayResults(false, "size after removeBacks");
			displayResults(false, "element returned after removeBacks");
		}

		try {
			removed = list3.removeBack();
			removed = list3.removeFront();
			removed = list3.removeBack();
			displayResults(list3.size()==0, "size after removals");
			displayResults(removed.equals(3), "element returned after removals");
		} catch (ListEmptyException e) {
			System.out.println("exception thrown");
			displayResults(false, "size after removals");
			displayResults(false, "element returned after removals");
		}
		
		try {
			removed = list4.removeFront();
			removed = list4.removeBack();
			removed = list4.removeFront();
			displayResults(false, "size after removals");
			displayResults(false, "element returned after removals");
		} catch (ListEmptyException e) {
			System.out.println("exception thrown");
			displayResults(list4.size()==0, "size after removals");
			displayResults(removed.equals(4), "element returned after removals");
		}
		
		try {
			list1.addBack(9);
			displayResults(list1.size()==1, "size after insert at removeAll");
			result = list1.toString();
			expected = "{9}";
			displayResults(expected.equals(result), "contents after insert at removeAll");
		} catch (Exception e) {
			System.out.println("exception thrown");
			displayResults(false, "size after insert at removeAll");
			displayResults(false, "contents after insert at removeAll");
		}
		
		try {
			list2.addBack(9);
			displayResults(list2.size()==1, "size after insert at removeAll");
			result = list2.toString();
			expected = "{9}";
			displayResults(expected.equals(result), "contents after insert at removeAll");
		} catch (Exception e) {
			System.out.println("exception thrown");
			displayResults(false, "size after insert at removeAll");
			displayResults(false, "contents after insert at removeAll");
		}
		
		try {
			list3.addFront(9);
			displayResults(list3.size()==1, "size after insert at removeAll");
			result = list3.toString();
			expected = "{9}";
			displayResults(expected.equals(result), "contents after insert at removeAll");
		} catch (Exception e) {
			System.out.println("exception thrown");
			displayResults(false, "size after insert at removeAll");
			displayResults(false, "contents after insert at removeAll");
		}

		try {
			list4.addFront(9);
			displayResults(list4.size()==1, "size after insert at removeAll");
			result = list4.toString();
			expected = "{9}";
			displayResults(expected.equals(result), "contents after insert at removeAll");
		} catch (Exception e) {
			System.out.println("exception thrown");
			displayResults(false, "size after insert at removeAll");
			displayResults(false, "contents after insert at removeAll");
		}	
		
		try {
			list1.removeFront();
			list1.removeFront();
			displayResults(false, "correct exception thrown when removing from empty list");
		} catch (ListEmptyException e) {
			System.out.println("exception thrown");
			displayResults(true, "correct exception thrown when removing from empty list");
		} catch (Exception e) {
			System.out.println("exception thrown");
			displayResults(false, "correct exception thrown when removing from empty list");
		}
		
		try {
			list2.removeBack();
			list2.removeBack();
			displayResults(false, "correct exception thrown when removing from empty list");
		} catch (ListEmptyException e) {
			System.out.println("exception thrown");
			displayResults(true, "correct exception thrown when removing from empty list");
		} catch (Exception e) {
			System.out.println("exception thrown");
			displayResults(false, "correct exception thrown when removing from empty list");
		}
		
		try {
			list3.removeBack();
			list3.removeFront();
			displayResults(false, "correct exception thrown when removing from empty list");
		} catch (ListEmptyException e) {
			System.out.println("exception thrown");
			displayResults(true, "correct exception thrown when removing from empty list");
		} catch (Exception e) {
			System.out.println("exception thrown");
			displayResults(false, "correct exception thrown when removing from empty list");
		}
		
		try {
			list4.removeFront();
			list4.removeBack();
			displayResults(false, "correct exception thrown when removing from empty list");
		} catch (ListEmptyException e) {
			System.out.println("exception thrown");
			displayResults(true, "correct exception thrown when removing from empty list");
		} catch (Exception e) {
			System.out.println("exception thrown");
			displayResults(false, "correct exception thrown when removing from empty list");
		}
	
	}
	
	public static void disneyTests() {
		System.out.println("\nTesting Disney Lineup implementation");
		
		Person p1 = new Person("Ali", 23);
		Person p2 = new Person("Juan", 23);
		Person p3 = new Person("Sam", 23);
		Person p4 = new Person("Yiyi", 23);
		Person p5 = new Person("Lee", 23);
		Person p6 = new Person("Mika", 23);
		Person p7 = new Person("Ola", 23);
		
		DisneylandLine d = new DisneylandLine();
		Person result = null;
		Person expected = null;
		boolean inserted = false;
		int numRemoved = 0;
		
		displayResults(d.peopleInLine()==0, "people in empty line");
		
		try {
			result = d.handleOne();
			displayResults(result == null, "handled person in empty line");
		} catch (Exception e) {
			System.out.println("exception thrown");
			displayResults(false, "handled person in empty line");
		}
		
		d.enterLine(p1);
		displayResults(d.peopleInLine()==1, "people in line 1");
		d.enterLine(p2);
		d.enterLine(p3);
		d.enterLine(p4);
		displayResults(d.peopleInLine()==4, "people in line 2");
		
		
		try {
			result = d.handleOne();
			expected = p1;
			if (result == null) {
				displayResults(false, "handled first person in line 1");
			} else {
				displayResults(result.equals(expected), "handled first person in line 1");
			}
		} catch (Exception e) {
			System.out.println("exception thrown");
			displayResults(false, "handled first person in line 1");
		}
		
		displayResults(d.peopleInLine()==3, "people in line 3");
		d.enterLine(p5);
		d.enterLine(p6);
		displayResults(d.peopleInLine()==5, "people in line 4");
		
		try {
			inserted = d.premiumEntry(p7, 2);
			displayResults(inserted==true, "inserted into specific pos");
		} catch (Exception e) {
			displayResults(false, "inserted into specific pos");
		}
		
		try {
			result = d.handleOne();
			expected = p2;
			if (result == null) {
				displayResults(false, "handled first person in line 2");
			} else {
				displayResults(result.equals(expected), "handled first person in line 2");
			}
		} catch (Exception e) {
			System.out.println("exception thrown");
			displayResults(false, "handled first person in line 2");
		}

		try {
			result = d.handleOne();
			expected = p3;
			if (result == null) {
				displayResults(false, "handled first person in line 3");
			} else {
				displayResults(result.equals(expected), "handled first person in line 3");
			}
		} catch (Exception e) {
			System.out.println("exception thrown");
			displayResults(false, "handled first person in line 3");
		}

		try {
			result = d.handleOne();
			expected = p7;
			if (result == null) {
				displayResults(false, "handled first person in line 4");
			} else {
				displayResults(result.equals(expected), "handled first person in line 4");
			}
		} catch (Exception e) {
			System.out.println("exception thrown");
			displayResults(false, "handled first person in line 4");
		}
		
		displayResults(d.peopleInLine()==3, "people in line 4");		
		d.enterLine(p7);
		d.enterLine(p1);
		d.enterLine(p2);
		d.enterLine(p3);		
		displayResults(d.peopleInLine()==7, "people in line 5");
		
		try {
			numRemoved = d.handleMultiple(5);
			displayResults(numRemoved==5, "group of 5 enter disneyland");
		} catch (Exception e) {
			displayResults(false, "group of 5 enter disneyland");
		}
		displayResults(d.peopleInLine()==2, "people in line 6");
		
		try {
			numRemoved = d.handleMultiple(5); // buy 5 tickets, but only 2 in line
			displayResults(numRemoved==2, "group of 2 enter disneyland");
		} catch (Exception e) {
			displayResults(false, "group of 2 enter disneyland");
		}
		displayResults(d.peopleInLine()==0, "people in line 7");
		
		try {
			numRemoved = d.handleMultiple(5); // buy 5 tickets, but no one in line
			displayResults(numRemoved==0, "group of 0 enter disneyland");
		} catch (Exception e) {
			displayResults(false, "group of 0 enter disneyland");
		}
		displayResults(d.peopleInLine()==0, "people in line 8");		
		
		d.enterLine(p1);
		displayResults(d.peopleInLine()==1, "people in line 9");		

		try {
			inserted = d.premiumEntry(p2, 0); 
			displayResults(inserted==true, "inserted into specific pos");
		} catch (Exception e) {
			displayResults(false, "inserted into specific pos");
		}
		displayResults(d.peopleInLine()==2, "people in line 10");	

		try {
			inserted = d.premiumEntry(p3, 2); 
			displayResults(inserted==true, "inserted into specific pos");
		} catch (Exception e) {
			displayResults(false, "inserted into specific pos");
		}
		displayResults(d.peopleInLine()==3, "people in line 11");	

		try {
			inserted = d.premiumEntry(p4, -2); 
			displayResults(inserted==false, "inserted into specific pos");
		} catch (Exception e) {
			displayResults(false, "inserted into specific pos");
		}
		displayResults(d.peopleInLine()==3, "people in line 12");	

		try {
			inserted = d.premiumEntry(p5, 4); 
			displayResults(inserted==false, "inserted into specific pos");
		} catch (Exception e) {
			displayResults(false, "inserted into specific pos");
		}
		displayResults(d.peopleInLine()==3, "people in line 13");		

		try {
			result = d.handleOne();
			expected = p2;
			if (result == null) {
				displayResults(false, "handled first person in line 5");
			} else {
				displayResults(result.equals(expected), "handled first person in line 5");
			}
		} catch (Exception e) {
			System.out.println("exception thrown");
			displayResults(false, "handled first person in line 5");
		}

		try {
			result = d.handleOne();
			expected = p1;
			if (result == null) {
				displayResults(false, "handled first person in line 6");
			} else {
				displayResults(result.equals(expected), "handled first person in line 6");
			}
		} catch (Exception e) {
			System.out.println("exception thrown");
			displayResults(false, "handled first person in line 6");
		}

		try {
			result = d.handleOne();
			expected = p3;
			if (result == null) {
				displayResults(false, "handled first person in line 7");
			} else {
				displayResults(result.equals(expected), "handled first person in line 7");
			}
		} catch (Exception e) {
			System.out.println("exception thrown");
			displayResults(false, "handled first person in line 7");
		}
		
		try {
			result = d.handleOne();
			displayResults(result == null, "handled first person in line 8");
		} catch (Exception e) {
			System.out.println("exception thrown");
			displayResults(false, "handled first person in line 8");
		}

	}
		

	public static void displayResults (boolean passed, String testName) {
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