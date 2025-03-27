/*
* StudentNode.java
* A StudentNode class
*/
public class StudentNode {

	public StudentNode next;
	private Student    data;

	public StudentNode(Student data) {
		this.data = data;
		this.next = null;
	}

	public StudentNode(Student data, StudentNode next) {
		this.data = data;
		this.next = next;
	}

	/*
	 * Purpose: returns the value of this node's next
	 * Parameters: none
	 * Returns: StudentNode - the next
	 */
	public StudentNode getNext() {
		return this.next;
	}

	/*
	 * Purpose: sets this node's next to parameter value
	 * Parameters: StudentNode - next
	 * Returns: nothing
	 */
	public void setNext(StudentNode next) {
		this.next = next;
	}

	/*
	 * Purpose: returns the value of this node's data
	 * Parameters: none
	 * Returns: Student - the data
	 */
	public Student getData() {
		return this.data;
	}

	/*
	 * Purpose: sets this node's data to parameter value
	 * Parameters: Student - data
	 * Returns: nothing
	 */
	public void setData(Student data) {
		this.data = data;
	}
}
