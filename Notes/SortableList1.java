import java.lang.Math;
public class SortableList1 {
    private class Node{
        int num;
        Node next;

        public Node(int n){
            this.num = n;
            this.next = null;
        }
    }

    Node head;

    /*
     * 
     * 
     * 
     * private void exch(Comparable[] a, int i, int j){
     *     Comparable tmp = a[i];
     *     a[i] = a[j];
     *     a[j] = tmp;
     * }
     * 
     * 
     */

    private void exch(Node a, Node b){
        int tmp = a.num;
        a.num = b.num;
        b.num = tmp;
    }

    public void selectionSort(){
        Node cur = head;
        int min;

        for (Node n = head; n != null; n=n.next){
            Node minNode = n;
            for (Node m = n.next; m!=null; m = m.next){
                if (m.num < minNode.num){
                    minNode = m;
                }
            }
            exch(n, minNode);
            System.out.println(this);
        }
    }

    private void selectionSort_R(Node h){
        if (h.next == null) return;
        Node minNode = h;
        for (Node cur = h.next; cur!=null; cur = cur.next){
            if (cur.num < minNode.num){
                minNode = cur;
            }
        }
        exch(minNode, h);
        System.out.println(this);
        selectionSort_R(h.next);
    }

    public void selectionSort_R(){
        selectionSort_R(head);
    }

    public void fillWithRandomInts(int n, int maxInclusive){
        for (int i=0; i<n; i++){
            int value = (int)(maxInclusive * Math.random()) + 1;
            Node node = new Node(value);
            node.next = head;
            head = node;
        }
    }

    public String toString(){
        // Node cur = head;
        // String str = "head";
        // while (cur != null){
        //     str += "->" + cur.num;
        //     cur = cur.next;
        // }
        String str = "head";

        for (Node n = head; n != null; n = n.next){
            str += "->" + n.num;
        }
        return str;
    }

    public static void main(String[] args) {
        SortableList1 list = new SortableList1();
        list.fillWithRandomInts(25,10);
        System.out.println(list);
        list.selectionSort_R();
        // System.out.println(list);
    }
}
