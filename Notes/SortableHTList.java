public class SortableHTList {
    private class Node{
        int num;
        Node next;
        Node prev;
    }

    Node head;
    Node tail;

    private void fillWithRandomInts(int n, int maxInclusive) {
        // Integer[] tarr = new Integer[n];

        for (int i=0; i<n; i++){
            Node node = new Node();
            node.num = (int)(Math.random() * maxInclusive);
            // tarr[i] = node.num;
            
            if (head == null) {
                // Empty list
                head = node;
                tail = node;
            }
            else{
                node.next = head;
                head.prev = node;
                head = node;
            }
        }

        // for (int i:tarr){
        //     System.out.print(i + " ");
        // }
        // System.out.println();
    }

    public String tailToString(){
        if (tail == null) return "null";
        String str = "tail";
        for (Node n = tail; n != null; n = n.prev){
            str += "->" + n.num;
        }
        str += "<-head";
        return str;
    }

    public String toString(){
        String str = "head";

        for (Node n = head; n != null; n = n.next){
            str += "->" + n.num;
        }
        str += "<-tail\n";
        str += tailToString();


        str += "\nhead->" + (head != null ? head.num : "null") + 
               "\ntail->" + (tail != null ? tail.num : "null");
        return str;
    }

    private void exch(Node a, Node b){
        int tmp = a.num;
        a.num = b.num;
        b.num = tmp;
    }

    public void insertionSort(){
        for (Node i = head; i != null; i = i.next){
            for (Node j = i; j != head; j = j.prev){
                if (j.prev.num > j.num){
                    exch(j, j.prev);
                }
                else{
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        SortableHTList list = new SortableHTList();
        list.fillWithRandomInts(25,10);
        System.out.println(list);
        list.insertionSort();
        System.out.println(list);
    }



}
