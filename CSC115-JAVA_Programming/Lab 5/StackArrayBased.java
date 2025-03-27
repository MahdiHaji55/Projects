public class StackArrayBased implements Stack {
	
	private static final int INITIAL_SIZE = 4;
	private char[] data;
	private int top;

	public StackArrayBased() {
		data = new char[INITIAL_SIZE];
		top = 0;
	}

	public int size() {
		return top;
	}

	public boolean isEmpty() {
		return (top == 0);
	}

	public void push(char val) {
		if(top == data.length) {
			expandAndCopy();
		}
		data[top] = val;
		top++;
	}

	private void expandAndCopy() {
		char[] newData = new char[data.length*2];
		for(int i=0; i<data.length; i++) {
			newData[i] = data[i];
		}
		data = newData;
	}

	public char pop() {
		top--;
		char result = data[top];
		return result;
	}

	public char peek()  {
		return data[top-1]; 
	}

	public char top() {
		return data[top-1];
	}

	public void popAll() {
		top = 0;
	}

	public void makeEmpty() {
		top = 0;
	}

	public String toString() {
		String result = "{";
		String separator = "";

		for (int i=top-1; i>=0; i--) {
			result += separator + data[i];
			separator = ", ";
		}

		result += "}";
		return result;
	}
}

