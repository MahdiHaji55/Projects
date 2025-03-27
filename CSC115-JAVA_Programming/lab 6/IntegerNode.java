/*
 * IntegerNode.java
 *
 * An implementation of a node class for a singly-linked list of integers.
 *
 */

public class IntegerNode {
	IntegerNode	next;
	int	data;

	public IntegerNode() {
		next = null;
		data = 0;
	}

	public IntegerNode (Integer data) {
		this.data = data;
		next = null;
	}

	public IntegerNode (Integer data, IntegerNode next) {
		this.data = data;
		this.next = next;
	}

	public IntegerNode getNext()	{
		return next;
	}
	
	public void setNext (IntegerNode next) {
		this.next = next;
	}

	public int getData() {
		return data;
	}
	
	public void setData(Integer data) {
		this.data = data;
	}	
}

