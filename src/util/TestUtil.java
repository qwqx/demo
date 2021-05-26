package util;

import java.util.Arrays;

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
}
