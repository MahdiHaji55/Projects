public class BSTNode<K extends Comparable<K>, V> {
	K	key;
	V	value;

	BSTNode<K,V>	left;
	BSTNode<K,V>	right;

	public BSTNode() {
		this(null,null);
	}
	
	public BSTNode(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public String toString() {
		String s = "\"" + key + ":" + value + "\"";
		return s;
	}
}