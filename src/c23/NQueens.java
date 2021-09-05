package c23;

import c08.TrieTree;

/**
 * @author TK
 */
public class NQueens {

    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }

        final int[] record = new int[n];
        return process(0, record, n);
    }

    public static int process(int i, int[] record, int n) {
        if (i == n) {
            return 1;
        }

        int ways = 0;
        for (int k = 0; k < n; k++) {
            if (isValid(record, i, k)) {
                record[i] = k;
                ways += process(i + 1, record, n);
            }
        }

        return ways;
    }

    public static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (record[k] == j || Math.abs(record[k] - j) == Math.abs(k - i)) {
                return false;
            }
        }
        return true;
    }


    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }


        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    public static int process2(int limit, int colLim, int leftLim, int rightLim) {
        if (colLim == limit) {
            return 1;
        }

        //1：可选位置
        int pos = limit & ~(colLim | leftLim | rightLim);

        int ways = 0;
        while (pos != 0) {
            int mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            ways += process2(limit, colLim | mostRightOne, (leftLim | mostRightOne) << 1, (rightLim | mostRightOne) >>> 1);
        }

        return ways;
    }

    public static void main(String[] args) {

        int n = 4;

        long start = System.currentTimeMillis();
        System.out.println(num2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(num1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");
    }
}
