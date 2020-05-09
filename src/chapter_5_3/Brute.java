package chapter_5_3;

public class Brute {
    private String pat;
    private int M;

    public Brute(String pat) {
        this.pat = pat;
        M = pat.length();
    }

    public int search(String text) {
        int N = text.length();
        for (int i = 0; i <= N-M; i++) {
            int j;
            for (j = 0; j  < M; j++) {
                if(pat.charAt(j) != text.charAt(i+j)) break;
            }
            if (j == M) return i;
        }
        return N;
    }

    public int search2(String text) {
        int N = text.length();
        int i, j;
        for (i = 0, j = 0; i < N && j < M; i++) {
            if (pat.charAt(j) == text.charAt(i)) j++;
            else {
                i = i - j;
                j = 0;
            }
        }
        if (j == M) return i-M;
        else return N;
    }
}
