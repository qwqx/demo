package c18;

/**
 * @author TK
 */
public class RobotWalk {

    public static int ways1(int N, int start, int aim, int k) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || k < 1) {
            return -1;
        }

        return process(N, start, aim, k);

    }

    public static int process(int N, int curr, int aim, int rest) {
        if (rest == 0) {
            return aim == curr ? 1 : 0;
        }

        if (curr == 1) {
            return process(N, 2, aim,rest - 1);
        }

        if (curr == N) {
            return process(N, N - 1, aim, rest - 1);
        }

        return process(N, curr - 1, aim, rest - 1) + process(N, curr + 1, aim, rest - 1);
    }


    public static int ways2(int N, int start, int aim, int k) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || k < 1) {
            return -1;
        }

        int[][] dp = new int[N+1][k+1];
        dp[aim][0] = 1;

        for(int i = 1; i <=k ; i++) {

            dp[1][i] = dp[2][i-1];
            for(int j = 2; j < N; j++) {
                dp[j][i] = dp[j-1][i-1] + dp[j+1][i-1];
            }
            dp[N][i] = dp[N-1][i-1];

        }

        return dp[start][k];

    }

    public static int ways3(int[][] ways, int N, int k) {

        return process3(N, 1, N, k, ways);
    }

    public static int process3(int N, int curr, int aim, int rest, int[][] ways) {
        if(rest == 0) {
            return curr == aim ? 1 : 0;
        }

        int sum=0;
        for(int i = 0; i < ways.length; i++) {
            if(ways[i][0] == curr) {
                sum += process3(N, ways[i][1], aim, rest-1, ways);
            }
        }

        return sum;
    }

    //dp
    public static int ways4(int[][] ways, int N, int k) {

        int[][] dp = new int[N+1][k+1];
        dp[N][0] = 1;

        for(int i = 1; i<=k ; i++) {
            for(int j = 0; j<=N; j++) {
                for(int l = 0; l < ways.length; l++) {
                    if(ways[l][0] == j) {
                        dp[j][i] += dp[ways[l][1]][i-1];
                    }
                }

            }
        }

        return dp[0][k];
    }

    public static void main(String[] args) {
        // 1 2 3 4 5
        final int ways1 = ways1(5, 1, 5, 8);
        System.out.println(ways1);

        final int ways2 = ways2(5, 1, 5, 8);
        System.out.println(ways2);

        int[][] matrix= {
                {1,2},
                {2,3},{2,1},
                {3,4},{3,2},
                {4,5},{4,3},
                {5,4},

        };
        final int ways3 = ways3(matrix, 5, 8);
        System.out.println(ways3);

        final int ways4 = ways3(matrix, 5, 8);
        System.out.println(ways4);
    }
}
