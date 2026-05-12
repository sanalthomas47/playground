import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    class Node {
        Node next;
        Node prev;
        int data;
        int key;
        Node(int data, int key){
            this.data = data;
            this.key = key;
        }
    }
    private Map<Integer, Node> lru = null;
    private Node node = null;
    private int capacity = 0;
    private Node end;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        lru = new HashMap<Integer, Node>(capacity);
    }

    public int get(int key) {
        if(lru.containsKey(key)){
            Node nd = lru.get(key);
            if (nd != node)
                rearrange(nd);
            return lru.get(key).data;
        }
        return -1;
    }

    private void rearrange(Node d) {

        Node prev = d.prev;
        Node next = d.next;

        if(d.next == null){
            end = prev;
            prev.next = null;
        }

        if(prev != null)
            prev.next = next;
        if(next!=null)
            next.prev = prev;

        d.next = node;
        node.prev = d;
        d.prev = null;
        node = d;
    }

    private void add(int key, Node nd){
        nd.next = node;
        node.prev = nd;
        node = nd;
        lru.put(key, nd);
    }

    public void put(int key, int data) {

        if(node == null){
            node = new Node(data,key);
            lru.put(key, node);
            end = node;
        }
        else if(lru.get(key) == null ){

            Node nd = new Node(data,key);

            if(lru.size() == capacity){
                lru.remove(end.key);
                Node d = end.prev;
                if(d != null){
                    d.next = null;
                    end = d;
                }
                else
                    end = nd;
            }
            add(key, nd);
        }else if(lru.get(key).data != data){
            lru.get(key).data = data;
            rearrange(lru.get(key));
        }

    }
    public static void main(String[] args){

        LRUCache cache = new LRUCache(2);
        /*cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));
        cache.put(3,3);
        System.out.println(cache.get(2));
        cache.put(4,4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));*/


       /* cache.put(2,1);
        cache.put(2,2);
        System.out.println(cache.get(2));
        cache.put(1,1);
        cache.put(4,1);
        System.out.println(cache.get(2));*/



        System.out.println(cache.get(2));
        cache.put(2,6);
        System.out.println(cache.get(1));
        cache.put(1,5);
        cache.put(1,2);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }
}
