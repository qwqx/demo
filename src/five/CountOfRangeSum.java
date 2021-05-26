package five;

import util.TestUtil;

/**
 * @author TK
 *
 * @TAG difficult
 */
public class CountOfRangeSum {

    /**
     * 2 5 9   1   7   4
     * 2 7 16  17  24  28
     * <p>
     * S(i, j) = S(0, j) - S(0, i-1) -->（lower, upper）
     * ==> S(0, i-1)  -> S(0, j)-(lower, ipper)
     *
     * @param arr
     * @return
     */
    public static int countOfRangeSum(int[] arr, int lower, int upper) {
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }

        return mergeSort(sum, 0, sum.length - 1, lower, upper);
    }

    // 以R结尾的range在[lower, upper]个数
    public static int mergeSort(int[] sum, int L, int R, int lower, int upper) {
        if (L == R) {
            if (sum[L] >= lower && sum[L] <= upper) {
                System.out.println("count2" + "L:" + L + "r:" + R);
            }

            return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
        }

        int mid = L + ((R - L) >> 1);
        return mergeSort(sum, L, mid, lower, upper) + mergeSort(sum, mid + 1, R, lower, upper) + merge(sum, L, mid, R, lower, upper);
    }

    //以左组元素结尾的子序列 满足的数量
    public static int merge(int[] sum, int L, int mid, int R, int lower, int upper) {
        int count = 0;
        int winL = L;
        int winR = L;

        //[winL, winR)
        for (int i = mid + 1; i <= R; i++) {

            int min = sum[i] - upper;
            int max = sum[i] - lower;

            while (winL <= mid && sum[winL] < min) {
                winL++;
            }
            //sum[winL]>=min || winL > mid

            while (winR <= mid && sum[winR] <= max) {
                winR++;
            }
            //sum[winR]>max || winR > mid

            //[min, max] ?????????????????????????????????????????????????
            /*System.out.println("L:" + L + ", MID:" + mid + ", R:" + R + ", winL:" + winL + "winR:" + winR);
            //6, 7,   5,  9
            //6, 13, 18, 27 [5, 10]
            if(winL == winR) {
                if(sum[winL] >= min && sum[winL] <= max) {
                    count++;
                    System.out.println("1count +1, i=" + i);
                }
            }else {
                if(winR == mid + 1) {
                    count = winR - winL;
                    System.out.println("2count +" + (winR - winL) + " , i=" + i);
                }else {
                    if(sum[winR] == max) {
                        count += winR - winL + 1;
                        System.out.println("3count +" + (winR - winL + 1) + " , i=" + i);
                    }else {
                        count += winR - winL;
                        System.out.println("4count +" + (winR - winL)+ " , i=" + i);
                    }
                }
            }*/

            count += winR - winL;
        }

        return count;
    }

    public static int countOfRangeSumViolence(int[] arr, int lower, int upper) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum >= lower && sum <= upper) {
                    count++;
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {

        /*int[] arr1 = new int[]{6, 7, 5, 9};
        int[] sum = new int[]{6, 13, 18, 27};
        //final int merge = merge(sum, 0, 2, 4, 5, 10);
        final int c1 = countOfRangeSum(arr1, 5, 10);
        final int c2 = countOfRangeSumViolence(arr1, 5, 10);
        System.out.println(c1 + " " + c2);*/


        int testTime = 1000;
        int maxValue = 10;
        int maxLen = 5;
        for (int i = 0; i < testTime; i++) {
            final int[] arr = TestUtil.generateRandomArray(maxLen, maxValue, false);
            final int[] copyArray = TestUtil.copyArray(arr);

            final int s1 = countOfRangeSum(arr, 5, 10);
            final int s2 = countOfRangeSumViolence(copyArray, 5, 10);

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
