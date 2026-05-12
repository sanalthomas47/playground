import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    private class Node {

        Node left;
        Node right;
        int val;

        Node(int val){
            this.val = val;
        }
    }

    Node root = null;

    public void insert(int val){
        root = insertRecursive(val, root);
    }

    private Node insertRecursive(int val, Node node){

        if(node == null){
            node = new Node(val);
            return node;
        }

        if(val < node.val){
            node.left = insertRecursive(val, node.left);
        }
        if(val > node.val){
            node.right = insertRecursive(val, node.right);
        }
        return node;
    }

    public void inOrder(){
        inOrder(root);
    }

    public void inOrder(Node node){
        if(node == null) return;
        inOrder(node.left);
        System.out.print(node.val+"  ");
        inOrder(node.right);
        System.out.println();
    }

    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){
        if(node == null) return;
        System.out.print(node.val+"  ");
        preOrder(node.left);
        preOrder(node.right);
        System.out.println();
    }

    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node){
        if(node==null) {
            return;
        }
        System.out.println();
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val+"  ");

    }

    public void levelOrder(){

        if(root == null) return;

        Queue<Node> nodeQueue = new LinkedList<Node>();

        nodeQueue.offer(root);
        int count = 0;
        while(!nodeQueue.isEmpty()){

            count = nodeQueue.size();

            System.out.println();

            for(int i=0; i<count; i++) {
                Node node = nodeQueue.poll();
                System.out.print(node.val+"   ");
                if(node.left!=null) nodeQueue.offer(node.left);
                if(node.right!=null) nodeQueue.offer(node.right);
            }

            //System.out.println();
        }
    }

    public void sumAtLevel(int level){

        if(root == null) return;
        Queue<Node> nodeQueue = new LinkedList<Node>();
        nodeQueue.offer(root);
        int count = 0;
        int currentLevel = 0;
        while(!nodeQueue.isEmpty()){

            count = nodeQueue.size();
            int sum=0;

            for(int i=0; i<count; i++) {
                Node node = nodeQueue.poll();
                if(level == currentLevel){
                    sum+=node.val;
                }
                if(node.left!=null) nodeQueue.offer(node.left);
                if(node.right!=null) nodeQueue.offer(node.right);
            }

            if(currentLevel == level){
                System.out.println();
                System.out.println("sum:"+sum);
                break;
            }
            currentLevel++;

        }
    }

    public static void main(String[] args){

        BinaryTree binaryTree = new BinaryTree();

        binaryTree.insert(5);
        binaryTree.insert(3);
        binaryTree.insert(4);
        binaryTree.insert(2);
        binaryTree.insert(7);
        binaryTree.insert(6);
        binaryTree.insert(8);
        binaryTree.insert(9);
        binaryTree.insert(1);

        /*binaryTree.inOrder();
        binaryTree.preOrder();
        binaryTree.postOrder();*/

        binaryTree.levelOrder();

        binaryTree.sumAtLevel(1);
    }

}
