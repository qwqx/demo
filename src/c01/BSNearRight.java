package c01;

import java.util.Arrays;

public class BSNearRight {

    // <= value最左右置
    public static int nearestIndex(int[] arr, int num) {

        if (null == arr || arr.length < 1) {
            return -1;
        }

        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        int nearIndex = -1;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (num >= arr[mid]) {
                L = mid + 1;
                nearIndex = mid;
            } else if (num < arr[mid]) {
                R = mid - 1;

            }
        }
        return nearIndex;
    }

    public static int nearestIndex1(int[] arr, int num) {

        if (null == arr || arr.length < 1) {
            return -1;
        }

        int L = -1;
        int R = arr.length;
        while (L+1 != R) {
            int mid = L + ((R - L) >> 1);
            if(isBlue(arr[mid], num)) {
                L = mid;
            }else {
                R = mid;
            }
        }
        return L;
    }

    private static boolean isBlue(int mid, int num) {
        if(mid <= num) {
            return true;
        }
        return false;
    }

    // for test
    public static int test(int[] arr, int value) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= value) {
                return i;
            }
        }
        return -1;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (test(arr, value) != nearestIndex1(arr, value)) {
                printArray(arr);
                System.out.println(value);
                System.out.println(test(arr, value));
                System.out.println(nearestIndex(arr, value));
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
