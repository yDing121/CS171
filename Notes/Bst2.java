public class Bst2<Key extends Comparable<Key>, Value> {
    private class Node{
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private int count;

        private Node(Key key, Value val){
            this.key = key;
            this.val = val;
        }
    }

    Node root;

    public Value get(Key key){
        return get(root, key);
    }

    private Value get(Node n, Key key){
        if (n == null) return null;

        int cmp = key.compareTo(n.key);
        if (cmp < 0) return get(n.left, key);
        if (cmp > 0) return get(n.right, key);
        return n.val;
    }

    public void put(Key key, Value val){
        root = put(root, key, val);
    }

    private Node put(Node n, Key key, Value val){
        if (n == null){
            n = new Node(key, val);
            n.count = 1;
            return n;
        }

        int cmp = key.compareTo(n.key);
        if (cmp < 0) n.left = put(n.left, key, val);
        if (cmp > 0) n.right = put(n.right, key, val);
        if (cmp == 0) n.val = val;
        n.count = 1 + size(n.left) + size(n.right);
        return n;
    }

    public Node min(){
        Node n = root;
        if (n == null) return null;

        while (n.left != null) n = n.left;
        return n;
    }



    private Node deleteMin(Node n){
        if (n.left == null) return n.right;
        
        n.left = deleteMin(n.left);
        
        n.count = 1 + size(n.left) + size(n.right);
        
        return n;
    }
    
    public void delete(Key key){
        root = delete(root, key);
    }

    private Node delete(Node n, Key key){
        if (n == null) return null;

        int cmp = key.compareTo(n.key);
        if (cmp < 0){
            n.left = delete(n.left, key);
        }
        else if (cmp > 0){
            n.right = delete(n.right, key);
        }
        else{
            if (n.right == null) return n.left;

            Node t = n;

            n = keyWithRank(0);
        }
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
        if (cmp < 0) return rank(key, n.left); // key in left subtree
        if (cmp > 0) return size(n.left) + 1 + rank(key, n.right); // key in right subtree
        return size(n.left); // key is n
    }

    public Key keyWithRank(int r){
        if (r < 0) return null; // negative rank
        if (r >= size(root)) return null; // rank > number of nodes

        return keyWithRank(root, r).key;
    }

    private Node keyWithRank(Node n, int r){
        if (n == null) return null;

        int leftside = size(n.left);
        if (leftside > r) return keyWithRank(n.left, r);
        if (leftside < r) return keyWithRank(n.right, r - 1 - leftside);
        return n;
    }

    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node n){
        if (n == null) return;
        System.out.print(n.key + " ");
        preOrder(n.left);
        preOrder(n.right);
    }

    public static void main(String[] args) {
        Bst2<Character, Integer> tree = new Bst2<Character, Integer>();
        tree.put('D', 1);
        tree.put('B', 1);
        tree.put('A', 1);
        tree.put('C', 1);
        tree.put('E', 1);
        tree.put('G', 1);
        tree.put('F', 1);
        tree.put('H', 1);

        tree.preOrder();
        System.out.println();
        System.out.println(tree.rank('H'));
    }
}
