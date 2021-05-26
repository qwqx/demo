package four;

import util.TestUtil;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.Arrays;

/**
 * @author TK
 */
public class SmallSum {


    /**
     * [2, 5, 6, 2, 4, 8]
     * 对于数组每个元素，左边比当前元素小的数之和 ==> 小和
     * ====》可转化为
     * 对于数组中每个元素，右边比当前元素大的数的（个数*当前元素）
     * ：
     * 可用归并排序实现：
     *  merge时，计算左组每一个元素x，在右组有多少元素（n）比x大, sum += nx
     *
     *  merge过程， i，j分别索引左组，右组访问位置。左组、有组有序，i,j不回退。所以O（N） = N*log(N);
     *  i = 0, j = 4,
     *  i = 1, j 必然从4往后索引。
     *
     * @param arr
     */

    public static int  smallSum(int[] arr) {

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
        int sum = 0;

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
            sum += arr[left] < arr[right] ? arr[left] * (R - right + 1) : 0;
            temp[i++] = arr[left] < arr[right] ? arr[left++] : arr[right++];
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

        return sum;
    }

    public static int smallSumViolence(int[] arr) {
        int sum = 0;
        for(int i = 1; i< arr.length; i++) {
            for(int j = 0; j < i; j++) {
                if(arr[j] < arr[i]) {
                    sum += arr[j];
                }
            }
        }
        return sum;
    }


    public static void main(String[] args) {


        int testTime = 1000;
        int maxValue = 20;
        int maxLen = 20;
        for (int i = 0; i < testTime; i++) {
            final int[] arr = TestUtil.generateRandomArray(maxLen, maxValue, false);
            final int[] copyArray = TestUtil.copyArray(arr);

            final int s1 = smallSum(arr);
            final int s2 = smallSumViolence(copyArray);

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
