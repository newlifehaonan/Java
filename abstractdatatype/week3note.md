**Week3note**
  * Abstract datatype    

  Build a object which is an entity to hold data value.   
  A class of a datatype includes:    
    * **private variable/class**      
    * **a constructor (should has same name as class name)**    
    * **several instance methods**      
    * **inherited methods**
      * toString( ) :string representation  
      * equals(object another): is this object equal to that
      * get class( )

  ```Java
  public class Counter{
    /*Usually a reference datatype includes some instance variable to be used, you should defined them
    at first*/
    private int count;
    private String name;

    /*Usually a constructor has the same name as the class name */
    public Counter{
      name = id;
      count = 0;
    }

    /*accumulator is one of the instance method that can be called in client code*/
    public void accumulator{
      count++
    }

    /*toString is one of the inherited method that used to print of the result*/
    public String toString(){
      return "this is the format of return value " + name + count;
    }
  }
```

* Applying ADT

  By use reference datatype client do not need to know what's implemented in the API, they can simply use the object to hold the value and compute operations on them.     
  **object used as a variable**    
    `Counter head = new Counter("head")`
    `head.accumulator()`    
  **object used as a return value**    
  The object are usually embed in `StdOut.println()`to return     
  **object used as a array**    
  `counter[] item = new counter[5];`        
  **Array is also a object**   
  `array.sort()` is one of the instance method      

  Example of using object includes:
    * **Date**      
    API:      
    ```java
    public class date{
          public class month(){};
          public class year(){};
          public class day(){};
          public class isValid(int month, day year){};
          public class isLeap(){};
          public class next(){};
          public class isAfter{};
          public class isBefore{};
    }
    ```
    Implemented:  
    ```java    
    Public class date{
      private int[] day = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
      private int month;
      private int year;

      public date(String date){
        String[] a = date.split("/");
        this.month = Integer.parseInt(a[0]);
        this.day = Integer.parseInt(a[1]);
        this.year = Integer.parseInt(a[2]);
      }

      public int month() { return month; };
      public int day()   { return day;   };
      public int year()  { return year;  };
      public String toString(){return ...};
      ...  
    }
    ```
    Client Code:    
    * **Geometric object**     
  API: Interval1D, Interval2D, Point2D.....      
  Instance method: .draw(), contain(), distTo(),intersects()....

    * **accumulator**        
  API:    
  Implemented:     
  Client code:       

    * **Visual accumulator**      
  API:       
  Implemented:       
  Client code:             

    * **Binary Search**      
  API:       
  Implemented:       
  Client Code:         

  * Generic      
  If I want a stack to hold a set of String:  
  ```java
  /*Stack of string*/
  public class Stack {
    private String[] a;
    private int count;
    public Stack(int length){
      this.a = new String[length];
      this.count = 0;

    public boolean isEmpty(){};

    public String peak(){};

    public void push(String item){};

    public String pop(){};

    public String toString(){return ...};
    }
  }
  ```
  What if I want a Stack to hold a set of Object, let's say any kinds of reference datatype;
  ```java
  Public class GenericStack<E>{
    private E[] a;
    private int count;

    /*A stack which can hold object E*/
    public GenericStack(Int length){
      this.a = (E[]) new Object[length];
      this.count = 0;

    /*input item should be E*/
    public void push(E item){};

    /*Output should return E too*/
    public E pop(){};

    public String toString(){return ...};

    }
  }
  ```

* Bag & Stack & Queue      
  1. They are all collections of object. The difference in the specification of which object is to be removed or examined next
  2. Collection can be Generic which means its can hold any kind of object.
  3. Collection are auto-boxing. Collection need to hold reference data type, primitive datatype are implicitly converted to its corresponding wrapper type.
  4. collections is iterable which means they can be used in `for each ` loop

  ```java   
  Stack<E> A = new Stack<E>();

  for (E a : A){ StdOut.println();}
  ```
  * Bag      
    1. add items to the bag and it can not be removed.
    2. The order is immaterial  
    ```Java  
    public class bag<E>{
        /*Create a empty bag*/
        public bag (){...};

        /*Add a item in the bag*/
        public void add(E item){...};

        /*Check whether the beg is empty*/
        public boolean isEmpty(){...};

        /*How many items are in the bag? */
        public int size(){...};
      }
    ```
  * Queue      
    1. FIFO, first in first out. new arrival items is at the end of the queue.
    2. When a client iterates through the items in a queue with the foreach construct, the items are processed in the order they were added to the queue.
    3. The main use of queue is to save the order  
    ```java
    public class queue<item>{
      /*Create a empty queue*/
      public queue(){..};

      /*add new arrival to the end of queue*/
      public void enqueue(item item){...};

      /*Remove the item which is at the bottom of the queue*/
      public item dequeue(){...};

      /*Check whether the queue is empty*/
      public boolean isEmpty(){...};

      /*How many items are in the queue? */
      public int size(){...};
    }
    ```       

  * Stack     
    1. LIFO, last in first out.
    2. When a client iterates through the items in a stack with the foreach construct, the items are processed in the reverse of the order in which they were added.
    3.  A typical reason to use a stack iterator in an application is to save items in a collection while at the same time `reversing` their relative order.

    ```java
    public class stack<item>{
        /*Create a empty queue*/
        public stack(){..};

        /*add new arrival to the top of stack*/
        public void push(item item){...};

        /*Remove the item which is at the top of the stack*/
        public item pop(){...};

        /*Check whether the stack is empty*/
        public boolean isEmpty(){...};

        /*How many items are in the stack? */
        public int size(){...};
    }
    ```
* Stack implement.
  ** Both those two implements is uniterable;
  * fix size stack
  ```java
    public class fixedsizestack<E>{
      private E[] S;
      private int count;

      public fixedsizestack(int length){
        this.S = (E[]) new Object[length];
        this.count =0;
      };

      public boolean isEmpty(){ if(count ==0) return true;};
      public int size(){ return this.count};
      public void push(E item){ S[count++] = item};
      public E pop(){ return S[--count]};
      }
    }
  ```
  * resized stack
  ```java
  public class resizedstack<E>{
    private E[] S;
    private int count;

    public resizedstack(int length){
      this.S = (E[]) new Object[length];
      this.count =0;
    };

    public void resize(int hi){
      E[] temp = E[] new Object[hi];
      for(i =0 ; i< count ; i++){ temp[i] = S[i];};
      S = temp ;    
    }

    public void push(E item){
      if(count >= S.length){ resize(2*S.length);};
      S[Count++] = item;
    }

    public E pop(){
      E item = S[--count];
      if (count > 0 && count <= a.length/4) resize(S.length/2);
      return item;
    }
  }
  ```
