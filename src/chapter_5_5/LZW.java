package chapter_5_5;

import chapter_5_2.TST;
import utils.BinaryStdIn;
import utils.BinaryStdOut;

public class LZW {
    private static final int R = 256; // Количество входных символов
    private static final int L = 4096; // Количество кодовых последовательностей = 2A12
    private static final int W = 12; // Ширина кодовой последовательности
    public static void compress() {
        String input = BinaryStdIn.readString();
        TST<Integer> st = new TST<Integer>();
        for (int i = 0; i < R; i++)
            st.put("" + (char) i, i) ;
        int code = R+1; // R — кодовая последовательность для EOF
        while (input.length () > 0) {
            String s = st.longestPrefixOf(input); // Поиск максимального соответствия префикса.
            BinaryStdOut.write(st.get(s), W); // Вывод кодировки для s.
            int t = s . length () ;
            if (t < input.length () && code < L) // Добавление s в таблицу имен
            st.put(input.substring(0, t + 1) , code++);
            input = input.substring(t); // Просмотр входных данных после s.
        }
        BinaryStdOut.write(R, W) ; // Запись EOF.
        BinaryStdOut.close() ;
    }
    public static void expand() {
        String[] st = new String[L];
        int i; // Следующее доступное значение кодовой последовательности
        for (i = 0; i < R; i++) // Инициализация таблицы для символов.
            st[i] = "" + (char) i;
        st[i++] = " "; // (не используется) Заглядывание вперед, EOF ли это
        int codeword = BinaryStdIn.readInt(W);
        String val = st[codeword];
        while (true) {
            BinaryStdOut.write(val); // Запись текущей подстроки.
            codeword = BinaryStdIn.readInt(W);
            if (codeword == R) break;
            String s = st[codeword]; // Получение следующей кодовой последовательности,
            if (i == codeword) // Если заглядывание выполняется неверно,
                s = val + val.charAt(0); // кодовая последовательность формируется из последней,
            if (i < L)
                st[i++] = val + s.charAt(0); // Добавление нового элемента в кодовую таблицу,
            val = s; // Изменение текущей кодовой последовательности.
        }
        BinaryStdOut.close();
    }
}
