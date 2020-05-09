package chapter_5_1;

public class MSD {
    private static int R = 256; // Основание
    private static final int M = 15; // Отсечка для небольших подмассивов
    private static String [] aux; // Вспомогательный массив для распределения
    private static int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d); else return -1;
    }
    public static void sort(String[] a) {
        int N = a.length;
        aux = new String [N] ;
        sort(a, 0, N-1, 0);
    }
    private static void sort (String [] a, int lo, int hi, int d) {
        // Сортировка от а[1о] до a[hi], начиная с d-го символа.
        if (hi <= lo + M) { Insertion.sort(a, lo, hi, d) ; return; }
        int[] count = new int[R+2]; // Вычисление счетчиков повторений,
        for (int i = lo; i <= hi; i++)
            count[charAt(a[i] , d) +2]++;
        for (int r = 0; r < R+1; r++) // Преобразование счетчиков в индексы,
            count[r+1] += count[r];
        for (int i = lo; i <= hi; i++) // Распределение,
            aux[count[charAt(a[i], d) +1]++] =a[i];
        for (int i = lo; i <= hi; i++) // Копирование назад,
            a [i] = aux[i - lo] ;
        // Рекурсивная сортировка для каждого значения символа,
        for (int r = 0; r < R; r++)
            sort (a, lo + count [r], lo + count [r+1] - 1, d+1) ;
    }
}
