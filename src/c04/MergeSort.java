package c04;

import util.TestUtil;

import java.util.Arrays;

/**
 * @author TK
 */
public class MergeSort {

    public static void sort(int[] arr) {

        mergeSort(arr, 0, arr.length - 1);
    }


    // 迭代实现
    public static void mergeSort2(int[] arr) {
        int len = arr.length;
        int maxStep = len * 2;
        int step = 2;
        while(step <= maxStep) {
            for(int i = 0; i< arr.length; i = i + step) {
                int L = i;
                int R = Math.min(arr.length - 1, i + step - 1);
                int mid = L + (step - 1) / 2;
                if(mid >= arr.length - 1 ) {
                    break;
                }
                merge(arr, L, mid, R);
            }

            step *= 2;
        }
    }

    // 迭代实现
    public static void mergeSort3(int[] arr) {
        int maxStep = arr.length;
        int step = 1;
        while(step <= maxStep) {
            for(int i = 0; i< arr.length; i = i + step * 2) {
                int L = i;
                int mid = L + step - 1;
                int R = Math.min(arr.length - 1, mid + step);
                if(mid >= arr.length - 1 ) {
                    break;
                }
                merge(arr, L, mid, R);
            }
            step *= 2;
        }
    }

    public static void mergeSort(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        mergeSort(arr, L, mid);
        mergeSort(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public static void merge(int[] arr, int L, int mid, int R) {
        int[] temp = new int[R - L + 1];
        int left = L;
        int right = mid + 1;
        int i = 0;
        while (left <= mid && right <= R) {
            /*if (arr[left] <= arr[right]) {
                temp[i] = arr[left];
                left++;
            } else {
                temp[i] = arr[right];
                right++;
            }
            i++;*/
            temp[i++] = arr[left] <= arr[right] ? arr[left++] : arr[right++];
        }

        while (left <= mid) {
            temp[i] = arr[left];
            left++;
            i++;
        }

        while (right <= R) {
            temp[i] = arr[right];
            right++;
            i++;
        }

        int idx = L;
        for (i = 0; i < temp.length; i++, idx++) {
            arr[idx] = temp[i];
        }
    }


    public static void main(String[] args) {

        /*int[] arr = new int[]{1,5, 9, 12, 15, 2, 4, 7, 8, 10 };
        merge(arr, 0, 4, 9);*/

        int testTime = 1000;
        int maxValue = 20;
        int maxLen = 10;
        for (int i = 0; i < testTime; i++) {
            final int[] arr = TestUtil.generateRandomArray(maxLen, maxValue, true);
            final int[] copyArray = TestUtil.copyArray(arr);

            //TestUtil.print(arr);
            sort(arr);
            //mergeSort2(arr);
            //mergeSort3(arr);
            //TestUtil.print(arr);
            Arrays.sort(copyArray);

            if (!TestUtil.equals(copyArray, arr)) {
                System.out.println("failed!");
                return;
            }
        }

        System.out.println("success!");

    }
}
