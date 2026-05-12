================================================================
CLASS: HashMap<K, V>
FILE: src/main/java/HashMap.java
================================================================

OVERVIEW
--------
HashMap<K,V> is a custom hash map implementation using separate
chaining to resolve collisions. It stores key-value pairs in an
array of linked lists (buckets). Fixed capacity of 20 buckets;
the map does not resize.

  Internal structure:
    LinkedList<Entry<K,V>>[] buckets   (length = capacity = 20)

  Entry<K,V>:
    K key
    V value
    toString: "key->value"


HOW HASHING WORKS
------------------
Bucket index = key.hashCode() % capacity

Every key maps to exactly one bucket. Multiple keys may land in
the same bucket (collision), which is resolved by storing them all
in that bucket's linked list.

Example (capacity=20):
  "hello".hashCode() % 20 → some index, e.g. 9
  (Integer) 1 .hashCode() % 20 → 1
  (Integer) 10.hashCode() % 20 → 10
  (Integer) 100.hashCode()% 20 → 0 (100 % 20 = 0)
  (Integer) 1000.hashCode()%20 → 0 (1000 % 20 = 0) ← collision with 100


OPERATIONS & HOW THEY WORK
----------------------------

1. put(K key, V val)  —  O(1) amortized, O(n) worst case
   --------------------------------------------------------
   a. Compute index = key.hashCode() % capacity
   b. If bucket[index] is null → create new list, add Entry(key, val)
   c. If bucket[index] exists:
        - Scan list for an existing entry with the same key
        - If found → update its value (overwrite)
        - If not found → append new Entry(key, val)

   Example:
     put(1, 3)   → bucket[1]  = [(1→3)]
     put(100, 3) → bucket[0]  = [(100→3)]
     put(1000, 3)→ bucket[0]  = [(100→3), (1000→3)]  ← chained


2. get(K key)  —  O(1) amortized, O(n) worst case
   --------------------------------------------------
   a. Compute index = key.hashCode() % capacity
   b. If bucket[index] is null → return null
   c. Scan the bucket's list for key.equals(entry.key) → return entry.value
   d. Not found → return null

   Example:
     get(100) → bucket[0] → scan [(100→3), (1000→3)] → found key=100 → return 3
     get(999) → bucket[19] → null → return null


3. remove(K key)  —  O(1) amortized, O(n) worst case
   -----------------------------------------------------
   a. Compute index = key.hashCode() % capacity
   b. Scan bucket list for matching key → remove and return that Entry
   c. Not found → return null

   Example:
     remove(1000) → bucket[0] → finds (1000→3) → removes it
     bucket[0] now = [(100→3)]


4. toString()  —  prints all buckets
   ------------------------------------
   Calls Arrays.toString(buckets), which invokes each LinkedList's
   toString(), which calls Entry.toString() for each element.

   Example output (partial):
     [null, [(1→3)], null, ..., [(100→3)], ..., [(hello→hello)], ...]


COLLISION EXAMPLE WALKTHROUGH
-------------------------------
  put(100, "A")   → bucket[0]  = [(100→A)]
  put(1000, "B")  → bucket[0]  = [(100→A), (1000→B)]   ← collision!

  get(1000) → bucket[0] → scan: key=100? no. key=1000? yes → return "B"

  remove(100) → bucket[0] → removes (100→A) → bucket[0] = [(1000→B)]


MAIN METHOD WALKTHROUGH
------------------------
  hashMap.put("hello", "hello")
  hashMap.put(1, 3)
  hashMap.put(10, 3)
  hashMap.put(100, 3)
  hashMap.put(1000, 3)   ← collides with 100 at bucket[0]
  hashMap.toString()     → prints all buckets
  hashMap.remove(1000)   → removes from bucket[0]
  hashMap.get(100)       → returns 3
  hashMap.toString()     → prints updated buckets


EDGE CASES & LIMITATIONS
--------------------------
  - Negative hashCode: Java's % can return negative values for negative
    hashCodes (e.g. String with high char values). This could cause an
    ArrayIndexOutOfBoundsException. The standard fix is
    Math.abs(key.hashCode()) % capacity.
  - Null key: key.hashCode() will throw NullPointerException.
  - No resizing: as the map fills, chains grow and performance degrades
    toward O(n) per operation.
  - remove() iterates with for-each and calls entries.remove() inside —
    this is safe with LinkedList but would throw ConcurrentModificationException
    with ArrayList.
