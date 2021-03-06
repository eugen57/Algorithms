package chapter_2;

import java.util.Random;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    public MaxPQ() {

    }
    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN+1];
    }
    public MaxPQ(Key[] a) {

    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public Key max() {
        return pq[1];
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, N);
        pq[N] = null;
        N--;
        sink(1);
        return max;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j, j+1)) {
                j++;
            }
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public static void main(String[] args) {
        int size = 5;
        Integer[] test = new Integer[size];
        Random rand = new Random();
        for (int i = 0; i < test.length; i++) {
            test[i] = rand.nextInt(20);
        }
        MaxPQ<Integer> pq = new MaxPQ<>(size);
        for (Integer integer : test) {
            pq.insert(integer);
        }
        Integer firstMax = pq.delMax();
        Integer secondMax = pq.delMax();
        System.out.println("Done");
    }
}
