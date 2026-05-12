import java.util.Arrays;
import java.util.LinkedList;

/**
 * Custom hash map implementation using separate chaining (array of linked lists).
 * Fixed capacity of 20 buckets; does not resize. Supports put, get, and remove.
 *
 * @param <K> key type; must implement {@code hashCode} and {@code equals}
 * @param <V> value type
 */
public class HashMap<K,V> {

    private class Entry<K,V>{
        K key;
        V value;

        Entry(K key, V value){
            this.key = key;
            this.value = value;
        }
        public String toString(){
            return key + "->" + value;
        }
    }
    int capacity = 20;
    LinkedList<Entry<K,V>>[] buckets = new LinkedList[capacity];

    /**
     * Inserts or updates the entry for {@code key} with {@code val}.
     *
     * @param key map key
     * @param val value to associate with the key
     */
    public void put(K key, V val){

        int index = key.hashCode()%capacity;
        LinkedList<Entry<K,V>> entry = buckets[index];
        if(entry == null){
            LinkedList<Entry<K,V>> newEntry = new LinkedList<Entry<K, V>>();
            newEntry.add(new Entry<K,V>(key, val));
            buckets[index] = newEntry;
        }else {
            for(Entry<K,V> item: entry) {
                if (item.key.equals(key)) {
                    item.value = val;
                    return;
                }
            }
            entry.add(new Entry<K,V>(key, val));
        }
    }

    /**
     * Removes and returns the entry for {@code key}, or {@code null} if absent.
     *
     * @param key map key to remove
     * @return the removed entry, or {@code null}
     */
    public Entry<K,V> remove(K key){
        int index = key.hashCode()%capacity;
        LinkedList<Entry<K,V>> entries = buckets[index];
        if(entries == null){
            //nothing
        }
        for(Entry<K,V> entry:entries){
            if(entry.key.equals(key)){
                entries.remove(entry);
                return entry;
            }
        }
        return null;
    }

    /**
     * Returns the value associated with {@code key}, or {@code null} if not found.
     *
     * @param key map key to look up
     * @return associated value, or {@code null}
     */
    public V get(K key){
        int index = key.hashCode()%capacity;
        LinkedList<Entry<K,V>> entries = buckets[index];
        if(entries == null){
            return null;
        }
        for(Entry<K,V> entry:entries){
            if(entry.key.equals(key)){
                return entry.value;
            }
        }
        return null;
    }

    public String toString(){
        System.out.println( Arrays.toString(buckets));
        return null;
    }

    public static void main(String[] args){
        HashMap hashMap  = new HashMap();

        hashMap.put("hello", "hello");
        hashMap.put(1, 3);
        hashMap.put(10, 3);
        hashMap.put(100, 3);
        hashMap.put(1000, 3);
        hashMap.toString();
        hashMap.remove(1000);
        hashMap.get(100);
        hashMap.toString();
    }
}
