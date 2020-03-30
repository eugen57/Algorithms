package utils;

public class BinaryConverter {
    private static String intToBinary(int n) {
        StringBuilder s = new StringBuilder();
        while (n > 0)
        {
            s.insert(0, ((n % 2) == 0 ? "0" : "1"));
            n = n / 2;
        }
        s.insert(0,0);
        return s.toString();
    }

    private static int binaryToInt(String str) {
        char[] chars = str.toCharArray();
        int result = 0;
        for (int i = 0; i < chars.length; i++) {
            int a = chars[i] - '0';
            int pow = chars.length - i - 1;
            result += a * Math.pow(2, pow);
        }
        return result;
    }

    public static String stringToBinary(String str) {
        int[] ints = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            ints[i] = str.charAt(i);
        }
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < ints.length; i++) {
            String binary = intToBinary(ints[i]);
            s.append(binary);
            s.append(" ");
        }
        s.deleteCharAt(s.length() - 1);
        return s.toString();
    }

    public static String binaryToString(String binaryStr) {
        String[] words = binaryStr.split(" ");
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            int code = binaryToInt(words[i]);
            text.append((char) code);
        }
        return text.toString();
    }
}
