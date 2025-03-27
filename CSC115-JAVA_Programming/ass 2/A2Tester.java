public class A2Tester {

	private static int testPassCount = 0;
	private static int testCount = 0;
	
	// acceptable error THRESHOLD for floating-point calculations
	private static final double THRESHOLD = 0.01;

	public static void main(String[] args) {

		// Uncomment and test one method at a time. 
		// Write additional tests as you see fit.

		/* Part 1: The UvicCourse class */
		testUvicCourseConstructor();
		testAverageGrade();
		testGetGrade();
		testUpdateGrade();		
		testAddStudent();

		
		/* Part 2: The A2Exercises class */
		testCountAbove();
		testHighestAverage();
		testStudentAverage();
		
		System.out.println("Passed " + testPassCount + " / " + testCount + " tests");
	}
	
	/*
	 * Purpose: determines if two arrays are equal
	 * Parameters: Student[] a1, a2 - the two arrays to compare
	 * Returns: boolean - true if equal, false otherwise
	 */
	public static boolean arraysEqual(Student[] a1, Student[] a2) {
		boolean same = false;
		if (a1.length == a2.length) {
			same = true;
			int i = 0;
			while (i < a1.length && same == true) {
				if (!a1[i].equals(a2[i])) {
					same = false;
				}
				i++;
			}			
		}
		return same;
	}
	
	public static void testUvicCourseConstructor() {
		System.out.println("\nTesting UvicCourse Constructor");
		
		Student s1 = new Student("v00123456", 84);
		Student s2 = new Student("v00555555", 71);
		Student s3a = new Student("v00998877", 65);
		Student s3b = new Student("v00998877", 74);
		Student s4 = new Student("v00224466", 92);
		
		Student[] arr1 = {s1, s2, s3a};
		Student[] arr2 = {s3b, s4};
					
		UvicCourse csc110 = new UvicCourse("CSC 110", arr1);
		UvicCourse math100 = new UvicCourse("MATH 100", arr2);
		
		String expected;
		
		
		expected = "CSC 110";
		displayResults(expected.equals(csc110.getName()), "csc110 constructor course name initialization");
		expected = "MATH 100";
		displayResults(expected.equals(math100.getName()), "math100 constructor course name initialization");
		
		// Uncomment the tests below once you have completed
		// the constuctor in the UvicCourse class.
		
		displayResults(arraysEqual(csc110.getClassList(),arr1), "csc110 constructor class list initialization");
		displayResults(arraysEqual(math100.getClassList(),arr2), "math100 constructor class list initialization");
		
	}
	
	
	public static void testAverageGrade() {
		System.out.println("\nTesting UvicCourse averageGrade method");
		
		Student s1 = new Student("v00123456", 84);
		Student s2 = new Student("v00555555", 71);
		Student s3a = new Student("v00998877", 67);
		Student s3b = new Student("v00998877", 74);
		Student s4 = new Student("v00224466", 92);
		
		Student[] arr0 = new Student[0];
		Student[] arr1 = {s1, s2, s3a};
		Student[] arr2 = {s3b, s4};
		
		UvicCourse empty = new UvicCourse("test course", arr0);
		UvicCourse csc110 = new UvicCourse("CSC 110", arr1);
		UvicCourse math100 = new UvicCourse("MATH 100", arr2);
		
		double result = 0.0;
		double expected = 0.0;
		
		result = empty.averageGrade();
		expected = 0.0;
		System.out.println(result); // for debugging
		displayResults(Math.abs(result-expected)<THRESHOLD, "average grade for empty test course");
		
		result = csc110.averageGrade();
		expected = 74.0;
		System.out.println(result); // for debugging
		displayResults(Math.abs(result-expected)<THRESHOLD, "average grade for csc110");
		
		result = math100.averageGrade();
		expected = 83.0;
		System.out.println(result); // for debugging
		displayResults(Math.abs(result-expected)<THRESHOLD, "average grade for math100");
		
		// TODO: add more tests until you are sure your
		// method handles all valid input scenarios
		
	}
	
	
	public static void testGetGrade() {
		System.out.println("\nTesting UvicCourse getGrade method");
		
		Student s1 = new Student("v00123456", 84);
		Student s2 = new Student("v00555555", 71);
		Student s3a = new Student("v00998877", 65);
		Student s3b = new Student("v00998877", 74);
		Student s4 = new Student("v00224466", 92);
		
		
		Student[] arr1 = {s1, s2, s3a};
		Student[] arr2 = {s3b, s4};
					
		UvicCourse csc110 = new UvicCourse("CSC 110", arr1);
		UvicCourse math100 = new UvicCourse("MATH 100", arr2);
		
		int result = 0;
		int expected = 0;
		
		String s1Sid = new String("v00123456");
		String s3Sid = new String("v00998877");
		String s4Sid = new String("v00224466");		
		
		result = csc110.getGrade(s1Sid);
		expected = 84;
		System.out.println(result); // for debugging
		displayResults(result==expected, "get grade of s1 in csc110");
		
		// TODO: add more tests until you are sure your
		// method handles all valid input scenarios
		
	}
	
	
	public static void testUpdateGrade() {
		System.out.println("\nTesting UvicCourse updateGrade method");
		
		Student s1 = new Student("v00123456", 84);
		Student s2 = new Student("v00555555", 71);
		Student s3a = new Student("v00998877", 65);
		Student s3b = new Student("v00998877", 74);
		Student s4 = new Student("v00224466", 92);
		
		Student[] arr1 = {s1, s2, s3a};
		Student[] arr2 = {s3b, s4};
					
		UvicCourse csc110 = new UvicCourse("CSC 110", arr1);
		UvicCourse math100 = new UvicCourse("MATH 100", arr2);
		
		int result = 0;
		int expected = 0;
		
		result = s1.getGrade();
		expected = 84;
		System.out.println(result); // for debugging
		displayResults(result==expected, "s1 original grade");
		
		csc110.updateGrade("v00123456", 86);
		result = s1.getGrade();
		expected = 86;
		System.out.println(result); // for debugging
		displayResults(result==expected, "s1 updated grade");
		
		result = s3a.getGrade();
		expected = 65;
		System.out.println(result); // for debugging
		displayResults(result==expected, "s3a original grade");
		
		csc110.updateGrade("v00998877", 64);
		result = s3a.getGrade();
		expected = 64;
		System.out.println(result); // for debugging
		displayResults(result==expected, "s3a updated grade");
		
		// TODO: add more tests until you are sure your
		// method handles all valid input scenarios
	
	}
	
	
	public static void testAddStudent() {
		System.out.println("\nTesting UvicCourse addStudent method");
		
		Student s1 = new Student("v00123456", 84);
		Student s2 = new Student("v00555555", 71);
		Student s3 = new Student("v00998877", 67);
		Student s4 = new Student("v00224466", 92);
		
		Student[] empty = new Student[0];
		Student[] arr1 = {s1};
		Student[] arr2 = {s1, s2};
		Student[] arr3 = {s1, s2, s3};
		Student[] arr4 = {s1, s2, s3, s4};
		
		UvicCourse csc115 = new UvicCourse("CSC 115", empty);
		
		Student[] classList = csc115.getClassList();
		displayResults(classList.equals(empty), "csc 115 class list initially empty");
		
		csc115.addStudent(s1);
		classList = csc115.getClassList();
		displayResults(arraysEqual(classList,arr1), "csc 115 class list after inserting 1 student");
		
		// TODO: add more tests until you are sure your
		// method handles all valid input scenarios
		
	}
	
	
	public static void testCountAbove() {
		System.out.println("\nTesting countAbove method");
		
		Student s1 = new Student("v00123456", 84);
		Student s2 = new Student("v00555555", 71);
		Student s3 = new Student("v00998877", 67);
		Student s4 = new Student("v00224466", 92);
		
		Student[] arr0 = new Student[0];
		Student[] arr1 = {s1, s2, s3, s4};
		UvicCourse empty = new UvicCourse("test course", arr0);
		UvicCourse csc115 = new UvicCourse("CSC 115", arr1);
		
		int result = 0;
		int expected = 0;

		result = A2Exercises.countAbove(empty, 0);
		expected = 0;
		//System.out.println(result); // for debugging
		displayResults(result==expected, "test course grades above 0");
		
		result = A2Exercises.countAbove(csc115, 92);
		expected = 0;
		//System.out.println(result); // for debugging
		displayResults(result==expected, "csc115 grades above 92");

		result = A2Exercises.countAbove(csc115, 91);
		expected = 1;
		//System.out.println(result); // for debugging
		displayResults(result==expected, "csc115 grades above 91");

		// TODO: add more tests until you are sure your
		// method handles all valid input scenarios
		
	}
	
	public static void testHighestAverage() {
		System.out.println("\nTesting highestAverage method");
		
		Student s1a = new Student("v00123456", 60);
		Student s1b = new Student("v00123456", 70);
		Student s1c = new Student("v00123456", 80);
		Student s2a = new Student("v00555555", 70);
		Student s2b = new Student("v00555555", 80);
		Student s3 = new Student("v00998877", 80);
		Student s4 = new Student("v00224466", 90);
		Student s5 = new Student("v00910019", 70);
		Student s6 = new Student("v00123321", 50);
		
		Student[] arr1 = {s1a, s2a, s3}; // 70 average
		Student[] arr2 = {s1b, s4}; // 80 average 
		Student[] arr3 = {s1c}; // 80 average 
		Student[] arr4 = {s5, s6}; // 60 average
		
		UvicCourse csc110 = new UvicCourse("CSC 110", arr1);
		UvicCourse csc111 = new UvicCourse("CSC 111", arr2);
		UvicCourse csc115 = new UvicCourse("CSC 115", arr3);
		UvicCourse csc116 = new UvicCourse("CSC 116", arr4);
		
		UvicCourse[] courseArray1 = {csc110, csc116};
		UvicCourse[] courseArray2 = {csc110, csc115};
		UvicCourse[] courseArray3 = {csc110, csc111, csc115, csc116};
		UvicCourse[] courseArray4 = {csc110, csc115, csc116, csc111};
		
		String result = "";
		String expected = "";
		
		result = A2Exercises.highestAverage(courseArray2);
		expected = "CSC 115";
		//System.out.println(result); // for debugging
		displayResults(result.equals(expected), "highestAverage in courseArray2");
		
		// TODO: add more tests until you are sure your
		// method handles all valid input scenarios

	}
	
	
	public static void testStudentAverage() {
		System.out.println("\nTesting studentAverage method");
		
		Student s1a = new Student("v00123456", 60);
		Student s1b = new Student("v00123456", 70);
		Student s1c = new Student("v00123456", 80);
		Student s2a = new Student("v00555555", 70);
		Student s2b = new Student("v00555555", 80);
		Student s3 = new Student("v00998877", 80);
		Student s4 = new Student("v00224466", 90);
		Student s5 = new Student("v00910019", 70);
		Student s6 = new Student("v00123321", 50);
		
		Student[] arr1 = {s1a, s2a, s3}; 
		Student[] arr2 = {s1b, s2b, s4};  
		Student[] arr3 = {s1c, s5};  

		UvicCourse csc110 = new UvicCourse("CSC 110", arr1);
		UvicCourse csc111 = new UvicCourse("CSC 111", arr2);
		UvicCourse csc115 = new UvicCourse("CSC 115", arr3);
		
		UvicCourse[] courseArray = {csc110, csc111, csc115};
		
		double result = 0.0;
		double expected = 0.0;
		
		result = A2Exercises.studentAverage(courseArray, "v00123456");
		expected = 70.0;
		//System.out.println(result); // for debugging
		displayResults(Math.abs(result-expected)<THRESHOLD, "v00123456 average grade");
		
		result = A2Exercises.studentAverage(courseArray, "v00555555");
		expected = 75.0;
		//System.out.println(result); // for debugging
		displayResults(Math.abs(result-expected)<THRESHOLD, "v00555555 average grade");
		
		// TODO: add more tests until you are sure your
		// method handles all valid input scenarios
		
	}
	
	
	public static void displayResults (boolean passed, String testName) {
		/* There is some magic going on here getting the line number
		* Borrowed from:
		* http://blog.taragana.com/index.php/archive/core-java-how-to-get-java-source-code-line-number-file-name-in-code/
		*/

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
