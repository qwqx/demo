package c21;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author TK
 */
public class CoinsWaySameValueSamePapper {

    public static class Info {
        int[] coins;
        int[] zhang;
    }

    public static Info getInfo(int[] arr) {
        final Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }


        final int size = map.size();
        int[] coins = new int[size];
        int[] zhang = new int[size];
        final Iterator<Integer> iterator = map.keySet().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            final Integer next = iterator.next();
            coins[i] = next;
            zhang[i] = map.get(next);
            i++;
        }

        final Info info = new Info();
        info.coins = coins;
        info.zhang = zhang;
        return info;
    }

    public static int ways(int[] arr, int aim) {
        if (null == arr || arr.length == 0) {
            return 0;
        }

        final Info info = getInfo(arr);
        return process(info.coins, info.zhang, 0, aim);
    }

    public static int process(int[] coins, int[] zhang, int index, int rest) {
        if (index == coins.length) {
            return rest == 0 ? 1 : 0;
        }

        if (rest == 0) {
            return 1;
        }

        int ways = 0;
        for (int i = 0; i <= zhang[index] && i * coins[index] <= rest; i++) {
            ways += process(coins, zhang, index + 1, rest - i * coins[index]);
        }

        return ways;
    }

    public static int waysDp(int[] arr, int aim) {
        if (null == arr || arr.length == 0) {
            return 0;
        }

        final Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhang = info.zhang;

        int N = coins.length;

        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int i = 0; i < N; i++) {
            dp[i][0] = 1;
        }

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int i = 0; i <= zhang[index] && i * coins[index] <= rest; i++) {
                    ways += dp[index + 1][rest - i * coins[index]];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    public static int waysDp1(int[] arr, int aim) {
        if (null == arr || arr.length == 0) {
            return 0;
        }

        final Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhang = info.zhang;

        int N = coins.length;

        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int i = 0; i < N; i++) {
            dp[i][0] = 1;
        }

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - coins[index] >= 0) {
                    dp[index][rest] += dp[index][rest - coins[index]];
                }
                if (rest - (zhang[index] + 1) * coins[index] >= 0) {
                    dp[index][rest] -= dp[index + 1][rest - (zhang[index] + 1) * coins[index]];
                }

            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
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
        int maxValue = 20;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
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
