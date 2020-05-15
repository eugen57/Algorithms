package chapter_5_5;

import chapter_2.PriorityQueueResize;
import utils.BinaryStdIn;
import utils.BinaryStdOut;

public class Huffman {
    private static int R=256;

    private static class Node implements Comparable<Node> {
        private char ch;
        private int freq;
        private final Node left;
        private final Node right;

        public Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    public static void compress() {
        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();

        int[] freq = new int[R];
        for (int i = 0; i < input.length; i++) {
            freq[input[i]]++;
        }

        Node root = buildTrie(freq);
        String[] st = buildCode(root);

        writeTrie(root);
        BinaryStdOut.write(input.length);

        for (int i = 0; i < input.length; i++) {
            String code = st[input[i]];
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(j) == '1') BinaryStdOut.write(true);
                else BinaryStdOut.write(false);
            }
        }
        BinaryStdOut.close();
    }

    public static void expand() {
        Node root = readTrie();
        int N = BinaryStdIn.readInt();
        for (int i = 0; i < N; i++) {
            Node x = root;
            while (!x.isLeaf()) {
                if (BinaryStdIn.readBoolean()) {
                    x = x.right;
                } else {
                    x = x.left;
                }
            }
            BinaryStdOut.write(x.ch);
        }
        BinaryStdOut.close();
    }

    private static Node buildTrie(int[] freq) {
        PriorityQueueResize<Node> pq = new PriorityQueueResize<>(PriorityQueueResize.Orientation.MIN);
        for (char c = 0; c < R; c++) {
            if (freq[c] > 0) pq.insert(new Node(c, freq[c], null, null));
        }
        while (pq.size() > 1) {
            Node x = pq.deleteTop();
            Node y = pq.deleteTop();
            Node parent = new Node('\0', x.freq+y.freq, x, y);
            pq.insert(parent);
        }
        return pq.deleteTop();
    }

    private static String[] buildCode(Node root) {
        String [] st = new String [R];
        buildCode(st, root, "") ;
        return st;
    }

    private static void buildCode(String[] st, Node x, String s) {
        if (x.isLeaf()) {
            st[x.ch] = s;
            return;
        }
        buildCode(st, x.left, s + '0');
        buildCode(st, x.right, s + '1');
    }

    private static void writeTrie(Node x) {
        // Запись trie-дерева в виде битовой строки
        if (x.isLeaf()) {
            BinaryStdOut.write(true);
            BinaryStdOut.write(x.ch);
            return;
        }
        BinaryStdOut.write(false);
        writeTrie(x.left);
        writeTrie(x.right);
    }

    private static Node readTrie() {
        if (BinaryStdIn.readBoolean()) {
            return new Node(BinaryStdIn.readChar(), 0, null, null);
        }
        return new Node('\0', 0, readTrie(), readTrie());
    }
}
