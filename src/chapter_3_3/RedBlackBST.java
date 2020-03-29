package chapter_3_3;

import java.security.Key;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        private int N;
        private boolean color;
        private Node left;
        private Node right;
        private Key key;
        private Value val;

        public Node(int n, boolean color, Key key, Value val) {
            this.N = n;
            this.color = color;
            this.key = key;
            this.val = val;
        }
    }

    private boolean isRed(Node node) {
        if (node == null) return BLACK;
        return node.color == RED;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.N;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) return null;
        int compare_result = key.compareTo(node.key);
        if (compare_result < 0) {
            return get(node.left, key);
        } else if (compare_result > 0) {
            return get(node.right, key);
        } else {
            return node.val;
        }
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value value) {
        if (h == null) {
            return new Node(1, RED, key, value);
        }

        int compare_result = key.compareTo(h.key);
        if (compare_result < 0) {
            h.left = put(h.left, key, value);
        } else if (compare_result > 0) {
            h.right = put(h.right, key, value);
        } else {
            h.val = value;
        }

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        h.N = 1 + size(h.left) + size(h.right);
        return h;
    }
}
