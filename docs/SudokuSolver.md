================================================================
CLASS: SudokuSolver
FILE: src/main/java/SudokuSolver.java
================================================================

OVERVIEW
--------
SudokuSolver solves a standard 9×9 Sudoku puzzle using recursive
backtracking. Empty cells are represented by 0. The board is modified
in place — when the method returns, the board holds the solution.

  Rules of Sudoku:
    - Every row must contain digits 1-9 with no repetition.
    - Every column must contain digits 1-9 with no repetition.
    - Every 3×3 sub-grid must contain digits 1-9 with no repetition.


ALGORITHM: BACKTRACKING
------------------------
Backtracking tries placing a digit; if it leads to a contradiction
later, it "backtracks" (undoes the placement) and tries the next digit.

  solveSudokuRecursively(board, row, col):

    Base case:
      If row==8 and col==9  →  entire board filled → return true

    Column overflow:
      If col==9  →  move to next row: row++, col=0

    Skip pre-filled cell:
      If board[row][col] != 0  →  recurse on (row, col+1)

    Try digits 1-9 for the empty cell:
      For i = 1 to 9:
        If isSafeToInsert(board, row, col, i):
          board[row][col] = i
          If solveSudokuRecursively(board, row, col+1) == true:
            return true          ← solution found down this path
          board[row][col] = 0    ← backtrack: undo the placement

    If no digit works  →  return false  (trigger backtrack in caller)


isSafeToInsert — CONSTRAINT CHECKING
--------------------------------------
Checks three constraints for placing num at (row, col):

  1. Row check:
       Scan all 9 cells in board[row][*].
       If num already exists → not safe.

  2. Column check:
       Scan all 9 cells in board[*][col].
       If num already exists → not safe.

  3. 3×3 box check:
       Compute top-left corner of the box:
         startRow = row - (row % 3)
         startCol = col - (col % 3)
       Scan the 3×3 sub-grid from (startRow, startCol).
       If num already exists → not safe.

  Return true only if all three checks pass.

  Box corner calculation example:
    Cell (4, 7):  startRow = 4 - (4%3) = 4-1 = 3
                  startCol = 7 - (7%3) = 7-1 = 6
    → Box covers rows 3-5, cols 6-8.


STEP-BY-STEP EXAMPLE (first few cells)
-----------------------------------------
Board (0 = empty):
  3 0 6 | 0 0 8 | 4 0 0
  5 0 0 | 0 0 0 | 0 0 0
  0 8 7 | 0 0 0 | 0 3 0
  ------+-------+------
  ...

Cell (0,1) is empty (value=0). Try digits 1-9:
  i=1: isSafe(0,1,1)? Row 0 has [3,6,8,4] → 1 not in row. Col 1 has [8] → 1 not in col.
       Box (0,0): has [3,6,5,8,7] → 1 not in box. SAFE.
       Place 1 at (0,1). Recurse on (0,2).

  (0,2)=6, pre-filled → recurse on (0,3).
  (0,3) is empty. Try 1: row has [3,1,6,8,4] → 1 already in row! Not safe.
  Try 2: safe → place 2, recurse...

  If at any point we reach a dead end (no digit 1-9 fits a cell),
  we return false and the caller backtracks (sets cell back to 0 and
  tries its next digit).


SOLUTION FOR THE GIVEN BOARD
------------------------------
Input:
  3 0 6 | 0 0 8 | 4 0 0
  5 0 0 | 0 0 0 | 0 0 0
  0 8 7 | 0 0 0 | 0 3 0
  0 0 3 | 0 1 0 | 0 8 0
  9 0 0 | 8 6 0 | 0 0 5
  0 5 0 | 0 9 0 | 6 0 0
  0 3 0 | 0 0 0 | 2 5 0
  0 0 0 | 0 0 0 | 0 7 4
  0 0 0 | 2 0 6 | 3 0 0

Output (printed by main):
  3 1 6  5 7 8  4 9 2
  5 2 9  1 3 4  7 6 8
  4 8 7  6 2 9  5 3 1
  2 6 3  4 1 5  9 8 7
  9 7 4  8 6 3  1 2 5
  8 5 1  7 9 2  6 4 3
  1 3 8  9 4 7  2 5 6
  6 9 2  3 5 1  8 7 4
  7 4 5  2 8 6  3 1 9


TIME COMPLEXITY
---------------
  Worst case: O(9^(n)) where n = number of empty cells.
  With the constraint checks, the search space is pruned dramatically
  and the algorithm solves typical puzzles in milliseconds.


EDGE CASES
----------
  - Fully pre-filled board (all non-zero): immediately returns true
    at the base case after scanning all cells without placing anything.
  - Unsolvable board: returns false all the way up; the board is left
    in whatever partial state the backtracking reached (not necessarily
    the original — callers should check the boolean return value).
  - The entry point solveSudoku() ignores the boolean return value;
    the board is printed regardless, so an unsolvable puzzle would
    print a partially or incorrectly filled board.
