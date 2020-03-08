package chapter_1_1;

import java.util.Random;

public class BinarySearch {
    public static int find(int[] a, int value) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = (hi + lo) / 2;
            if (value < a[mid]) {
                hi = mid - 1;
            } else if (value > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] test = new int[10];
        Random rand = new Random();
        for (int i = 0; i < test.length; i++) {
            test[i] = rand.nextInt(100);
        }
//        int[] test = new int[]{0,1,2,3,4,5,6,7,8,9,10};
        int a = BinarySearch.find(test, 53);
        if (a != -1) {
            System.out.println("Value found. Index is: " + a);
        } else {
            System.out.println("Value not found found");
        }

    }
}
