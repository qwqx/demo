package four;

import util.TestUtil;

import java.util.Arrays;

/**
 * @author TK
 */
public class ReversePair {


    // 1 4 7 9   2 3 5 8
    public static int reversePair(int[] arr) {

        return mergeSort(arr, 0, arr.length - 1);
    }

    public static int mergeSort(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);

        return mergeSort(arr, L, mid) + mergeSort(arr, mid + 1, R) + merge(arr, L, mid, R);
    }

    public static int merge(int[] arr, int L, int mid, int R) {
        int count = 0;

        int[] temp = new int[R - L + 1];
        int left = mid;
        int right = R;
        int i = temp.length - 1;
        while (left >= L && right > mid) {
            count += arr[left] > arr[right] ? (right - mid) : 0;
            temp[i--] = arr[left] > arr[right] ? arr[left--] : arr[right--];
        }

        while (left >= L) {
            temp[i--] = arr[left--];
        }

        while (right > mid) {
            temp[i--] = arr[right--];
        }

        int idx = L;
        for (i = 0; i < temp.length; i++, idx++) {
            arr[idx] = temp[i];
        }

        return count;
    }

    public static int reversePairViolence(int[] arr) {
        int count = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    count++;
                }

            }
        }
        return count;
    }


    public static void main(String[] args) {

        int testTime = 1000;
        int maxValue = 20;
        int maxLen = 10;
        for (int i = 0; i < testTime; i++) {
            final int[] arr = TestUtil.generateRandomArray(maxLen, maxValue, true);
            final int[] copyArray = TestUtil.copyArray(arr);

            TestUtil.print(arr);
            int c1 = reversePair(arr);
            System.out.println("COUNT: " + c1);
            int c2 = reversePairViolence(copyArray);

            if (c1 != c2) {
                System.out.println("failed! ");
                return;
            }
        }

        System.out.println("success!");
    }
}
