package c22;

/**
 * @author TK
 */
public class KillMonster {

    public static double right(int N, int M, int K) {
        if (N < 0 || M < 0 || K < 0) {
            return 0d;
        }

        double all = Math.pow(M + 1, K);
        double die = process(N, M, K);
        return die / all;
    }

    public static double process(int hp, int M, int rest) {
        if (rest == 0) {
            return hp == 0 ? 1 : 0;
        }


        int die = 0;
        for (int i = 0; i <= M; i++) {
            if (hp - i > 0) {
                die += process(hp - i, M, rest - 1);
            } else {
                die += Math.pow(M + 1, rest - 1);
            }

        }

        return die;
    }


    public static double dp1(int N, int M, int K) {
        if (N < 0 || M < 0 || K < 0) {
            return 0d;
        }

        double all = Math.pow(M + 1, K);

        int[][] dp = new int[K + 1][N + 1];
        dp[0][0] = 1;

        for (int rest = 1; rest <= K; rest++) {
            for (int hp = 0; hp <= N; hp++) {

                for (int t = 0; t <= M; t++) {
                    if (hp - t > 0) {
                        dp[rest][hp] += dp[rest - 1][hp - t];
                    } else {
                        dp[rest][hp] += Math.pow(M + 1, rest - 1);
                    }
                }

            }
        }

        return dp[K][N] / all;
    }


    /**
     * ☆☆☆☆
     * @param N
     * @param M
     * @param K
     * @return
     */
    public static double dp2(int N, int M, int K) {
        if (N < 0 || M < 0 || K < 0) {
            return 0d;
        }

        double all = Math.pow(M + 1, K);

        int[][] dp = new int[K + 1][N + 1];
        dp[0][0] = 1;

        for (int rest = 1; rest <= K; rest++) {
            dp[rest][0] += Math.pow(M + 1, rest);
            for (int hp = 1; hp <= N; hp++) {
                dp[rest][hp] = dp[rest][hp - 1] + dp[rest - 1][hp];

                if (hp - M - 1 >= 0) {
                    dp[rest][hp] -= dp[rest - 1][hp - M - 1];
                } else {
                    dp[rest][hp] -= Math.pow(M + 1, rest - 1);
                }

            }
        }

        return dp[K][N] / all;
    }


    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = right(N, M, K);
            double ans2 = dp1(N, M, K);
            double ans3 = dp2(N, M, K);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
