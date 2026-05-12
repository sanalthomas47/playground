================================================================
CLASS: Merge
FILE: src/main/java/Merge.java
================================================================

OVERVIEW
--------
Merge solves the "merge sorted arrays in place" problem (LeetCode #88).
Given two sorted arrays nums1 and nums2, it merges nums2 into nums1
so that nums1 ends up sorted. nums1 has extra trailing zeros to
accommodate the incoming elements.

  Example:
    nums1 = [1, 2, 3, 0, 0, 0]   (m=3 valid elements)
    nums2 = [2, 3, 4]             (n=3 elements)
    After merge: nums1 = [1, 2, 2, 3, 3, 4]


HOW THE ALGORITHM WORKS
-------------------------
The approach walks through nums1 (up to the last real element slot)
and inserts elements from nums2 at the correct positions.

  counter  — index into nums2 (how many of nums2 have been inserted)

  Outer loop (i from 0 to nums1.length-2):
    If all of nums2 is inserted → break
    If nums1[i] >= nums2[counter]:
      Call updateNums(nums1, i) to shift nums1[i..end] right by one
      (the last element at nums1.length-1 is sacrificed as the "spare").
      Place nums2[counter] at nums1[i].
      Advance counter.
    Else: nums1[i] < nums2[counter], so it stays → continue

  Tail copy:
    If any elements of nums2 remain after the loop,
    copy them into the trailing positions of nums1.


updateNums HELPER
------------------
  Purpose: shift elements in nums[pos..end] one position to the right,
           losing the last element.

  int replace = nums[nums.length-1]   ← save last element (will be lost)
  for i from pos to length-1:
    int curVal = nums[i]
    nums[i]    = replace
    replace    = curVal

  This rotates the segment so that position pos becomes available.

  Example — nums1 = [1, 2, 3, 0, 0, 0], pos=1:
    replace=0 (last element)
    i=1: swap: nums[1]=0,  replace=2  → [1, 0, 3, 0, 0, 0] … wait
    Actually: replace starts at nums[5]=0
    i=1: curVal=nums[1]=2; nums[1]=0;  replace=2  → [1,0,3,0,0,0] - no
    Let's trace carefully:
      replace = nums[5] = 0
      i=1: curVal=nums[1]=2; nums[1]=replace=0; replace=curVal=2  → [1,0,3,0,0,0]... hmm

  Actual trace for [1,2,3,0,0,0] pos=1:
    replace=0
    i=1: curVal=2; nums[1]=0; replace=2 → [1,0,3,0,0,0]
    i=2: curVal=3; nums[2]=2; replace=3 → [1,0,2,0,0,0]
    i=3: curVal=0; nums[3]=3; replace=0 → [1,0,2,3,0,0]
    i=4: curVal=0; nums[4]=0; replace=0 → [1,0,2,3,0,0]
    i=5: curVal=0; nums[5]=0; replace=0 → [1,0,2,3,0,0]
  After: nums1[1] = 0 (free slot), elements shifted right starting from pos.


FULL WALKTHROUGH
-----------------
  nums1 = [1, 2, 3, 0, 0, 0]
  nums2 = [2, 3, 4],   counter=0

  i=0: nums1[0]=1, nums2[0]=2  → 1 < 2 → continue
  i=1: nums1[1]=2, nums2[0]=2  → 2 >= 2 →
         updateNums(nums1, 1)   → [1, 0, 2, 3, 0, 0]
         nums1[1] = 2           → [1, 2, 2, 3, 0, 0]
         counter=1
  i=2: nums1[2]=2, nums2[1]=3  → 2 < 3 → continue
  i=3: nums1[3]=3, nums2[1]=3  → 3 >= 3 →
         updateNums(nums1, 3)   → [1, 2, 2, 0, 3, 0]
         nums1[3] = 3           → [1, 2, 2, 3, 3, 0]
         counter=2
  i=4: nums1[4]=3, nums2[2]=4  → 3 < 4 → continue

  Loop ends (i reaches nums1.length-1=5, loop goes to length-2=4).
  counter=2 < nums2.length=3 → tail copy:
    i starts at nums1.length-1-(nums2.length-1-counter) = 6-1-0 = 5
    nums1[5] = nums2[2] = 4

  Final: nums1 = [1, 2, 2, 3, 3, 4] ✓


EDGE CASES
----------
  - If nums2 is exhausted first: break exits the loop early; no tail copy.
  - If nums1's valid portion is all smaller than nums2: all of nums2
    lands in the tail copy section.
  - The algorithm has known edge-case bugs (the updateNums rotation is not
    a clean right-shift); Merge2 provides a cleaner alternative using a
    separate output array.
