public class BSTIndex {
    private class Node{
        public MovieInfo val;
        public Node left;
        public Node right;

        public String key(){
            return this.val.shortName;
        }
    }

    private Node root;

    public BSTIndex(){}

    public MovieInfo findExact(String key){
        return findExact(root, key);
    }

    private MovieInfo findExact(Node n, String key){
        if (n == null){
            return null;
        }

        int cmp = key.compareToIgnoreCase(n.key());
        if (cmp < 0){
            return findExact(n.left, key);
        }
        else if (cmp > 0){
            return findExact(n.right, key);
        }
        else{
            return n.val;
        }
    }

    public MovieInfo findPrefix(String prefix){
        return findPrefix(root, prefix);
    }

    private MovieInfo findPrefix(Node n, String prefix){
        if (n == null){
            return null;
        }

        int cmp;

        // If prefix is longer than node string
        String actualPrefix = prefix.substring(0, prefix.length() - 1);
        if (n.key().length() < actualPrefix.length()){
            cmp = actualPrefix.compareToIgnoreCase(n.key());
        }
        else{
            String nkey = n.key().substring(0, actualPrefix.length());
            cmp = actualPrefix.compareToIgnoreCase(nkey);
        }
        
        if (cmp < 0){
            return findPrefix(n.left, prefix);
        }
        else if (cmp > 0){
            return findPrefix(n.right, prefix);
        }
        else{
            return n.val;
        }
    }

    public void insert(MovieInfo data){
        root = insert(root, data);
    }

    private Node insert(Node n, MovieInfo data){
        if (n == null){
            n = new Node();
            n.val = data;
            return n;
        }

        int cmp = data.shortName.compareToIgnoreCase(n.key());
        if (cmp <= 0){
            n.left = insert(n.left, data);
        }
        else{
            n.right = insert(n.right, data);
        }

        return n;
    }

    private void inOrder(Node n){
        if (n.left != null) inOrder(n.left);
        System.out.print(n.key() + " ");
        if (n.right != null) inOrder(n.right);
    }

    public void print(){
        inOrder(root);
    }


    public static void main(String[] args) {
        BSTIndex tree = new BSTIndex();
        tree.insert(new MovieInfo(1, "2A", ""));
        tree.insert(new MovieInfo(2, "1A", ""));
        tree.insert(new MovieInfo(3, "3A", ""));
        tree.print();
        System.out.println();
        System.out.println(tree.findExact("2a").shortName);
        System.out.println(tree.findPrefix("2*").shortName);

        System.out.println("abcdec".compareTo("abcde"));
    }
}
