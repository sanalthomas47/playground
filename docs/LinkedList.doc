================================================================
CLASS: LinkedList<E>
FILE: src/main/java/LinkedList.java
================================================================

OVERVIEW
--------
LinkedList<E> is a singly linked list that stores String values.
Despite the generic type parameter <E>, the implementation always
stores Strings internally. Nodes only have a "next" pointer —
traversal is forward-only.

  Fields:
    Node head  — first node (null when empty)
    Node tail  — last  node (null when empty)
    int  size  — number of elements


INNER CLASS: Node
-----------------
  String val      — stored value
  Node   next     — pointer to the next node
  Node   previous — declared but never used (singly linked in practice)


DIFFERENCE FROM DoublyLinkedList
----------------------------------
  LinkedList   — nodes only link forward (next); no backward pointer used.
  DoublyLinkedList — nodes link both ways (next + previous).


OPERATIONS & HOW THEY WORK
----------------------------

1. addElement(String val)  —  O(1) append
   -----------------------------------------
   Case A — empty list:
     head = tail = new Node(val)

   Case B — non-empty:
     tail.next = new Node(val)
     tail      = tail.next

   Example:
     addElement("A") → head=A, tail=A
     addElement("B") → A → B          (head=A, tail=B)
     addElement("C") → A → B → C      (head=A, tail=C)


2. addElementAtPosition(int pos, String val)  —  O(n) insert
   -----------------------------------------------------------
   Walks to position pos, collecting the predecessor (prev).
   Splices in the new node between prev and temp.

   newNode.next = temp      (point forward to current occupant)
   prev.next    = newNode   (prev now points to new node)

   NOTE: does NOT update backward links (none exist in this class).

   Example — list: A → B → C → D, insert "X" at pos=2:
     i=0: prev=null, temp=A
     i=1: prev=A,    temp=B
     i=2: prev=B,    temp=C  ← stop
     newNode("X").next = C
     B.next = newNode("X")
     Result: A → B → X → C → D


3. remove(int pos)  —  O(n) removal
   ------------------------------------
   Case A — pos == 0 (remove head):
     t    = head
     head = head.next
     t.next = null        (unlink)

   Case B — middle/tail node:
     Walk to pos, tracking prev.
     prev.next = temp.next
     temp.next = null     (unlink)

   Example — list: A → B → C → D, remove pos=2 (node C):
     count=0: prev=null, temp=A
     count=1: prev=A,    temp=B
     count=2: prev=B,    temp=C  ← stop
     B.next = D
     C.next = null
     Result: A → B → D


4. print()  —  O(n)
   Walks head → tail printing "val->".

   Example output for [A, B, C]:
     A->B->C->


MAIN METHOD WALKTHROUGH
------------------------
  list.addElement("tes1".."tes6")
  → tes1->tes2->tes3->tes4->tes5->tes6->

  list.addElementAtPosition(3, "tes3.5")
  → tes1->tes2->tes3->tes3.5->tes4->tes5->tes6->

  list.remove(4)     (removes tes4, now at index 4)
  → tes1->tes2->tes3->tes3.5->tes5->tes6->

  list.remove(5)     (removes tes6, the tail)
  → tes1->tes2->tes3->tes3.5->tes5->

  list.remove(0)     (removes tes1, the head)
  → tes2->tes3->tes3.5->tes5->


EDGE CASES
----------
  - remove(0) on a one-element list: head = head.next = null; size becomes 0.
    tail still points to the old node — minor stale reference; causes no
    functional bug since head is the guard for emptiness checks.
  - addElementAtPosition when pos > size: silently returns; no insertion.
  - remove when pos > size: returns "element doesn't exist".
  - print() on empty list: prints "Empty List".
