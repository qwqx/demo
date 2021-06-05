package c01;

public class BSAwesome {


    // 找波谷
    public static int getLessIndex(int[] arr) {
        if (null == arr && arr.length < 2) {
            return -1;
        }

        if (arr[0] < arr[1]) {
            return 0;
        }

        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }

        int L = 0;
        int R = arr.length - 1;
        while (L < R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                L = mid -1;
            }else {
                return mid;
            }

        }
        return L;
    }
}
