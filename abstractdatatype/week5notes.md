* review reference datatype implement
  * Node for tone
    * digitalize tone
      ```java
      import stdlib.StdAudio

      public static void play(double frequency, double duration){
          //cut the wave into millions slices. How many slices you want to hear? mutiplied by duration//
          final int sliceCount = (int) (StdAudio.SAMPLE_RATE * duration);
          //create a slices array to hold different frequency tone//
          final double[] slices = new double[sliceCount+1];
          //following the math to fill the slices array//
          for (int i = 0; i <= sliceCount; i++) {
                slices[i] = Math.sin(2 * Math.PI * i * frequency / StdAudio.SAMPLE_RATE);
          }
          //using StdAudio to play tone
          StdAudio.play(slices);
      }
      ```
    * API
      Node(double frequency, double duration)
      frequency()
      duration()
      compareTo()
      play()
      tostring()
      hashcode()
      equals()
    * implement
      ```java
      //implements comarable mean there is a comparation instance method in this class//
      public class Node implements comparable<Node>{
        private double frequency;
        private double duration;

        //constractor, if not specify, eclips automaticlly create a defoult Node()//
        public Note(double frequency, double duration){
          this.frequency = frequency;
          this.duration = duration;
        }

        //return frequency//
        public double frequency(){
          return this.frequency;
        }

        //return duration//
        public double frequency(){
          return this.double;
        }

        //play the tone//
        public void play(){
          //cut the wave into millions slices. How many slices you want to hear? mutiplied by duration//
          final int sliceCount = (int) (StdAudio.SAMPLE_RATE * duration);
          //create a slices array to hold different frequency tone//
          final double[] slices = new double[sliceCount+1];
          //following the math to fill the slices array//
          for (int i = 0; i <= sliceCount; i++) {
                slices[i] = Math.sin(2 * Math.PI * i * frequency / StdAudio.SAMPLE_RATE);
                }
          //using StdAudio to play tone
          StdAudio.play(slices);
        }

        Public int compareTo(Node that){
          if(this.frequency < that.frequency()) return -1;
          if(this.frequency > that.frequency()) return +1;
          if(this.duration < that.duration()) return -1;
          if(this.duration > that.duration()) return +1;
        }

        //3inheriate methods//
        public String toString(){
          return "frequency: " + frequency + "duration" + duration;
        }

        public int hashcode(){...}

        public boolean equals(Node that){
          if(this == that) return true;
          //use instanceof to distinguish whether variable are specific data type//
          if(!(that instanceof Node)) return false;

          //cast//
          Node thatnode = (Node) that;
          return (this.frequency == thatnode.frequency()) && (this.duration == thatnode.duration());
        }
      }
      ```
      * String format
      instead of using:
      ```java
      public String toString(){
        return "frequency: " + frequency + "duration" + duration;
      }
      ```
      we can use string format(just as in python String.format method)
      ```python
      print('frequency {6.2f} duration {4.3f} ').format(frequency,duration)
      ```
      ```java
      public String toString(){
        return String.format("frequency: %.2f duration %.3f",this.frequency, this.duration);
      }
      ```
  * Depth first search `Stack`
    file tree in depth
    ```java
    public static void fileindepth(File file){
      //create a stack to hold file//
      Stack<File> s = new Stack<File>();

      //specify a root file//
      File root = new file("d:/java");

      //push the root into stack//
      s.push(root);

      //pop a file in stack and judge whether the file is a directory, if so, list all the subfiles and push each of them back to stack//
      while(!s.isEmpty()){
        File directory = s.pop();
        StdOut.print(directory); //print !!!//
        if(directory.isDirectory()){
          File[] files = directory.listFiles();
          for (File file : files){
            s.push(file);
          }
        }
      };
      //each loop pop up a directory and defile the directory.M//
    }
    ```
    Summary: print all directories first until there is no directory, than print all files. Print nodes first, than leaves.

  * Breath first search `Queue`
    ```java
    public static void Inbreath(File file){
      Queue<File> q = new Queue<File>();
      File root = new File("D:/java");
      q.enqueue(root)
      while(!isEmpty()){
        File Directory = q.dequeue();
        //assume the root file is a directory//
        File[] files = Directory.listFiles();
        for (File file : files){
            StdOut.print(file);
            if(file.isDirectory()){
                q.enqueue(file);
            }
        }
      }
    }
    ```
    summary: print all the files include directory in the same level.

  * Binary search
  array :`[1,2,3,4,5,6,7,8,9,10]`
  index : `0 1 2 3 4 5 6 7 8 9`
    ```java
    public int binarysearch(int[] a, int key){
      int lo =0;
      int hi =a.length-1
      while(lo < hi){
        int mid = (lo+hi)/2;
        if(a[mid] == key) return mid;
        if(a[mid] < key){
          lo = mid + 1；
          //why + 1 ? : lo(first:5, second：8,third:9 == hi exit loop)//
          //if not +1 : lo(first:4, second: 6,third:7,fourth:8, fifth: 8.....8 infinite loop)//
        }

        if(a[mid] > key){
          hi =mid -1;
          //why - 1? :hi（1th:3, 2th:0 == lo exit loop)//
          //if not -1:hi (1th:4, 2th:2, 3th:1..........1 infinite loop)
        }
      }
      //if found no matched value, return -1 means no index in the array//
      return -1
    }
    ```
  * example of recursion;

    * Fibbonaci sequence
      **Iteration vision**
      ```java
      public static int fibi(int n){
        int first =1;
        int second =1;
        if( n==1 || n ==2) return 1;
        for(int count =0; count < n-2;count++){
          int next = first +second;
          first = second;
          second = next;
        }
        return second;
      }
      ```
      **Recursion vision**
      ```java
      public static int fiboR(int n){
        int first =1;
        int second =1;
        if( n==1 || n ==2) return 1;
        return fiboR(n-1) + fiboR(n-2);
      }
      ```

    * Factorial

      **Iteration vision**
      ```java
      public static int factorial(int n){
        //mutiply till achieve to the upper case//
        int factor =1;
        if (n==0) return 1;
        for(int i =1;i <= n ; i++){
          factor = i*factor;
        }
        return factor;
      }
      ```

      **Recursion vision**
      ```java
      //base is 1; n-1 convergent to 0//
      public static int factorialR(int n){
        if(n==0) return 1;
        else
          return n*factorialR(n-1);
      }
      ```
    * Filesearch in Depth
      **iteration vision is in privious part**
      **Recursion vision**
        
      ```java
      public static void FSDR(File file){
        StdOut.print(file);
        if(file.isDirectory()){
          File[ ] Files = file.listFiles();
          for (File file : Files){
            FSDR(file);
          };
        };  
      };
      ```
    * Binary Search
      **iteration vision is in privious part**
      **Recursion vision**
      ```java
      public static int BSR(int[] a, int key, int lo, int hi){
        if (lo > hi) return -1;
        if (lo < hi){
          mid = (lo+hi)/2;
          if (key == a[mid]) return mid;
          else if (key < a[mid]) return BSR(a, key, lo, mid -1)
          else
          return BSR(a, key, mid + 1, hi)
        }
      }
      ```
  * How comes the complexity of Binary Search equals to log(n)?
  *
