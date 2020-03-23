package chapter_3_1;

import chapter_1_3.Queue;

public class SequentialST<Key extends Comparable<Key>, Value> {
    private Node first;
    private int N = 0;

    private class Node {
        Node next;
        Key key;
        Value value;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (x.key.compareTo(key) == 0) {
                return x.value;
            }
        }
        return null;
    }

    public void put(Key key, Value value) {
        for (Node x = first; x != null; x = x.next) {
            if (x.key.compareTo(key) == 0) {
                x.value = value;
                return;
            }
        }
        first = new Node(key, value, first);
        N++;
    }

    public int size() { return N; }

    public boolean isEmpty() { return size() == 0; }

    public Iterable<Key> keys() {
        Queue<Key> keys = new Queue<>();

        for(Node node = first; node != null; node = node.next) {
            keys.enqueue(node.key);
        }

        return keys;

    }

    public void delete(Key key) {
        if (isEmpty()) {
            return;
        }

        if (first.equals(key)) {
            first = first.next;
            N--;
            return;
        }

        for (Node x = first; x != null; x = x.next) {
            if(x.next.key.equals(key)) {
                x.next = x.next.next;
                N--;
                return;
            }
        }

    }
}
