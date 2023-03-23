public class SortableList<Item extends Comparable<Item>> {
    public class Node {
        Item item;
        Node next;

        Node(Item item) {
            this.item = item;
        }
    }

    private Node head;

    public void add(Item item) {
        Node n = new Node(item);
        n.next = head;
        head = n;
    }

    private boolean less(Item item1, Item item2) {
        return item1.compareTo(item2) < 0;
    }

    private void exch(Node n, Node m) {  //swaps items inside nodes n and m (not pointers)
        Item item = n.item;
        n.item = m.item;
        m.item = item;
    }

    private Node getTail() {
        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        return tail;
    }

    private Node partition(Node lo, Node hi) {
        Item pivot = hi.item;
        Node i = lo; // next swap spot -> [lo,i) are all smaller than
        Node j = lo; // element to be swapped to the left

        while (j != hi) {
            if (less(j.item, pivot)) {
                if (i != j) {
                    exch(i, j);
                }
                i = i.next;
            }
            j = j.next;
        }

        exch(i, hi); // swap pivot (hi) with i:
                     // [lo, i] < piv, (i, hi] >= piv
        return i; // return pivot node
    }

    private void quickSortHelper(Node lo, Node hi) {
        if (lo == hi || lo == null || hi == null || lo == hi.next) { // empty section or circular
            return;
        }

        Node p = partition(lo, hi); 

        if (p != null && p != lo) { // pivot is not first node in section
            Node prev = lo;
            while (prev.next != p) {
                prev = prev.next;
            }
            quickSortHelper(lo, prev); // sort(lo, mid)
        }
        if (p != null && p.next != hi) { // pivot is not last thing in section
            quickSortHelper(p.next, hi); // sort(mid+1, hi)
        }
    }

    public void sort() { // public API
        quickSortHelper(head, getTail());
    }


    public String toString() {
        String s = "";
        for (Node n = head; n != null; n = n.next) {
            s += n.item + (n.next != null ? "->" : "");
        }
        return s;
    }

    public static SortableList<Character> convertStringToSortableList(String s) {
        SortableList<Character> list = new SortableList<Character>();
        for (int i = 0; i < s.length(); i++) {
            list.add(s.charAt(i));
        }
        return list;
    }

    public static void main(String[] args) {
        SortableList<Character> list = convertStringToSortableList("ambidextrous");
        System.out.println(list);
        list.sort();
        System.out.println(list);
    }
}
