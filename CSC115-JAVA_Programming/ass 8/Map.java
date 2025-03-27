import java.util.*;

public interface Map<K extends Comparable<K>, V> {

	/*
	 * Purpose: determines whether an entry with the
	 *          the given key is stored in the map.
	 * Parameters: K key - the key to search for
	 * Returns: boolean - true if an entry with the 
	 *          given key is stored in this map
	 */
	boolean containsKey(K key);

	/*
	 * Purpose: gets the value of the entry with the 
	 *          given key stored in this map
	 * Parameters: K key - the key to search for
	 * Returns: V - the value of the entry with given key
	 * Throws: KeyNotFoundException - if no entry with 
	 *         the given key is stored in the map
	 */
	V get (K key) throws KeyNotFoundException;

	/*
	 * Purpose: creates and returns a list of Entry types
	 *          which contain the key/value pairs of
	 *	        every entry stored in the map.
	 * Parameters: None
	 * Returns: List<Entry<K,V>> - an instance of a List 
	 *          containing all the key/value pair entries
	 *          stored in this map.
	 */
	List<Entry<K,V>> entryList();

	/*
	 * Purpose: insert the key/value pair into the map.
	 *	        if the key already exists in the map, then
	 *          update the value associated with the key instead
	 * Parameters: K key, V value
	 * Returns: void - nothing
	 *
	 * Examples:
	 *	if m is {("hello", 5)} and m.put("joe",8") is called
	 *	then m is: {("hello", 5), ("joe",8)}
	 *
	 *	if m is {("hello", 5)} and m.put("hello", 99) is called
	 *	then m is {("hello", 99)}
	 *
	 *	NOTE: Maps do not provide ordering, so these examples
	 * 		have chosen an arbitrary ordering.  Your implementation
	 *		may store items in a different order than the examples.
	 */
	void put (K key, V value);

	/*
	 * Purpose: get the number of elements in this map
	 * Parameters: none
	 * Returns: int - the number of elements in the map
	 */
	int size();

	/*
	 * Purpose: remove all elements from the map.
	 * Parameters: none
	 * Returns: void - nothing
	 */
	void clear();
	
}