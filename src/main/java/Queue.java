/**
 * FIFO queue of {@code String} values backed by a singly linked list.
 * Supports O(1) enqueue and dequeue.
 */
public class Queue {

    private class Node{
        Node previous;
        Node next;
        String val;

        Node (String val){
            this.val = val;
        }
        Node( String val,Node next){
            this.val = val;
            this.next = next;
        }
        Node(){

        }
    }

    Node head = null;
    Node tail = null;
    int size = 0;

    /** Adds {@code val} to the tail of the queue. */
    public void enqueue(String val){
        if(head == null){
            head = tail = new Node(val);
            size++;
            return;
        }
        Node newN = new Node(val);
        tail.next = newN;
        tail = newN;
        size++;
    }

    /**
     * Removes and returns the value at the head of the queue.
     *
     * @return head value, or {@code null} if the queue is empty
     */
    public String dequeue(){
        if(size == 0){
            return null;
        }
        if(size ==1){
            head.next = null;
            size--;
            return head.val;
        }
        Node t = head.next;
        head.next = null;
        head = t;
        size--;
        return head.val;
    }

    public void peek(){
        if(head == null) {
            System.out.println("Empty List");
            return;
        }

        Node temp = head;
        for(int i=0; i<size; i++){
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args){
        Queue list = new Queue();
        list.peek();
        list.enqueue("tes1");
        list.enqueue("tes2");
        list.enqueue("tes3");
        list.enqueue("tes4");
        list.enqueue("tes5");
        list.enqueue("tes6");

        list.peek();

        list.dequeue();
        list.peek();
        list.dequeue();
        list.peek();
        list.dequeue();
        list.peek();
        list.dequeue();
        list.dequeue();
        list.peek();
        list.dequeue();
        list.peek();
        list.dequeue();
        list.peek();
    }
}
