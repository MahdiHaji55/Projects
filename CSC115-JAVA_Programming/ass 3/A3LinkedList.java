// Name:Mahdi Haji Hosseini
// Student number: v00968642

public class A3LinkedList implements A3List {
	private A3Node head;
	private A3Node tail;
	private int length;
	
	public A3LinkedList() {
		head = null;
		tail = null;
		length = 0;
	}
	
	public void addFront(String s) {
		
		A3Node n = new A3Node(s);
		
		if (head != null){
			
			
			head.prev = n;
			n.next = head;
			head = n;
			
			
		
		
		} else {
			
			head = n;
			tail = n;
		}
		
		length += 1;

	}

	public void addBack(String s) {
		
		
		A3Node n = new A3Node(s);
		
		if (head != null){
			
			
			tail.next = n;
			n.prev = tail;
			tail = n;
			
			
		
		
		} else {
			
			head = n;
			tail = n;
		}
		
		length += 1;
		
		
	}
	
	public int size() {
		return length;
	}
	
	public boolean isEmpty() {
		return length==0;
	}
	
	public void removeFront() {
		
		if (length >= 2){
			
			head = head.next;
			head.prev = null;
			
			length -= 1;
			
			
		} else {
			
			head = null;
			tail = null;
			length = 0;
			
		}
		

	}
	
	public void removeBack() {
		
		if (length >= 2){
			
			tail = tail.prev;
			tail.next = null;
			
			length -= 1;
			
			
		} else {
			
			head = null;
			tail = null;
			length = 0;
			
		}
		
		

	}
	
	public void moveToBack(int pos) {
		
	
		
		if (pos >= 0 && pos <= length - 1){
			
			
			for (int i = 0; i < pos; i++){
				
				addBack(head.getData());
				removeFront();
				
			}
			
			String tempstring = head.getData();
			removeFront();
			
			for (int j = 0; j < length - pos  ; j++){
				
				addBack(head.getData());
				removeFront();
				
				
			}
				
			addBack(tempstring);
			
			
		}
		
		
		
		
	}
	

	public void insertInto(String s) {
		
		
		A3Node S = new A3Node(s);
		
		if (tail.comesBefore(S) == false){
			
			
	
			int pos = 0;
			for(A3Node cur = head; cur != null; cur = cur.next){
			
				if (cur.comesBefore(S) == true){
				
					moveToBack(0);
					pos +=1;

				} else {
				
					break;
				
				}
			
			}
		
			addFront(s);
		
			for(int i = 0; i < pos; i++){
			
		
				addFront(tail.getData());
				removeBack();
			
			
			
			}
		} else {
			
			addBack(s);
			
		}
		
		
	}
	
	/*
	 * Purpose: return a string representation of the list 
	 *          when traversed from front to back
	 * Parameters: none
	 * Returns: nothing
	 */
	public String frontToBack() {
		String result = "{";
		A3Node cur = head;
		while (cur != null) {
			result += cur.getData();
			cur = cur.next;
		}
		result += "}";
		return result;
	}
	
	/*
	 * Purpose: return a string representation of the list 
	 *          when traversed from back to front
	 * Parameters: none
	 * Returns: nothing
	 */
	public String backToFront() {
		String result = "{";
		A3Node cur = tail;
		while (cur != null) {
			result += cur.getData();
			cur = cur.prev;
		}
		result += "}";
		return result;
	}
}
	