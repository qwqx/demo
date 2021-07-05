package c19;

import com.sun.xml.internal.bind.v2.model.core.ID;

/**
 * @author TK
 */
public class Knapsack {

    // 所有的货，重量和价值，都在w和v数组里
    // 为了方便，其中没有负数
    // bag背包容量，不能超过这个载重
    // 返回：不超重的情况下，能够得到的最大价值
    public static int maxValue(int[] w, int[] v, int bag) {
        if(null == w || null == v || w.length != w.length || w.length == 0 || bag < 0) {
            return 0;
        }

        return process(w, v, 0, bag);
    }

    public static int process(int[] w, int[] v, int index, int rest) {
        if(rest < 0) {
            return -1;
        }

        if(index == w.length) {
            return 0;
        }

        int p1 = process(w, v, index + 1, rest);
        int p2 = 0;
        int next = process(w, v, index + 1, rest - w[index]);
        if(-1 != next) {
            p2 = v[index] + next;
        }


        return Math.max(p1, p2);
    }


    public static int maxValue2(int[] w, int[] v, int bag) {
        if(null == w || null == v || w.length != w.length || w.length == 0 || bag < 0) {
            return 0;
        }

        int N = w.length;
        int[][] dp = new int[N+1][bag+1];

        for(int index = N-1; index>=0 ; index--) {
            for(int rest = 0; rest< bag +1; rest++) {
                final int p1 = dp[index + 1][rest];
                int p2 = 0;
                if(rest-w[index] >= 0) {
                    p2 = v[index] + dp[index+1][rest-w[index]];
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }

        return dp[0][bag];
    }


    public static void main(String[] args) {
        int[] w = {5, 10, 3, 6};
        int[] v = {5, 2, 5, 4};

        System.out.println(maxValue(w, v, 10));
        System.out.println(maxValue2(w, v, 10));
    }
}
