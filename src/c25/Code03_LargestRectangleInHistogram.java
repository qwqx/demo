package c25;

import java.util.Stack;

/**
 * @author TK
 * https://leetcode.com/problems/largest-rectangle-in-histogram
 * <p>
 * 给定一个非负数组arr，代表直方图，返回直方图的最大长方形面积
 * 对于每一个位置j 求出 k,i
 * k---j----i
 * k-j之间的都比i大，所以可以算出以i为高的最大面积（i -k -1）* h[j]
 */
public class Code03_LargestRectangleInHistogram {


    public static int largestRectangleArea1(int[] height) {

        final Stack<Integer> maxStack = new Stack<>();
        int size = height.length;
        int max = 0;
        for (int i = 0; i < size; i++) {
            while (!maxStack.isEmpty() && height[i] <= height[maxStack.peek()]) {
                final Integer j = maxStack.pop();
                int k = maxStack.isEmpty() ? -1 : maxStack.peek();
                max = Math.max(max, (i - k - 1) * height[j]);
            }
            maxStack.push(i);
        }


        while (!maxStack.isEmpty()) {
            final Integer j = maxStack.pop();
            int k = maxStack.isEmpty() ? -1 : maxStack.peek();
            max = Math.max(max, (size - k - 1) * height[j]);
        }

        return max;
    }

    public static int largestRectangleArea2(int[] height) {

        int size = height.length;
        final int[] maxStack = new int[size];
        int idx = -1;
        int max = 0;
        for (int i = 0; i < size; i++) {
            while (idx != -1 && height[i] <= height[maxStack[idx]]) {
                final Integer j = maxStack[idx--];
                int k = idx == -1 ? -1 : maxStack[idx];
                max = Math.max(max, (i - k - 1) * height[j]);
            }
            maxStack[++idx] = i;
        }


        while (idx != -1) {
            final Integer j = maxStack[idx--];
            int k = idx == -1 ? -1 : maxStack[idx];
            max = Math.max(max, (size - k - 1) * height[j]);
        }

        return max;
    }


    public static void main(String[] args) {

        final int[] height = {2, 5, 3, 1, 7, 4, 2};
        final int area1 = largestRectangleArea1(height);
        final int area2 = largestRectangleArea2(height);

        System.out.println(area1 + "-------"+ area2);
    }
}
