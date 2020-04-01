package chapter_3_4;

import chapter_3_1.SequentialST;

public class SeparateChainingHashST<Key extends Comparable<Key>, Value> {
    int M; // size of hash-table
    int N; // count of key-val pairs
    private SequentialST<Key, Value>[] st;

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialST<Key, Value>[]) new SequentialST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialST<>();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        st[hash(key)].put(key, value);
    }

    public void delete(Key key) {
        if (isEmpty() || !contains(key)) return;
        st[hash(key)].delete(key);
        N--;
//        resize()
    }

    public Iterable<Key> keys() {
        return null;
    }
}
