public class Set<Item extends Comparable<Item>> {
    // Private inner class Node for linked list
    private class Node {
        Item item;
        Node next;

        Node(Item item) {
            this.item = item;
        }
    }

    private Node head;

    // Default constructor
    public Set() { // creates an empty set
        this.head = null;
    }

    // Alternate constructor interface
    public Set(Item item) { // creates a set containing only the Item item
        this.head = new Node(item);
    }

    // Private constructor given a node
    private Set(Node head) {
        this.head = head;
    }

    // Add new node (test code)
    public void add(Item item) {
        Node n = new Node(item);
        n.next = head;
        head = n;
    }

    // toString - returns the set as: {<item1.toString()>,
    // <item2.toString()>,...,<itemN.toString()>}
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

    // We use .compareTo() directly in code so this is redundant
    private boolean less(Item item1, Item item2) {
        return item1.compareTo(item2) < 0;
    }

    // Public union interface
    public Set<Item> union(Set<Item> other) {
        return new Set<Item>(union(head, other.head));
    }

    // Private recursive union
    private Node union(Node h1, Node h2) {
        // First set is empty
        if (h1 == null) {
            return h2;
        }
        // Second set is empty
        if (h2 == null) {
            return h1;
        }

        int cmp = h1.item.compareTo(h2.item);
        if (cmp < 0) { // First item less than second item
            Node n = new Node(h1.item);
            n.next = union(h1.next, h2);
            return n;
        } else if (cmp > 0) { // Second item less than first item
            Node n = new Node(h2.item);
            n.next = union(h1, h2.next);
            return n;
        } else { // Items are equal
            Node n = new Node(h2.item);
            n.next = union(h1.next, h2.next);
            return n;
        }
    }

    // Public intersect interface
    public Set<Item> intersect(Set<Item> other) {
        return new Set<Item>(intersect(head, other.head));
    }

    // Private recursive intersect
    private Node intersect(Node h1, Node h2) {
        // One set has reached its end
        if (h1 == null || h2 == null) {
            return null;
        }

        int cmp = h1.item.compareTo(h2.item);
        if (cmp < 0) { // First item less than second item
            return intersect(h1.next, h2);
        } else if (cmp > 0) { // Second item less than first item
            return intersect(h1, h2.next);
        } else { // Items are equal
            Node intersection = new Node(h1.item);
            intersection.next = intersect(h1.next, h2.next);
            return intersection;
        }
    }

    // Public difference interface
    public Set<Item> difference(Set<Item> other) {
        return new Set<Item>(difference(head, other.head));
    }

    // Private recursive difference
    private Node difference(Node h1, Node h2) {
        if (h1 == null) { // First set is empty
            return null;
        }
        if (h2 == null) { // Second set is empty
            return h1;
        }

        int cmp = h1.item.compareTo(h2.item);
        if (cmp == 0) { // Items are equal
            return difference(h1.next, h2.next);
        } else if (cmp < 0) { // First item less than second item
            Node diff = new Node(h1.item);
            diff.next = difference(h1.next, h2);
            return diff;
        } else { // Second item less than first item
            return difference(h1, h2.next);
        }
    }

    // Unique letters in string
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

    // Generate test cases from string
    public static Set<Character> setOfUniqueCharsFromString(String s) {
        char[] chars = uniqueLettersIn(s);
        Set<Character> set = new Set<Character>();
        for (int i = chars.length - 1; i >= 0; i--) {
            set = set.union(new Set(chars[i]));
        }
        return set;
    }

    // Test cases
    public static void main(String[] args) {
        Set<Character> set1 = setOfUniqueCharsFromString("the quick brown fox jumps");
        Set<Character> set2 = setOfUniqueCharsFromString("over the lazy dog");

        System.out.println("set1: " + set1);
        System.out.println("set2: " + set2);
        System.out.println();

        System.out.println("set1 intersect set2:\n" + set1.intersect(set2) + "\n");

        System.out.println("set1 union set2:\n" + set1.union(set2) + "\n");

        System.out.println("set1 difference set2:\n" + set1.difference(set2) + "\n");
    }
}
