public class StudentLinkedList implements StudentList {

	StudentNode head;
	int count;

	public StudentLinkedList() {
		head = null;
		count = 0;
	}

	public void add(Student s) {
		// TODO: complete
		
		StudentNode n = new StudentNode(s);
		
		if (head != null) {
			
			StudentNode cur = head;
			while (cur.next != null) {
				
				cur = cur.next;
				
			}
			
			cur.next = n;
			
		} else {
			
			head = n;
		}
		
		
		count += 1;
		
		
	}

	public int size() {
		// TODO: complete
		
		return count;
		
	}

	public void removeFront() {
		// TODO: complete
		
		if (head != null) {
			
			head = head.next;
			count -= 1;
			
		}
		
		
	}

	public boolean contains(Student s) {
		// TODO: complete
		
		
		for (StudentNode cur = head; cur != null; cur = cur.next) {
			
			Student sid = cur.getData();
			
			
			
			if (sid.getSID().equals (s.getSID())){
				
				return true;
				
			}
			
		}
	
		
		return false;
	}
	
	/*
	 * Purpose: returns a String reprensentation of elements
	 *      in this list separated by newlines
	 * Parameters: none
	 * Returns: String - the representation
	 */
	public String toString() {
		// DO NOT CHANGE THIS METHOD - it is correct
		// Changing it will result in your code getting
		// a score of 0 from the autograder for this lab
		
		String s = "";
		StudentNode tmp = head;

		while(tmp != null) {
			s  += tmp.getData() + "\n";
			tmp = tmp.next;
		}

		return s;
	}
}
