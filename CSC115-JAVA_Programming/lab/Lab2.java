/*
 * Lab2.java
 *
 * A class of static methods that operate on Students
 *
 */
public class Lab2 {
    
	/*
	 * Purpose: determines which student has the higher grade
	 * Parameters: Student - s1, Student - s2
	 * Returns: Student - the Student with the higher grade,
	 *                    s1 if they have the same grade
	 * Precondition: s1 and s2 are not null
	 */
	public static Student getHigherGradeStudent(Student s1, Student s2) {
		// TODO: implement getHigherGradeStudent
		
		
		if (s1.getGrade() >= s2.getGrade()){
			return s1;
		} else{
			return s2;
		}

		
	}


	/*
	 * Purpose: determines whether the grade of Student s
	 *          is above the threshold
	 * Parameters: Student - s, int - threshold
	 * Returns: boolean - true if grade is above threshold, false otherwise
	 * Preconditions: s is not null
	 */
	// TODO: implement isGradeAbove
	public static boolean isGradeAbove(Student s1, int thresh){
		
		if (s1.getGrade() > thresh){
			return true;
		} else{
			return false;
		}
		
	}



	/*
	 * Purpose: creates an array sIDs of all Students in students
	 * Parameters: Student[] - students
	 * Returns: String[] - array of sIDs
	 * Preconditions: students is not null and contains no null elements
	 */
	// TODO: implement getClasslist
	public static String[] getClasslist(Student[] students){
		
		String [] SIDlist = new String[students.length];
		
		for(int i = 0; i < students.length; i++){
			
			SIDlist[i] = students[i].getSID();
			
		}
		
		return SIDlist;
		
	}


	/*
	 * Purpose: counts the number of Students in students
	 *          with grade above threshold
	 * Parameters: Student[] - students, int - threshold
	 * Returns: int - the number of students with a grade above threshold
	 * Preconditions: students is not null and contains no null elements
	 */
	// TODO: implement countAbove
	// HINT: you should be using the isGradeAbove method!
	public static int countAbove(Student[] students, int threshold){
		
		int count = 0;
		for (int i = 0; i < students.length; i++){
			if (students[i].getGrade() > threshold){
			count += 1;
			}
		}
		
		return count;
	}
	


	/*
	 * Purpose: calculates the average grade of Students in students,
	 *          does NOT include students with -1 grade in calculation
	 *          average is 0.0 if students is empty or all students have -1 grade
	 * Parameters: Student[] - students
	 * Returns: double - the average grade
	 * Preconditions: students is not null and contains no null elements
	 */
	// TODO: implement getClassAverage
	// HINT: you can use the isGradeAbove method again
	public static double getClassAverage(Student[] students){
		
		double average = 0.0;
		double grades = 0;
		int counts = 0;
		
		if (students.length == 0){
			return 0;
		} else {
			for (int i = 0; i < students.length; i++){
			if (students[i].getGrade() > -1){
				grades += students[i].getGrade();
				counts += 1;
				
				
			}
			
		    
		}
		}
		
		
		
		average = grades/counts;
		return average;	
		
	
	
	
	}
	
	


	/*
	 * Purpose: creates a new array 1 longer than students
	 *          and adds all students and s to the new array
	 * Parameters: Student[] - students, Student s
	 * Returns: Student[] - the new array
	 * Preconditions: students is not null and contains no null elements
	 *                Student s is not already contained within students
	 */
	// TODO: implement registerStudent
	
	public static Student[] registerStudent(Student[] students, Student s){
		
		
		Student newarray[] = new Student[students.length + 1];
		
		for (int i = 0; i < students.length; i++){
			newarray[i] = students[i];
			
		}
		
		newarray[newarray.length - 1] = s;
		
		return newarray;
		
	}
	

}
