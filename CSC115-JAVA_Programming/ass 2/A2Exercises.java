public class A2Exercises {

	/*
	 * Purpose: get the number of students in the given course  	
	 *          with a grade above the given grade threshold
	 * Parameters: UvicCourse course
	 *             int gradeThreshold 
	 * Returns: int - the number of enrolled students with 	
	 *                a grade above gradeThreshold
	 */
	public static int countAbove(UvicCourse course, int gradeThreshold) {
		int count = 0;
		Student[] classlists = course.getClassList();
		
		for (int i = 0; i < classlists.length; i++){
			if (classlists[i].getGrade() > gradeThreshold){
				count += 1;
			}
		}
		
		return count; 
		
	}
	
	/*
	 * Purpose: get the name of the class with the highest average
	 * Parameters: UvicCourse[] courses - the array of courses
	 * Returns: String - the name of the course with the highest average
	 * Precondition: the array has at least one course in it
	 * NOTE: if there are two are more courses with the same class average 
	 *       which is the highest value of all courses in the array,
	 *       the name of course that comes first in the array is returned
	 */
	public static String highestAverage(UvicCourse[] courses) {
		double maxGrade = courses[0].averageGrade ();

		for (int i = 1; i < courses.length ; i++) {
			if (maxGrade < courses[i].averageGrade ()){
				maxGrade = courses[i].averageGrade ();
			}
		}

		String[] highestCourseNames = new String[courses.length];
		int j  = 0;
		for (int i = 0; i < courses.length; i++) {
			UvicCourse course = courses[i];
			if (maxGrade == course.averageGrade ()) {
				highestCourseNames[j++] = course.getName ();
			}
		}

		
		return highestCourseNames[0];
	}
	
	/*
	 * Purpose: get the average grade of the student with given sid
	 *          across all courses the student is enrolled in
	 * Parameters: UvicCourse[] courses - an array of courses
	 *             String sid - the sid of the student for which 
	 *                          to calculate the average grade for
	 * Returns: double - the average grade of the student with given sid
	 *                   -1 if the student is not in any of the courses	 
	 *
	 * Note: the student is not necessarily enrolled in the courses
	 *       found in the given array
	 */
	public static double studentAverage(UvicCourse[] courses, String sid) {
		double averageGrade = 0;
		int countOfClasses = 0;

		for (int i = 0; i < courses.length; i++) {
			UvicCourse course = courses[i];
			int gradeInCourse = course.getGrade (sid);
			if (gradeInCourse != -1) {
				averageGrade += gradeInCourse;
				countOfClasses++;
			}
		}

		return averageGrade == 0 ? 0 : averageGrade / countOfClasses;

	}
	
}