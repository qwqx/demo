package c24;

import java.util.LinkedList;

/**
 * @author TK
 */
public class Code03_GasStation {

    /**
     * [1  3  2  1  2]
     * [2  1  1  4  1]
     * <p>
     * [-1 2  1  -3 1]
     * <p>
     * 1.遍历每个节点，跑一圈相加看看有无负值 O(n2)
     * <p>
     * 2.求出前缀和数组（多求一个循环），然后求每个窗口内最小值(需要补偿)是否大于等于0，O(n)
     * [-1 1  2  -1 0  -1 1 2 -1 0]
     *
     * @param gas
     * @param cost
     * @return
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {

        final boolean[] ans = goodArray(gas, cost);
        for (int i = 0; i < ans.length; i++) {
            if (ans[i]) {
                return i;
            }
        }

        return -1;
    }

    public static boolean[] goodArray(int[] g, int[] cost) {
        int num = g.length;
        int M = num << 1;
        int[] prefixSum = new int[M];
        for (int i = 0; i < num; i++) {
            prefixSum[i] = g[i] - cost[i];
            prefixSum[i + num] = g[i] - cost[i];
        }

        for (int i = 1; i < M; i++) {
            prefixSum[i] += prefixSum[i - 1];
        }

        boolean[] ans = new boolean[num];

        final LinkedList<Integer> minQueue = new LinkedList<>();
        int l = 0;
        int r = 0;
        for (; r < num - 1; r++) {
            while (!minQueue.isEmpty() && prefixSum[minQueue.peekLast()] >= prefixSum[r]) {
                minQueue.pollLast();
            }
            minQueue.addLast(r);
            r++;
        }

        while (l < num) {
            while (!minQueue.isEmpty() && prefixSum[minQueue.peekLast()] >= prefixSum[r]) {
                minQueue.pollLast();
            }
            minQueue.addLast(r);

            final Integer winMinIndex = minQueue.peekFirst();
            ans[l] = prefixSum[winMinIndex] - help(l, prefixSum) >= 0;

            if (l == winMinIndex) {
                minQueue.pollFirst();
            }

            l++;
            r++;
        }

        return ans;

    }

    public static int help(int l, int[] prefixSum) {
        if (l == 0) {
            return 0;
        }
        return prefixSum[l - 1];
    }

    /**
     * [1  3  2  1  2]
     * [2  1  1  4  1]
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] gas = new int[]{1, 3, 2, 1, 2};
        int[] cost = new int[]{2, 1, 1, 4, 1};
        final int i = canCompleteCircuit(gas, cost);

        System.out.println(i);
    }
}
