/*
* HeapPriorityQueue.java
*
* An implementation of a minimum PriorityQueue using a heap.
* based on the implementation in "Data Structures and Algorithms
* in Java", by Goodrich and intamassia
*
* inthis implementation will throw a Runtime, HeapEmptyException
*	if the heap is empty and removeMin is called.
*
* inthis implementation will throw a Runtime, HeapFullException
*	if the heap is full and insert is called.
*
*/
public class HeapPriorityQueue implements PriorityQueue {

	protected final static int DEFAULT_SIZE = 10000;
	
	protected int[] storage;
	protected int currentSize;
	int rootIndex; // should be 0 or 1 depending on implementation

	/*
	 * Constructor that initializes the array to hold DEFAULT_SIZE elements
	 */
	public HeapPriorityQueue() {
		// TODO: implement this
		
		// if using a 1-based implementation, remember to allocate an 
		// extra space in the array since index 0 is not used. 
		
		storage = new int[DEFAULT_SIZE];
	
		currentSize = 0;
		
		
	}
	
	/*
	 * Constructor that initializes the array to hold size elements
	 */
	public HeapPriorityQueue(int size) {
		// TODO: implement this
		
		// if using a 1-based implementation, remember to allocate an 
		// extra space in the array since index 0 is not used. 
		
		storage = new int[size];
	
		currentSize = 0;
		
	}

	public void insert (int element) throws HeapFullException {
		// TODO: implement this
		
		// When inserting the first element, choose whether to use 
		// a 0-based on 1-based implementation. Whatever you choose,
		// make sure your implementation for the rest of the program
		// is consistent with this choice.		
		
		
		if(currentSize >= storage.length){
			
			throw new HeapFullException();
			
		} else {
			
			
			storage[currentSize] = element;
			currentSize++;
			bubbleUp(currentSize - 1);
			
			
			
			
			
			
		}
		
		
		
    }
	
	private void bubbleUp(int index) {
		// TODO: implement this
		
		if(index <= 0){
			
			return;
			
		}
		
		int partake = index / 2;
		if(partake >= 0){
			
			if(storage[index] < storage[partake]){
				
				int temp = storage[partake];
				storage[partake] = storage[index];
				storage[index] = temp;
				
				bubbleUp(partake);
				
			}
			
			
		}
		
		
		
		
	}
			
	public int removeMin() throws HeapEmptyException {
		// TODO: implement this
		
		if(isEmpty()){
			
			throw new HeapEmptyException();
			
			
		} else {
			
			int element = storage[0];
			currentSize--;
			storage[0] = storage[currentSize];
			storage[currentSize] = 0;
			
			bubbleDown(0);
			
			return element;
			
			
		}
		
		
		
		
		
		
		
	}
	
	
	private void bubbleDown(int index) {
		// TODO: implement this
		
		
		int left  = index * 2 + 1;
		int right = left + 1;
		
		
		if (index >= storage.length){
			
			return;
			
		}
		
		if (left >= storage.length){
			
			return;
			
		}
		
		
		if (right >= storage.length){
			
			return;
			
		}
		
		int leftChild  = storage[left];
		int rightChild = storage[right];
		
		if (leftChild == 0 && rightChild == 0) {
			
			return;
			
		}
		
		
		if (rightChild == 0 || leftChild < rightChild) {
			
			if (leftChild <= storage[index]) {
        
				storage[left] = storage[index];
				storage[index] = leftChild;
				bubbleDown(left);
			}
			
			
		} else if(leftChild == 0 || rightChild < leftChild) {
			
			
				if (rightChild <= storage[index]) {
        
					storage[right] = storage[index];
					storage[index] = rightChild;
					bubbleDown(right);
					
				}
			
			
		} else {
			
			storage[left] = storage[index];
			storage[index] = leftChild;
			bubbleDown(left);
			
			
			
		}
		
		
		
		
		
	}

	public boolean isEmpty(){
		// TODO: implement this
		
		if(currentSize == 0){
			
			return true;	
			
		} else {
			
			return false;
			
		}
		
	}
	
	public boolean isFull() {
		// TODO: implement this
		
		if (currentSize >= storage.length){
			
			return true;
			
		} else {
			
			return false;
			
		}
		
		
		
		
		
		
		
		
	}
	
	public int size () {
		// TODO: implement this
		
		return currentSize; // so it compiles
	}

	public String toString() {
		String s = "";
		String sep = "";
		if (rootIndex == 0) {
			for (int i = 0; i < currentSize; i++) {
				s += sep + storage[i];
				sep = " ";				
			}
		} else if (rootIndex == 1) {
			for(int i=1; i<=currentSize; i++) {
				s += sep + storage[i];
				sep = " ";
			}
		}
		return s;
	}
}
