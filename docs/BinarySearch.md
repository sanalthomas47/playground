================================================================
CLASS: BinarySearch
FILE: src/main/java/BinarySearch.java
================================================================

OVERVIEW
--------
BinarySearch implements the classic binary search algorithm on a
sorted integer array. Instead of scanning every element linearly,
it repeatedly halves the search space by comparing the target
against the middle element.

Time Complexity  : O(log n)
Space Complexity : O(1)  — no extra memory, purely iterative


HOW IT WORKS — STEP BY STEP
-----------------------------
Given a sorted array and a target value:

  1. Set two pointers:  left = 0,  right = array.length - 1
  2. While left <= right:
       a. Compute mid = (left + right) / 2
       b. If array[mid] == target  →  found! return mid
       c. If array[mid] <  target  →  target is in right half; move left  = mid + 1
       d. If array[mid] >  target  →  target is in left half;  move right = mid - 1
  3. Loop ends without a match → return -1


VISUAL EXAMPLE
--------------
Array (sorted): [-45, -10, 1, 4, 9, 61, 77, 92]
Target: 61

Step 1:  left=0, right=7  →  mid=3  →  array[3]=4   < 61  →  left=4
Step 2:  left=4, right=7  →  mid=5  →  array[5]=61  == 61 →  FOUND at index 5

Target: 5 (not in array)

Step 1:  left=0, right=7  →  mid=3  →  array[3]=4   < 5   →  left=4
Step 2:  left=4, right=7  →  mid=5  →  array[5]=61  > 5   →  right=4
Step 3:  left=4, right=4  →  mid=4  →  array[4]=9   > 5   →  right=3
Step 4:  left=4 > right=3  →  loop ends → return -1


API
---
  int search(int[] input, int target)
      input  — a sorted integer array (ascending order required)
      target — the value to locate
      returns the zero-based index of target, or -1 if not found


EDGE CASES
----------
  - Target equals Integer.MIN_VALUE or Integer.MAX_VALUE: handled normally.
  - Single-element array: one iteration, direct compare.
  - Target smaller than all elements: right pointer crosses left → -1.
  - Target larger than all elements: left pointer crosses right  → -1.
  - Duplicate values: returns the index of ONE matching element
    (not guaranteed to be first or last).


MAIN METHOD DEMONSTRATION
--------------------------
Array used:
  [Integer.MIN_VALUE, -45, -33, -26, -10, -4, 1, 3, 4, 6, 9, 11,
   45, 56, 61, 77, 92, 111, 325, 666, 667, 680, 747, 750, 900, Integer.MAX_VALUE]

  search(array, 61)           → index 14
  search(array, 747)          → index 22
  search(array, 900)          → index 24
  search(array, Integer.MAX_VALUE) → index 25
