================================================================
CLASS: GraphWeighted<T>
FILE: src/main/java/GraphWeighted.java
================================================================

OVERVIEW
--------
GraphWeighted<T> is a generic undirected weighted graph. Each edge
carries an integer weight. The class also implements Dijkstra's
shortest-path algorithm to find the minimum-cost path from a
source vertex to all other vertices.

  Internal structure:
    Map<Vertex<T>, LinkedList<Edge>> graphMap

  An Edge stores:
    Vertex<T> destination  — the other endpoint
    int       weight       — the edge cost


INNER CLASSES
-------------
  Vertex<T>
    T data         — stored value
    equals/hashCode/toString delegate to data.

  Edge
    Vertex<T> destination
    int       weight
    equals/hashCode consider both destination and weight.
    toString: "Edge{destination=Vertex{data=X}, weight=W}"


OPERATIONS & HOW THEY WORK
----------------------------

1. addVertex(T v)  —  O(1)
   Maps a new Vertex to an empty edge list.


2. addEdge(T source, T destination, int weight)  —  O(1)
   -------------------------------------------------------
   Creates two Edge objects (one per direction) so the graph
   remains undirected.

   graphMap.get(sourceV).add( new Edge(destinationV, weight) )
   graphMap.get(destinationV).add( new Edge(sourceV,  weight) )

   Example:
     addEdge(10, 12, 100):
       10's list: [Edge(12, 100)]
       12's list: [Edge(10, 100)]


3. removeVertex(T val)  —  O(V + E)
   ------------------------------------
   For each Edge in val's list, removes the back-edge from the
   neighbour's list using Edge.equals (matches on destination + weight),
   then removes val from the map.


4. shortestPath(T source)  —  Dijkstra's Algorithm  O((V+E) log V)
   -------------------------------------------------------------------
   Goal: find the shortest weighted distance from source to every
         other vertex.

   Algorithm:
     a. Initialize distance map: all vertices → Integer.MAX_VALUE
        Set distance[source] = 0
     b. Push source into a min-PriorityQueue ordered by distance.
     c. While the queue is not empty:
          - Poll the vertex u with minimum known distance.
          - For each Edge e from u to neighbour v:
              newDist = distance[u] + e.weight
              If newDist < distance[v]:
                distance[v] = newDist
                Push v into the queue (with updated distance).
     d. Print all (vertex → distance) pairs.

   WHY IT WORKS:
   The priority queue always processes the closest unvisited vertex
   next. Because edge weights are non-negative, once a vertex is
   settled (popped from the queue), its distance is final.


STEP-BY-STEP EXAMPLE
----------------------
Graph (subset of main):
  10 --[80]-- 11
  10 --[100]- 12
  10 --[120]- 14
  11 --[110]- 13
  12 --[110]- 14

shortestPath(12):
  Initial distances: {10:MAX, 11:MAX, 12:0, 13:MAX, 14:MAX}
  Queue: [12]

  Poll 12 (dist=0):
    Neighbour 10: 0+100=100 < MAX → dist[10]=100, enqueue 10
    Neighbour 14: 0+110=110 < MAX → dist[14]=110, enqueue 14

  Poll 10 (dist=100):
    Neighbour 12: 100+100=200 > 0 → skip
    Neighbour 11: 100+80 =180 < MAX → dist[11]=180, enqueue 11
    Neighbour 14: 100+120=220 > 110 → skip
    Neighbour 13: 100+130=230 < MAX → dist[13]=230, enqueue 13

  Poll 14 (dist=110):
    Neighbour 10: 110+120=230 > 100 → skip
    Neighbour 11: 110+90 =200 > 180 → skip
    ...

  Final distances from 12:
    12 → 0
    10 → 100
    14 → 110
    11 → 180
    13 → 230


MAIN METHOD WALKTHROUGH
------------------------
  Builds a graph of integers 10–14 with various weighted edges.
  Calls printGraph() to display adjacency lists.
  Calls shortestPath(12) to print min distances from vertex 12.


EDGE CASES
----------
  - Disconnected vertices remain at Integer.MAX_VALUE distance.
  - Duplicate edges between the same pair increase the adjacency list
    but Dijkstra will always settle on the minimum path.
  - The PriorityQueue comparator captures the distance map by reference,
    so distances updated mid-algorithm are reflected correctly during polling.
