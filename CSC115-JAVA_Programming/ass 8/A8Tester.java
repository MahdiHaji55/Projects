/* 
 * A8Tester.java
 */
import java.util.*;

public class A8Tester {

	private static int testPassCount = 0;
	private static int testCount = 0;

	static int tree1_to_add = 15;
	static String tree1_keys[] = { "blue","dog","icecream","hockey","house","car","cry","apple","baseball","apple","school","red","why","dog","street"};
	static int tree1_values[] = { 18,17,32,41,28,18,18,9,7,41,40,37,8,9,10};
	static int tree1_height = 5;
	static int tree1_size = 13;
	static String    tree1_keys_preorder[] = {"blue","apple","baseball","dog","car","cry","icecream","hockey","house","school","red","why","street"};
	static int     tree1_values_preorder[] = {18,41,7,9,18,18,32,41,28,40,37,8,10};
	static String    tree1_keys_postorder[] = {"baseball","apple","cry","car","house","hockey","red","street","why","school","icecream","dog","blue"};
	static int     tree1_values_postorder[] = {7,41,18,18,28,41,37,10,8,40,32,9,18};
	static String    tree1_keys_inorder[] = {"apple","baseball","blue","car","cry","dog","hockey","house","icecream","red","school","street","why"};
	static int     tree1_values_inorder[] = {41,7,18,18,18,9,41,28,32,37,40,10,8};
	static String   tree1_keys_levelorder[] = {"blue","apple","dog","baseball","car","icecream","cry","hockey","school","house","red","why","street"};
	static int     tree1_values_levelorder[] = {18,41,9,7,18,32,18,41,40,28,37,8,10};

	static int tree2_to_add = 16;
	static String tree2_keys[] = { "blue","baseball","field","field","what","street","sing","bird","yellow","pitch","dog","jump","bike","shop","school","apple"};
	static int tree2_values[] = { 41,21,39,23,38,3,12,19,6,5,14,33,39,11,23,42};
	static  int tree2_height = 7;
	static  int tree2_size = 15;
	static String   tree2_keys_preorder[] = {"blue","baseball","apple","bird","bike","field","dog","what","street","sing","pitch","jump","shop","school","yellow"};
	static int     tree2_values_preorder[] = {41,21,42,19,39,23,14,38,3,12,5,33,11,23,6};
	static String   tree2_keys_postorder[] = {"apple","bike","bird","baseball","dog","jump","school","shop","pitch","sing","street","yellow","what","field","blue"};
	static int     tree2_values_postorder[] = {42,39,19,21,14,33,23,11,5,12,3,6,38,23,41};
	static String   tree2_keys_inorder[] = {"apple","baseball","bike","bird","blue","dog","field","jump","pitch","school","shop","sing","street","what","yellow"};
	static int     tree2_values_inorder[] = {42,21,39,19,41,14,23,33,5,23,11,12,3,38,6};
	static String   tree2_keys_levelorder[] = {"blue","baseball","field","apple","bird","dog","what","bike","street","yellow","sing","pitch","jump","shop","school"};
	static int     tree2_values_levelorder[] = {41,21,23,42,19,14,38,39,3,6,12,5,33,11,23};

	static int tree3_to_add = 5;
	static String tree3_keys[] = { "court","globe","school","pitch","hockey"};
	static int tree3_values[] = { 33,35,9,32,0};
	static int tree3_height = 4;
	static int tree3_size = 5;
	static String  tree3_keys_preorder[] = {"court","globe","school","pitch","hockey"};
	static int     tree3_values_preorder[] = {33,35,9,32,0};
	static String  tree3_keys_postorder[] = {"hockey","pitch","school","globe","court"};
	static int     tree3_values_postorder[] = {0,32,9,35,33};
	static String  tree3_keys_inorder[] = {"court","globe","hockey","pitch","school"};
	static int     tree3_values_inorder[] = {33,35,0,32,9};
	static String  tree3_keys_levelorder[] = {"court","globe","school","pitch","hockey"};
	static int     tree3_values_levelorder[] = {33,35,9,32,0};

