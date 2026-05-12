================================================================
CLASS: DoublyLinkedList
FILE: src/main/java/DoublyLinkedList.java
================================================================

OVERVIEW
--------
DoublyLinkedList is a doubly linked list that stores String values.
Each node holds a reference to both its previous and next neighbour,
enabling efficient traversal in both directions.

  Fields:
    Node head  — first node (null for empty list)
    Node tail  — last  node (null for empty list)
    int  size  — number of elements


INNER CLASS: Node
-----------------
  String val      — stored value
  Node   previous — pointer to predecessor
  Node   next     — pointer to successor


OPERATIONS & HOW THEY WORK
----------------------------

1. addElement(String val)  —  O(1) append
   -----------------------------------------
   Case A — list is empty:
     head = tail = new Node(val)

   Case B — list has elements:
     newNode.previous = tail
     tail.next        = newNode
     tail             = newNode

   Example:
     addElement("A") → head=A, tail=A
     addElement("B") → A <-> B          (head=A, tail=B)
     addElement("C") → A <-> B <-> C    (head=A, tail=C)


2. addElementAtPosition(int pos, String val)  —  O(n) insert
   -----------------------------------------------------------
   Walks to position pos, then splices a new node between
   the node at pos-1 (prev) and the node at pos (temp).

   Before: ... prev <-> temp ...
   After:  ... prev <-> newNode <-> temp ...

   Pointer updates:
     newNode.next     = temp
     temp.previous    = newNode
     newNode.previous = prev
     prev.next        = newNode

   Example — list: A <-> B <-> C <-> D, insert "X" at pos=2:
     Walk: prev=B, temp=C
     Result: A <-> B <-> X <-> C <-> D


3. remove(int pos)  —  O(n) removal
   ----------------------------------
   Three cases:

   Case A — pos == 0 (remove head):
     new head = head.next
     new head.previous = null

   Case B — pos == size-1 (remove tail):
     new tail = tail.previous
     new tail.next = null

   Case C — middle node:
     Walk to pos.  temp is the node to remove, prev is its predecessor.
     prev.next        = temp.next
     temp.next.prev   = prev
     Unlink temp:  temp.next = null, temp.previous = null

   Example — list: A <-> B <-> C <-> D, remove pos=2 (node C):
     prev=B, temp=C
     B.next = D,  D.previous = B
     C is now disconnected.
     Result: A <-> B <-> D


4. print()  —  forward traversal
   print() walks from head → tail printing "val <-> ".

   Example output for [A, B, C]:
     A <-> B <-> C <->

5. printReverse()  —  backward traversal
   Walks from tail → head printing "val <-> ".

   Example output for [A, B, C]:
     C <-> B <-> A <->


MAIN METHOD WALKTHROUGH
------------------------
  list.addElement("tes1" .. "tes6")
  → tes1 <-> tes2 <-> tes3 <-> tes4 <-> tes5 <-> tes6

  list.addElementAtPosition(3, "tes3.5")
  → tes1 <-> tes2 <-> tes3 <-> tes3.5 <-> tes4 <-> tes5 <-> tes6

  list.remove(4)   (removes tes4)
  → tes1 <-> tes2 <-> tes3 <-> tes3.5 <-> tes5 <-> tes6

  list.remove(5)   (removes tes6, now the tail)
  → tes1 <-> tes2 <-> tes3 <-> tes3.5 <-> tes5

  list.remove(0)   (removes tes1, the head)
  → tes2 <-> tes3 <-> tes3.5 <-> tes5


EDGE CASES
----------
  - remove(0) on a one-element list: head becomes null (note: code sets
    head = head.next, but head.next is null, so head.previous = null would NPE —
    defensive check is missing; safe only when size > 1).
  - addElementAtPosition beyond size: silently returns without inserting.
  - remove beyond size: returns "element doesn't exist" string.
