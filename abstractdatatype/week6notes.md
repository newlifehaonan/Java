* Dutch flag problems in sorting
  white, blue and red in random position and sort them
* log-log graph     
    Y : T(N)`Runtime`       
    X : N `problem size`     
    **If:**        
    T(n) is polynomial equation of n             
    **Then:**       
    log(T(n)) is linear equation of n            
    log(T(n)) ~ klog(n)  

* Doubling ratio test
  * timer
    ```java
    public class stopwatch{
      private final long startime;
      public stopwatch(){
        startime = System.currentTimeMillis();
      }
      public double elapsedtime(){
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
      }
    }
    ```
  * TimeTrial(program runtime)       
    **Take 3sum as example**
    ```java
    public class TimeTrial{
      public double TimeTrial(int N){
      int[] a =new[N];
      for(int i=0; i<N; i++){
        a[i] = StdRandom.uniform(-1000000, 1000000);
      }
      stopwatch timer = new stopwatch();
      int cnt = 3sum(a);
      return timer.elapsedtime();
      }
    }
    ```
  * Doubling the problem size
    ```java
    public static void main(String[] ags){
      for(int N =250; N<10000; N +=N){
        double time = TimeTrial(N);
        StdOut.printf("%3d : %6.2f\n",N,time);
      }
    }
    ```
  * `T(2N)/T(N)`
  
$$T(N)=a*N^b*lg(N)$$
$$\frac{T(2N)}{T(N)}=2^b(1+lg(2)/lg(N))$$

* selection sort
  ```java
  public class selectionsort{
    public int[] selectionsort(int[] a){
      for (int i =0;i< a.length;i++){
        int minindex = i;
        //after i =0 finished, the least value of the array is on the leftmost position, we just need to select the second smallest value and put it in the position of index 1//
        for (int j =i; j <a.length;j++){
        //Do the comparation//
        if(a[minindex] > a[j]) { minindex = j;}
        }
        //Swap the value//
        int temp = a[i];
        a[i] = a[minindex];
        a[minindex] = temp;
      }
      return a;
    }
  }
  ```
  * how many times does it takes to run this algorithm in a regular computer

$$T(N)=\sum_{i=0}^{n-1}\sum_{j=i}^{n-1} 1 =\sum_{i=0}^{n-1} (n-1-i+1)$$

$$=\sum_{i=0}^{n-1}(n-i) =\sum_{i=0}^{n-1}n -\sum_{i=0}^{n-1}i=\frac{1}{2}n^2 +\frac{1}{2}n$$      

$$Lg(T(N)) = 3*lg(1/2N)$$

**<center>This is Complexity patten is called : Quadratic </center>**

* bubble sort method
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
  }
  ```
  * how many comparations need bubble sort takes

$$T(N)=\sum_{i=0}^{n-1}\sum_{j=0}^{n-i-2}1 =\frac{1}{2}N^2-\frac{1}{2}N$$
$$Lg(T(N)) = lg(1/2N)$$

**<center>compare to selection sort, bubble sort make the slop decrease </center>**

* merge sort method
  * Divide step(recursion)
  ```java
    public class mergesort{
      public int[] mergesort(int[]a,int left,int right){
        if(left >= right) return a;
        int middle = (left + right)/2;
        //splite the array//
        mergesort(a,0,middle);
        mergesort(a,middle+1,left);
        merge(.....);
      }
    }
  ```
  * Conquer step(pointer compare)
  ```java
  public int[] merge(int[]a, int left, int right, int middle){
    int[] temp = new int[a.length];
    int leftindex = left;
    int rightindex = middle+1;
    int tempindex = left;
    while(leftindex <= middle && rightindex <= right){
      if(a[leftindex]<a[rightindex]){
        temp[tempindex]=a[leftindex];
        leftindex++;
      }else{
        temp[tempindex] =a[rightindex];
        rightindex++;
      }
      tempindex++;
    }
    //if rightindex hit the right first, it will not enter the first while loop, but we still need put value of the rest of the left array into temp//
    while(leftindex <= middle){
      temp[tempindex] = a[leftindex];
      leftindex++;
      tempindex++;
    }
    //if leftindex hit the middle first,do the same thing//
    while(rightindex<=right){
      temp[tempindex] = a[rightindex];
      rightindex++;
      tempindex++
    }
    for(int i=0;i<temp.length;i++){
      a[i] = temp[i];
    }
    return a;
  }
  ```
  * how to write a call tree     
  It's a binary tree
  * how to compute the complexity of merge sort
$$T(N) = T(mergesort) * T(merge)$$
$$T(N) = lg(N) * N $$  
**<center>Each mergesort is followed by a merge which has N cost</center>**
**<center>This patten is call Linearithmic </center>**

* slow 2-Sum
    * code
    ```java
    public class 2sum{
      public int 2sum(int[]a){
        int count=0;
        for(int i=0; i<a.length; i++){
          for(int j=i+1; j<a.length; j++){
            if(a[i]+a[j]==0){
              count++;
            }
          }
        }
        return count;
      }
    }
    ```
    * complexity
$$T(N)=\sum_{i=0}^{n-1}\sum_{j=i+1}^{n-1}1=\sum_{i=0}^{n-1}n-i-1$$
$$=\frac{1}{2}N^2-\frac{1}{2}N$$
**<center>same as bubblesort</center>**

* fast 2-Sum
  * code
      ```java
      public class 2sumfast{
        public int 2sumfast(int[] a){
          int count =0;
          Arrays.sort(a);
          for(int i=0; i<a.length; i++){
            if (BinarySearch.rank(-a[i],a) >i){
              count++
            }
          }
          return count;
        }
      }
      ```
    * complexity      
      Mergesort: `N*lg(N)`     
      N times BinarySearch: `N*lg(N)`     
      Totoal: `2N*lg(N)`

* slow 3-sum
    * code
      ```java
      public class 3sum{
        public int 3sum(int[]a){
          int count =0;
          for(int i=0; i<a.length; i++){
            for(int j=i+1; j<a.length; j++){
              for(int z=j+1; z<a.length; z++){
                if(a[i]+a[j]+a[z]==0){
                  count++;
                }
              }
            }
          }
          return count;
        }
      }
      ```
    * complexity
$$T(N)=aN^3+bN^2+cN+d$$
** <center>This patten is called Cubic</center> **

  * fast 3-sum
    * code
      ```java
      public class 3sumfast{
        public int 3sumclass(int[] a){
          int count =0;
          Arrays.sort(a);
          for(int i=0; i<a.length; i++){
            for(int j = i+1; j<a.length; j++){
              if(BinarySearch.rank(-a[i]-a[j],a)>j){
                count++;
              }
            }
          }
          return count;
        }
      }
      ```
    * complexity         
    Bubblesort:`N*lg(N)`      
    Binarysearch in a double loop: `N^2 * lg(N)`
* Order-Of-Growth Classification

Order-Of-Growth |Name | example |speed
:--------------:|:---:|:-------:|:---:
1|Constant|a=b+c|1
lg(N)|Logarithmic|Binarysearch|2
N|Linear|single for loop |3
N*lg(N)|Linearithmic|mergesort|4
N^2|Quadratic|double for loop|5
N^3|Cubic|Triple for loop|6
2^N|Exponential|Useless|7

* Linked list
  * insert in between
    * find nodes that is upper the key value
    * find nodes that is previous the key value
    * create new node and insert
  * delete in between
    * find the value
    * reconnect

* double link list
