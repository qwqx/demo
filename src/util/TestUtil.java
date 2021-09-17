package util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

/**
 * @author TK
 */
public class TestUtil {

    public static int[] generateRandomArray(int maxLength, int maxValue, boolean negative) {
        int len = (int) (Math.random() * maxLength + 1);

        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            if(negative) {
                arr[i] = (int) (Math.random() * maxValue + 1) - (int) (Math.random() * maxValue + 1);
            }else {
                arr[i] = (int) (Math.random() * maxValue + 1);
            }

        }
        return arr;
    }

    public static int[] getRandomArrayNoRepeat(int size) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (Math.random() * arr.length);
            int tmp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }

    public static int[] generateRandomArrayInstanceLessK(int maxLength, int maxValue, int k) {
        final int[] arr = generateRandomArray(maxLength, maxValue, false);
        sort(arr);
        final boolean[] isSwap = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if(!isSwap[i]) {
                continue;
            }
            int swapIndex = (int) (Math.random() * k + 1);

            while(!isSwap[i + swapIndex] && swapIndex <= k) {
                swapIndex++;
            }

            if(swapIndex <= k) {
                swap(arr, i, i+ swapIndex);
                isSwap[i] = true;
                isSwap[i+ swapIndex] = true;
            }
        }

        return arr;
    }

    public static int[] copyArray(int[] arr) {
        int len = arr.length;
        int[] copy = new int[len];
        for (int i = 0; i < len; i++) {
            copy[i] = arr[i];
        }
        return copy;
    }

    public static boolean equals(int[] arr1, int[] arr2) {
        if(null == arr1 && null == arr2) {
            return true;
        }
        if(arr1.length == 0 && arr2.length == 0) {
            return true;
        }
        if(arr1.length != arr2.length) {
            return false;
        }

        int len = arr1.length;
        for(int i = 0; i < len; i++) {
            if(arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void sort(int[] arr) {
        Arrays.sort(arr, 0, arr.length);
    }

    public static void print(int arr[]) {
        for(int i = 0; i< arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void swap(int[] arr, int L, int R) {
        int temp = arr[L];
        arr[L] = arr[R];
        arr[R] = temp;
    }

    // must L!=R
    // X xor 0 = x
    // X xor X = 0
    //不进位相加
    public static void swap2(int[] arr, int L, int R) {
        arr[L] = arr[L] ^ arr[R];
        arr[R] = arr[L] ^ arr[R];//arr[L] ^ arr[R] ^ arr[R]
        arr[L] = arr[L] ^ arr[R];
    }


    //generate Random lower String
    public static String generateRandomString(int maxLen) {
        int len = (int) ((Math.random() * maxLen) + 1);
        char[] chars = new char[len];
        for(int i = 0; i < len; i++) {
            int randomChar = (int) ((Math.random() * 26));
            chars[i] = (char) ('a' + randomChar);
        }

        return String.valueOf(chars);
    }


    public static boolean isEqual(int[][] res1, int[][] res2) {
        if (res1.length != res2.length) {
            return false;
        }
        for (int i = 0; i < res1.length; i++) {
            if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
                return false;
            }
        }

        return true;
    }
}
