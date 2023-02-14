public class LinkyListy <E>{
    private class Node {
        E value;
        Node next;

        public Node (E thing){
            this.value = thing;
            this.next = null;
        }

        public Node (E thing, Node next){
            this.value = thing;
            this.next = next;
        }
    }

    private Node head;
    private int size;

    public boolean isEmpty() {
        return head == null;
    }

    public int size(){
        return size;
    }

    public void add(E item){
        Node temp = new Node(item);
        temp.next = head;
        head = temp;
        size++;
    }

    public String toString(){
        Node cur = head;
        String str = "(Head)";

        while (cur != null){
            str += "->" + cur.value;
            cur = cur.next;
        }
        str += "(size = " + size + ")";

        return str;
    }

    public E remove(){
        if (isEmpty()){
            // return null;
            throw new RuntimeException("Nothing to remove");
        }

        E item = head.value;
        head = head.next;
        size--;
        return item;
    }

    public void removeAll(E item){
        this.head = removeAll(head,item);
    }

    private Node removeAll(Node n, E e){
        if (n==null) return null;
        if (n.value.equals(e)){
            return removeAll(n.next, e);
        }
        n.next = removeAll(n.next, e);
        return n;
    }

    public static void main(String[] args) {
        LinkyListy<String> list = new LinkyListy<String>();
        // list.add("Alice");
        // list.add("Bob");
        // list.add("Carl");
        // list.add("Boris");
        // list.add("Natasha");
        System.out.println(list);
    }
}
