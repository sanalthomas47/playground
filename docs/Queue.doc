================================================================
CLASS: Queue
FILE: src/main/java/Queue.java
================================================================

OVERVIEW
--------
Queue implements a FIFO (First-In First-Out) queue of String values
backed by a singly linked list. Elements are added at the tail
(enqueue) and removed from the head (dequeue).

  Fields:
    Node head  — front of the queue (next to be dequeued)
    Node tail  — back of the queue  (most recently enqueued)
    int  size  — number of elements


INNER CLASS: Node
-----------------
  String val      — stored value
  Node   next     — pointer to the next node toward the tail
  Node   previous — declared but not used (queue is singly linked)


FIFO PRINCIPLE
--------------
  First element enqueued is the first element dequeued.

  Enqueue order:  A, B, C
  Dequeue order:  A, B, C  (same order)

  Analogy: a line at a checkout — first person in line is first served.


OPERATIONS & HOW THEY WORK
----------------------------

1. enqueue(String val)  —  O(1) append at tail
   -----------------------------------------------
   Case A — empty queue:
     head = tail = new Node(val)

   Case B — non-empty:
     newN        = new Node(val)
     tail.next   = newN
     tail        = newN

   Example:
     enqueue("A") → head=A, tail=A
     enqueue("B") → head=A, tail=B   (A.next = B)
     enqueue("C") → head=A, tail=C   (B.next = C)

     State: A → B → C
            ^head      ^tail


2. dequeue()  —  O(1) removal from head
   ----------------------------------------
   Returns null if the queue is empty.

   Case — one element:
     head.next = null; size--; return head.val
     (Note: head and tail still point to the old node after this —
      minor stale reference, but head is checked for size==0 next time.)

   Case — multiple elements:
     t    = head.next     ← save the second node
     head.next = null     ← unlink current head
     head = t             ← advance head
     size--
     return head.val      ← returns the NEW head's value (second element)

   NOTE: the method returns head.val AFTER advancing head, so it returns
   the value of the node that BECAME the new head, not the removed one.
   This is a subtle behaviour — for size==1 it returns the only element.

   Example:
     State: A → B → C,  size=3
     dequeue():
       t = B,  A.next = null,  head = B,  size=2
       returns "B"  (the new head, NOT "A")

     State: B → C,  size=2
     dequeue():
       t = C,  B.next = null,  head = C,  size=1
       returns "C"


3. peek()  —  prints entire queue without removing
   --------------------------------------------------
   Walks from head to tail printing "val -> ".

   Example output for [A, B, C]:
     A -> B -> C ->


MAIN METHOD WALKTHROUGH
------------------------
  Enqueue: tes1, tes2, tes3, tes4, tes5, tes6
  peek() → tes1 -> tes2 -> tes3 -> tes4 -> tes5 -> tes6 ->

  dequeue() → returns "tes2" (new head after removing tes1)
  peek()    → tes2 -> tes3 -> tes4 -> tes5 -> tes6 ->

  dequeue() → returns "tes3"
  peek()    → tes3 -> ...  (and so on until queue is empty)


EDGE CASES
----------
  - dequeue() on empty queue (size==0): returns null immediately.
  - dequeue() on single-element queue (size==1): returns the element's
    value; head/tail become stale but size=0 guards future operations.
  - peek() on empty queue: prints "Empty List" and returns.
  - The dequeue return value is the NEW head (after advancing), not the
    removed head — this is a logic quirk in the implementation.
