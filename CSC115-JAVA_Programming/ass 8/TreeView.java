/* 
 * TreeView.java
 * 
 * This helper class relies on package friendly 
 * elements of the BinarySearchTree class so you can
 * print trees in a special "dot" language
 * that allows you to see your tree drawn.  
 * 
 * Copy and paste the output from dotPrint to here:
 * 
 * http://sandbox.kidstrythisathome.com/erdos/
 * 
 * To see your tree drawn.
 *  */

public class TreeView <K extends Comparable<K> , V> 
{
	private BinarySearchTree<K,V> tree;
	private	int	nullcount;

	public TreeView(BinarySearchTree<K,V> t) {
		tree = t;
		nullcount = 0;
	}

	// This modified from 
	// http://eli.thegreenplace.net/2009/11/23/visualizing-binary-trees-with-graphviz
	//
	public void	dotPrint ()
	{

		System.out.println("digraph BST {");
		System.out.println("    node [fontname=\"Arial\"];");

		nullcount = 0;

		if (tree.root != null)
		{
			doDotPrint(tree.root);
		}
		else
		{
			System.out.println("");
		}
		System.out.println("}");
	}


	private void doDotPrint (BSTNode<K,V> n) {
		if (n.left != null) {
			System.out.println("    " + n + " -> " + n.left + ";");
			doDotPrint(n.left);
		}
		else {
			System.out.println("    null" + nullcount + "[shape=point];");
			System.out.println("    " + n + " -> null" + nullcount + ";");
			nullcount++;
		}

		if (n.right != null ) {
			System.out.println("    " + n + " -> " + n.right + ";");
			doDotPrint(n.right);
		}
		else
		{
			System.out.println("    null" + nullcount + "[shape=point];");
			System.out.println("    " + n + " -> null" + nullcount + ";");
			nullcount++;
		}	
	}
}
