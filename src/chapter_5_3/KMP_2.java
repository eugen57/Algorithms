package chapter_5_3;
/*
Based on prefix function
*/
public class KMP_2 {
    private String pattern;
    private int[] prefix;

    public KMP_2(String pattern) {
        this.pattern = pattern;
        int M = pattern.length();

        prefix  = new int[M];
        prefix[0] = 0;
        int i = 0;
        int j = 1;
        while (j < M) {
            if(pattern.charAt(i) != pattern.charAt(j)) {
                if(i==0) {
                    prefix[j] = 0;
                    j++;
                } else {
                    i = prefix[i-1];
                }
            } else {
                prefix[j] = i+1;
                i++;
                j++;
            }
        }
    }

    public int search(String text) {
        int N = text.length();
        int M = pattern.length();
        int i = 0, j = 0;
        while (i <= N - M) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == M) return i - M;
            } else {
                if (j != 0) {
                    j = prefix[j - 1];
                } else {
                    i++;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String pat = "test";
        String text = "ololotestololo";
        KMP_2 finder = new KMP_2(pat);
        int i = finder.search(text);
        System.out.println("Index find: " + i);
    }
}
