package c04;

import util.TestUtil;

/**
 * @author TK
 */
public class BiggerThanRightTwice {

    public static int  biggerThanRightTwice(int[] arr) {

        return mergeSort(arr, 0, arr.length - 1);
    }

    public static int mergeSort(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);


        return mergeSort(arr, L, mid) + mergeSort(arr, mid + 1, R) + merge(arr, L, mid, R);
    }

    // 1 4 9   2 3 4
    public static int merge(int[] arr, int L, int mid, int R) {
        int sum = 0;
        int winR = mid + 1;//不回退
        int p = L;

        while(p <= mid) {
            while(winR <= R && arr[p] > arr[winR] * 2) {
                winR++;
            }
            sum += winR - mid - 1;
            p++;
        }

        int[] temp = new int[R - L + 1];
        int left = L;
        int right = mid + 1;
        int i = 0;
        while (left <= mid && right <= R) {
            temp[i++] = arr[left] < arr[right] ? arr[left++] : arr[right++];
        }

        while (left <= mid) {
            temp[i++] = arr[left++];
        }

        while (right <= R) {
            temp[i++] = arr[right++];
        }

        int idx = L;
        for (i = 0; i < temp.length; i++, idx++) {
            arr[idx] = temp[i];
        }

        return sum;
    }

    public static int biggerThanRightTwiceViolence(int[] arr) {
        int count = 0;
        for(int i = 0; i< arr.length - 1; i++) {
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[i] >  arr[j] * 2) {
                    count++;
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {

/*        int[] arr = new int[]{4, 6, 15, 1, 5, 7};
        final int merge = merge(arr, 0, 2, 5);
        System.out.println(merge);*/

        int testTime = 1000;
        int maxValue = 20;
        int maxLen = 20;

        for (int i = 0; i < testTime; i++) {
            final int[] arr = TestUtil.generateRandomArray(maxLen, maxValue, false);
            final int[] copyArray = TestUtil.copyArray(arr);

            final int s1 = biggerThanRightTwice(arr);
            final int s2 = biggerThanRightTwiceViolence(copyArray);

            if (s1 != s2) {
                TestUtil.print(arr);
                System.out.println(s1);

                TestUtil.print(copyArray);
                System.out.println(s2);

                System.out.println("failed!");
                return;
            }
        }

        System.out.println("success!");

    }
}
