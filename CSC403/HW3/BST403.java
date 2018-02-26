package HW3;
/**
 * Version 1.0
 * 
 * Your homework is to complete the methods marked ToDoX.
 * You must not change the declaration of any method.
 */

import algs13.Queue;

/**
 *  The B(inary)S(earch)T(ree) class represents a symbol table of
 *  generic key-value pairs.  It supports put, get, and delete methods.
 *  
 *  the book's recursive versions of get and put have been renamed 
 *  rGet  and rPut 
 *  to facilitate testing of your non-recursive versions
 *  
 */
public class BST403<Key extends Comparable<Key>, Value> {
	private Node root;             // root of BST

	private class Node {
		private Key key;           // sorted by key
		private Value val;         // associated data
		private Node left, right;  // left and right subtrees
		
		public Node(Key key, Value val) {
			this.key = key;
			this.val = val;
		}
		
		/**
		 * Appends the preorder string representation of the sub-tree to the given StringBuilder.
		 */
		public void buildString(StringBuilder s) {
			s.append(left == null ? '[' : '(');
			s.append(key + "," + val);
			s.append(right == null ? ']' : ')');
			if (left != null) left.buildString(s);
			if (right != null) right.buildString(s);
		}
	}
	/**
	 * Returns the preorder string representation of the BST.
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		root.buildString(s);
		return s.toString();
	}
	
	/**
	 * Initializes an empty symbol table.
	 */
	public BST403() {
	}
	
	/* 
	 * return the size of the tree
	 */
	public int size() {
		return size(root); // ToDo 0
	}
	
	private int size(Node x) {
		if(x == null) return 0;
		return size(x.left) + size(x.right) +1;
	}
	/**
	 * Returns the value associated with the given key.
	 * Returns null if the key is not in the table
	 * 
	 * ToDo 1   write a non-recursive implementation of get
	 * 
	 */
	public Value get(Key key) {
		Node x = root;
		while(x!= null) {
			int cmp = x.key.compareTo(key);
			if(cmp < 0) x = x.right;
			else if(cmp > 0) x = x.left;
			else
				return x.val;
		}
		return null;  //Todo 1
	}
	
	/**
	 * Inserts the specified key-value pair into the symbol table, overwriting the old 
	 * value with the new value if the symbol table already contains the specified key.
	 * 
	 * ToDo 2   write a non-recursive implementation of put
	 * 
	 * 
	 */
	public void put(Key key, Value val){
		Node newcome = new Node(key, val);
		if(root == null) {
			root = newcome;
			return;
		}
		Node parent = null;
		Node x =root;
		while(x != null) {
			parent =x;
			int cmp = x.key.compareTo(key);
			if(cmp < 0) x = x.right;
			else if(cmp > 0) x = x.left;
			else {
				x.val = val; 
				return;
			}
		}
		
		int cmp =key.compareTo(parent.key);
		if(cmp < 0) {
			parent.left = newcome;
		}
		else{
			parent.right =newcome;// ToDo 2
		}
	}
	
	
	/**
	 * deletes the key (&value) from the table if the key is present
	 * using the the dual of the Hibbard approach from the text. That is, 
	 * for the two-child scenario, delete the node by replacing it 
	 * with it's predecessor (instead of its successor)
	 * 
	 * ToDo 3:  implement a version of delete meeting the above spec
	 * 
	 */
	public void delete(Key key) {
		root = delete(root, key);  // ToDo 3
	}
	
	private Node Max(Node x) {
		if(x.right == null) return x;
		else return Max(x.right);
	}
	
	private Node deleteMax(Node x) {
		if(x.right == null)return x.left;
		x.right = deleteMax(x.right);
		return x;
	}
	
	private Node delete(Node x, Key key) {
		if(x == null) return null;
		int cmp = x.key.compareTo(key);
		if(cmp > 0) x.left = delete(x.left, key);
		else if(cmp < 0)x.right = delete(x.right,key);
		else {
			if(x.right == null) return x.left;
			if(x.left == null) return x.right;
			Node t = x;
			x = Max(t.left);
			x.left = deleteMax(t.left);
			x.right = t.right;
		}
		return x;
	}
	
