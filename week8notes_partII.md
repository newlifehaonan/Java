# Sort Algorithm
<hr />

* Rules     
1.Sort Algorithm should works on any object that has `compareTo` method implemented in it     
2.Test performance
  * count how many times dose `compareTo`,`exchange` method being used in each Algorithm      
  * if `exchange` method is not being used, we count how many times does the Algorithm get access to the array.
  * Algorithm use extra memory need to be considered separately.
* API     
1.`Sort()`: include different sort Algorithm     
2.`Less()`: Compare two object.     
3.`exchange()`:Exchange two values of objects     
4.`IsSorted()`:Is the array already sorted?
* code
  ```java
  public class Sort{
    public T[] sort(T[] a){
      //need to be implemented//
      return a;
    }

    public boolean less(T a,T b){
      return a.compareTo(b)<0;
    }

    public void exchange(T[] a, T i, T j){
      T temp=a[i];
      a[i] = a[j];
      a[j] = temp;
    }

    public boolean IsSorted(T[] a){
      for(int i ; i <T.length; i++){
        if(less(a[i],a[i-1])) return false;
      return true;
      }
    }
  }
  ```

<hr />

## elementary sort

### 1. Selection sort
* definition
  * Select the min value and put them in the first index, and put the second smallest value in the second position        
* implement code
  ```java
  public class SelectionSort{
    public T[] SelectionSort(T[] a){
      for(int i=0; i<a.length; i++){
        int min = i;
        for(int j = i +1; j< a.length; j++){
          if(less(a[j],a[min])) min = j;
        exchange(a,i,min);
        }
      }
      return a;
    }

    ......
  }
  ```
* Runtime     
see week6nodes for detail.
$$ \frac{1}{2}n^2 -\frac{1}{2}n $$

### 2. bubble sort
* definition
  move the highest value to the right in each array access.
* implement code
  ```java
  public class bubblesort{
    public int[] bubblesort(int[] a){
      for(i=0;i<a.length;i++){
        //push the maximum value to the rightmost//
        for(j=0;j<a.length-i-1;j++){
          if(a[j] > a[j+1]) {
            int temp = a[j];
            a[j] =a[j+1];
            a[j+1] =temp;
          }
        }
      }
      return a;
    }

    .....
  }
  ```

### 3. Insertion sort
* definition
  * consider one value at each time, put them in proper location among those already considered.
  * For insertion we need to create space by moving larger position to the right one at the time.
* implement code
  ```java
  public class InsertionSort{
    public T[] InsertionSort(T[] a){
      for(int i = 1; i<a.length; i++){
        for(int j = i; j>0 && less(a[j],a[j-1]); j--){
          exchange(a,j,j-1)
        }
      }
    }
  }
  .....
  }
  ```
* version 2:Compare while shift position
  ```
  public class InsertionSort{
    public T[] InsertionSort(T[] a){
      for( int i =1; i<a.length; i++){
        if(less(a[i] < a[i-1]){
          T temp = a[i];
          for ( int j=i-1; j>=0 && less(temp, a[j]); j--){
            //shift position//
            a[j+1] = a[j];
          }
          a[j+1] = temp;
          }
        }
      }
    }
    .....
  ```

* Runtime
  * The best-case is the list is in acs order, which takes `N-1` comparer 0 exchange
  * The worse-case is the list is in dec order, which takes `n(n-1)/2` comparer and `n(n-1)/2` exchange


* optimization of InsertionSort
  * Using Binary search method to insert the new value to the preordered sequence
    ```java
    public class halfexchange{
      public T[] halfexchange(T[] a){
        int lo=0;
        int hi;
        int m;

        for( int i =1; i<a.length; i++){
          T temp=a[i];
          hi = i-1;
          while(lo <= hi){
            m =(lo+hi)/2;
            if(a[m] > a[i]){
              hi = m -1;
              }
            }else if (a[m] < a[i]){
              lo = m + 1;
              }
            }
          //low is the position where temp is gonna insert//
          //shift position from low ~ i//
          for (int j =i; j>low;j--)
            a[j]=a[j-1];
          }
          //fill in the value//
          a[low] = temp;
        }
    ```

