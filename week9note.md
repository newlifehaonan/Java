# priority queue and heap sort
<hr />

## Definition of PQ
  Remove the high or low priority items decided by key while has the ability to insert new items with fixed priority.

## Implementation method of PQ
  generally speaking we want implement a method that would return the M highest item out of N sized array.

* modify resized array
  * unordered array

  * ordered array

* modify linked list

* Using new structure heap

  * Structure of heap (Use MaxHeap as example)

  1. The root of the heap is the item with largest key.

  2. A binary heap is a tree with each node has two child and the child should be no larger than its parents

  3. Using Array to represent complete binary tree. Leave the Index 0 to be null while put the key of root in Index 1 and put its child nodes from left to right in order to the list.

  4. Given a Key which index is `K`, then we know the index of its parent is `K/2`, and the left child of it is at index `2*K` and the right child of it is at index `2*K+1`

  * implementation of MaxPQ    
  1. In order to construct a MaxPQ, we need to compare the key of parents and child nodes to see whether they follow the Definition, so at first we need to implement `less()` and `exchange()` method

  2. There are two kinds of exchange, first is the low priority nodes turn to be high, in this case we need to `swim` it up to the right position; second, the high priority nodes turn to be low, in this case we need to `sind` it down to the right position.

## API of MaxPQ using heap
Application interface includes:
* Constructor

  * `MaxPQ(int M)`: create a PQ has capacity M.

  *  `MaxPQ(key[] A)`: create a PQ has the key from list A.

* Public method

  * `isEmpty()`: return whether the PQ is empty.

  * `size()`: return the size of PQ.

  * `max()`: return the item with highest priority.

  * `insert(key v)`: insert v into PQ.

  * `delMax()`: remove and return the highest priority key in PQ.

* private method to construct API

  * `exchange(int i, int j)`: exchange a[i] and a[j]

  * `less(int i, int j)`: return whether a[i] is less then a[j].

  * `swim(int v)`:move up a[v] to the correct position.

  * `sink(int v)`:move down a[v] to the correct position.

## Implementation code
  ```java
  public class MaxPQ<Key extends comparable<key>>{
    private Key[] a;
    private int N=0;

    private boolean less(int i, int j){
      return a[i].comareTo(a[j])<0;
    }

    private void exchange(int i, int j){
      Key temp = a[i];
      a[i] = a[j];
      a[j] = temp;
    }

    private void swim(int v){
      // when v =1, it already been the root, so terminate the loop//
      while(v>1 && less(v/2, v)){
        exchange(v/2,v);
        v = v/2;
      }
    }

    private void sink(int v){
      //since the size of PQ so far is N, index can't be larger than N//
      while(2*v < N){
        int j =2*v;
        //compare the two child node, swap v with the larger one//
        if(j < N && less(j,j+1)) j++;

        if(less(v,j)){
          exchange(v,j);
          v = j;
        }
      }
    }

    //construct MaxPQ with capacity M//
    public MaxPQ(int M){
      //The reason why it's M+1 is index 0 is null//
      this.a = new Key[M+1];
    }

    public boolean isEmpty(){
      return N==0;
    }

    public int size(){
      return N;
    }

    public Key max(){
      return a[1];
    }

    public void insert(Key v){
      N = N + 1;
      a[N] = v;
      swim(N);
    }

    public Key delMax(){
      Key max = a[1];
      exchange(1,N)
      N = N -1;
      //release the memory//
      a[N+1] = null;
      sink[1]
      return max;
    }
}
  ```

## Runtime Analysis for MaxPQ
* for Insert method     
  no more than `1+lg(N)` compare;
* for delMax method
  no more than `2lg(N)` compare;