	static int tree4_to_add = 14;
	static String tree4_keys[] = { "run","red","bird","bannana","blue","orange","street","icecream","yellow","hockey","football","bird","football","football"};
	static int tree4_values[] = { 29,11,6,28,13,31,6,6,40,25,1,30,5,0};
	static int tree4_height = 7;
	static int tree4_size = 11;
	static String tree4_keys_preorder[] = {"run","red","bird","bannana","blue","orange","icecream","hockey","football","street","yellow"};
	static int     tree4_values_preorder[] = {29,11,30,28,13,31,6,25,0,6,40};
	static String  tree4_keys_postorder[] = {"bannana","football","hockey","icecream","orange","blue","bird","red","yellow","street","run"};
	static int     tree4_values_postorder[] = {28,0,25,6,31,13,30,11,40,6,29};
	static String   tree4_keys_inorder[] = {"bannana","bird","blue","football","hockey","icecream","orange","red","run","street","yellow"};
	static int     tree4_values_inorder[] = {28,30,13,0,25,6,31,11,29,6,40};
	static String  tree4_keys_levelorder[] = {"run","red","street","bird","yellow","bannana","blue","orange","icecream","hockey","football"};
	static int     tree4_values_levelorder[] = {29,11,6,30,40,28,13,31,6,25,0};


	public static void main (String[] args)	{
		testCount = 0;
		testPassCount = 0;

		try {
			test_insert_size_height();
			test_insert_find();

			tree1_test();
			 tree2_test();
			tree3_test();
			tree4_test();

			map1_test();
			map2_test();
		}
		catch (Exception e) {
			System.out.println("Unhandled exception in tester: " + e);
		}
		
		System.out.println("Passed "+testPassCount+ "/" +testCount+" tests");
	}


	public static void add_key_values (BinarySearchTree<String, Integer> t, String keys[], int[] values, int count){
		for (int i = 0; i < count; i++)
			t.insert(keys[i], values[i]);
		}

	public static void compare_lists (String[] key_expected, int[] value_expected, int count, List<Entry<String, Integer> > l) {
		displayResults( count == l.size(), "comparing lists, size");
		Iterator<Entry<String,Integer> > i = l.iterator();
		int pos = 0;
		boolean failed = false;

		while (!failed && i.hasNext()) {
			Entry<String,Integer> e = i.next();

			if (!e.getKey().equals(key_expected[pos]) || !e.getValue().equals(value_expected[pos])) {
				// System.out.println(e + ":" + key_expected[pos] + "," + value_expected[pos]);
				failed = true;
				break;
			}
			pos++;
		}
		displayResults(!failed, "end of comparing lists");
	}

	static void tree1_test() {
		System.out.println("**** begin insert traversals tree 1");
		BinarySearchTree<String, Integer> t = new BinarySearchTree<String, Integer> ();
		add_key_values(t,tree1_keys, tree1_values, tree1_to_add);

		displayResults(t.height() == tree1_height, "height tree1");

		// TreeView tv = new TreeView<String,Integer>(t);
		// tv.dotPrint();

		/* If you are failing these tests, try uncommenting the TreeView code
		* above then copying and pasting the resulting output into the web page:
		*
		*  http://www.webgraphviz.com/
		*
		* This will allow you to see what your tree looks like and you can
		* compare it to the tree in the assignment PDF.
		*/

		List <Entry<String,Integer> > l = t.entryList();
		compare_lists (tree1_keys_levelorder, tree1_values_levelorder, tree1_size, l);

		System.out.println("l after level order: " + l);
		System.out.println("l's keys should be: " + Arrays.toString(tree1_keys_levelorder));
		System.out.println("l's values should be: " + Arrays.toString(tree1_values_levelorder));

		l = t.entryList(BinarySearchTree.BST_PREORDER);
		compare_lists (tree1_keys_preorder, tree1_values_preorder, tree1_size, l);



		l = t.entryList(BinarySearchTree.BST_POSTORDER);
		compare_lists (tree1_keys_postorder, tree1_values_postorder, tree1_size, l);

		l = t.entryList(BinarySearchTree.BST_INORDER);
		compare_lists (tree1_keys_inorder, tree1_values_inorder, tree1_size, l);

		System.out.println("****** end insert traversals tree 1\n");

	}


