# insert into ordered linked list
<hr />
1. implement  

  * constructor        
  step1:contained `Node` reference datatype      
  step2:create `head` local private variable     

  * Insert API      
  step1:create `previous` and `current` Node   
  step2:compare input value with current value till input less than current    
  step3:create new node and change the reference      
  step4: special case       
    * The value you want to insert is the first node: current is head which is null; change the head to newnode      
    * The value you want to insert is the largest value  

  * hasValue API     
  step1:search each value of the node to see if they match the input value  

  * Delete API     
  step1:create `previous` and `current` Node     
  step2:find the input value in the list:             
  step3:if exists, change the reference, if not consider the special case         
  step4: special case       
    * If you delete the value, list become empty      

  * mapping method         
  step1 : create an empty arraylist       
  step2 : add each value of the orderedlist into the arraylist you just create       

2. code

    ```java
    public class orderedlist<T extends Comparable<? super T>>{
      //constructor//
      //step1//
      public Node head;
      //step2//
      public class Node<T>{
        private T value;
        private Node<T> next;
        }

      public orderedlist(){
        this.head = null;
      }

      //Insert API//
      public void insert(T value){
        //Step1//
        Node current = head;
        Node previous = null;
        //step2//
        while(current != null && value.compareTo(current.value)>0){
          previous = current;
          current = current.next;
        }
        //step3//
        Node newnode = new Node();
        newnode.value = value;
        newnode.next = current;
        //step4//
        if(current == null){
        //newnode is the first//
          head = newnode;
        } else
        //newnode is the largest//
        previous.next = newnode;
      }

      //hasValue API//
      public boolean hasValue(T value){
        //Step1//
        for(Node current=head; current != null; current = current.next){
          if(current.value.equals(value)) return true;
        }
        return false;
      }
    }

      //Delete API//
      public void delete(T value){
        //step1//
        Node current = head;
        Node previous = null;
        //step2//
        while(current != null && !current.value.equals(value)){
          previous = current;
          current = current.next;
        }
        if(current != null){
          if(previous == null){
            //step4//
            //if delete, the list become empty//
            head = head.next
          }
          else
          //step3//
          previous.next = current.next;
        }
      }

      //mapping API//
      public Iterable<T> values(){
        //step1//
        ArrayList<T> list = new ArrayList<T>;
        //step2//
        for(Node current=head; current != null; current = current.next){
          list.add(current.value);
        }
        return list;
      }
    ```
# Union find
<hr />

1. Problem discription

* give a set of integer value with each has unique name and a set of instruction to connect two value into a pair.
* after connection instruction, we want to know how many isolate components does the network has? Given two sits, are they connected? in other word, are they belong to same components? further, given a sits, which component does it belong to.

2. API define

* `UF()` : initialize a set of value.
* `union(int p, int q)` : connect p with q.
* `find(int p)`: find the component name of p.
* `connected(int p, int q)`: is p and q connected.
* `count()` : how many component does the network have.
