package chapter_2;

import utils.StdOut;

import java.util.Random;

public class MergeDesc {
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

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length]; // Память выделяется один раз.
        sort (a, 0, a.length -1);
    }

    private static void sort (Comparable [] a, int lo, int hi) { // Сортировка а[1о..hi].
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid); // Сортировка левой половины.
        sort(a, mid + 1, hi); // Сортировка правой половины.
        merge(a, lo, mid, hi); // Слияние результатов (листинг 2.2.1).
    }

    public static void merge (Comparable [] a, int lo, int mid, int hi) { // Слияние a[lo..mid] с a[mid+1..hi].
        int i = lo;
        int j = mid + 1;
        for (int k =lo; k <= hi; k++) { // Копирование а[1о. .hi] в aux[lo. .hi] .
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {// Слияние назад в а[1о. .hi] .
            if (i > mid) {
                a[k] = aux[j++];
            }
            else if (j > hi) {
                a[k] = aux[i++];
            }
            else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            }
            else {
                a[k] = aux[i++];
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