	public static void tree2_test() {
		System.out.println("**** begin insert traversals tree 2");

		BinarySearchTree<String, Integer> t = new BinarySearchTree<String, Integer> ();
		add_key_values(t,tree2_keys, tree2_values, tree2_to_add);

		// TreeView tv = new TreeView<String,Integer>(t);
		// tv.dotPrint();

		/* If you are failing these tests, try uncommenting the TreeView code
		* above then copying and pasting the resulting output into the web page:
		*
		*  http://www.webgraphviz.com/
		*
		* This will allow you to see what your tree looks like and you can
		* compare it to the tree in the assignment PDF.
		*/

		displayResults(t.height() == tree2_height, "height tree2");
		List <Entry<String,Integer> > l = t.entryList();
		compare_lists (tree2_keys_levelorder, tree2_values_levelorder, tree2_size, l);

		l = t.entryList(BinarySearchTree.BST_PREORDER);
		compare_lists (tree2_keys_preorder, tree2_values_preorder, tree2_size, l);

		l = t.entryList(BinarySearchTree.BST_POSTORDER);
		compare_lists (tree2_keys_postorder, tree2_values_postorder, tree2_size, l);

		l = t.entryList(BinarySearchTree.BST_INORDER);
		compare_lists (tree2_keys_inorder, tree2_values_inorder, tree2_size, l);

		System.out.println("****** end insert traversals tree 2\n");

	}

	static void tree3_test() {
		System.out.println("**** begin insert traversals tree 3");

		BinarySearchTree<String, Integer> t = new BinarySearchTree<String, Integer> ();
		add_key_values(t,tree3_keys, tree3_values, tree3_to_add);

		displayResults(t.height() == tree3_height, "height tree3");
		List <Entry<String,Integer> > l = t.entryList();

		// TreeView tv = new TreeView<String,Integer>(t);
		// tv.dotPrint();

		/* If you are failing these tests, try uncommenting the TreeView code
		* above then copying and pasting the resulting output into the web page:
		*
		*  http://www.webgraphviz.com/
		*
		* This will allow you to see what your tree looks like and you can
		* compare it to the tree in the assignment PDF.
		*/

		compare_lists (tree3_keys_levelorder, tree3_values_levelorder, tree3_size, l);

		l = t.entryList(BinarySearchTree.BST_PREORDER);
		compare_lists (tree3_keys_preorder, tree3_values_preorder, tree3_size, l);

		l = t.entryList(BinarySearchTree.BST_POSTORDER);
		compare_lists (tree3_keys_postorder, tree3_values_postorder, tree3_size, l);

		l = t.entryList(BinarySearchTree.BST_INORDER);
		compare_lists (tree3_keys_inorder, tree3_values_inorder, tree3_size, l);

		System.out.println("****** end insert traversals tree 3\n");

	}

	static void tree4_test() {
		System.out.println("**** begin insert traversals tree 4");

		BinarySearchTree<String, Integer> t = new BinarySearchTree<String, Integer> ();
		add_key_values(t,tree4_keys, tree4_values, tree4_to_add);

		displayResults(t.height() == tree4_height, "height tree4");

		// TreeView tv = new TreeView<String,Integer>(t);
		// tv.dotPrint();

		/* If you are failing these tests, try uncommenting the TreeView code
		* above then copying and pasting the resulting output into the web page:
		*
		*  http://www.webgraphviz.com/
		*
		* This will allow you to see what your tree looks like and you can
		* compare it to the tree in the assignment PDF.
		*/


		List <Entry<String,Integer> > l = t.entryList();
		compare_lists (tree4_keys_levelorder, tree4_values_levelorder, tree4_size, l);

		l = t.entryList(BinarySearchTree.BST_PREORDER);
		compare_lists (tree4_keys_preorder, tree4_values_preorder, tree4_size, l);

		l = t.entryList(BinarySearchTree.BST_POSTORDER);
		compare_lists (tree4_keys_postorder, tree4_values_postorder, tree4_size, l);

		l = t.entryList(BinarySearchTree.BST_INORDER);
		compare_lists (tree4_keys_inorder, tree4_values_inorder, tree4_size, l);

		System.out.println("****** end insert traversals tree 4\n");
	}

