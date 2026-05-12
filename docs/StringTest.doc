================================================================
CLASS: StringTest
FILE: src/main/java/StringTest.java
================================================================

OVERVIEW
--------
StringTest is a scratch / exploratory class used to test Java's
built-in String.substring() method and to reference LinkedHashMap.
It has no production logic — it serves as a quick experiment pad.


WHAT IT DEMONSTRATES
---------------------

1. String.substring(int beginIndex, int endIndex)
   -------------------------------------------------
   Returns a new String from beginIndex (inclusive) to
   endIndex (exclusive).

   The string used: "abcdefgh"  (indices 0-7)

     Index:  0  1  2  3  4  5  6  7
     Char:   a  b  c  d  e  f  g  h

   Calls and their output:
     v.substring(0, 2)  →  "ab"   (chars at index 0 and 1)
     v.substring(0, 1)  →  "a"    (char  at index 0 only)
     v.substring(1, 2)  →  "b"    (char  at index 1 only)

   General rule:
     substring(begin, end) extracts characters at positions
     begin, begin+1, ..., end-1.
     Length of result = end - begin.


2. LinkedHashMap reference
   --------------------------
   A variable of type LinkedHashMap is declared but never initialized
   or used. This line simply verifies that LinkedHashMap can be imported
   and referenced without a compile error.

   LinkedHashMap preserves insertion order (unlike HashMap which makes
   no order guarantees, unlike TreeMap which sorts by key).


SUBSTRING RULES SUMMARY
------------------------
  s.substring(begin)       →  from begin to end of string
  s.substring(begin, end)  →  from begin (inclusive) to end (exclusive)

  Special cases:
    substring(0, s.length()) → returns the full string
    substring(i, i)          → returns "" (empty string)
    substring with end > length → throws StringIndexOutOfBoundsException
    substring with begin > end  → throws StringIndexOutOfBoundsException


NOTES
-----
  - The fully qualified name java.lang.String is used explicitly
    (java.lang.String v = ...) rather than just String — both are
    identical since java.lang is auto-imported, demonstrating that
    explicit qualification is valid but unnecessary.
  - This class has no meaningful logic to test or extend.
