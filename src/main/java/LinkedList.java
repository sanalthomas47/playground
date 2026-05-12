/**
 * Singly linked list of {@code String} values with O(1) append and
 * O(n) positional insert/remove. The type parameter {@code E} is declared
 * but unused internally — elements are stored as {@code String}.
 *
 * @param <E> unused type parameter (retained for API symmetry)
 */
public class LinkedList<E> {

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

    public LinkedList(){

    }

    /** Appends {@code val} to the tail of the list. */
    public void addElement(String val){
        if(head == null){
            head = tail = new Node(val);
            size++;
            return;
        }
        tail.next = new Node(val);
        tail = tail.next;
        size++;
    }

    /**
     * Removes the node at zero-based {@code pos}.
     *
     * @param pos position to remove (0 = head)
     * @return empty string on success, or an error message if out of bounds
     */
    public String remove(int pos){
        if(pos>size){
            return "element doesn't exist";
        }
        if(pos == 0){
            Node t = head;
            head = head.next;
            t.next = null;
            size--;
            return "";
        }
        Node temp = head;
        Node prev = null;
        int count = 0;
        while(count <pos){
            prev = temp;
            temp = temp.next;
            count++;
        }

        prev.next = temp.next;
        temp.next = null;
        size--;
        return "deleted";
    }


    /**
     * Inserts {@code val} before the node currently at zero-based {@code pos}.
     * Does nothing if {@code pos} exceeds the list size.
     *
     * @param pos target position
     * @param val value to insert
     */
    public void addElementAtPosition(int pos, String val){
        if(pos > size){
            return;
        }
        Node temp = head;
        Node prev = null;
        for(int i=0; i<pos;i++){
            prev = temp;
            temp = temp.next;
        }
        Node newNode = new Node(val);
        newNode.next = temp;
        prev.next = newNode;
        size++;
    }

    public void print(){
        if(head == null) {
            System.out.println("Empty List");
            return;
        }

        Node temp = head;
        for(int i=0; i<size; i++){
            System.out.print(temp.val + "->");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args){
        LinkedList list = new LinkedList();
        list.print();
        list.addElement("tes1");
        list.addElement("tes2");
        list.addElement("tes3");
        list.addElement("tes4");
        list.addElement("tes5");
        list.addElement("tes6");

        list.print();

        list.addElementAtPosition(3, "tes3.5");
        list.print();

        list.remove(4);
        list.print();
        list.remove(5);
        list.print();

        list.remove(0);
        list.print();
    }
}