	static void test_insert_size_height() {
		System.out.println("**** begin insert size height");
		BinarySearchTree<String,String>	t = new BinarySearchTree<String,String>();

		displayResults(t.height() == -1, "height");

		displayResults(t.size() == 0 , "size");

		t.insert("bob", "bobdata");
		t.insert("abe", "abedata");
		t.insert("jane", "janedata");
		
		// TreeView tv = new TreeView<String,String>(t);
		// tv.dotPrint();

		/* If you are failing these tests, try uncommenting the TreeView code
		* above then copying and pasting the resulting output into the web page:
		*
		*  http://www.webgraphviz.com/
		*
		* This will allow you to see what your tree looks like and you can
		* compare it to the tree in the assignment PDF.
		*/

		displayResults(t.height() == 1, "height after insert");

		displayResults(t.size() == 3, "size after insert");
		System.out.println("****** end insert size height\n");
	}

	static void test_insert_find()	{
		System.out.println("**** begin insert find");
		BinarySearchTree<String,String>	t = new BinarySearchTree<String,String>();

		t.insert("bob", "bobdata");
		t.insert("abe", "abedata");
		t.insert("jane", "janedata");


		try {
			String s = t.find("bob");
			displayResults( s.equals("bobdata"), "find - should  be there");
		} catch (KeyNotFoundException e) {
			displayResults(false, "find - should = be there");
		}

		try {
			String s = t.find("sarah");
			displayResults(false, "find - should not be there");
		}
		catch (KeyNotFoundException e)    {
			displayResults(true, "find - should not be there");
		}

		t.insert("bob", "newbobdata");

		try {
			String s = t.find("bob");
			displayResults(s.equals("newbobdata"), "find -  be there");
		} catch (KeyNotFoundException e) {
			displayResults(false, "find - should not be there");
		}

		t.insert("wilma","datawilma");
		t.insert("candy","datacandy");


		try {
			String s = t.find("wilma");
			System.out.println(s );
			String q = t.find("candy");
			System.out.println(q);
			displayResults(s.equals("datawilma") && q.equals("datacandy"), "find - should be there");
		} catch (KeyNotFoundException e) {
			displayResults(false, "find - should be there");
		}
		
		System.out.println("****** end insert find\n");
	}

	public static void map1_test() {
		System.out.println("**** begin test map size put get");
		Map<String, Integer> d = A8Tester.<String,Integer>createMap();

		displayResults(d.size() == 0 ,"size");
		d.put("abc", 10);

		displayResults(d.size() == 1,"put + size");
		try {
			int val = d.get("abc");

			displayResults(val == 10,"put + get");
		} catch (KeyNotFoundException e) {
			displayResults(false, "put + get");
		}

		d.put("abc", 15);

		displayResults(d.size() == 1,"put update + size");

		try {
			int val = d.get("abc");

			displayResults(val == 15,"put + get");
		} catch (KeyNotFoundException e) {
			displayResults(false, "put + get");
		}

		try {
			int val = d.get("def");
			displayResults(false, "put + get should not be there");
			val = val + 1;
		} catch (KeyNotFoundException e) {
			displayResults(true, "put + get should not be there");
		}
		
		System.out.println("****** end map size put get\n");

	}

	public static void map2_test() {
		System.out.println("**** begin map stress test");
		Map<Integer, Integer> d = A8Tester.<Integer,Integer>createMap();

		for (int i = 0; i < 10000; i++) {
			d.put(i,i);
		}
		
		displayResults(d.size() == 10000, "put + size");

		boolean passed = true;
		for (int i = 0; i < 10000; i++) {
			try {
				d.get(i);
			} catch (KeyNotFoundException e) {
				passed = false;
				break;
			}
		}
		displayResults(passed, "get");
		System.out.println("****** end map stress test\n");
	}

	public static <K extends Comparable<K>,V> Map<K,V> createMap() {
		return new BSTMap<K,V>();
	}
		
	public static void displayResults (boolean passed, String testName) {
		/* There is some magic going on here getting the line number
		* Borrowed from:
		* http://blog.taragana.com/index.php/archive/core-java-how-to-get-java-source-code-line-number-file-name-in-code/
		*/
		
		testCount++;
		if (passed) {
			System.out.println ("Passed test: " + testCount);
			testPassCount++;
		} else {
			System.out.println ("Failed test: " + testName + " at line "
			+ Thread.currentThread().getStackTrace()[2].getLineNumber());
		}
	}
}
