public class QueueRefBased implements Queue {

	private QueueNode front;
	private QueueNode back;
	// no numElements for this implementation

	public QueueRefBased() {
		front = null;
		back = null;
	}

	public int size() {
		int count = 0;
		for(QueueNode cur = front; cur != null; cur = cur.next){
			
			count++;
			
		}
	
		return count;			
	}

	public boolean isEmpty() {
		if (size() == 0){
			
			return true;
			
		} else {
			
			return false;
			
		}
	}

	public void enqueue (int value) {
		
		
		QueueNode n = new QueueNode(value);
		
		if(front != null){
			
			n.next = front;
			front = n;
			
		} else {
			
			back = n;
			front = n;
			
		}
		
		
		
		
		
		
		
	}

	public int dequeue() {
		
		
		
		int temp = front.getValue();
		
		
		
		front = front.next;
		
		
		return temp;
		
	}

	public int peek()  {
		
		return front.getValue();
	}

	public void makeEmpty() {
		
		
		front = null;
		back = null;

	}
}
