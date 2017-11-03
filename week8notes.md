# Optimize Quickunion
<hr />

  * weighted quickunion     
  1.`Since naive quickunion may cause "tall tree", This need to be optimized`        
  2.`linked small tree into large tree. Small means has less belongs thats has less depth, cause lg(N)~N)`               
  3.`The depth of Unioned forest will be no greater than Lg(T1 + T2) = lg(N)`      
  4.`base on the fact that the depth of N nodes tree will no larger than Lg(N). each find will cost lg(n) times`           
  ```java
  public class UF{
    private count;
    private int[] ID;
    private int[] sz;

    public int[] UF(int N){
      for(int i ; i <N; i++){
        ID[i] = i;
        sz[i] = 1;
      }
    }

    public boolean connected(int p, int q){
      return find(p)==find(q)
    }

    public int find(int p){
      while(p != ID[p]){
        p = ID[p];
      }
      return p;
    }

    public void WeightedUnion(int p, int q){
      pid = find(p);
      qid = find(q);

      if(sz[p] < sz[q]){
        ID[pid] = qid;
        sz[qid] = sz[qid] + sz[pid];
      }else{
        ID[qid] = pid;
        sz[pid] = sz[pid] + sz[qid];
      }
      count --;
    }
  }

  ```
  * press compression(Optimize the worse-case, the tree is degenerate to a linked list )         
  1.`link the node direct to its grandfather, making the tree more flat`
  ```java
  public class UF{
    private count;
    private int[] ID;
    private int[] sz;

    public int[] UF(int N){
      for(int i ; i <N; i++){
        ID[i] = i;
        sz[i] = 1;
      }
    }

    public boolean connected(int p, int q){
      return find(p)==find(q)
    }

    public int find(int p){
      while(p != ID[p]){
        ID[p] = ID[ID[p]];
        p = ID[p];
      }
      return p;
    }

    public void WeightedUnion(int p, int q){
      pid = find(p);
      qid = find(q);

      if(sz[p] < sz[q]){
        ID[pid] = qid;
        sz[qid] = sz[qid] + sz[pid];
      }else{
        ID[qid] = pid;
        sz[pid] = sz[pid] + sz[qid];
      }
      count --;
    }
  }
  ```
* worse-case time for 4 algorithms ( 1 billion N)

  M times union on N sites

| method | complexity    |
| :------------- | :------------- |
| quickfind      | N*M      |
| quickunion     | N*M      |
| weightedquickuion| N + M*lg(N)|
| WQUPC          |N + Mlg*(N)   |
| no linear linked method, gotta proved by fredeman-??? |

  what is lg*(N)?         
  number of times to apply lg() on N to bring N down to 1        

| N | lg*(N)     |
| :------------- | :------------- |
| 2       | 1       |
| 4       | 2       |
| 16        | 3       |
| 65536    | 4|



magnitude of number

| Name     | number     | represent |
| :------------- | :------------- |:------------- |
| Ten       | 10      |10^1
| Hundred       | 100       |10^2
| Thousand      | 1,000 |10^3
| Ten Thousand  |10,000 |10^4
| Hundred Thousand |100,000|10^5
| Million       |1,000,000 |10^6
| billion       |1,000,000,000|10^9|
| Trillion      |1,000,000,000,000|10^12|

# application of Union find
<hr />

## percolation
  * definition       
  1.N by N grid       
  2.each square is vacant or occupied       
  3.grid percolate if top and bottom are connected by vacant squares.       
  4.likelihood depends on site vacancy probability p.      
  5.we need to find the threshold p that if exceed, the grid are almost percolate
  
