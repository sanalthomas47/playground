================================================================
CLASS: BinaryTree
FILE: src/main/java/BinaryTree.java
================================================================

OVERVIEW
--------
BinaryTree implements a Binary Search Tree (BST). Every node
satisfies the BST invariant:
  - All values in the LEFT  subtree < node value
  - All values in the RIGHT subtree > node value
  - Duplicates are silently ignored

Supported operations:
  - insert      — recursive insertion maintaining BST order
  - inOrder     — Left → Root → Right  (produces sorted output)
  - preOrder    — Root → Left → Right
  - postOrder   — Left → Right → Root
  - levelOrder  — breadth-first, level by level
  - sumAtLevel  — sum of all node values at a given depth


INNER CLASS: Node
-----------------
  int  val    — the stored integer
  Node left   — left child (null if absent)
  Node right  — right child (null if absent)


HOW INSERTION WORKS
--------------------
insert(5) → root = Node(5)
insert(3) → 3 < 5 → go left  → root.left  = Node(3)
insert(7) → 7 > 5 → go right → root.right = Node(7)
insert(4) → 4 < 5 → go left (Node 3) → 4 > 3 → go right → Node(3).right = Node(4)

Resulting tree:

            5
           / \
          3   7
           \
            4


TRAVERSAL EXAMPLES
-------------------
Tree built from: 5, 3, 4, 2, 7, 6, 8, 9, 1

              5
            /   \
           3     7
          / \   / \
         2   4 6   8
        /           \
       1             9

inOrder   (L→Root→R): 1  2  3  4  5  6  7  8  9   ← always sorted for BST
preOrder  (Root→L→R): 5  3  2  1  4  7  6  8  9
postOrder (L→R→Root): 1  2  4  3  6  9  8  7  5
levelOrder (BFS):
  Level 0:  5
  Level 1:  3   7
  Level 2:  2   4   6   8
  Level 3:  1           9


HOW LEVEL-ORDER (BFS) WORKS
-----------------------------
Uses a Queue<Node>. Algorithm:

  1. Enqueue root.
  2. While queue is not empty:
       a. count = queue.size()  (all nodes at current level)
       b. Print a new line.
       c. For i in 0..count-1:
            - Dequeue node, print its value.
            - Enqueue its non-null children.

Example (first two iterations):
  Queue: [5]          → print "5",   enqueue 3, 7
  Queue: [3, 7]       → print "3 7", enqueue 2, 4, 6, 8
  Queue: [2, 4, 6, 8] → print "2 4 6 8", ...


HOW sumAtLevel WORKS
---------------------
sumAtLevel(level) uses the same BFS loop but tracks a currentLevel counter.
When currentLevel == level, it accumulates the sum of all dequeued node
values and breaks.

Example — sumAtLevel(1) on the tree above:
  currentLevel=0: process [5] → not level 1 → enqueue 3, 7 → currentLevel=1
  currentLevel=1: process [3, 7] → sum = 3+7 = 10 → print "sum:10" → break


API
---
  void insert(int val)
  void inOrder()
  void preOrder()
  void postOrder()
  void levelOrder()
  void sumAtLevel(int level)     — 0-indexed depth


EDGE CASES
----------
  - Empty tree: all traversals return immediately.
  - Single node: inOrder/preOrder/postOrder each print that one value.
  - Duplicate insert: the recursive insert skips (neither < nor >) — node unchanged.
  - sumAtLevel beyond tree height: loop exhausts the queue; prints nothing.
