package c23;

/**
 * @author TK
 */
public class SplitSumClosed {

    public static int right(int[] arr) {
        if (null == arr || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        return process(arr, 0, sum / 2);

    }

    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return 0;
        }

        //fast return
        if (rest == 0) {
            return 0;
        }

        int p1 = process(arr, index + 1, rest);
        int p2 = 0;
        if (rest - arr[index] >= 0) {
            p2 = arr[index] + process(arr, index + 1, rest - arr[index]);
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


        int N = arr.length;
        int[][] dp = new int[N + 1][sum + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 1; rest <= sum; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                if (rest - arr[index] >= 0) {
                    p2 = arr[index] + dp[index + 1][rest - arr[index]];
                }

                dp[index][rest] = Math.max(p1, p2);
            }

        }

        return dp[0][sum];
    }


    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("????????????");
        for (int i = 0; i < 1; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = right(arr);

            int ans2 = dp(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("????????????");
    }
}
