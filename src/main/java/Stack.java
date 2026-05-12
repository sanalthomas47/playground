public class Stack {

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
    int size = 0;

    public Stack(){

    }

    public void push(String val){
        if(head == null){
            head = new Node(val);
            size++;
            return;
        }
        Node newN = new Node(val);
        newN.next = head;
        head = newN;
        size++;
    }

    public void pop(){
        if(size == 0){
            return;
        }
        if(size ==1){
            head.next = null;
            head = null;
            size--;
            return;
        }
        Node t = head.next;
        head.next = null;
        head = t;
        size--;
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
        Stack list = new Stack();
        list.peek();
        list.push("tes1");
        list.push("tes2");
        list.push("tes3");
        list.push("tes4");
        list.push("tes5");
        list.push("tes6");

        list.peek();

        list.pop();
        list.peek();
        list.pop();
        list.peek();
        list.pop();
        list.peek();
        list.pop();
        list.pop();
        list.peek();
        list.pop();
        list.peek();
        list.pop();
        list.peek();
    }
}
