package c19;

/**
 * @author TK
 */
public class LongestCommonSubsequence {

    public static int longestCommonSubsequence1(String s1, String s2) {
        if(null == s1 || null == s2 || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }

        final char[] str1 = s1.toCharArray();
        final char[] str2 = s2.toCharArray();

        return process(str1, str2, str1.length - 1, str2.length - 1);
    }

    public static int process(char[] str1, char[] str2, int l1, int l2) {
        if(l1 == l2 && l1 == 0) {
            return str1[l1] == str2[l2] ? 1 : 0;
        }

        if(l1 == 0) {
            return str1[l1] == str2[l2] ? 1 : process(str1, str2, l1, l2-1);
        }

        if(l2 == 0) {
            return str1[l1] == str2[l2] ? 1 : process(str1, str2, l1-1, l2);
        }

        if(str1[l1] == str2[l2]) {
            return 1 + process(str1, str2, l1-1, l2-1);
        }else {
            return Math.max(process(str1, str2, l1, l2-1), process(str1, str2, l1-1, l2));
        }
    }


    public static int longestCommonSubsequence2(String s1, String s2) {
        if(null == s1 || null == s2 || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }

        final char[] str1 = s1.toCharArray();
        final char[] str2 = s2.toCharArray();

        int[][] dp = new int[str1.length][str2.length];
        dp[0][0] = str1[0] == str2[0] ? 1 :0;
        for(int i = 0; i< str2.length; i++) {
            dp[0][i] = str1[0] == str2[i] ? 1 : dp[0][i-1];
        }

        for(int i = 0; i< str1.length; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i-1][0];
        }
        for(int i = 1; i< str1.length; i++) {
            for(int j = 1; j< str2.length; j++) {
                if(str1[i] == str2[j]) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        return dp[str1.length-1][str2.length - 1];
    }

    public static void main(String[] args) {

        String str1 = "abdcyyf";
        String str2 = "afdchhyf";

        final int count = longestCommonSubsequence1(str1, str2);
        System.out.println(count);

        final int count2 = longestCommonSubsequence2(str1, str2);
        System.out.println(count2);
    }
}
