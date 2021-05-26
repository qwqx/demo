package util;

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

    public static void print(int arr[]) {
        for(int i = 0; i< arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
