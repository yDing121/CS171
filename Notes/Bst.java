import java.util.Deque;

import javax.management.RuntimeErrorException;

import java.util.ArrayDeque;
public class Bst<Key extends Comparable<Key>, Value>{
    private class Node{
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private int count;

        private Node(Key key, Value val){
            this.key = key;
            this.val = val;
            this.count = 1;
        }
    }

    Node root;

    // public Value get(Key key){
    //     Node n = root;

    //     while (n != null) {
    //         int cmp = key.compareTo(n.key);
    //         if (cmp > 0){
    //             n = n.right;
    //         }
    //         else if (cmp < 0){
    //             n = n.left;
    //         }
    //         else{
    //             return n.val;
    //         }
    //     }
    //     return null;
    // }

    public Value get(Key key){
        return get(root, key);
    }

    private Value get (Node n, Key key){
        if (n == null) return null;
        int cmp = key.compareTo(n.key);
        if (cmp < 0) return get(n.left, key);
        if (cmp > 0) return get(n.right,key);
        return n.val;
    }

    public void put(Key key, Value val){
        root = put(root, key, val);
    }

    private Node put (Node n, Key key, Value val){
        if(n == null) return new Node(key,val);
        int cmp = key.compareTo(n.key);
        if (cmp < 0)  n.left = put(n.left, key, val);
        if (cmp > 0)  n.right = put(n.right, key, val);
        if (cmp == 0) n.val = val;
        n.count = 1 + size(n.left) + size(n.right); //fixes count!
        return n;
    }

    // public Value rGet(Key key){
    //     return rGet(root, key);
    // }

    // private Value rGet(Node n, Key key){
    //     if (n == null) return null;
    //     int cmp = key.compareTo(n.key);
    //     if (cmp < 0) return rGet(n.left, key);
    //     else if (cmp > 0) return rGet(n.right, key);
    //     else return n.val;
    // }

    // public void rPut(Key key, Value val){
    //     root = rput(root, key, val);
    // }


    // private Node rput(Node n, Key key, Value val){
    //     if (n == null) return new Node(key, val);

    //     int cmp = key.compareTo(n.key);
    //     if (cmp < 0) n.left = rput(n.left, key, val);
    //     else if (cmp > 0) n.right = rput(n.right, key, val);
    //     else n.val = val;

    //     return n;
    // }

    public Iterable<Key> keysPreOrder(){
        Deque<Key> q = new ArrayDeque<Key>();
        enqueueKeysPreOderFromNode(root, q);
        return q;
    }
    private void enqueueKeysPreOderFromNode(Node n, Deque<Key> q){ //
        if (n == null) return;
        q.add(n.key);
        enqueueKeysPreOderFromNode(n.left, q);
        enqueueKeysPreOderFromNode(n.right, q);

    }
    //postfix order
    public Iterable<Key> keysPostOrder(){
        Deque<Key> q = new ArrayDeque<Key>();
        enqueueKeysPostOderFromNode(root, q);
        return q;
    }

    private void enqueueKeysPostOderFromNode(Node n, Deque<Key> q){ //
        if (n == null) return;
        enqueueKeysPostOderFromNode(n.left, q);
        enqueueKeysPostOderFromNode(n.right, q);
        q.add(n.key);
    }
    //infix order
    public Iterable<Key> keysInOrder(){
        Deque<Key> q = new ArrayDeque<Key>();
        enqueueKeysInOderFromNode(root, q);
        return q;
    }

    private void enqueueKeysInOderFromNode(Node n, Deque<Key> q){ //
        if (n == null) return;
        enqueueKeysInOderFromNode(n.left, q);
        q.add(n.key);
        enqueueKeysInOderFromNode(n.right, q);

    }

    public int size(){
        return size(root);
    }

    private int size(Node n){
        return (n == null ? 0 : n.count);
    }

    public int rank(Key key){
        return rank(key, root);
    }

    private int rank(Key key, Node n){
        if (n == null) return 0;

        int cmp = key.compareTo(n.key);
        if (cmp < 0) return rank(key, n.left);
        else if (cmp > 0) return size(n.left) + 1 - rank(key, n.right);
        else return size(n.left);
    }

    public Key keyWithRank(int rank){
        if (rank > size(root)) return null;
        return keyWithRank(root, rank).key;
    }

    private Node keyWithRank(Node n, int rank){
        if (n==null) return null;

        int numToLeft = size(n.left);
        if (numToLeft > rank) return keyWithRank(n.left, rank);
        else if (numToLeft < rank) return keyWithRank(n.right, rank - numToLeft - 1);
        else return n;
    }

    public String toString(){
        return this.keysPostOrder().toString();
    }

