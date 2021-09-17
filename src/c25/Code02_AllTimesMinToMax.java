package c25;

import util.TestUtil;

import java.util.Stack;

/**
 * @author TK
 * <p>
 * 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
 * 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
 * 那么所有子数组中，这个值最大是多少？
 */
public class Code02_AllTimesMinToMax {

    public static int max1(int arr[]) {
        int len = arr.length;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int min = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    min = Math.min(arr[k], min);
                    sum += arr[k];
                }
                ans = Math.max(ans, sum * min);
            }
        }
        return ans;
    }


    /**
     * V---E---T
     * V-T的元素比E小，E为改子数组最小值，切该子数组是以E为最小值的的大范围数组，所以求出的值肯定是E为最小值的最大答案
     * 求出以所有元素为最小值答案，答案肯定在其中
     *
     * @param arr
     * @return
     */
    public static int max2(int[] arr) {

        final Stack<Integer> maxStack = new Stack<>();

        final int[][] ans = new int[arr.length][2];
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {

            while (!maxStack.isEmpty() && arr[i] < arr[maxStack.peek()]) {
                final Integer index = maxStack.pop();
                max = Math.max(max, (maxStack.isEmpty() ? sum[i - 1] : sum[i - 1] - sum[maxStack.peek()]) * arr[index]);
            }

            maxStack.push(i);
        }

        while (!maxStack.isEmpty()) {
            final Integer index = maxStack.pop();
            max = Math.max(max, (maxStack.isEmpty() ? sum[arr.length - 1] : sum[arr.length - 1] - sum[maxStack.peek()]) * arr[index]);
        }

        return max;
    }

    public static void main(String[] args) {
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = TestUtil.generateRandomArray(10, 20, false);
            if (max1(arr) != max2(arr)) {
                System.out.println("FUCK!");
                break;
            }
        }
        System.out.println("test finish");
    }


}
