package c08;

import util.TestUtil;

/**
 * @author TK
 */
public class CountSort {

    public static void sort(int[] arr) {
        int max = 0;
        for (Integer item : arr) {
            max = item > max ? item : max;
        }

        int [] bucket = new int[max + 1];

        for (Integer item : arr) {
            bucket[item]++;
        }


        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            if(bucket[i] == 0) {
                continue;
            }

            for (int j = 0; j < bucket[i]; j++) {
                arr[index++] = i;
            }
        }
    }

    public static void main(String[] args) {
        int testTimes = 10000;
        int maxLen = 20;
        int maxValue = 100;

        for (int i = 0; i < testTimes; i++) {
            final int[] arr = TestUtil.generateRandomArray(maxLen, maxValue, false);
            final int[] copyArray = TestUtil.copyArray(arr);
            sort(arr);
            TestUtil.sort(copyArray);

            if(!TestUtil.equals(arr, copyArray)) {
                System.out.println("failed");
                return;
            }
        }
        System.out.println("success");
    }
}