    private Node deleteMin(Node n){
        if (n.left == null) return n.right;

        n.left = deleteMin(n.left);
        n.count = 1 + size(n.left) + size(n.right);
        return n;
    }

    public void delete(Key key){
        root = delete(key, root);
    }

    private Node delete(Key key, Node n){
        if (n == null) return null;

        int cmp = key.compareTo(n.key);
        if (cmp < 0){
            n.left = delete(key, n.left);
            n.count = 1 + size(n.left) + size(n.right);
            return n;
        }
        else if (cmp > 0){
            n.right = delete(key, n.right);
            n.count = 1 + size(n.right);
            return n;
        }

        if (n.right == null) return n.left;
        
        Node t = n;
        n = keyWithRank(t.right, 0);
        n.left = t.left;
        n.count = 1 + size(n.left) + size(n.right);
        return n;
    }

    public static void main(String[] args) {
        Bst<Character, Integer> bst = new Bst<Character, Integer>();
        Character[] chrs = {'S','E','B','Y','C','A','R','H','M','X','Z'};
        for (Character c : chrs) {
            bst.put(c, (int) c);
        }
        System.out.println("Pre-order traversal = " + bst);
    }
}
 


// ////////////////////////////////////////////////
// public class Bst<Key extends Comparable<Key>, Value> {

//     private class Node{
//         private Key key;
//         private Value val;
//         private Node left;
//         private Node right;
//         private int count;

//         private Node(Key key, Value val){
//             this.key = key;
//             this.val = val;
//             this.count = 1;
//         }

//     }

//     Node root;
// /*
//     public Value get(Key key){
//         Node n = root;
//         while(n != null){
//             int cmp = key.compareTo(n.key);
//             if (cmp > 0)
//                 n = n.right;
//             else if(cmp < 0)
//                 n = n.left;
//             else
//                 return n.val;
//         }
//         return null;
//     }

//     */

//     public Value get(Key key){
//         return get(root, key);
//     }

//     private Value get (Node n, Key key){
//         if (n == null) return null;
//         int cmp = key.compareTo(n.key);
//         if (cmp < 0) return get(n.left, key);
//         if (cmp > 0) return get(n.right,key);
//         return n.val;
//     }

//     public void put(Key key, Value val){
//         root = put(root, key, val);
//     }

//     private Node put (Node n, Key key, Value val){
//         if(n == null) return new Node(key,val);
//         int cmp = key.compareTo(n.key);
//         if (cmp < 0)  n.left = put(n.left, key, val);
//         if (cmp > 0)  n.right = put(n.right, key, val);
//         if (cmp == 0) n.val = val;
//         n.count = 1 + size(n.left) + size(n.right); //fixes count!
//         return n;
//     }
//     //prefix order
//     public Iterable<Key> keysPreOrder(){
//         Deque<Key> q = new ArrayDeque<Key>();
//         enqueueKeysPreOderFromNode(root, q);
//         return q;
//     }

//     private void enqueueKeysPreOderFromNode(Node n, Deque<Key> q){ //
//         if (n == null) return;
//         q.add(n.key);
//         enqueueKeysPreOderFromNode(n.left, q);
//         enqueueKeysPreOderFromNode(n.right, q);

//     }
//     //postfix order
//     public Iterable<Key> keysPostOrder(){
//         Deque<Key> q = new ArrayDeque<Key>();
//         enqueueKeysPostOderFromNode(root, q);
//         return q;
//     }

//     private void enqueueKeysPostOderFromNode(Node n, Deque<Key> q){ //
//         if (n == null) return;
//         enqueueKeysPostOderFromNode(n.left, q);
//         enqueueKeysPostOderFromNode(n.right, q);
//         q.add(n.key);
//     }
//     //infix order
//     public Iterable<Key> keysInOrder(){
//         Deque<Key> q = new ArrayDeque<Key>();
//         enqueueKeysInOderFromNode(root, q);
//         return q;
//     }

//     private void enqueueKeysInOderFromNode(Node n, Deque<Key> q){ //
//         if (n == null) return;
//         enqueueKeysInOderFromNode(n.left, q);
//         q.add(n.key);
//         enqueueKeysInOderFromNode(n.right, q);

//     }

//     public int size(){
//         return size(root);
//     }

//     private int size(Node n){
//         return (n == null ? 0 : n.count);
//     }

//     public String toString(){
//         return this.keysPostOrder().toString();
//     }

//     public static void main(String[] args){
//         Bst<Character, Integer> bst = new Bst<Character, Integer>();
//         Character[] chrs = {'S','E','B','Y','C','A','R','H','M','X','Z'};
//         for (Character c : chrs) {
//             bst.put(c, (int) c);
//         }
//         System.out.println("Pre-order traversal = " + bst);
//     }
// }