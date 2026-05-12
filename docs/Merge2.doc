================================================================
CLASS: Merge2
FILE: src/main/java/Merge2.java
================================================================

OVERVIEW
--------
Merge2 provides a clean implementation of the merge-two-sorted-arrays
problem using a dedicated output array. Unlike Merge.java (in-place),
this approach allocates a new array of size m+n and fills it with
the sorted merged result.

It also includes JUnit test cases runnable from the main method.


HOW mergeArrays WORKS
-----------------------
  1. Handle degenerate cases: if either input is empty, return the other.
  2. Route to the private merge() helper, passing the longer array as
     "large" and the shorter as "small".
  3. merge() fills finalArr using a two-pointer technique.
  4. Print and return the merged array.


TWO-POINTER MERGE (merge helper)
----------------------------------
  i      — index into large[]
  j      — index into small[]
  counter— index into finalArr[]

  While both arrays have remaining elements:
    If large[i] < small[j]:  pick large[i], advance i
    Else:                     pick small[j], advance j
    Advance counter.

  Drain remaining elements:
    Copy any leftover small[] elements.
    Copy any leftover large[] elements.

  This guarantees the output is sorted because both inputs are sorted
  and we always pick the smaller of the two current front elements.


STEP-BY-STEP EXAMPLE
----------------------
  myArray    = [2, 4, 6]
  alicesArray= [1, 3, 7]

  large=[2,4,6], small=[1,3,7]  (equal length; myArray chosen as large)
  finalArr = new int[6]
  i=0, j=0, counter=0

  Iteration 1: large[0]=2 vs small[0]=1 → 2 >= 1 → pick small[0]=1 → finalArr=[1,...], j=1
  Iteration 2: large[0]=2 vs small[1]=3 → 2 <  3 → pick large[0]=2 → finalArr=[1,2,...], i=1
  Iteration 3: large[1]=4 vs small[1]=3 → 4 >= 3 → pick small[1]=3 → finalArr=[1,2,3,...], j=2
  Iteration 4: large[1]=4 vs small[2]=7 → 4 <  7 → pick large[1]=4 → finalArr=[1,2,3,4,...], i=2
  Iteration 5: large[2]=6 vs small[2]=7 → 6 <  7 → pick large[2]=6 → finalArr=[1,2,3,4,6,...], i=3
  i=3 == large.length → inner while exits

  Drain small: small[2]=7 → finalArr=[1,2,3,4,6,7]  ✓

DIFFERENT LENGTH EXAMPLE
--------------------------
  myArray    = [2, 4, 6, 8]
  alicesArray= [1, 7]

  large=[2,4,6,8] (len=4), small=[1,7] (len=2)
  i=0,j=0,counter=0

  2 vs 1 → pick 1 → [1,...], j=1
  2 vs 7 → pick 2 → [1,2,...], i=1
  4 vs 7 → pick 4 → [1,2,4,...], i=2
  6 vs 7 → pick 6 → [1,2,4,6,...], i=3
  8 vs 7 → pick 7 → [1,2,4,6,7,...], j=2  (j==small.length → inner loop exits)

  Drain large: large[3]=8 → finalArr=[1,2,4,6,7,8]  ✓


JUNIT TESTS
-----------
  bothArraysHaveSomeNumbersTest:
    Input: [2,4,6] and [1,3,7]
    Expected: [1,2,3,4,6,7]
    → passes ✓

  arraysAreDifferentLengthsTest:
    Input: [2,4,6,8] and [1,7]
    Expected: [1,2,4,6,7,8]
    → passes ✓

  (Commented-out tests for empty arrays exist in the source but are disabled.)


MAIN METHOD
-----------
  Uses JUnitCore.runClasses(Merge2.class) to run all @Test methods
  programmatically and report pass/fail without an IDE or build tool.


COMPARISON WITH Merge.java
----------------------------
  Merge.java   — in-place, complex rotation logic, some edge-case bugs.
  Merge2.java  — allocates new array, clean two-pointer approach, correct.
