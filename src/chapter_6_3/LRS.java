package chapter_6_3;

import utils.StdIn;

// поиск максимальной повторяющейся подстроки
public class LRS {
    public static void main(String[] args) {
        String text = StdIn.readAll();
        int N = text.length();
        SuffixArray sa = new SuffixArray(text);
        String lrs = "";
        for (int i = 1; i < N; i++) {
            int length = sa.lcp(i);
            if (length > lrs.length()) {
                lrs = text.substring(sa.index(i), sa.index(i) + length);
            }
        }
        System.out.println("LRS: " + lrs);
    }
}
