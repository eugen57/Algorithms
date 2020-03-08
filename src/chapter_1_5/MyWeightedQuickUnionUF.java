package chapter_1_5;

import utils.StdIn;
import utils.StdOut;

public class MyWeightedQuickUnionUF {
    private int[] id; // родительская ссылка (индексация узлами)
    private int[] sz; // размер компонента для корней (индексация узлами)
    private int count; // количество компонентов

    public MyWeightedQuickUnionUF(int N) {
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

    public int count() {
        return count;
    }

    private int find(int el){
        while (el != id[el]) {
            el = id[el];
        }
        return el;
    }

    public boolean connected(int first, int second) {
        return find(first) == find(second);
    }

    public void union(int first, int second) {
        int root_of_first = find(first);
        int root_of_second = find(second);
        if (root_of_first == root_of_second) return;

        if (sz[root_of_first] < sz[root_of_second]) {
            id[root_of_first] = root_of_second;
            sz[root_of_second] += sz[root_of_first];
        } else {
            id[root_of_second] = root_of_first;
            sz[root_of_first] += sz[root_of_second];
        }
        count --;
    }


    public static void main(String[] args) {
        // Решение задачи динамической связности с данными из Stdln.
        int N = StdIn.readInt(); // Ввод количества узлов.
        MyWeightedQuickUnionUF uf = new MyWeightedQuickUnionUF(N) ; // Инициализация N компонентов
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
