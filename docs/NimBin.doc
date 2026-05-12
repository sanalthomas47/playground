================================================================
CLASS: NimBin
FILE: src/main/java/NimBin.java
================================================================

OVERVIEW
--------
NimBin determines the winner of a Nim game using XOR-based
combinatorial game theory (Sprague-Grundy / Nimber theory).

In the Nim game:
  - There are n piles of objects.
  - Players alternate turns.
  - A player must remove at least one object from exactly one pile.
  - The player who cannot move (all piles empty) loses.

The class determines whether Alice (first player) or Bob wins,
assuming both play optimally.


THE MATH: WHY XOR WORKS
-------------------------
XOR of all pile sizes is called the "nim-sum".

Standard Nim theory (misère aside):
  - nim-sum == 0  →  the current player (Alice here) is in a LOSING position
                     (any move she makes will give Bob a non-zero nim-sum).
  - nim-sum != 0  →  the current player can always make a move that
                     leaves the opponent with nim-sum == 0.

The code adds a second condition: if the number of piles (n) is even,
Alice wins. This appears to handle a variant or a specific rule
about even pile counts.


FINDING THE WINNER
-------------------
  res = A[0] XOR A[1] XOR ... XOR A[n-1]

  if (res == 0 OR n % 2 == 0):  Alice wins
  else:                          Bob   wins


HOW XOR ENCODES THE GAME STATE
--------------------------------
XOR (^) operates bitwise. For Nim, the key property is:

  If you XOR all piles and get 0, every bit "cancels out" —
  meaning the piles are "balanced" and the current player has no
  winning move.

  If XOR != 0, there is always at least one pile you can reduce
  to make the total XOR equal to 0, leaving your opponent in the
  losing position.


EXAMPLE WALKTHROUGH
--------------------
  A = [1, 4, 3, 5],  n = 4

  Step 1: res = 0
  Step 2: res ^= 1  → res = 1   (binary: 001)
  Step 3: res ^= 4  → res = 5   (binary: 001 XOR 100 = 101)
  Step 4: res ^= 3  → res = 6   (binary: 101 XOR 011 = 110)
  Step 5: res ^= 5  → res = 3   (binary: 110 XOR 101 = 011)

  res = 3 (non-zero), BUT n = 4 (even) → Alice wins.


ANOTHER EXAMPLE (pure XOR rule)
---------------------------------
  A = [1, 2, 3],  n = 3

  res = 1 XOR 2 XOR 3
      = (01 XOR 10) XOR 11
      = 11 XOR 11
      = 00 = 0

  res == 0 → Alice wins (she faces a losing position but the condition
  catches it — or interpreted as: Bob would be first to be stuck).


EDGE CASES
----------
  - Single pile [0]: res=0 → Alice wins (no moves possible; Bob can't move).
  - All piles of 1 and n is even: XOR = 0 (even XORs of 1) → Alice wins.
  - All piles of 1 and n is odd:  XOR = 1 → Bob wins (standard Nim).
  - n = 0 (empty): res stays 0 → n%2 == 0 → Alice wins (degenerate case).
