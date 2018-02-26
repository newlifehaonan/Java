/*
 * Author : Haonan(Harry) Chen
 * CSC 403
 */
package HW1;

import algs13.Queue;

/**
 * Complete the methods marked TODO.
 * You must not change the declaration of any method.
 */

/**
 *  The LinkedListST class represents an (unordered) symbol table of
 *  generic key-value pairs.  It supports put, get, and delete methods.
 */
public class LinkedListST<Key extends Comparable<Key>, Value extends Comparable<Value>> {
	private int N;           // number of key-value pairs
    private Node first;      // the linked list of key-value pairs

    // a helper linked list data type
    private class Node {
        private Key key;
        private Value val;
        private Node next;

        public Node(Key key, Value val, Node next)  {
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
    }

    /**
     * Initializes an empty symbol table.
     */
    public LinkedListST() {
    }

    /**
     * Returns the value associated with the given key in this symbol table.
     */
    public Value get(Key key) {
        if (key == null) throw new NullPointerException("argument to get() is null"); 
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.val;
        }
        return null;
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old 
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is null.
     */
    public void put(Key key, Value val) {
        if (key == null) throw new NullPointerException("first argument to put() is null"); 
        if (val == null) {
            delete(key);
            return;
        }

        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
    }

    /**
     * Removes the specified key and its associated value from this symbol table     
     * (if the key is in this symbol table).    
     */
    public void delete(Key key) {
        if (key == null) throw new NullPointerException("argument to delete() is null"); 
        first = delete(first, key);
    }

    // delete key in linked list beginning at Node x
    // warning: function call stack too large if table is large
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        if (key.equals(x.key)) {
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    /**
     * size returns the number of key-value pairs in the symbol table.
     * it returns 0 if the symbol table is empty.
     */
    public int size() {
    		return size(first);
    }
    private int size (Node x) {
    		if(x == null)return 0;
    		return size(x.next) +1;
    }
    /**
     * secondMaxKey returns the second maximum key in the symbol table.
     * it returns null if the symbol table is empty or if it has only one key.
     *  See if you can write it with only one loop
     */
    public Key secondMaxKey () {
    		if(first == null || first.next==null) return null;
    		else {
    			Key FirstMax =first.key, SecondMax=first.next.key;
    			for(Node x = first.next; x!= null; x=x.next) {
    				if(x.key.compareTo(FirstMax)>0) {
    					SecondMax = FirstMax;
    					FirstMax = x.key;
    				}
    				else {
    					if(x.key.compareTo(SecondMax)>0) SecondMax = x.key;
    					else								continue;
    				}
    			}
    			return SecondMax;
    		}
    }


    /**
     * rank returns the number of keys in this symbol table that is less than the given key.
     * your implementation should be recursive. 
     */
    public int rank(Key key) {
    		return rank (first,key);
    }
    
    private int rank (Node x, Key key) {
    		if(x ==null) return 0;
    		int cmp = x.key.compareTo(key);
    		if(cmp>=0) return rank(x.next,key);
    		else		   return rank(x.next,key) +1;
    }

    /**
     * floor returns the largest key in the symbol table that is less than or equal to the given key.
     * it returns null if there is no such key.
     */
    public Key floor (Key key) {
    		Key floorvalue = first.key;
    		if(first == null)return null;
    		for(Node x = first; x!=null;x=x.next) {
    			
    			int cmp =x.key.compareTo(key);
    			if(cmp<0) {
    				if(x.key.compareTo(floorvalue)>0) {
    					floorvalue = x.key;
    				}
    			}
    			else {
    				floorvalue = key;
    			}
    		}
    		if(get(floorvalue)==null) return null;
    		return floorvalue;
    }

    /**
     * inverse returns the inverse of this symbol table.
     * if the symbol table contains duplicate values, you can use any of the keys for the inverse
     */
    public LinkedListST<Value, Key> inverse () {
    		LinkedListST<Value, Key> tmp = new LinkedListST<Value, Key>();
    		for(Node x = first;x!=null;x=x.next) {
    			tmp.put(x.val, x.key);
    		}
    		return tmp;
    }
    
    public Iterable<Key>  keys() {
    	Queue<Key> theKeys = new Queue<Key>();
    	for ( Node temp = first; temp != null; temp=temp.next) {
    		theKeys.enqueue(temp.key);
    	}
    	return theKeys;
    }
}