package c22;

/**
 * @author TK
 */
public class SplitNumber {

    public static int ways(int n) {
        if (n <= 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        return process(1, n);
    }

    public static int process(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }

        if(pre == rest) {
            return 1;
        }


        int ways = 0;
        for (int k = pre; k <= rest; k++) {
            ways += process(k, rest - k);
        }

        return ways;
    }


    public static int dp1(int n) {
        if (n <= 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        final int[][] dp = new int[n + 1][n + 1];

        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
        }

        for (int pre = n; pre >= 1; pre--) {
            for (int rest = 0; rest <= n; rest++) {
                for (int k = pre; k <= rest; k++) {
                    dp[pre][rest] += dp[k][rest - k];
                }
            }
        }

        return dp[1][n];
    }


    public static int dp2(int n) {
        if (n <= 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        final int[][] dp = new int[n + 1][n + 1];

        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }

        for (int pre = n-1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] += dp[pre + 1][rest] + dp[pre][rest -pre];
            }
        }

        return dp[1][n];
    }

    public static void main(String[] args) {
        System.out.println(ways(20));

        System.out.println(dp1(20));
        System.out.println(dp2(20));
    }
}