## extension of complete binary tree
* Build different MaxPQ Constructor
  * `MaxPQ()`       

    create a empty pq
    ```java
    private int N;
    private Key[] a;
    private Comparator<Key> comparator;

    public MaxPQ() {this(1,null);}//what does this mean?//
    ```
  * `MaxPQ(int M)`      

    create a M capacity pq
    ```java
    private int N;
    private Key[] a;
    private Comparator<Key> comparator;

    public MaxPQ(int M) {
      this(M, null);
    }
    ```
  * `MaxPQ(int M, Comparator<Key> comparator)`       

    create a M capacity pq with a comparator of Key
    ```java
    private int N;
    private Key[] a;
    private Comparator<Key> comparator;

    public MaxPQ(int M, Comparator<Key> comparator){
      N = 0;
      this.comparator = comparator;
      a = new Key[M+1];
    }
    ```
  * `MaxPQ(comparator<Key> Comparator)`         

    create a pq with a comparator of Key
    ```java
    private int N;
    private Key[] a;
    private Comparator<Key> comparator;

    public MaxPQ(Comparator<Key> comparator) {
      this(1, comparator);
    }

    ```
  * `MaxPQ(Key[] a)`:

    create a pq with the key from array a
    ```java
    private int N;
    private Key[] a;
    private Comparator<Key> comparator;

    public MaxPQ(Key[] v){
      this(v.length, null);
      for(int i; i<v.length; i++){
        a[i+1] =v[i];
      }
      //why k should be v.length, in order to enter the loop of sink, k should be the half of array length//
      for (int k = v.length/2; k >= 1; k--){
  			sink(k);
      }
    }
    ```


* Mutiway heaps     

  Q: what if each node has three child, how do you construct the MaxPQ()?

  A: Given Key v, its parent is `V+1/3`, its child are `3*k-1`, `3*k`,`3*k+1`

* resized heaps

  Q: what if Constructor didn't specify the max capacity, in another word, Constructor MaxPQ does not have any argument.

  A: Solution is initialize the least capacity, and for each insert instruction , if its hit the bound , doubling the array size; for each delMax instruction, if N is less than size-1/4,  halving the array size just as we did in week3 on resizing stack!
  ```java
  public void resize(int capacity){
    if(N > capacity) throw new IllegalArgumentException ();
    Key[] temp = new Key[capacity];
    for(int i =1; i <= N; i++){
      temp[i] = a[i];
    }

    a = temp;
  }

  public void insert(Key v){
    N = N + 1;
    if(N >=a.length-1) resize(int 2*a.length);
    a[N] = v;
    swim(N);
  }

  public Key delMax(){
    Key max = a[1];
    exchange(1,N)
    N = N -1;
    if ((N > 0) && (N == (a.length - 1) / 4)) resize(a.length / 2);
    //release the memory//
    a[N+1] = null;
    sink[1]
    return max;
  }
  ```
