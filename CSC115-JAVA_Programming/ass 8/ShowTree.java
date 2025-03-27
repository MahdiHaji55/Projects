public class ShowTree {

	public static void main (String[] args) {
		BinarySearchTree<String, String>	t1;
		t1 = new BinarySearchTree<String,String>();

		t1.insert("a", "data1"); 
		t1.insert("c", "data2"); 
		t1.insert("b", "data3"); 

		TreeView<String,String>	tv = new TreeView<String,String>(t1);
		tv.dotPrint();
	}
}