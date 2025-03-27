public class BSTNode<K extends Comparable<K>, V> {
	public K key;
	public V value;
	public BSTNode<K,V>	left;
	public BSTNode<K,V>	right;

	public BSTNode(K key, V value) {
		this.key = key;
		this.value = value;
		left = null;
		right = null;
	}
}