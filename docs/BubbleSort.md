================================================================
CLASS: BubbleSort
FILE: src/main/java/BubbleSort.java
================================================================

OVERVIEW
--------
BubbleSort sorts an integer array in ascending order using the
bubble sort algorithm. It repeatedly compares pairs of elements
and swaps them if they are out of order.

Time Complexity  : O(n²) — worst and average case
Space Complexity : O(1)  — in-place, no extra array needed


HOW IT WORKS — STEP BY STEP
-----------------------------
Outer loop index i runs from 0 to n-1.
Inner loop index j runs from i to n-1.
At each step: if array[i] > array[j], swap them.

This is a selection-style variation of bubble sort: after each
pass of i, array[i] holds the smallest remaining unsorted element.


VISUAL EXAMPLE
--------------
Input: [5, 3, 8, 1, 4]

i=0:
  j=0: array[0]=5 vs array[0]=5  → no swap → [5, 3, 8, 1, 4]
  j=1: array[0]=5 vs array[1]=3  → swap    → [3, 5, 8, 1, 4]
  j=2: array[0]=3 vs array[2]=8  → no swap → [3, 5, 8, 1, 4]
  j=3: array[0]=3 vs array[3]=1  → swap    → [1, 5, 8, 3, 4]
  j=4: array[0]=1 vs array[4]=4  → no swap → [1, 5, 8, 3, 4]
  → array[0] = 1 (minimum is now fixed)

i=1:
  j=1: 5 vs 5 → no swap
  j=2: 5 vs 8 → no swap
  j=3: 5 vs 3 → swap    → [1, 3, 8, 5, 4]
  j=4: 3 vs 4 → no swap
  → array[1] = 3

i=2:
  j=2: 8 vs 8 → no swap
  j=3: 8 vs 5 → swap    → [1, 3, 5, 8, 4]
  j=4: 5 vs 4 → swap    → [1, 3, 4, 8, 5]
  → array[2] = 4

i=3:
  j=3: 8 vs 8 → no swap
  j=4: 8 vs 5 → swap    → [1, 3, 4, 5, 8]
  → array[3] = 5

i=4:
  j=4: 8 vs 8 → no swap

Final: [1, 3, 4, 5, 8]  ✓


SWAP MECHANISM
--------------
  int tmp  = array[i];
  array[i] = array[j];
  array[j] = tmp;

A temporary variable holds one value while the other is overwritten.


API
---
  static int[] bubbleSort(int[] array)
      Sorts array in place and returns the same array reference.


MAIN METHOD DEMONSTRATION
--------------------------
  - Creates an array of 100 random integers in range [0, 1_000_000).
  - Prints the unsorted array.
  - Prints the sorted array.


EDGE CASES
----------
  - Empty array (length 0): both loops never execute; returns as-is.
  - Single element: inner loop runs once (j=i=0), no swap needed.
  - Already sorted: no swaps occur; still O(n²) comparisons.
  - All same values: no swaps; output unchanged.
  - Negative values: handled correctly since integer comparison works for all int values.
