public class LinkedPriorityQueue implements PriorityQueue {
	A7Node front;
	A7Node back;
	int	count;

	public LinkedPriorityQueue() {
		front = null;
		back = null;
		count = 0;
	}

 	public void insert (int key) {
		A7Node n = new A7Node(key);

		// add to front if empty
		if(front == null) {
			front = n;
			back = n;
			count++;
			return;
		}	
		
		// add to back if priority is lower than lowest in this Queue
		if(back.getData() < key) {
			append(n);	
			return;
		}

		// find first node in q that has lower priority than n
		A7Node cur = front;	 
		
		while(cur!=null && cur.getData() < key) {
			cur = cur.next;
		}
	
		// insert n into q before cur
		insertBefore(n, cur);
	}

	public int removeMin() {
		if (count == 0) {
			throw new HeapEmptyException();
		}
		
		int retval = front.getData();
		front = front.next;
		if(front != null) {
			front.prev = null;
		}
		count--;
		
		if (count == 0) {
			back = null;
		}
		
		return retval;
	}

	private void append (A7Node n) {
		n.prev = back;
		if(back == null) {
			front = n;
		} else {
			back.next = n;
		}
		
		back = n;
		count++;
	}
	
	private void insertBefore (A7Node n, A7Node after) {
		if(after == null) {
			append(n);
			return;
		}
		n.next = after;
		n.prev = after.prev;
		after.prev = n;
		if(n.prev == null) {
			front = n;
		} else {
			n.prev.next = n;
		}
		
		count++;
	}

	public boolean isEmpty() {
		return size() == 0;
	}
			
	public int size() {
		return count;
	}
	
	public String toString() {
	
		String s = "";
		A7Node cur = front;
		while(cur != null) {
			s += cur.getData() + " ";
			cur = cur.next;
		}
			
		return s;
	}
	
	
}

