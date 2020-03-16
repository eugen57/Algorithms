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

//    private static int partition(Comparable[] a, int lo, int hi) {
//        // Разбиение на a[lo..i-l], a[i], a[i+l..hi].
//        int i = lo, j = hi+1; // Левый и правый индексы просмотра
//        Comparable v = a[lo]; // Центральный элемент
//        while (true)
//        { // Просмотр справа, просмотр слева, проверка на завершение и обмен,
//            while (less (a [++i] , v) ) if (i == hi) break;
//            while (less(v, a[--j])) if (j == lo) break;
//            if (i >= j) break;
//            exch(a, i, j) ;
//        }
//        exch(a, lo, j);
//        return j;
//    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;

        int leftMarker = lo;
        int rightMarker = hi;
        Comparable pivot = a[(leftMarker + rightMarker) / 2];
        while (leftMarker <= rightMarker) {
            // Двигаем левый маркер слева направо пока элемент меньше, чем pivot
            while (less(a[leftMarker], pivot)) {
                leftMarker++;
            }
            // Двигаем правый маркер, пока элемент больше, чем pivot
            while (less(pivot, a[rightMarker])) {
                rightMarker--;
            }
            // Проверим, не нужно обменять местами элементы, на которые указывают маркеры
            if (leftMarker <= rightMarker) {
                // Левый маркер будет меньше правого только если мы должны выполнить swap
                if (leftMarker < rightMarker) {
                    exch(a, leftMarker, rightMarker);
                }
                // Сдвигаем маркеры, чтобы получить новые границы
                leftMarker++;
                rightMarker--;
            }
        }
        // Выполняем рекурсивно для частей
        if (leftMarker < hi) {
            sort(a, leftMarker, hi);
        }
        if (lo < rightMarker) {
            sort(a, lo, rightMarker);
        }
    }


    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a); // Устранение зависимости от входных данных.
        sort(a, 0, a.length-1);
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
