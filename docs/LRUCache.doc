================================================================
CLASS: LRUCache
FILE: src/main/java/LRUCache.java
================================================================

OVERVIEW
--------
LRUCache implements a Least Recently Used cache with a fixed capacity.
When the cache is full and a new entry must be inserted, the
least-recently-used entry is evicted to make room.

  Data structures used:
    HashMap<Integer, Node>   lru      — O(1) lookup by key
    Doubly linked list       (node)   — tracks access order
      head (node) = most recently used
      tail (end)  = least recently used

Both structures work together: the map gives instant access to any
node; the linked list maintains usage order without full traversal.


INNER CLASS: Node
-----------------
  int  data  — cached value
  int  key   — cache key (needed for eviction to remove from map)
  Node next  — points toward LRU end (tail)
  Node prev  — points toward MRU end (head)


HOW THE LINKED LIST ENCODES RECENCY
-------------------------------------
  head (node field) = most recently used
  end  (end  field) = least recently used

  Every get or put that touches a key moves that node to the head.
  When capacity is exceeded, the node at end is evicted.

  Example (capacity=3, after a few puts):
    [MRU] C ↔ A ↔ B [LRU]

    get(A) → rearrange A to front:
    [MRU] A ↔ C ↔ B [LRU]


OPERATIONS & HOW THEY WORK
----------------------------

1. put(int key, int data)
   -----------------------
   Three sub-cases:

   Case A — cache is empty (node == null):
     Create first node; both node and end point to it.

   Case B — key is NEW and cache is NOT full:
     Create a new node and prepend it to the head via add().

   Case B' — key is NEW and cache IS FULL:
     Evict: remove end (LRU) from the map, detach it from the list,
     update end to end.prev.
     Then add the new node at the head via add().

   Case C — key EXISTS with a different value:
     Update node.data in place, then call rearrange() to move it to head.

   add(key, node) helper:
     nd.next  = node (old head)
     node.prev = nd
     node     = nd   (new head)
     lru.put(key, nd)


2. rearrange(Node d)  —  move d to the head
   --------------------------------------------
   a. If d is the tail (d.next == null): set end = d.prev, prev.next = null
   b. Unlink d: prev.next = next, next.prev = prev
   c. Prepend d: d.next = node (old head), node.prev = d, d.prev = null, node = d

   Example:
     Before: [C] ↔ [A] ↔ [B]   (head=C, end=B)
     rearrange(A):
       Step a: A is not tail.
       Step b: C.next = B,  B.prev = C   → [C] ↔ [B]
       Step c: A.next = C,  C.prev = A,  A.prev = null,  node = A
     After:  [A] ↔ [C] ↔ [B]   (head=A, end=B)


3. get(int key)  —  O(1)
   -----------------------
   If key is in the map:
     - Fetch the node.
     - If it's not already the head, call rearrange() to promote it.
     - Return node.data.
   Otherwise return -1.


FULL EXAMPLE — capacity=2
--------------------------
  put(2, 6):  list=[2], map={2:Node(6)}
  get(1):     key 1 not in map → return -1
  put(1, 5):  list=[1,2], map={1:Node(5), 2:Node(6)}
  put(1, 2):  key 1 exists, data differs → update data=2, rearrange
              list=[1,2] (1 is already head, no visible change)
  get(1):     → return 2
  get(2):     → rearrange 2 to head → list=[2,1] → return 6

  main() output:
    -1   (get(2) before any put)
    -1   (get(1) before put(1,...))
     2   (get(1) after put(1,5) then put(1,2))
     6   (get(2) after put(2,6))


EVICTION EXAMPLE — capacity=2
-------------------------------
  put(1, A):  list=[1]
  put(2, B):  list=[2,1]    (2 is MRU, 1 is LRU)
  get(1):     rearrange 1 → list=[1,2]
  put(3, C):  cache full (size==2), evict LRU (end=2)
              map.remove(2), detach 2, end = node(1)
              add(3,C) → list=[3,1]


EDGE CASES
----------
  - get on an empty cache: node == null → lru is empty → returns -1.
  - put with the same key and same value: the current code updates
    data and calls rearrange only if data differs; identical put is a no-op.
  - Single-element cache at capacity: eviction sets end to nd (the new node)
    via the else branch in put.
