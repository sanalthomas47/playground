================================================================
CLASS: Graph<T>
FILE: src/main/java/Graph.java
================================================================

OVERVIEW
--------
Graph<T> is a generic undirected, unweighted graph backed by an
adjacency-list map. Each vertex stores a value of type T. An edge
between A and B is represented by adding B to A's list AND A to
B's list.

  Internal structure:
    Map<Vertex<T>, LinkedList<Vertex<T>>> graphMap

  The map key is a Vertex wrapper; equality and hashing delegate
  to the wrapped data value T.


INNER CLASSES
-------------
  Vertex<T>
    T    data      — the stored value
    equals/hashCode delegate to data, so two Vertex objects with
    the same data are considered equal.

  (No Edge class — unweighted graph stores Vertex references directly.)


OPERATIONS & HOW THEY WORK
----------------------------

1. addVertex(T v)  —  O(1) amortized
   ------------------------------------
   Creates a new Vertex and maps it to an empty adjacency list.
   If the vertex already exists, a duplicate entry is added (minor
   caveat — the map uses Vertex.equals, so the new key may collide).

   Example:
     addVertex(10) → graphMap: { 10 → [] }
     addVertex(11) → graphMap: { 10 → [], 11 → [] }


2. addEdge(T source, T destination)  —  O(1) amortized
   -------------------------------------------------------
   Ensures both vertices exist, then appends each to the other's
   adjacency list (undirected edge).

   graphMap.get(sourceV).add(destinationV)
   graphMap.get(destinationV).add(sourceV)

   Example:
     addEdge(10, 12):
       10's list: [12]
       12's list: [10]

     addEdge(10, 11):
       10's list: [12, 11]
       11's list: [10]


3. removeVertex(T val)  —  O(V + E)
   ------------------------------------
   a. Find the vertex for val.
   b. For each neighbour in its adjacency list, remove the back-edge
      (the reference back to val's vertex).
   c. Remove val's vertex from the map entirely.

   Example — remove vertex 10 from the graph:
     Before:
       10 → [12, 11, 13, 14]
       12 → [10, 14]
       11 → [10, 13, 14]
       13 → [11, 10]
       14 → [10, 11, 12]

     Step b: iterate 10's neighbours [12, 11, 13, 14] and remove 10 from each list.
     Step c: remove key 10.

     After:
       12 → [14]
       11 → [13, 14]
       13 → [11]
       14 → [11, 12]


4. printGraph()
   --------------
   Iterates over each map entry and prints:
     sourceData -> neighbour1Data --> neighbour2Data -->

   Example output for a small graph:
     10->12-->11-->14-->
     11->13-->10-->14-->
     12->10-->14-->
     ...


MAIN METHOD WALKTHROUGH
------------------------
  Graph<Integer> g = new Graph<>();
  g.addVertex(10); g.addVertex(11); g.addVertex(12); g.addVertex(13);

  Edges added (undirected):
    10-12, 11-13, 10-14, 10-11, 13-10, 14-11, 12-14

  g.printGraph()   → prints adjacency lists
  g.removeVertex(10)
  g.printGraph()   → 10 no longer appears; its neighbours lose the back-edge


EDGE CASES
----------
  - removeVertex on a vertex with no edges: the inner if(edges.size() > 0)
    guard skips the loop, so graphMap.remove is never called — vertex stays.
    (Minor bug: isolated vertices cannot be removed.)
  - addEdge creates vertices implicitly if they don't exist yet.
  - T must implement equals() and hashCode() consistently for the map to work.
