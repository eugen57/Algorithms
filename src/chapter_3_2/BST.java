package chapter_3_2;

import chapter_1_3.Queue;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private int N;

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
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
        put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) return new Node(key, value, 1);
        int compare_result = key.compareTo(node.key);

        if (compare_result < 0) {
            node.left = put(node.left, key, value);
        } else if (compare_result > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.val = value;
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node node) {
        if (node == null) return null;
        if (node.left == null) return node;
        else return min(node.left);
    }

    public Key floor(Key key) {
        Node x = floor (root, key);
        if (x == null) return null;
        return x.key;
    }
    private Node floor (Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo (x.key) ;
        if (cmp == 0) return x;
        if (cmp < 0) return floor (x.left, key);
        Node t = floor (x. right, key);
        if (t != null) return t;
        else return x;
    }
    public void deleteMin() {
        deleteMin(root); // returns root
    }

    private Node deleteMin(Node node) {
        if (size(root) == 0) return null;
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void delete(Key key) {
        delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;
        int compare_result = key.compareTo(node.key);
        if (compare_result < 0) node.left = delete(node.left, key);
        else if(compare_result > 0) node.right = delete(node.right, key);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node t = node;
            node = min(t.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void print() {
        print(root);
    }

    private void print(Node node) {
        Queue<Node> nodes = new Queue<>();
        nodes.enqueue(node);

        while (!nodes.isEmpty()) {
            Node x = nodes.dequeue();
            System.out.println("   "  + x.key);

            if (x.left != null) nodes.enqueue(x.left);
            if (x.right != null) nodes.enqueue(x.right);
        }
    }
}
