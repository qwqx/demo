package c23;

import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;

/**
 * @author TK
 */
public class SplitSumClosedSizeHalf {

    public static int right(int[] arr) {
        if (null == arr || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        int len = arr.length;

        if ((len & 1) == 0) {
            return process(arr, 0, len / 2, sum / 2);
        } else {
            return Math.max(process(arr, 0, len / 2, sum / 2), process(arr, 0, len / 2 + 1, sum / 2));
        }
    }

    public static int process(int[] arr, int index, int picks, int aim) {
        if (index == arr.length) {
            return picks == 0 ? 0 : -1;
        }

        int p1 = process(arr, index + 1, picks, aim);
        int p2 = -1;
        int next = -1;
        if (aim - arr[index] >= 0) {
            next = process(arr, index + 1, picks - 1, aim - arr[index]);
        }
        if (next != -1) {
            p2 = arr[index] + next;
        }

        return Math.max(p1, p2);
    }

    public static int dp(int[] arr) {
        if (null == arr || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        sum /= 2;

        int len = arr.length;

        int N = arr.length;
        int M = (N + 1) / 2;
        int[][][] dp = new int[N + 1][M + 1][sum + 1];

        for(int index = 0; index <= N; index++) {
            for (int picks = 0; picks <= M; picks++) {
                for (int rest = 0; rest <= sum; rest++) {
                    dp[index][picks][rest] = -1;
                }
            }
        }

        for (int rest = 0; rest <= sum; rest++) {
            dp[N][0][rest] = 0;
        }


        for(int index = N-1; index >= 0; index--) {
            for (int picks = 0; picks <= M; picks++) {
                for (int rest = 0; rest <= sum; rest++) {
                    int p1 = dp[index + 1][picks][rest];
                    int p2 = -1;
                    int next = -1;
                    if ( picks - 1 >=0 && rest - arr[index] >= 0) {
                        next = dp[index + 1][ picks - 1][rest - arr[index]];
                    }
                    if (next != -1) {
                        p2 = arr[index] + next;
                    }
                    dp[index][picks][rest] = Math.max(p1, p2);
                }
            }
        }

        if ((arr.length & 1) == 0) {
            return dp[0][arr.length / 2][sum];
        } else {
            return Math.max(dp[0][arr.length / 2][sum], dp[0][(arr.length / 2) + 1][sum]);
        }
    }

    // for test
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = right(arr);
            int ans2 = dp(arr);
            //int ans3 = dp2(arr);
            if (ans1 != ans2 /*|| ans1 != ans3*/) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                //System.out.println(ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
