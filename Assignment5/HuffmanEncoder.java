import java.util.Arrays;
import java.util.Scanner;
import java.util.Hashtable;
import java.util.Set;

public class HuffmanEncoder {

    private class Node implements Comparable<Node> {
        private String symbols;
        private int frequency;
        private Node left;
        private Node right;

        public Node(String symbols, int frequency) {
            this.symbols = symbols;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Node other) {
            return this.frequency - other.frequency;
        }
    }

    private Node root;
    private Hashtable<Character, Integer> uniqueSymbolCounts;

    public HuffmanEncoder(String s) {

        uniqueSymbolCounts = new Hashtable<>();
        for (char c : s.toCharArray()) {
            uniqueSymbolCounts.put(c, uniqueSymbolCounts.getOrDefault(c, 0) + 1);
        }

        MinPriorityQueueHeap<Node> pq = new MinPriorityQueueHeap<>();
        for (char c : uniqueSymbolCounts.keySet()) {
            pq.put(new Node(Character.toString(c), uniqueSymbolCounts.get(c)));
        }

        while (pq.size() > 1) {
            Node left = pq.removeMinItem();
            Node right = pq.removeMinItem();
            Node parent = new Node(left.symbols + right.symbols, left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            pq.put(parent);
        }

        root = pq.removeMinItem();

    }

    private String codeFor(char c) {
        StringBuilder code = new StringBuilder();
        Node currentNode = root;
        while (currentNode.left != null || currentNode.right != null) {
            if (currentNode.left.symbols.contains(Character.toString(c))) {
                code.append("0");
                currentNode = currentNode.left;
            } else {
                code.append("1");
                currentNode = currentNode.right;
            }
        }
        return code.toString();
    }

    public String encode(String s) {

        StringBuilder encoded = new StringBuilder();
        for (char c : s.toCharArray()) {
            encoded.append(codeFor(c));
        }
        return encoded.toString();
    }

    public String decode(String s) {

        StringBuilder decoded = new StringBuilder();
        Node currentNode = root;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
            if (currentNode.left == null && currentNode.right == null) {
                decoded.append(currentNode.symbols);
                currentNode = root;
            }
        }
        return decoded.toString();
    }

    public void printCodes() {
        Set<Character> uniqueSymbols = uniqueSymbolCounts.keySet();
        for (Character c : uniqueSymbols) {
            System.out.println(c + " : " + codeFor(c));
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string to serve as the basis for the Huffman Coding: ");
        String s = scanner.nextLine();  // Try the following: "a_dead_dad_ceded_a_bad_babe_a_beaded_abaca_bed";
        scanner.close();

        HuffmanEncoder huffmanEncoder = new HuffmanEncoder(s);

        System.out.println("\nCodes Used:");
        huffmanEncoder.printCodes();
        System.out.println();

        String encodedString = huffmanEncoder.encode(s);
        System.out.println("String provided can be encoded in " + encodedString.length() + " bits (+ code information)");
        System.out.println(encodedString);
        System.out.println();

        String decodedString = huffmanEncoder.decode(encodedString);
        System.out.println("Decodes as: " + decodedString);
        System.out.println("Note, this would have required " + (decodedString.length() * 8) + " bits required to store in ASCII characters)");

    }
}
