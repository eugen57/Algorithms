package chapter_5_3;

public class KMP {
    private String pat;
    private int[][] dfa;
    public KMP(String pat)
    { // Построение ДКА для образца,
        this .pat = pat;
        int M = pat .length () ;
        int R = 256;
        dfa = new int[R] [M] ;
        dfa[pat.charAt(0)][0] = 1;
        for (int X = 0, j = 1; j < M; j++) { // Вычисление dfa[][j].
            for (int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][X]; // Копирование случаев несовпадения.
            dfa[pat.charAt(j)][j] = j+1; // Оформление случая совпадения.
            X = dfa[pat.charAt(j)][X]; // Обновления состояния перезапуска.
        }
    }
    public int search(String txt)
    { // Моделирование работы ДКА для txt.
        int i, j, N = txt .length () , M = pat. length () ;
        for (i = 0, j = 0; i < N && j < M; i++)
            j = dfa[txt.charAt(i)][j];
        if (j == M) return i - M; // образец найден (достигнут конец образца)
        else return N; // не найден (достигнут конец текста)
    }
}
