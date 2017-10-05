* week3 review
  * group3 assignment
    * Date reference class, create a instance method
    * Balanced punctration: Using Stack
    * letter frequencies: apply array
    example: balanced (). inbalance `) (`, `[ ( ] )`

  * Loops
    * while loop
    ```java
    while(!In.isEmpty()){
      Out.println(variable);
    }
    ```
    * for each loop
    ```java
    for(int i=0;i < T; i++){}；
    ```
    * stander for loop
    ```java
    for (int i : x){};
    ```

  * Abstract datatype
    implemented in java reference classes
    * **counter**
    * **date**
  * Stack
    * last in first out = LIFO
    * push(),pop() method
    * Stack is Array of Strings
    * Generic Stack: create a stack that can contain ant kinds of object
    * Resizable Array : Optimization Method.
  Linked list of Stack.
  * Queue
    * First in first out = FIFO

* week4 notes
  * linked lists     
  **A linked list is a recursive data structure that is either empty(null) or a
   reference to a `node` having a generic item and a reference to a linked list.**
  `node` = `item` + `next node`

  ```java
  public class Node<T> {
    public Node() {};
    public Item item;
    public Node<T> next;
  }
  ```

  **each node has its own memory id which can be referenced by `next`**

  ```java
  Node first = new Node();
  Node second = new Node();
  Node third = new Node();

  first.item = 1;
  second.item =2;
  third.item =3;

  first.next =second;
  second.item = third;
  ```
  * Insert and delete at the beginning
  ```java
    public void insert(Item item){
      Node oldfirst = first;
      first = new Node();
      first.item = item;
      first.next = oldfirst;
    }

    public Item remove(){
      Item result = first.item
      first = first.next;
      return result;
    }
  ```
  * pushdown stack using linked list

  ```java
  public class Stack{
    private Node<Item> first;
    private int count;

    private class Node<Item>{
      public node() {};
      public Item item;
      public Node next
    }

    /*create a empty stack*/
    public stack(){ this.first = null; this.count =0};

    public void push(Item item){
      Node oldfirst = first;
      first = new node();
      first.item = item
      first.next = oldfirst;
      count++
    }

    public Item pop(){
      Item result = first.item
      first = first.next;
      count--;
      return result;
    }
  }
  ```

  * FIFO Queue using linked list

  ```java
  public class queue<Item>{
    private Node<Item> first;
    private Node<Item> Last;
    private int count;

    private class Node<Item>{
      public node() {};
      public Item item;
      public Node next
    }

    /*make the new node to be the last node which next attribute is null*/
    public void enqueue(Item item){
      Node oldlast = last;
      last = new node();
      last.item = item;
      last.next = null;
      oldlast.next = last;
    }

    /*remove the oldest node out of the queue*/
    public Item dequeue(){
      T item = first.item;
      first = first.next;
      count--;
      return item;
    }
   }
   ```
   * application of stack queue and bag
     * CarWashSimulation
     * postfix arithmatic
     * double stack algrithm
     * Listfilebylevel
