# Merge Sort
<hr />

**Merge sort use divide and conquer method, divided the array into two parts and then sort each part then merge them together.**

## 1. In-place merge

**Create a aux array to preserve the original order, divided the aux array using for sort**

```java
public class merge<K extends comparable<K>>{
  private K[] aux;

  public static void merge(K[] a, int lo, int mid, int hi){

    //each time you doing merge allocate a space, this is not good, so initiate when sort !!//
    //this.aux = new K[a.length];//

    //pointer for the left and right part in aux array//
    int i = lo; j =mid+1;

    //copy a[] to aux[]//
    for (int k= lo; k <= hi; k++){
      aux[k] = a[k];
    }

    //compare the values of the two parts in aux array//
    for (int k = lo; k <= hi; k++){
      if(less(aux[i],aux[j])) a[k]= aux[i++];
      else if(i > mid) a[k] = aux[j++];
      else if(j > hi) a[k] = aux[i++];
      else a[k] = aux[j++];
    }
  }
}

```
## 2.Top-Down sort (recursion)

**divided the aux array to the one only contain 1 item, then merge up. Consider it recursively by put each sort function in a stack, when it hit the base case, doing merge.**
```java
private static void sort(K[] a , int lo, int hi){
  //this is the control
  if(hi <= lo) return;
  int mid = (lo + hi)/2;
  sort(a,lo,mid);
  sort(a,mid+1,hi);
  merge(a,lo,mid,hi);
}

public static void sort(K[] a){
  aux = new Key[a.length];
  sort(a,0,a.length-1);
}
```

## 3. Bottom-up sort (iteration)
* Trace      
**Merge the array in the following pattern**       
`size=1`:          
merge(a,0,0,1);          
merge(a,2,2,3);         
merge(a,4,4,5);           
`size =2`:       
merge(a,0,1,3);         
merge(a,4,5,7);         
merge(a,8,9,11);       
`size =4`:      
merge(a,0,3,7);
merge(a,8,11,15);
* Control        
`outer loop`:size += size;       
`inner loop`:mid = lo + size -1; hi = lo+size + size-1;

```java
public static void sort(K[] a, int lo, int mid, int hi){
  for(int sz = 1; sz <= a.length; sz + = sz ){
    for(int lo = 0; sz < N; lo += sz +sz )
      //in case of the total item in the array is odd, the last item index should be N-1//
      merge(a, lo, lo + size -1, Math.min(lo+sz+sz-1,N-1))
  }
}
```


## 4. Runtime analysis
* Top town     
  `tree height`:n = lg(N);        
  `number of subarray`: 2^k (k=0,1,2,3,4....n);     
  `array size of subarray`: 2^(n-k);

  **the number of comparer that each subarray  needed is at most 2^(n-k), which equals to its size. so in each level we need 2^k * 2^(n-k) which equals to 2^n compare. there are n level in total, so n * 2^n is the total compare. Specifically it's at most N * log(N)**


# Quick Sort

<hr />

**Quick sort involves three step: shuffle, partition and sort. The idea is find a pivot that every nodes before the pivot is less than it, every nodes after the pivot is larger than it. divided the array in the same way as merge sort and use the partition method on each subarray**

## 1. partition
* set the a[0] as the pivot, find its correct position in the array.
* set pointer i, which scan from right until it find the item that larger than the pivot.
* set point j, which scan from left until it find the item that less than the pivot.
* exit the loop, when pointer i and j meet each other.
* set the pivot at the position where i and j meet.

```java
public class quicksort<K extends comparable<K>>{

  public static int partition(K[] a, int lo, int hi){
    int i=lo; int j = hi + 1;
    K pivot = a[lo];
    while(TRUE){
      while(less(a[++i], pivot)){ if(i == hi) break};
      while(less(pivot, a[--j])){ if(j == low) break};
      //This is the break point of the outer loop//
      if(i >= j) break;
      exchange(a, i, j);
    }
    exchange(a, lo, j) //a[lo] is pivot//
    return j;
  }
}
```

## 2. shuffle and sort.
* shuffle the array to avoid the condition that no item are less or larger than pivot, in another word, a[0】 is the final correct position.
* divided the array using recursion
```java
private static void sort(K[] a, int lo, int hi){
  //make sure to terminate the recursion when it hit the base case//
  if(lo >= hi) return;
  p = partition(a, lo, hi);
  sort(a, lo, p-1);
  sort(a, p+1, hi);
}

public static void sort(K[] a){
  StdRandom.Shuffle(a);
  sort(a,0, a.length-1)
}
```

## 3. optimization.
* what if the array is short ?
**implement insertion sort when the array size decreased to certain range since insertion sort is faster when the array is small**
  ```java
  if (hi < = lo + M) {insertion.sort(a, lo , hi) return;}
  ```
* Do we have another way to choose the pivot?
**since quick sort is depend on how we chose pivot, if original array is already partially sorted, it will cause quick sort degenerate to O(N^2)**
  * choose first : can't make sure there are at least one item that larger or less than itself.
  * randomly choose: time for using StdRandom is costly
  * Mid-Of-Three: make sure the first and the last item do not need to be involved in partition.
    ```java
    int mid = (lo + hi)/2;
    exchange(mid, hi-1);
    if(less(hi-1, mid )) { exchange(a, hi-1, mid)};
    if(less(hi, lo)) { exchange(a, hi, lo)};
    if(less(hi, hi-1)){exchange(a, hi-1, hi)};
    p = partition(a, lo, hi);
    ```
    **M can't be too small or too large, better are the range of 5 to 25**
* what if the array has a lots of duplicate key    
**we don't need to swap two keys that has same value, just let them stay in place**
  ```java
  public static int partition(K[] a, int lo, int hi){
    int lt = lo, i = lo+1, gt = hi;
    Comparable v = a[lo];
    while (i <= gt)
    {
      int cmp = a[i].compareTo(v);
      if      (cmp < 0) exchange(a, lt++, i++);
      else if (cmp > 0) exchange(a, i, gt--);
      else              i++;
    }
  ```
