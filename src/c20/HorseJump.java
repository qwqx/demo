package c20;

/**
 * @author TK
 */
public class HorseJump {

    // 当前来到的位置是（x,y）
    // 还剩下rest步需要跳
    // 跳完rest步，正好跳到a，b的方法数是多少？
    // 10 * 9
    public static int jump(int a, int b, int k) {
        return process(0, 0, k, a, b);
    }

    public static int process(int x, int y, int rest, int a, int b) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }

        if (rest == 0) {
            return (x == a && y == b) ? 1 : 0;
        }

        int ways = 0;
        ways += process(x + 2, y + 1, rest - 1, a, b);
        ways += process(x + 1, y + 2, rest - 1, a, b);
        ways += process(x - 1, y + 2, rest - 1, a, b);
        ways += process(x - 2, y + 1, rest - 1, a, b);

        ways += process(x - 2, y - 1, rest - 1, a, b);
        ways += process(x - 1, y - 2, rest - 1, a, b);
        ways += process(x + 1, y - 2, rest - 1, a, b);
        ways += process(x + 2, y - 1, rest - 1, a, b);

        return ways;
    }


    public static int jump2(int a, int b, int k) {
        int[][][] dp = new int[10][9][k + 1];
        dp[a][b][0] = 1;

        for (int rest = 1; rest <= k; rest++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    int ways = 0;
                    ways += pick(dp, i + 2, j + 1, rest - 1);
                    ways += pick(dp, i + 1, j + 2, rest - 1);
                    ways += pick(dp, i - 1, j + 2, rest - 1);
                    ways += pick(dp, i - 2, j + 1, rest - 1);

                    ways += pick(dp, i - 2, j - 1, rest - 1);
                    ways += pick(dp, i - 1, j - 2, rest - 1);
                    ways += pick(dp, i + 1, j - 2, rest - 1);
                    ways += pick(dp, i + 2, j - 1, rest - 1);
                    dp[i][j][rest] = ways;
                }
            }
        }
        return dp[0][0][k];
    }

    public static int pick(int[][][] dp, int x, int y, int rest) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        return dp[x][y][rest];
    }


    public static void main(String[] args) {
        System.out.println(jump(2, 1, 3));
        System.out.println(jump2(2, 1, 3));
    }
}
