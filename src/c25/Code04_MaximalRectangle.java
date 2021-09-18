package c25;

import java.util.Stack;

/**
 * @author TK
 * https://leetcode.com/problems/maximal-rectangle/
 * <p>
 * 给定一个二维数组matrix，其中的值不是0就是1，返回全部由1组成的最大子矩形内部有多少个1（面积）
 *
 * 以每一行为底作为直方图，算出对应直方图的最大面积
 */
public class Code04_MaximalRectangle {

    public static int maximalRectangle(char[][] matrix) {
        if(null == matrix || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        final int m = matrix.length;
        final int n = matrix[0].length;
        final int[] buttom = new int[n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                buttom[j] = matrix[i][j] == '0' ? 0 : buttom[j] + 1;
            }

            final Stack<Integer> maxStack = new Stack<>();

            for (int j = 0; j < n; j++) {
                //相等的弹出，计算的不准（可达宽度可能更宽），没关系后面弹出的会计算正确
                while (!maxStack.isEmpty() && buttom[j] <= buttom[maxStack.peek()]) {
                    int curr = maxStack.pop();
                    int leftLess = maxStack.isEmpty() ? -1 : maxStack.peek();
                    int rightLess = j;
                    max = Math.max(max, (rightLess - leftLess - 1) * buttom[curr]);
                }

                maxStack.push(j);
            }

            while (!maxStack.isEmpty()) {
                int curr = maxStack.pop();
                int leftLess = maxStack.isEmpty() ? -1 : maxStack.peek();
                max = Math.max(max, (n - leftLess - 1) * buttom[curr]);
            }


        }

        return max;
    }

    public static void main(String[] args) {
        char[][] mat = {
                {'0', '1', '1', '1', '0'},
                {'1', '1', '1', '0', '0'},
                {'1', '0', '1', '1', '0'},
                {'0', '1', '0', '1', '0'},
                {'1', '0', '1', '1', '0'}
        };

        final int i = maximalRectangle(mat);
        System.out.println(i);
    }
}
