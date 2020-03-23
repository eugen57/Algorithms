package chapter_3_1;

import chapter_1_3.Queue;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    int N;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() { return N==0; }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key)==0) {
            return values[i];
        } else {
            return null;
        }
    }

    public void put(Key key, Value value) {
        if (value == null) {
            delete(key);
            return;
        }

        int rank = rank(key);
        if (rank < N && key.compareTo(keys[rank])==0) {
            values[rank] = value;
            return;
        }

        if (N==keys.length) {
            resize(keys.length*2);
        }
        for (int i = N; i > rank; i--) {
            keys[i] = keys[i-1];
            values[i] = values[i-1];
        }
        keys[rank] = key;
        values[rank] = value;
        N++;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void delete(Key key) {
        if (isEmpty() || !contains(key)) return;
        int rank = rank(key);
        for (int i = rank; i < N - 1; i++) {
            keys[i] = keys[i+1];
            values[i] = values[i+1];
        }

        keys[N - 1] = null;
        values[N-1] = null;
        N --;

        if (N > 1 && N == keys.length / 4 ) {
            resize(keys.length/2);
        }
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();

        for(int i = 0; i < N; i++) {
            queue.enqueue(keys[i]);
        }

        return queue;
    }

    private int rank(Key key) {
        int lo = 0;
        int hi = N - 1;
        while (lo<=hi) {
            int mid = (lo+hi)/2;
            int comp = key.compareTo(keys[mid]);
            if (comp < 0) {
                hi = mid - 1;
            } else if (comp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    private void resize(int newSize) {
        Key[] newKeys = (Key[]) new Comparable[newSize];
        Value[] newValues = (Value[]) new Object[newSize];

        for (int i = 0; i < N; i++) {
            newKeys[i] = keys[i];
            newValues[i] = values[i];
        }

        keys = newKeys;
        values = newValues;
    }
}
