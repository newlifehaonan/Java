# Priority Queue Application
<hr />

* minimum-cost spanning tree algorithm   (kunskal's algorithm.)
  * start with a lowest edges
  * find the second lowest edges
  * try to choose the edge doesn't create a cycle.
  * put them in the min priority queue.
  * pseudo code
    ```
    node = new UF(n); ( the best solution you want to find, you are trying to complete the UF)

    edges = new minPQ();

    treeedges = new list();

    for each edge e in g{
      edges. insert(e);
    }

    while edges is not empty{
      e = edges.delmin();
      //assume e = (v,w)//

      if node.find(v) ! = nodes.find(w);{ //whether they are connnected are not//
        treeedges.add(e);
        nodes.union(v,w);
      }
    }

    ```
* collide program
  * create particle object.
  * create event object.
  * involved some physics.

# Java Interface
<hr />

* comparator

* iterator

# stability of sort algorithm
<hr />
