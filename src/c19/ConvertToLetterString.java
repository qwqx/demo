package c19;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TK
 */
public class ConvertToLetterString {

    // str只含有数字字符0~9
    // 返回多少种转化方案
    public static int number(String str) {
        if(null == str || str.length() == 0) {
            return 0;
        }

        final char[] chars = str.toCharArray();

        return process(chars, 0);
    }

    public static int process(char[] chars, int index) {
        if(index == chars.length) {
            return 1;
        }

        if(chars[index] == '0') {
            return 0;
        }

        int p1 = process(chars, index + 1);
        int p2 = 0;
        if(index + 1 < chars.length && ((chars[index] - '0') * 10 + chars[index+1] -'0') < 27) {
            p2 = process(chars, index + 2);
        }

        return p1 + p2;
    }

    public static int number3(String str) {
        if(null == str || str.length() == 0) {
            return 0;
        }

        final char[] chars = str.toCharArray();
        final Map<Integer, Integer> cache = new HashMap<>();

        return process3(chars, 0, cache);
    }

    public static int process3(char[] chars, int index, Map<Integer, Integer> cache) {
        if(cache.containsKey(index)) {
            return cache.get(index);
        }
        if(index == chars.length) {
            return 1;
        }

        if(chars[index] == '0') {
            return 0;
        }

        int p1 = process3(chars, index + 1, cache);
        int p2 = 0;
        if(index + 1 < chars.length && ((chars[index] - '0') * 10 + chars[index+1] -'0') < 27) {
            p2 = process3(chars, index + 2, cache);
        }
        cache.put(index, p1 + p2);
        return p1 + p2;
    }

    public static int number2(String str) {
        final char[] chars = str.toCharArray();
        int N = chars.length;
        int[] dp = new int[N + 1];

        dp[N] = 1;
        for(int i = N - 1; i >= 0; i--) {
            if(chars[i] != '0') {
                int p1 = dp[i+1];
                int p2 = 0;
                if(i + 1 < N && ((chars[i] - '0') * 10 + chars[i+1] -'0') < 27) {
                    p2 = dp[i + 2];
                }
                dp[i] = p1 + p2;
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
        String str = "1212210356782222341122378945451";

        int testTimes = 100000;
        //1
        long start = System.currentTimeMillis();
        for(int i = 0; i< testTimes; i++) {
            number(str);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        //2
        start = System.currentTimeMillis();
        for(int i = 0; i< testTimes; i++) {
            number2(str);
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);

        //3
        start = System.currentTimeMillis();
        for(int i = 0; i< testTimes; i++) {
            number3(str);
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);

    }
}
