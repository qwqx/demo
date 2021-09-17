package c25;

import util.TestUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 找出数组中每个元素左右最近比他小的元素
 * <p>
 * 单条栈：栈顶元素大于栈低元素，
 * <p>
 * 0.每个元素（E）入栈
 * 1.入栈时大于栈顶直接入
 * 2.小于等于栈顶元素，则栈顶元素T弹出，生成栈顶元素的记录（V,E），右边比他小的为T下面的元素V,左边比他小的为E，
 * 3.栈不为空，继续弹出。生成记录（V,-1）
 * <p>
 * <p>
 * 性质：
 * V---E----T
 * V-E间的元素都比E大
 * E-T间的元素都比E大
 *
 * @author TK
 */
public class Code01_MonotonousStack {

    // arr = [ 3, 1, 2, 3]
    //         0  1  2  3
    //  [
    //     0 : [-1,  1]
    //     1 : [-1, -1]
    //     2 : [ 1, -1]
    //     3 : [ 2, -1]
    //  ]
    public static int[][] getNearLessNoRepeat(int[] arr) {
        final Stack<Integer> maxStack = new Stack<>();

        int[][] ans = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            while (!maxStack.isEmpty() && arr[i] < arr[maxStack.peek()]) {
                final Integer index = maxStack.pop();

                ans[index][0] = maxStack.isEmpty() ? -1 : maxStack.peek();
                ans[index][1] = i;
            }

            maxStack.push(i);
        }

        while (!maxStack.isEmpty()) {
            final Integer index = maxStack.pop();
            ans[index][0] = maxStack.isEmpty() ? -1 : maxStack.peek();
            ans[index][1] = -1;
        }

        return ans;
    }


    public static int[][] getNearLess(int[] arr) {
        final Stack<List<Integer>> maxStack = new Stack<>();

        int[][] ans = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            while (!maxStack.isEmpty() && arr[i] < arr[maxStack.peek().get(0)]) {
                final List<Integer> popList = maxStack.pop();
                for (Integer index : popList) {
                    ans[index][0] = maxStack.isEmpty() ? -1 : maxStack.peek().get(maxStack.peek().size() - 1);
                    ans[index][1] = i;
                }
            }

            if (!maxStack.isEmpty() && arr[i] == arr[maxStack.peek().get(0)]) {
                maxStack.peek().add(i);
            } else {
                final ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                maxStack.push(list);
            }

        }

        while (!maxStack.isEmpty()) {
            final List<Integer> popList = maxStack.pop();
            for (Integer index : popList) {
                ans[index][0] = maxStack.isEmpty() ? -1 : maxStack.peek().get(maxStack.peek().size() - 1);
                ans[index][1] = -1;
            }
        }

        return ans;
    }

    public static int[][] rightWay(int[] arr) {
        int[][] ans = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int l;
            for (l = i - 1; l >= 0; l--) {
                if (arr[l] < arr[i]) {
                    break;
                }
            }
            ans[i][0] = l;

            int r;
            for (r = i + 1; r < arr.length; r++) {
                if (arr[r] < arr[i]) {
                    break;
                }
            }
            ans[i][1] = r >= arr.length ? -1 : r;

        }

        return ans;
    }

    public static void main(String[] args) {
        int size = 10;
        int max = 20;
        int testTimes = 2000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = TestUtil.getRandomArrayNoRepeat(size);
            int[] arr2 = TestUtil.generateRandomArray(size, max, false);

            final int[][] nearLessNoRepeat = getNearLessNoRepeat(arr1);
            final int[][] right = rightWay(arr1);
            if (!TestUtil.isEqual(nearLessNoRepeat, right)) {
                System.out.println("Oops!");
                TestUtil.print(arr1);
                break;
            }
            if (!TestUtil.isEqual(getNearLess(arr2), rightWay(arr2))) {
                System.out.println("Oops!");
                TestUtil.print(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
