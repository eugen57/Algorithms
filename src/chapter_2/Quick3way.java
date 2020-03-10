package chapter_2;

import utils.StdOut;
import utils.StdRandom;

import java.util.Random;

public class Quick3way {
    private static void show(Comparable[] a) {
        for (int i = 0; i < a. length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void sort (Comparable [ ] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, i = lo+1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, lt++, i++) ;
            else if (cmp > 0) exch(a, i, gt--);
            else i++;
        }
        sort (a, lo, lt - 1);
        sort (a, gt + 1, hi);
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a); // Устранение зависимости от входных данных.
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