* index priority queue (another complex hash table)

  Q: what if each Key has its own information, how to store and retrieve the information while insert and delmax key ?

  A: Add unique key for client to refer, and create parallel array to store items.
  * Things need to be change or add.

  1. qp store the index if Key

  2. Key are store in another parallel list

  3. `delmax` delete the index of the Key while you should delete the key from the key list.

  4. `insert` insert the index of key while add the key into key list.

  5. the index needs to be unique.

  6. need to create a reference to know the currently position of a index.

  7. compare method and exchange method need to be modified as well

  * API

  1. `IndexMaxPQ(int maxN)`:create a MaxPQ with maxN capacity.

  2. `contain(int i)`: does the index of the key is unique.

  3. `insert(int i, Key key)`: add a new node to pq with index i;

  4. `delmax()`: detete the node with highest priority.

  5. `maxKey(int i)`: return the key of highest priority node.

  6. `indexMax()`: return the index associate with highest priority node.

  7. `changeKey(int i Key key)`: change the key associate with index i to a new value.

  8. `delete(int i)`: delete the key associate with index i.

  * implement code
  ```java
  public class IndexMaxPQ<Key extends Comparable<k>>{
    private int capacity;
    private int N =0;
    private int[] a; //store the indexes of keys//
    private int[] b; //store the position of key associate with index in heap//
    private Key[] keys; //store the key information//

    private boolean less(int i, int j){
      return keys[a[i]].compareTo(keys[a[j]]);
    }

    private void exchange(int i, int j){
      int temp = a[i]; a[i] = a[j]; a[j] = temp;
      //why, because position is fixed, we need to change the reference of the index//
      b[a[i]]=i;
      b[a[j]]=j
    }

    private void swim(int v){
      // when v =1, it already been the root, so terminate the loop//
      while(v>1 && less(v/2, v)){
        exchange(v/2,v);
        v = v/2;
      }
    }

    private void sink(int v){
      //since the size of PQ so far is N, index can't be larger than N//
      while(2*v < N){
        int j =2*v;
        //compare the two child node, swap v with the larger one//
        if(j < N && less(j,j+1)) j++;

        if(less(v,j)){
          exchange(v,j);
          v = j;
        }
      }
    }

    public IndexMaxPQ(int maxN){
      if(maxN<=0) throw new IllegalArgumentException();
      capacity = maxN;
      int[] a = new int[maxN+1];
      int[] b = new int[maxN+1];
      Key[] keys = new Key[maxN+1];
      for(int i=0; i<=maxN; i++){
        //when the pq is empty, all the index's reference position is -1//
        b[i] = -1;
      }
    }

    public boolean isEmpty(){
      return N==0;
    }

    public boolean contain(int i){
      // why i >= maxN, because a[1] could be 0//
      if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
      //if the b[i] != 1, the index must have some position in the heap//
      return b[i] != -1;
    }

    public void insert(int i, Key key){
      if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
      if(contain(i)) throw new IllegalArgumentException("index already in the priority queue");
      N = N +1;
      a[N] = i;
      b[i] = N;
      keys[i] = key;
      swim(N);
    }

    public int delmax(){
      if(N==0) throw new NoSuchElementException("Priority queue underflow");
      int max = a[1];
      exchange(1,N)
      N = N - 1;
      a[N] = null;
      sink(1);
      b[max] = -1;
    }

    public int maxindex(){
      if (N == 0) throw new NoSuchElementException("Priority queue underflow");
      return a[1];
    }

    public Key maxkey(){
      if (N == 0) throw new NoSuchElementException("Priority queue underflow");
      return keys[a[1]];
    }

    public K keyOf(int i) {
      if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
      if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
      else return keys[i];
    }

    public void changekey(int i, Key key){
      if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
      if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
      keys[i] = key;
      swim(b[i]);
      sink([b[i]]);
    }

    public void delete(int i){
      if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
      if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
      N = N -1;
      exchange(b[i], N)
      swim(b[i]);
      sink(b[i]);
      keys[i] = null;
      b[i] = -1;      
    }
  }
  ```
## heap sort
* step
  * given a N size array, using MaxPQ(key[] a) constructor to turn it into a N size heap.

  * exchange(1,N), re-heap the array from (1,N-1) until the heap become empty.

* implementation code
  ```java
  public class heapsort<Key extends comparable<Key>>{

    public static boolean less(Key[] a, int i, int j){
      return a[i].compareTo(a[j])<0;
    }

    public static void exchange(Key[] a, int v, int j){
      int N = a.length;
      Key temp = a[v];
      a[j] = a[v];
      a[v] = temp;
    }

    public static void sink(Key[] a, int v, int n){

      //n is the capacity of the heap//
      int N = a.length;

      if (n<0 || n > N) throw new IndexOutOfBoundsException("heap overflow");

      while(2*v < n){
        int j = 2* v;

        if(j<N && less(a,j,j+1)) j++;

        if(less(v,j)){
          exchange(a,v,j);
          v=j;
        }
      }
    }

    public static void Heapsort(Key [] a){
      int N = a.length;
      Key[] heap = new Key[N+1];

      for(int i; i < N; i++){
        heap[0] = null;
        heap[i+1]=a[i];
      }

      //re-arrange a into heap order//
      for(int i = N/2; i >=1; i--){
        sink(heap, i, N);
      }

      //exchange value in the heap//
      while(N>1){
        exchange(heap,1,N);
        N--;
        sink(heap,1,N);
      }
    }

  }
  ```
* runtime analysis of heapsort
  * runtime at value exchange step      
  `2N*log(N)`
  * runtime at heap construction

$$\sum_{i=0}^{logN}2^{logN-i}* i = N-log[N]-2$$


# particle application using PQ
<hr />
