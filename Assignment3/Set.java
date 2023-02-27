import java.util.Arrays;
public class Set<Item extends Comparable<Item>> {
    private class Node {
        Item item;
        Node next;

        Node(Item item) {
            this.item = item;
        }
    }

    private Node head;

    public Set() { // creates an empty set
        this.head = null;
    }

    public Set(Item item) { // creates a set containing only the Item item
        this.head = new Node(item);
    }

    private Set(Node head) {
        this.head = head;
    }

    public void add(Item item){
        Node n = new Node(item);
        n.next = head;
        head = n;
    }

    public String toString() {
        String result = "{";
        Node current = head;
        while (current != null) {
            result += current.item.toString();
            if (current.next != null) {
                result += ",";
            }
            current = current.next;
        }
        result += "}";
        return result;
    }


    private boolean less(Item item1, Item item2) {
        return item1.compareTo(item2) < 0;
    }

    public Set<Item> union(Set<Item> other) {
        return new Set<Item>(union(head, other.head));
    }

    private Node union(Node h1, Node h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }
        int cmp = h1.item.compareTo(h2.item);
        if (cmp < 0) {
            Node n = new Node(h1.item);
            n.next = union(h1.next, h2);
            return n;
        }
        else if (cmp > 0) {
            Node n = new Node(h2.item);
            n.next = union(h1, h2.next);
            return n;
        }
        else {
            Node n = new Node(h2.item);
            n.next = union(h1.next, h2.next);
        }
        return n;
    }

    public Set<Item> intersect(Set<Item> other) {
        return new Set<Item>(intersect(head, other.head));
    }

    private Node intersect(Node h1, Node h2) {
        if (h1 == null || h2 == null) {
            return null;
        }

        int cmp = h1.item.compareTo(h2.item);
        if (cmp < 0) {
            return intersect(h1.next, h2);
        }
        else if (cmp > 0) {
            return intersect(h1, h2.next);
        }
        else{ // cmp == 0
            Node intersection = new Node(h1.item);
            intersection.next = intersect(h1.next, h2.next);
            return intersection;
        }
        
        // return intersection;
    }

    public Set<Item> difference(Set<Item> other) {
        return new Set<Item>(difference(head, other.head));
    }

    private Node difference(Node h1, Node h2) {
        if (h1 == null) {
            return null;
        }
        if (h2 == null) {
            return h1;
        }
        int cmp = h1.item.compareTo(h2.item);
        if (cmp == 0) {
            return difference(h1.next, h2.next);
        }
        if (cmp < 0) {
            Node difference = new Node(h1.item);
            difference.next = difference(h1.next, h2);
            return difference;
        }
        return difference(h1, h2.next);
    }

    public static char[] uniqueLettersIn(String s) {
        final int MAX_ASCII = 128;
        boolean[] seenSoFar = new boolean[MAX_ASCII];
        int n = s.length();
        int numUnique = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if ((Character.isLetter(c)) && (!seenSoFar[(int) c])) {
                seenSoFar[(int) c] = true;
                numUnique++;
            }
        }
        char[] uniques = new char[numUnique];
        int count = 0;
        for (int i = 0; i < MAX_ASCII; i++) {
            if (seenSoFar[i]) {
                uniques[count] = (char) i;
                count++;
            }
        }
        return uniques;
    }

    public static Set<Character> setOfUniqueCharsFromString(String s) {
        char[] chars = uniqueLettersIn(s);
        Set<Character> set = new Set<Character>();
        for (int i = chars.length - 1; i >= 0; i--) {
            set = set.union(new Set(chars[i]));
        }
        return set;
    }


}
