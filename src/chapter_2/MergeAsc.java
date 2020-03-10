package chapter_2;

import utils.StdOut;

import java.util.Random;

public class MergeAsc {
    private static Comparable[] aux;

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a. length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int first_sub_array_index = lo;
        int second_sub_array_index = mid + 1;
        for (int k = lo; k <=hi ; k++) {
            aux[k] = a[k];
        }
        for (int i = lo; i <= hi; i++) {
            if (i > mid) {
                a[i] = aux[second_sub_array_index];
                second_sub_array_index ++;
            } else if (i > hi) {
                a[i] = aux[first_sub_array_index];
                first_sub_array_index ++;
            } else if (less(aux[second_sub_array_index], aux[first_sub_array_index])) {
                a[i] = aux[second_sub_array_index];
                second_sub_array_index ++;
            } else {
                a[i] = aux[first_sub_array_index];
                first_sub_array_index ++;
            }
        }
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz += sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
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
