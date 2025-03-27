/*
* QueueNode.java
*/
public class QueueNode {
	int data;
	QueueNode next;

	public QueueNode(int value) {
		this.data = value;
		this.next = null;
	}

	public int getValue() {
		return this.data;
	}

	public QueueNode getNext() {
		return this.next;
	}

	public void setNext(QueueNode next) {
		this.next = next;
	}

	public String toString() {
		return "" + data;
	}
}
