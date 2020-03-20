package chapter_2;

import utils.StdOut;
import utils.StdRandom;

import java.util.Random;

public class MyQuick {
    private static void show(Comparable[] a) {
        for (int i = 0; i < a. length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi;
        Comparable pivot = a[(lo+hi)/2];
        while (i<=j) {
            while (less(a[i], pivot)) {
                i++;
            }
            while (less(pivot, a[j])) {
                j--;
            }
            if (i>=j) break;
            exch(a, i++, j--);
        }
        return j;
    }

    private static void sort (Comparable [] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j);
        sort(a, j + 1, hi);
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1) ;
    }

    public static void main(String[] args) {
        Integer[] test = new Integer[10];
        Random rand = new Random();
        for (int i = 0; i < test.length; i++) {
            test[i] = rand.nextInt(20);
        }
        System.out.println("Before sort:");
        show(test);
        sort(test);
        System.out.println("After sort:");
        show(test);
    }
}