package chapter_1_5;

import utils.StdIn;
import utils.StdOut;

public class WeightedQuickUnionUF
{
    private int[] id; // родительская ссылка (индексация узлами)
    private int[] sz; // размер компонента для корней (индексация узлами)
    private int count; // количество компонентов

    public WeightedQuickUnionUF(int N) {
        count = N;

        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }

        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }
    }

    public int count () {
        return count;
    }

    public boolean connected (int p, int q) {
        return find(p) == find(q);
    }

    private int find (int p) {
        // Переходы по ссылкам до корня.
        while (p != id[p]) p = id[p] ;
        return p;
    }

    public void union (int p, int q) {
        int i = find(p) ;
        int j = find(q) ;
        if (i == j) return;

        // Меньший корень должен указывать на больший.
        if (sz[i] <sz[j]) {
            id[i] = j;
            sz[j] +=sz[i];
        }
        else { id[j] = i; sz[i] += sz[j]; }
        count--;
    }

    public static void main(String[] args) {
        // Решение задачи динамической связности с данными из Stdln.
        int N = StdIn.readInt(); // Ввод количества узлов.
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N) ; // Инициализация N компонентов
        while (!StdIn.isEmpty())
        {
            int p = StdIn.readInt();
            int q = StdIn.readInt() ; // Чтение пары связанных узлов.
            if (uf. connected (p, q) ) continue; //Игнорирование, если они уже связаны.
            uf.union(p, q) ; // Объединение компонентов
            StdOut.println (p + " " + q) ; // и вывод соединения.
        }
        StdOut.println(uf.count () + " компонентов");
    }
}
