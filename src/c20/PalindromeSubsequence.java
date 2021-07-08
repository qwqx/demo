package c20;

/**
 * @author TK
 */
public class PalindromeSubsequence {

    public static int longestPalindromeSubseq(String s) {
        if (null == s || s.isEmpty()) {
            return 0;
        }
        final char[] str = s.toCharArray();
        return f(str, 0, str.length - 1);
    }

    public static int f(char[] str, int l, int r) {

        if (l == r) {
            return 1;
        }

        if (l == r - 1) {
            return str[l] == str[r] ? 2 : 1;
        }

        int p1 = f(str, l + 1, r - 1);
        int p2 = f(str, l, r - 1);
        int p3 = f(str, l + 1, r);
        int p4 = str[l] != str[r] ? 0 : 2 + f(str, l + 1, r - 1);

        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    public static int longestPalindromeSubseq2(String s) {
        if (null == s || s.isEmpty()) {
            return 0;
        }
        final char[] str = s.toCharArray();
        int N = str.length;

        int[][] dp = new int[N][N];

        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N-1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }

        for (int l = N - 3; l >= 0; l--) {
            for (int r = l + 2; r < N; r++) {
                //1
                /*int p1 = dp[l + 1][ r - 1];
                int p2 = dp[l][r - 1];
                int p3 = dp[l + 1][ r];
                int p4 = str[l] != str[r] ? 0 : 2 + dp[l + 1][r - 1];
                dp[l][r] =  Math.max(Math.max(p1, p2), Math.max(p3, p4));*/
                //2
                int p1 = dp[l + 1][ r];
                int p2 = dp[l][r - 1];
                int max = Math.max(p1, p2);
                if(str[l] == str[r]) {
                    int p4 = 2 + dp[l + 1][r - 1];
                    max = Math.max(p4, max);
                }

                dp[l][r] =  max;
            }
        }

        return dp[0][N-1];
    }


}