	/*
	 * equals determines if two BST403s are exactly the same:
	 * same key-value pairs, same structure
	 * recursion might be a good choice
	 * 
	 * NOT ToDo , but maybe think about how you would do it.
	 * 
	*/
	
	public Iterable<Key> levelOrder() {
		Queue<Key> keys = new Queue<>();
		Queue<Node> queue = new Queue<>();
		queue.enqueue(root);
		while (!queue.isEmpty()) {
			Node x = queue.dequeue();
			if (x == null) continue;
			keys.enqueue(x.key);
			queue.enqueue(x.left);
			queue.enqueue(x.right);
		}
		return keys;
	}
	
	public boolean equals(BST403<Key,Value> x) {
		if (x == this) return true;
		if (x == null) return false;
		if (x.getClass() != this.getClass()) return false;
		BST403<Key,Value> that = (BST403<Key,Value>) x;
		if(this.size() != that.size()) return false; 
		Iterable<Key> thiskeys = this.levelOrder();
		Iterable<Key> xkeys = x.levelOrder();
		return thiskeys.equals(xkeys);
	}
	

	/**
	 * Returns the number of leaf nodes in the tree
	 * 
	 * ToDo 4
	 */
	public int numLeaves() {
		return numLeaves(root); // ToDo 4
	}
	
	private int numLeaves(Node x) {
		if(x == null)  return 0;
		if(x!=null&& x.left ==null && x.right ==null) return 1;
		if(x.left == null && x.right != null) {
			return numLeaves(x.right);
		}
		else if(x.right == null && x.left != null) {
			return numLeaves(x.left);
		}
		else
			return numLeaves(x.left) + numLeaves(x.right);
	}
	
	
	/**
	 * Returns the length of the shortest path from root to a null node.
	 * 
	 * ToDo 5
	 */
	public int lenShortestPathToNull() {
		return lenShorttestPathToNull(root); // ToDo 5
	}
	
	private int lenShorttestPathToNull(Node x) {
		if(x == null) return 0;
		if(x != null && x.left ==null && x.right ==null) return 0;
		if(x.left == null && x.right != null) {
			return lenShorttestPathToNull(x.right);
		}
		else if(x.right == null && x.left != null) {
			return lenShorttestPathToNull(x.left);
		}
		else
			return Math.min(lenShorttestPathToNull(x.left), lenShorttestPathToNull(x.right)) +1;
	}
	
	/**
	 * Verifies that 'this' is a valid binary search tree
	 * useful to verify that your version of delete works correctly
	 * ToDo 6
	 */
	public boolean isValidBST() {
		
		return isValidBST(root); //ToDo 6
	}
	
	private boolean isValidBST(Node x) {
		//condition 1: root is null
		if(x == null) return true;
		//condition 2: the node is a leave node
		if(x.left == null && x.right == null) return true;
		//condition 3: the node only has right node
		if(x.left == null && x.right != null) {
			int cmpright = x.right.key.compareTo(x.key);
			if(cmpright<0) return false;
			else return isValidBST(x.right);
		}
		//condition 4: the node only has left node
		else if(x.right == null & x.left != null) {
			int cmpleft = x.left.key.compareTo(x.key);
			if(cmpleft<0) return false;
			else return isValidBST(x.left);
		}
		//condition 5: the node has both left and right node
		else return isValidBST(x.right)&&isValidBST(x.left);
	}
	
	/*****************************************************
	 * 
	 * Utility functions 
	 */
	
	
	public Value rGet(Key key) {
		return rGet(root, key);
	}
	private Value rGet(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) return rGet(x.left, key);
		else if (cmp > 0) return rGet(x.right, key);
		else              return x.val;
	}
	
	public void rPut(Key key, Value val) {
		if (key == null) throw new NullPointerException("first argument to put() is null");
		root = rPut(root, key, val);
	}
	
	private Node rPut(Node x, Key key, Value val) {
		if (x == null) return new Node(key, val);
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) x.left  = rPut(x.left,  key, val);
		else if (cmp > 0) x.right = rPut(x.right, key, val);
		else              x.val   = val;
		return x;
	}

}