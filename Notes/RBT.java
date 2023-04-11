public class RBT {
    class Node{

    }

    private Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
    }
}
