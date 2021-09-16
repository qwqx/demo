package c24;

import util.TestUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * @author TK
 */
public class Code04_MinCoinsOnePaper {

    public static int minCoins(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return Integer.MAX_VALUE;
        }

        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }

        final int p1 = process(arr, index + 1, rest);
        int p2 = process(arr, index + 1, rest - arr[index]);
        if (Integer.MAX_VALUE != p2) {
            p2++;
        }

        /*if (index == arr.length) {
            return rest == 0 ? 1 : Integer.MAX_VALUE;
        }

        final int p1 = process(arr, index + 1, rest);
        int p2 = Integer.MAX_VALUE;
        int temp = process(arr, index + 1, rest - arr[index]);
        if (Integer.MAX_VALUE != temp) {
            p2 = temp;
        }*/

        return Math.min(p1, p2);
    }


    public static int dp1(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }

        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];

        dp[N][0] = 0;
        for (int i = 1; i <= aim; i++) {
            dp[N][i] = Integer.MAX_VALUE;
        }

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int p1 = dp[index + 1][rest];
                /*int p2 = Integer.MAX_VALUE;
                if (rest - arr[index] >= 0 && dp[index + 1][rest - arr[index]] != Integer.MAX_VALUE) {
                    p2 = dp[index + 1][rest - arr[index]] + 1;
                }*/
                int p2 = rest - arr[index] >= 0 ? dp[index + 1][rest - arr[index]] : Integer.MAX_VALUE;
                if (p2 != Integer.MAX_VALUE) {
                    p2++;
                }
                dp[index][rest] = Math.min(p1, p2);
            }
        }

        return dp[0][aim];
    }


    static class Info{
        int[] coins;
        int[] zhangs;
    }

    public static Info getInfo(int[] arr) {

        final HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++) {
            if(map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            }else {
                map.put(arr[i], 1);
            }
        }

        final Info info = new Info();
        info.coins = new int[map.size()];
        info.zhangs = new int[map.size()];

        int idx = 0;
        final Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for(Map.Entry<Integer, Integer> entry : entries) {
            info.coins[idx] = entry.getKey();
            info.zhangs[idx] = entry.getValue();
            idx++;
        }

        return info;
    }


    public static int dp2(int[] arr, int aim) {
        if(aim == 0) {
            return 0;
        }
        final Info info = getInfo(arr);

        final int N = info.coins.length;
        final int[][] dp = new int[N + 1][aim + 1];

        dp[N][0] = 0;
        for(int i = 1; i<= aim; i++) {
            dp[N][i] = Integer.MAX_VALUE;
        }

        int[] coins = info.coins;
        int[] zhangs = info.zhangs;


        for(int index = N-1; index >=0; index--) {
            for(int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index+1][rest];
                for(int zhang = 1; zhang <= zhangs[index] && rest - (zhang * coins[index]) >=0; zhang++) {
                    if(dp[index+1][rest - (zhang * coins[index])] != Integer.MAX_VALUE) {
                        dp[index][rest] =Math.min(dp[index][rest], dp[index+1][rest - (zhang * coins[index])] + zhang);
                    }
                }

            }
        }

        return dp[0][aim];
    }


    //undo: 窗口更新结构，未完成
    public static int dp3(int[] arr, int aim) {
        if(aim == 0) {
            return 0;
        }
        final Info info = getInfo(arr);

        final int N = info.coins.length;
        final int[][] dp = new int[N + 1][aim + 1];

        dp[N][0] = 0;
        for(int i = 1; i<= aim; i++) {
            dp[N][i] = Integer.MAX_VALUE;
        }

        int[] coins = info.coins;
        int[] zhangs = info.zhangs;


        for(int index = N-1; index >=0; index--) {
            final LinkedList<Integer> minHelp = new LinkedList<>();
            for(int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index+1][rest];


                for(int zhang = 1; zhang <= zhangs[index] && rest - (zhang * coins[index]) >=0; zhang++) {
                    if(dp[index+1][rest - (zhang * coins[index])] != Integer.MAX_VALUE) {
                        dp[index][rest] =Math.min(dp[index][rest], dp[index+1][rest - (zhang * coins[index])] + zhang);
                    }
                }

            }
        }

        return dp[0][aim];
    }




    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = TestUtil.generateRandomArray(N, maxValue, false);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = minCoins(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            //int ans4 = dp3(arr, aim);

            if (ans1 != ans2 || /*ans3 != ans4 ||*/ ans1 != ans3) {
                System.out.println("Oops!");
                TestUtil.print(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                //System.out.println(ans4);
                break;
            }
        }
    }
}
