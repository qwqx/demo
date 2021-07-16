package c21;

/**
 * @author TK
 */
public class BobDie {

    public static double livePosibility1(int row, int col, int k, int N, int M) {
        return process(row, col, k, N, M) / Math.pow(4, k);
    }

    public static int process(int row, int col, int k, int N, int M) {
        if (row < 0 || row >= N || col < 0 || col >= M) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }

        final int p1 = process(row + 1, col, k - 1, N, M);
        final int p2 = process(row - 1, col, k - 1, N, M);
        final int p3 = process(row, col + 1, k - 1, N, M);
        final int p4 = process(row, col - 1, k - 1, N, M);

        return p1 + p2 + p3 + p4;
    }


    public static double livePosibility2(int row, int col, int k, int N, int M) {

        int[][][] dp = new int[N][M][k + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = 1;
            }
        }

        for (int rest = 1; rest <= k; rest++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    dp[i][j][rest] = pick(dp, N, M, i + 1, j, rest-1);
                    dp[i][j][rest] += pick(dp, N, M, i - 1, j, rest-1);
                    dp[i][j][rest] += pick(dp, N, M, i, j + 1, rest-1);
                    dp[i][j][rest] += pick(dp, N, M, i, j - 1, rest-1);
                }
            }
        }
        return dp[row][col][k] / Math.pow(4, k);
    }

    public static int pick(int[][][] dp, int N, int M, int r, int c, int rest) {
        if (r < 0 || r == N || c < 0 || c == M) {
            return 0;
        }
        return dp[r][c][rest];
    }


    public static void main(String[] args) {
        System.out.println(livePosibility1(6, 6, 10, 50, 50));
        System.out.println(livePosibility2(6, 6, 10, 50, 50));
    }

}
