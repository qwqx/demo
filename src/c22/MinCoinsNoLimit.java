package c22;

import sun.security.acl.AclImpl;

/**
 * @author TK
 */
public class MinCoinsNoLimit {

    public static int minCoins(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int rest) {

        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }

        int min = Integer.MAX_VALUE;
        for (int zhang = 0; rest - zhang * arr[index] >= 0; zhang++) {
            final int next = process(arr, index + 1, rest - zhang * arr[index]);
            if (next != Integer.MAX_VALUE) {
                min = Math.min(min, zhang + next);
            }

        }

        return min;
    }


    public static int dp1(int[] arr, int aim) {
        int N = arr.length;
        final int[][] dp = new int[N + 1][aim + 1];

        for (int index = 0; index <= N; index++) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = Integer.MAX_VALUE;
            }
        }

        dp[N][0] = 0;

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int min = Integer.MAX_VALUE;
                for (int zhang = 0; rest - zhang * arr[index] >= 0; zhang++) {
                    int next = dp[index + 1][rest - zhang * arr[index]];

                    if (next != Integer.MAX_VALUE) {
                        min = Math.min(min, next + zhang);
                    }
                }
                dp[index][rest] = min;
            }

        }

        return dp[0][aim];
    }


    public static int dp2(int[] arr, int aim) {
        int N = arr.length;
        final int[][] dp = new int[N + 1][aim + 1];

        for (int index = 0; index <= N; index++) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = Integer.MAX_VALUE;
            }
        }

        dp[N][0] = 0;

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];

                if (rest - arr[index] >= 0 && Integer.MAX_VALUE != dp[index][rest - arr[index]]) {
                    dp[index][rest] = Math.min(dp[index][rest - arr[index]] + 1, dp[index][rest]);
                }
            }

        }

        return dp[0][aim];
    }


    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = minCoins(arr, aim);

            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");
    }
}
