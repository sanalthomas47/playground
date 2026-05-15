================================================================
CLASS: Stack
FILE: src/main/java/Stack.java
================================================================

OVERVIEW
--------
Stack implements a LIFO (Last-In First-Out) stack of String values
backed by a singly linked list. The head of the list is always the
top of the stack.

  Fields:
    Node head  — top of the stack (null when empty)
    int  size  — number of elements


INNER CLASS: Node
-----------------
  String val      — stored value
  Node   next     — pointer to the node below in the stack
  Node   previous — declared but not used


LIFO PRINCIPLE
--------------
  Last element pushed is the first element popped.

  Push order:  A, B, C
  Pop  order:  C, B, A  (reverse)

  Analogy: a stack of plates — you add and remove from the top.


OPERATIONS & HOW THEY WORK
----------------------------

1. push(String val)  —  O(1) prepend at head
   ---------------------------------------------
   Case A — empty stack:
     head = new Node(val)

   Case B — non-empty:
     newN.next = head   ← new node points to current top
     head      = newN   ← new node becomes new top

   Example:
     push("A") → head=A
     push("B") → B → A          (head=B)
     push("C") → C → B → A      (head=C)

     Top of stack is C; bottom is A.


2. pop()  —  O(1) removal from head
   ------------------------------------
   Does nothing if the stack is empty (size==0).

   Case — one element:
     head.next = null; head = null; size--

   Case — multiple elements:
     t         = head.next   ← node below top
     head.next = null        ← unlink top
     head      = t           ← new top
     size--

   Example:
     State: C → B → A,  size=3
     pop():
       t = B,  C.next = null,  head = B,  size=2
     State: B → A,  size=2


3. peek()  —  prints entire stack without removing
   --------------------------------------------------
   Walks from head down printing "val -> ".
   Shows the stack from top to bottom.

   Example output for stack [C, B, A] (C on top):
     C -> B -> A ->


MAIN METHOD WALKTHROUGH
------------------------
  push: tes1, tes2, tes3, tes4, tes5, tes6
  Stack (top→bottom): tes6 → tes5 → tes4 → tes3 → tes2 → tes1

  peek() → tes6 -> tes5 -> tes4 -> tes3 -> tes2 -> tes1 ->

  pop() → removes tes6
  peek() → tes5 -> tes4 -> tes3 -> tes2 -> tes1 ->

  pop() → removes tes5
  peek() → tes4 -> tes3 -> tes2 -> tes1 ->

  ... and so on until the stack is empty.

  pop() on empty stack → size==0 guard → returns without error
  peek() on empty stack → prints "Empty List"


COMPARISON WITH Queue
----------------------
  Queue: enqueue adds at TAIL,  dequeue removes from HEAD → FIFO
  Stack: push    adds at HEAD,  pop     removes from HEAD → LIFO

  Both use the linked list's head as the "active end"; the difference
  is where new elements are inserted.


EDGE CASES
----------
  - pop() on empty stack: size==0 guard returns immediately; safe.
  - pop() on single-element stack: head set to null cleanly.
  - After all pops, peek() prints "Empty List".
