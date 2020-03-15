package chapter_2;

import utils.StdOut;

import java.util.Random;

public class HeapSort {
    private static void show(Comparable[] a) {
        for (int i = 0; i < a. length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    private static boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void sink(Comparable[] a, int k, int N) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(a, j, j+1)) {
                j++;
            }
            if (!less(a, k, j)) break;
            exch(a, k, j);
            k = j;
        }
    }

    public static void sort(Comparable[] a) {
        int N = a.length - 1;
        for (int k = N/2; k >= 1; k--) {
            sink(a, k, N);
        }
        while (N > 1) {
            exch (a, 1, N--) ;
            sink (a, 1, N) ;
        }
    }

    public static void main(String[] args) {
        int size = 10;
        Integer[] test = new Integer[size+1];
        Random rand = new Random();
        for (int i = 1; i < size+1; i++) {
            test[i] = rand.nextInt(20);
        }
        System.out.println("Before sort:");
        show(test);
        sort(test);
        System.out.println("After sort:");
        show(test);
    }
}