what is partially sorted array ?          
1. `A array with a few inversions in it`
2. `number of exchange is equal to the number of inversions in the array`
3. `number of compare is at least N-1, if there exit inversions, the number of compare equals to number of insertion plus N-1 `

`By conduct a experiment that applying two Algorithm to sort T N-dim array to see the relative speed of the two Algorithm, we find Selection sort is quicker than InsertionSort.`

### 4. shell sort
* definition     
  assume we have a descending ordered array, in naive insertion sort, we need N-1 exchange to move the smallest value to its right place since the Algorithm only allowed swap between adjacent entries. Shell sort allow items that far apart from its right place to swap directly by creating partially sorted array first and finally using insertion sort.

* implement code
  ```java
  public class shellsort{
    public T[] shellsort(T[] a){
      int N=a.length;
      int h = 1;
      //defined the largest step size following the 3*k+1 sequence//
      while(h<N) h=3*h+1;
      //do the h-sorted array//
      while(h >=1){
        for(int i=h; i < N; i++){
          for(int j =h; j>=h && less(a[j],a[j-h]); j-=h){
            exchange(a,j,j-h);
          }
        }
        //decrement the step size and redo the insertion sort until h hits 1//
        h = h/3;
      }
    }
  }
  ```
  
# comparator and lambda function
<hr />
`The following comparer are based on the Object Restaurant`

## Restaurant Object

```java
Public class Restaurant{

  private String name;
  private int price;
  private int rate;

  public Restaurant(String name, int price, int rate){
    this.name = name;
    this.price = price;
    this.rate = rate;
  }

  public String getname(){
    return this.name;
  }

  public int getprice(){
    return this.price;
  }

  public String getrate(){
    return this.rate;
  }

  public string toString(){
    return this.name+"\t"+this.city+"\t"+this.rating;
  }

  public boolean equals(Restaurant that) {
    return this.name.equals(that.name);
  }

}
```

## Comparable interface
* Interface
  ```java
  public interface Comparable<T> {
    public int compareTo(T object);
  }
  ```

* compareTo Restaurant
  ```java
  public class Restaurant implements Comparable<Restaurant>{
    //constructor and other method are defined above//
    ......
    //re-define compareTo method//
    public int compareTo(Restaurant that){
      //String datatype include compareTo class//
      return name.compareTo(that.name)
    }

    //Java doesn't support to compareTo method with same signature, so we need to find another way//

  }
  ```
## comparator interface
* Interface
  ```java
  public interface Comparator<T> {

    int compare(T o1, T o2);

    boolean equals(Object obj);
  }
  ```
* Compare Restaurant
  ```java
  //This class is inside the main class of Restaurant
  public class RestaurantByname implements Comparator<Restaurant>{
    public int compare(Restaurant r1, Restaurant r2){
      return r1.name.compareTo(r2.name)
    }
  }

  public class RestaurantByPrice implements Comparator<Restaurant>{
    public int compare(Restaurant r1, Restaurant r2){
      return r1.price.compareTo(r2.price)
    }
  }

  public class RestaurantByRate implements Comparator<Restaurant>{
    public int compare(Restaurant r1, Restaurant r2){
      return r1.Rate.compareTo(r2.Rate)
    }
  }
  ```

* Sort Restaurant
  * Using comparator
  ```java
  Arraylist restaurants = {new Restaurant("joy's",7,2),new Restaurant("chipotli",1,1)}
  collection.sort(restaurants, new RestaurantByRate);
  ```
  * Using lambda function
  very easy,don't need to modify comparator       
  ```java
  restaurants.sort((r1,r2)->(r1.getprice()-r2.getprice()))
  ```
