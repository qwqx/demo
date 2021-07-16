package c21;

/**
 * @author TK
 */
public class CoinsWayNoLimit {

    public static int ways(int[] arr, int aim) {

        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }

        if (rest == 0) {
            return 1;
        }

        int ways = 0;
        for (int i = 0; arr[index] * i <= rest; i++) {
            ways += process(arr, index + 1, rest - arr[index] * i);
        }

        return ways;
    }

    public static int waysDp(int[] arr, int aim) {
        int N = arr.length;

        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int i = 0; i < N; i++) {
            dp[i][0] = 1;
        }

        for (int index = N-1; index >=0; index--) {
            for(int rest = 1; rest <= aim; rest++) {
                int ways = 0;
                for(int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    ways += dp[index+1][rest-zhang * arr[index]];
                }
                dp[index][rest] = ways;
            }
        }

        return dp[0][aim];
    }

    public static int waysDp1(int[] arr, int aim) {
        int N = arr.length;

        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int i = 0; i < N; i++) {
            dp[i][0] = 1;
        }

        for (int index = N-1; index >=0; index--) {
            for(int rest = 1; rest <= aim; rest++) {

                dp[index][rest] = dp[index+1][rest];
                if(rest - arr[index] >=0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
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
        int maxLen = 10;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < 1; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = ways(arr, aim);
            int ans2 = waysDp(arr, aim);
            int ans3 = waysDp1(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
