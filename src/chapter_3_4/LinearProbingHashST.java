package chapter_3_4;

import chapter_1_3.Queue;

import java.util.Arrays;

public class LinearProbingHashST<Key, Value> {
    private int M = 16; // size of hash table
    private int N; // key-val pairs
    Key[] keys;
    Value[] values;

    public LinearProbingHashST() {
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int newSize) {

    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null ; i = (i+1) % M) {
            if(keys[i].equals(key)) return values[i];
        }
        return null;
    }

    public void put(Key key, Value value) {
        if (N >= M/2) resize(M*2);
        int i;
        for (i = hash(key); keys[i] != null ; i = (i+1) % M) {
            if(keys[i].equals(key)) {
                values[i] = value;
                return;
            };
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }

    public void delete(Key key) {
//        if (!contains(key)) return;
        int i = hash(key);
        while (!keys[i].equals(key)) {
            i = (i+1) % M;
        }
        keys[i] = null;
        values[i] = null;
        i = (i+1) % M;
        while (keys[i] != null) {
            Key keyToReplace = keys[i];
            Value valueToReplace = values[i];
            keys[i] = null;
            values[i] = null;
            N--;
            put(keyToReplace, valueToReplace);
            i = (i+1) % M;
        }
        N--;
        if (N > 0 && N == M/8) resize(M/2);
    }

    public Iterable<Key> keys() {
        Queue<Key> keysQueue = new Queue<>();
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] != null) keysQueue.enqueue(keys[i]);
        }
        // sort?
//        if (!keysQueue.isEmpty() && keysQueue.peek() instanceof Comparable) {
//            Key[] keysToBeSorted = (Key[]) new Comparable[keysQueue.size()];
//            for(int i = 0; i < keysToBeSorted.length; i++) {
//                keysToBeSorted[i] = keysQueue.dequeue();
//            }
//
//            Arrays.sort(keysToBeSorted);
//
//            for(Key key : keysToBeSorted) {
//                keysQueue.enqueue(key);
//            }
//        }


        return keysQueue;
    }
}
