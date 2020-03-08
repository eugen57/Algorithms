package chapter_1_2;

public class IsPalindrome {
    public static boolean check(String test) {
        char[] stringChars = test.toCharArray();
        int stringLength = stringChars.length;
        for (int i = 0; i < stringLength/2; i++) {
            if (stringChars[i] != stringChars[stringLength - 1 - i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String test = "ololo";
        boolean testResult = IsPalindrome.check(test);
        System.out.println(String.format("String %s is palindrome: %s", test, testResult));
    }
}
